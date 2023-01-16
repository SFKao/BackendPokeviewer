package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.modelo.LoginResponse;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import com.sfkao.pokeviewerbackend.backend.modelo.UsuarioSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.SQLIntegrityConstraintViolationException;
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
                        rs.getString("saltedHash"),rs.getString("salt"),rs.getString("apikey"),rs.getInt("pk1"),rs.getInt("pk2"),rs.getInt("pk3")));
        if(salida.size()==0)
            return new LoginResponse(null, 503,"El usuario o la contraseña no son validas");

        SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        if(!encoder.matches(decodedPass+salida.get(0).salt,salida.get(0).saltedHash))
            return new LoginResponse(null, 504,"El usuario o la contraseña no son validas");
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
            e.printStackTrace();
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        } catch (IOException e) {
            System.out.println("ERROR LEYENDO EL ARCHIVO key.priv");
            e.printStackTrace();
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        }
        byte[] saltB = new byte[16];
        new SecureRandom().nextBytes(saltB);
        String salt = new String(saltB);
        SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        String passCodedForDDBB = encoder.encode(decodedPass+salt);
        String apikey = null;
        try {
            apikey = generateApiKey(256);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new LoginResponse(null, 501,"Hubo un error en el endpoint. Pruebe despues");
        }

        Usuario u = new Usuario(name,email,apikey);
        try {
            jdbcTemplate.update("INSERT INTO Usuario(username, email, salt, saltedHash,apikey) VALUES(?,?,?,?,?)", name, email, salt, passCodedForDDBB, apikey);
        }catch (Exception e){
            e.printStackTrace();
            return new LoginResponse(null, 506,"El nombre de usuario ya esta escogido");
        }
        return new LoginResponse(u,400,"Registro valido");
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

    public static String generateApiKey(final int keyLen) throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return bytesToHex(encoded);
    }


    public static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }



}
