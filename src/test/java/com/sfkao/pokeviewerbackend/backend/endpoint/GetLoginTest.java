package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.modelo.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetLoginTest {

    @Autowired
    GetLogin login;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void register() throws Exception {
        jdbcTemplate.update("DELETE FROM Usuario WHERE username = 'test'");
        login.register("test",encode("pass").getBytes(StandardCharsets.UTF_8),"test@test.test");
        LoginResponse r = login.login("test",encode("pass").getBytes(StandardCharsets.UTF_8));
        System.out.println(r.getCode());
        System.out.println(r.getResponse());
        assertNotNull(r.getUsuario());
    }

    @Test
    void login() throws Exception {
        LoginResponse r = login.login("test",encode("pass").getBytes(StandardCharsets.UTF_8));

        System.out.println(r.getCode());
        System.out.println(r.getResponse());
        assertNotNull(r.getUsuario());
    }

    @Test
    void sal() throws Exception{
        List<String> sal = jdbcTemplate.query("SELECT salt FROM Usuario WHERE username='test'",(s, i) -> {return s.getString(1);});
        System.out.println(sal.get(0));
        List<String> sal2 = jdbcTemplate.query("SELECT salt FROM Usuario WHERE salt=?",new Object[]{sal.get(0)},(s, i) -> {return s.getString("salt");});
        System.out.println(sal2.get(0));
        assertEquals(sal.get(0), sal2.get(0));
    }

    @Test
    void decrypt() throws Exception {
        //lwNCiqdtgtGvCsjPzb2Bc3lD8M4eI/OtKnc6SxAZqwUgrw1qyZtHSowuc4G12FWa72dJTxqYBHCowoHor5+B8rdtMn7FAbMB+TOYe859w1s3aa2AOWwIAod3/9Mo4n7btCx30DJSDRe+eK/noobWJhUrgX10j3skzOAxq6JUMFirGrCVHbtwSNt8+UpRg6HKA9YYtcb7iZyVm1UrUOJEVGDPkjiLIAWmgKjAzPl/ZexSd8hCFUBszaedckRdcVDAnMpu+SqViTeuJEE0g5MNC0PPiYfC4sCNB45/LLgBsZLrVvNYOAnc4jJZYbOlWLhBJ7ZrGr18qbAtejNJ+TIaCQ==
        String pass = "lwNCiqdtgtGvCsjPzb2Bc3lD8M4eI/OtKnc6SxAZqwUgrw1qyZtHSowuc4G12FWa72dJTxqYBHCowoHor5+B8rdtMn7FAbMB+TOYe859w1s3aa2AOWwIAod3/9Mo4n7btCx30DJSDRe+eK/noobWJhUrgX10j3skzOAxq6JUMFirGrCVHbtwSNt8+UpRg6HKA9YYtcb7iZyVm1UrUOJEVGDPkjiLIAWmgKjAzPl/ZexSd8hCFUBszaedckRdcVDAnMpu+SqViTeuJEE0g5MNC0PPiYfC4sCNB45/LLgBsZLrVvNYOAnc4jJZYbOlWLhBJ7ZrGr18qbAtejNJ+TIaCQ==";
        System.out.println(login.decode(encode("pass").getBytes(StandardCharsets.UTF_8)));

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