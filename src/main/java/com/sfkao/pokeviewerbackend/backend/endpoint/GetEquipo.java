package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.EquipoDao;
import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.Equipo;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import com.sfkao.pokeviewerbackend.backend.modelo.UsuarioSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class GetEquipo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EquipoDao equipoDao;

    @Autowired
    UsuarioDao usuarioDao;

    @GetMapping("/equipo")
    @ResponseBody
    public Equipo saveEquipo(
            @RequestParam(name = "nombre", required = true) String nombre,
            @RequestParam(name = "apikey", required = true) String apikey,
            @RequestParam(name = "pk1",required = false) int pk1,
            @RequestParam(name = "pk2",required = false) int pk2,
            @RequestParam(name = "pk3",required = false) int pk3,
            @RequestParam(name = "pk4",required = false) int pk4,
            @RequestParam(name = "pk5",required = false) int pk5,
            @RequestParam(name = "pk6",required = false) int pk6
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        Equipo equipo = new Equipo(equipoDao.getNewID(),nombre,usuario,new Date(),pk1,pk2,pk3,pk4,pk5,pk6);

        jdbcTemplate.update("INSERT INTO Equipo(id,nombre,usernameAutor,fecha,pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6) VALUES(?,?,?,?,?,?,?,?,?,?)",
                equipo.getId(),
                equipo.getNombre(),
                equipo.getUsuario().getUsername(),
                equipo.getFecha(),
                equipo.getPokemon1(),
                equipo.getPokemon2(),
                equipo.getPokemon3(),
                equipo.getPokemon4(),
                equipo.getPokemon5(),
                equipo.getPokemon6());
        return equipo;

    }

}
