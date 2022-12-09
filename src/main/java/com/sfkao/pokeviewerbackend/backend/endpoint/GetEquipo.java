package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.EquipoDao;
import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.Equipo;
import com.sfkao.pokeviewerbackend.backend.modelo.EquipoCargado;
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
    public EquipoCargado saveEquipo(
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

        return getEquipoById(equipo.getId(),usuario.getUsername());
    }

    @GetMapping("/get_equipo")
    @ResponseBody
    public EquipoCargado getEquipoById(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "apikey",required = false) String apikey
    ){
        String username = null;
        if(apikey != null)
            username = usuarioDao.getUsuarioByApikey(apikey).getUsername();

        try {
            String finalUsername = username;
            return jdbcTemplate.query("SELECT * FROM Equipo WHERE id = ?", (rs, rowNum) ->
                            cargarLikesYFavs(new EquipoCargado
                                    (id,
                                            rs.getString("nombre")
                                            , rs.getString("usernameAutor")), finalUsername)
                                    .cargarPokemon(rs.getInt("pokemon1"), 0)
                                    .cargarPokemon(rs.getInt("pokemon2"), 1)
                                    .cargarPokemon(rs.getInt("pokemon3"), 2)
                                    .cargarPokemon(rs.getInt("pokemon4"), 3)
                                    .cargarPokemon(rs.getInt("pokemon5"), 4)
                                    .cargarPokemon(rs.getInt("pokemon6"), 5)
                    , id).get(0);

        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }


    @GetMapping("/get_equipos")
    @ResponseBody
    public List<EquipoCargado> getEquipos(
            @RequestParam(name = "cantidad", defaultValue = "20",required = false) int cantidad,
            @RequestParam(name = "posInicial", defaultValue = "0",required = false) int posInicial,
            @RequestParam(name = "apikey",required = false) String apikey
    ){
        String username = null;
        if(apikey != null)
             username = usuarioDao.getUsuarioByApikey(apikey).getUsername();

        final String finalUsername = username;
        return jdbcTemplate.query("SELECT * FROM Equipo ORDER BY fecha DESC LIMIT ?,?",
                (rs,rowNum) ->
                        cargarLikesYFavs(new EquipoCargado
                                (rs.getString("id")
                                        ,rs.getString("nombre")
                                        ,rs.getString("usernameAutor")), finalUsername)
                                .cargarPokemon(rs.getInt("pokemon1"),0)
                                .cargarPokemon(rs.getInt("pokemon2"),1)
                                .cargarPokemon(rs.getInt("pokemon3"),2)
                                .cargarPokemon(rs.getInt("pokemon4"),3)
                                .cargarPokemon(rs.getInt("pokemon5"),4)
                                .cargarPokemon(rs.getInt("pokemon6"),5)
                ,posInicial,cantidad);
    }

    @GetMapping("/borrar_equipo")
    @ResponseBody
    public boolean borrarEquipo(
            @RequestParam(name = "id") String id
    ){
        return jdbcTemplate.update("DELETE FROM Equipo WHERE id = ?",id) == 1;
    }

    @GetMapping("dar_like")
    @ResponseBody
    public boolean darLike(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "apikey") String apikey
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        if(usuario==null){
            return false;
        }
        return jdbcTemplate.update("INSERT INTO Likes(username, id) VALUES (?,?)",usuario.getUsername(),id) != 0;
    }

    @GetMapping("quitar_like")
    @ResponseBody
    public boolean quitarLike(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "apikey") String apikey
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        if(usuario==null){
            return false;
        }
        return jdbcTemplate.update("DELETE FROM Likes WHERE username = ? AND id = ?",usuario.getUsername(),id) != 0;
    }

    @GetMapping("dar_favorito")
    @ResponseBody
    public boolean darFavorito(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "apikey") String apikey
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        if(usuario==null){
            return false;
        }
        return jdbcTemplate.update("INSERT INTO Favoritos(username, id) VALUES (?,?)",usuario.getUsername(),id) != 0;
    }

    @GetMapping("quitar_favorito")
    @ResponseBody
    public boolean quitarFavorito(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "apikey") String apikey
    ){
        Usuario usuario = usuarioDao.getUsuarioByApikey(apikey);
        if(usuario==null){
            return false;
        }
        return jdbcTemplate.update("DELETE FROM Favoritos WHERE username = ? AND id = ?",usuario.getUsername(),id) != 0;
    }


    private EquipoCargado cargarLikesYFavs(EquipoCargado e,String username){
        int countLikes = 0;
        try{
            countLikes = jdbcTemplate.queryForObject("SELECT COUNT(Likes.username) FROM Likes WHERE id = ?",Integer.class,e.id);
        }catch (NullPointerException ignored){}
        int countFavs = 0;
        try{
            countFavs = jdbcTemplate.queryForObject("SELECT COUNT(Favoritos.username) FROM Favoritos WHERE id = ?",Integer.class,e.id);
        }catch (NullPointerException ignored){}
        e.likes = countLikes;
        e.favoritos = countFavs;
        if(username == null)
            return e;
        boolean fav = false, like = false;
        try{
            like = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Likes WHERE id = ? AND username = ?",Integer.class,e.id,username) != 0;
        }catch (NullPointerException ignored){}
        try{
            fav = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Favoritos WHERE id = ? AND username = ?",Integer.class,e.id,username) != 0;
        }catch (NullPointerException ignored){}
        e.dadoLike = like;
        e.dadoFavoritos = fav;
        return e;
    }
}
