package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.LoginResponse;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class GetAmigos {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioDao usuarioDao;

    @GetMapping("/get_amigos")
    @ResponseBody
    public List<Usuario> getAmigosYPeticiones(
            @RequestParam(name = "apikey",required = true) String apikey
    ){
        List<Usuario> amigos = new ArrayList<>();
        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);

        jdbcTemplate.query("SELECT  IF(SEARCHER.username = `ListaAmigos`.`username1`,true,false) AS enviada ,ListaAmigos.estado, FRIENDS.* FROM `Usuario` AS SEARCHER JOIN `ListaAmigos` ON SEARCHER.`username` = `ListaAmigos`.`username1` OR SEARCHER.`username` = `ListaAmigos`.`username2` JOIN `Usuario` AS FRIENDS ON FRIENDS.`username` = `ListaAmigos`.`username2` OR FRIENDS.`username` = `ListaAmigos`.`username1` WHERE SEARCHER.username = ? AND FRIENDS.username != ?", (rs, rowNum) -> {
            Usuario e = new Usuario(rs.getString("username"), null, rs.getString("estado"), rs.getInt("pk1"), rs.getInt("pk2"), rs.getInt("pk3"));
            if(e.getEstadoAmistad().equals("pendiente") &&!rs.getBoolean("enviada"))
                e.setEstadoAmistad("recibida");
            amigos.add(e);
            return null;
        },usuarioByApikey.getUsername(),usuarioByApikey.getUsername());

        return amigos;
    }

    @GetMapping("/enviar_solicitud_de_amistad")
    @ResponseBody
    public boolean solicitarAmistad(
            @RequestParam(name = "apikey",required = true) String apikey,
            @RequestParam(name = "username",required = true) String username
    ){

        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);

        AtomicBoolean yaExsiste = new AtomicBoolean(false);
        jdbcTemplate.query("SELECT * FROM `ListaAmigos` WHERE (ListaAmigos.username1 = ? AND ListaAmigos.username2 = ?) OR (ListaAmigos.username2 = ? AND ListaAmigos.username1 = ?)", (rs, rowNum) -> {
            yaExsiste.set(true);
            return null;
        },usuarioByApikey.getUsername(),username,usuarioByApikey.getUsername(),username );

        if(yaExsiste.get()){
            return false;
        }

        try {
            jdbcTemplate.update("INSERT INTO `ListaAmigos`(`username1`, `username2`) VALUES (?,?)",usuarioByApikey.getUsername(), username);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/borrar_amigo")
    @ResponseBody
    public boolean borrarAmigo(
            @RequestParam(name = "apikey",required = true) String apikey,
            @RequestParam(name = "username",required = true) String username
    ){

        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);
        try {
            jdbcTemplate.update("DELETE FROM `ListaAmigos` WHERE (`username1` = ? AND `username2` = ?) OR (`username2` = ? AND `username1` = ?)",usuarioByApikey.getUsername(), username,usuarioByApikey.getUsername(), username);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
