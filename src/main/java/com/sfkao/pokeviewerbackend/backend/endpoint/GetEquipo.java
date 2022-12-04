package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.EquipoDao;
import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.Equipo;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class GetEquipo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EquipoDao equipoDao;

    @Autowired
    UsuarioDao usuarioDao;

    @GetMapping("/save_equipo")
    @ResponseBody
    public Equipo saveEquipo(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "apikey") String apikey,
            @RequestParam(name = "pk1") int pk1,
            @RequestParam(name = "pk2",required = false) int pk2,
            @RequestParam(name = "pk3",required = false) int pk3,
            @RequestParam(name = "pk4",required = false) int pk4,
            @RequestParam(name = "pk5",required = false) int pk5,
            @RequestParam(name = "pk6",required = false) int pk6
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        if(usuario==null){
            System.out.println("API KEY NO VALIDA");
            return null;
        }
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

    @GetMapping("/get_equipo")
    @ResponseBody
    public Equipo getEquipoById(
            @RequestParam(name = "id") String id
    ){
        return jdbcTemplate.query("SELECT * FROM Equipo WHERE id = ?",(rs, rowNum) ->
                new Equipo(id, rs.getString("nombre")
                        ,new Usuario(rs.getString("usernameAutor"),null,null)
                        ,rs.getDate("fecha")
                        ,rs.getInt("pokemon1")
                        ,rs.getInt("pokemon2")
                        ,rs.getInt("pokemon3")
                        ,rs.getInt("pokemon4")
                        ,rs.getInt("pokemon5")
                        ,rs.getInt("pokemon6")),id).get(0);
    }


    @GetMapping("/get_equipos")
    @ResponseBody
    public List<Equipo> getEquipos(
            @RequestParam(name = "cantidad", defaultValue = "20") int cantidad,
            @RequestParam(name = "posInicial", defaultValue = "0") int posInicial
    ){
        return jdbcTemplate.query("SELECT * FROM Equipo ORDER BY fecha DESC LIMIT ?,?",
                (rs,rowNum) ->
                        new Equipo(rs.getString("id")
                                , rs.getString("nombre")
                                ,new Usuario(rs.getString("usernameAutor"),null,null)
                                ,rs.getDate("fecha")
                                ,rs.getInt("pokemon1")
                                ,rs.getInt("pokemon2")
                                ,rs.getInt("pokemon3")
                                ,rs.getInt("pokemon4")
                                ,rs.getInt("pokemon5")
                                ,rs.getInt("pokemon6")
                        ),posInicial,cantidad);
    }
}
