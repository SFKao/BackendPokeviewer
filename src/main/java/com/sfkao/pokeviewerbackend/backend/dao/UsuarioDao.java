package com.sfkao.pokeviewerbackend.backend.dao;

import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import com.sfkao.pokeviewerbackend.backend.modelo.UsuarioSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Usuario getUsuarioByApikey(String apikey){
        if(apikey.length()!=35)
            return null;
        List<UsuarioSQL> list = jdbcTemplate.query("SELECT * FROM Usuario WHERE apikey = ?", (rs, rw) -> {
            return new UsuarioSQL(rs.getString("username"),rs.getString("email"),
                    rs.getString("saltedHash"),rs.getString("salt"),rs.getString("apikey"));
        },apikey);
        UsuarioSQL usuarioSQL = list.get(0);
        if(usuarioSQL==null)
            return null;
        return usuarioSQL.toUser();
    }

}
