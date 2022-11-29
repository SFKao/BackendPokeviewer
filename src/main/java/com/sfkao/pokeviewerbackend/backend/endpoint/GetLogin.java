package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.modelo.LoginResponse;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

@RestController
public class GetLogin {

    //https://gustavopeiretti.com/rsa-encrypt-decrypt-java/
    //https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-scrypt
    //https://spring.io/guides/gs/accessing-data-mysql/

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    @ResponseBody
    public LoginResponse login(@RequestParam(name="usuario",required = true) String name, @RequestParam(name = "pass",required = true)byte[] pass){
        String decodedPass = null;
        try {
            decodedPass = decode(pass);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                InvalidKeySpecException | BadPaddingException | InvalidKeyException
                | NoSuchProviderException e) {
            e.printStackTrace();
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO key.priv");
            e.printStackTrace();
            return new LoginResponse(null, 502, "Hubo un error en el endpoint. Pruebe despues");
        }

        List<UsuarioSQL> salida = jdbcTemplate.query("SELECT * FROM Usuario WHERE username = ?",new Object[] {name},
                (rs, rowNum) -> new UsuarioSQL(rs.getString("username"),rs.getString("email"),
                        rs.getString("saltedHash"),rs.getString("salt"),rs.getString("apikey")));
        if(salida.size()==0)
            return new LoginResponse(null, 503,"El usuario o la contraseña no son validas");

        SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        if(!encoder.matches(decodedPass+salida.get(0).salt,salida.get(0).saltedHash))
            return new LoginResponse(null, 504,"El usuario o la contraseña no son validas");
        //TODO: que genere una api_key
        return new LoginResponse(salida.get(0).toUser(),400,"Login valido");
    }


    @GetMapping("/register")
    @ResponseBody
    public LoginResponse register(@RequestParam(name="usuario",required = true) String name, @RequestParam(name = "pass",required = true)byte[] pass, @RequestParam(name = "email",required = true)String email){
        String decodedPass = null;
        try {
            decodedPass = decode(pass);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException | NoSuchProviderException e) {
            System.out.println(e);
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO key.priv");
            e.printStackTrace();
            System.out.println(e);
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        }
        byte[] saltB = new byte[16];
        new SecureRandom().nextBytes(saltB);
        String salt = new String(saltB);
        SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        String passCodedForDDBB = encoder.encode(decodedPass+salt);
        String apikey = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        jdbcTemplate.update("INSERT INTO Usuario(username, email, salt, saltedHash,apikey) VALUES(?,?,?,?,?)", name,email,salt,passCodedForDDBB,apikey);
        return new LoginResponse(null,400,"Registro valido");
    }

    private PrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // reading from resource folder
        byte[] keyBytes = getClass().getResourceAsStream("/private_key.der").readAllBytes();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public String decode(byte[] toDecode) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
        PrivateKey privateKey = loadPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(toDecode));
        return new String(bytes,"ISO-8859-1");
    }


    class UsuarioSQL{
        String username;
        String email;
        String saltedHash;
        String salt;
        String apikey;

        public UsuarioSQL(String username, String email, String saltedHash, String salt, String apikey) {
            this.username = username;
            this.email = email;
            this.saltedHash = saltedHash;
            this.salt = salt;
            this.apikey = apikey;
        }

        public Usuario toUser(){
            return new Usuario(username,email,apikey);
        }
    }
}
