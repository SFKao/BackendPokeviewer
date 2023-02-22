package com.sfkao.pokeviewerbackend.backend.dao;

import com.sfkao.pokeviewerbackend.backend.modelo.EquipoCargado;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EquipoDao {

    public static final int KEYSIZE = 8;
    public static int MINCHAR = (int) 'A';
    public static int MAXCHAR = (int) 'Z';

    public static String KEYPARAM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public synchronized String getNewID(){
        try {
            String lastID = jdbcTemplate.queryForObject("SELECT id FROM Equipo ORDER BY id DESC  LIMIT 1", String.class);
            return getNextID(lastID);
        }catch (EmptyResultDataAccessException e) {
            return "00000000";
        }

    }


    public synchronized static String getNextID(String id){
        if(id.length()!= KEYSIZE)
            return null;
        int[] idArray = idAArray(id);
        boolean fin = false;
        int pos = idArray.length-1;
        while(!fin){
            if(pos==-1)
                return null;
            idArray[pos] = (idArray[pos]+1)%KEYPARAM.length();
            if(idArray[pos] == 0)
                pos--;
            else
                fin = true;
        }
        char[] returned = new char[idArray.length];
        for (int i = 0; i < returned.length; i++) {
            returned[i] = KEYPARAM.charAt(idArray[i]);
        }
        return new String(returned);

    }

    private static int[] idAArray(String id){
        int[] array = new int[id.length()];
        for(int i = 0; i < array.length;i++){
            int ascii = id.charAt(i);
            if(ascii>=48 && ascii<=57){
                array[i] = ascii-48;
            }else if(ascii>=65 && ascii<=90){
                array[i] = ascii-55;
            }else
                return null;
        }
        return array;
    }

    public Usuario cargarEquipos(Usuario u, Usuario requester){

        final String finalUsername = requester != null ? requester.getUsername() : null;
        AtomicInteger likes = new AtomicInteger(0);
        AtomicInteger favs = new AtomicInteger(0);
        u.setEquipos(jdbcTemplate.query("SELECT * FROM Equipo WHERE usernameAutor = ? ORDER BY fecha",
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
                ,u.getUsername()));

        u.getEquipos().forEach(equipoCargado -> {likes.addAndGet(equipoCargado.likes); favs.addAndGet(equipoCargado.favoritos);});
        u.setFavoritos(favs.get());
        u.setLikes(likes.get());
        return u;
    }

    public EquipoCargado cargarLikesYFavs(EquipoCargado e, String username){
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
