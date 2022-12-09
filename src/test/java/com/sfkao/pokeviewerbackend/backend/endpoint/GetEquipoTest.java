package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.modelo.Equipo;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetEquipoTest {

    @Autowired
    GetEquipo equipo;

    @Autowired
    GetLogin login;

    @Test
    public void registrarEquipo() throws Exception {
        Usuario u = login.login("test",encode("pass").getBytes()).getUsuario();
        Random r = new Random();
        if(u == null) {
            System.out.println("FUCK");
            return;
        }
        System.out.println(u.getApikey());
        for(int i = 0; i < 100; i++){
            Thread.sleep(5000);
            System.out.println(i+" "+equipo.saveEquipo("Prueba",u.getApikey(),r.nextInt(1,905),r.nextInt(1,905),r.nextInt(1,905),r.nextInt(1,905),r.nextInt(1,905),r.nextInt(1,905)));
        }
    }

    private PublicKey loadPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //
        byte[] keyBytes = getClass().getResourceAsStream("/public_key.der").readAllBytes();
        StringBuilder b = new StringBuilder();
        for(byte bit : keyBytes){
            b.append(bit);
        }
        System.out.println(b);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public String encode(String toEncode) throws Exception {
        PublicKey publicKey = loadPublicKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        String s = new String(Base64.getEncoder().encode(bytes));
        System.out.println("ASDASD "+s);
        return s;
    }

}