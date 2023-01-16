package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetPokemonFavorito {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioDao usuarioDao;


    @GetMapping("/set_favorito")
    @ResponseBody
    public boolean setFavorito(
            @RequestParam(name = "apikey",required = true) String apikey,
            @RequestParam(name = "pos", required = true) int pos,
            @RequestParam(name = "id", required = true) int id
    ){
        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);

        try {
            switch (pos){
                case 1 -> jdbcTemplate.update("UPDATE `Usuario` SET `pk1`= ? WHERE `Usuario`.`username` = ?",id,usuarioByApikey.getUsername());
                case 2 -> jdbcTemplate.update("UPDATE `Usuario` SET `pk2`= ? WHERE `Usuario`.`username` = ?",id,usuarioByApikey.getUsername());
                case 3 -> jdbcTemplate.update("UPDATE `Usuario` SET `pk3`= ? WHERE `Usuario`.`username` = ?",id,usuarioByApikey.getUsername());
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

}
