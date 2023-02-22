package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.dao.EquipoDao;
import com.sfkao.pokeviewerbackend.backend.dao.UsuarioDao;
import com.sfkao.pokeviewerbackend.backend.modelo.EquipoCargado;
import com.sfkao.pokeviewerbackend.backend.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class GetAmigos {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    EquipoDao equipoDao;

    @GetMapping("/get_amigos")
    @ResponseBody
    public List<Usuario> getAmigos(
            @RequestParam(name = "apikey",required = true) String apikey
    ){
        List<Usuario> amigos = new ArrayList<>();
        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);


        jdbcTemplate.query("SELECT `ListaAmigos`.`estado` ,FRIENDS.* FROM `Usuario` AS SEARCHER JOIN `ListaAmigos` ON SEARCHER.`username` = `ListaAmigos`.`username1` OR SEARCHER.`username` = `ListaAmigos`.`username2` JOIN `Usuario` AS FRIENDS ON FRIENDS.`username` = `ListaAmigos`.`username2` OR FRIENDS.`username` = `ListaAmigos`.`username1` WHERE SEARCHER.username = ? AND FRIENDS.username != ? AND (`ListaAmigos`.`estado` = 'aceptada' OR (`ListaAmigos`.`estado`= 'pendiente' AND SEARCHER.username = `ListaAmigos`.`username1`))", (rs, rowNum) -> {
            Usuario e = new Usuario(rs.getString("username"), (ArrayList<EquipoCargado>) null, rs.getString("estado"), rs.getInt("pk1"), rs.getInt("pk2"), rs.getInt("pk3"));
            amigos.add(e);
            return null;
        },usuarioByApikey.getUsername(),usuarioByApikey.getUsername());
        amigos.forEach(am -> {
            equipoDao.cargarEquipos(am, usuarioByApikey);
        });
        return amigos;
    }

    @GetMapping("/enviar_solicitud_de_amistad")
    @ResponseBody
    public String solicitarAmistad(
            @RequestParam(name = "apikey",required = true) String apikey,
            @RequestParam(name = "username",required = true) String username
    ){

        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);

        AtomicBoolean yaExsiste = new AtomicBoolean(false);
        AtomicReference<String> estado = new AtomicReference<>();
        AtomicReference<String> reciever = new AtomicReference<>();
        jdbcTemplate.query("SELECT * FROM `ListaAmigos` WHERE (ListaAmigos.username1 = ? AND ListaAmigos.username2 = ?) OR (ListaAmigos.username2 = ? AND ListaAmigos.username1 = ?)", (rs, rowNum) -> {
            yaExsiste.set(true);
            estado.set(rs.getString("estado"));
            reciever.set(rs.getString("username2"));
            return null;
        },usuarioByApikey.getUsername(),username,usuarioByApikey.getUsername(),username );

        if(yaExsiste.get()){
            if(estado.get().equals("pendiente") && reciever.get().equals(usuarioByApikey.getUsername())){
                jdbcTemplate.update("UPDATE ListaAmigos SET estado = 'aceptada' WHERE (ListaAmigos.username1 = ? AND ListaAmigos.username2 = ?) OR (ListaAmigos.username2 = ? AND ListaAmigos.username1 = ?)",usuarioByApikey.getUsername(),username,usuarioByApikey.getUsername(),username);
                return "aceptada";
            }
            return estado.get();
        }

        try {
            jdbcTemplate.update("INSERT INTO `ListaAmigos`(`username1`, `username2`) VALUES (?,?)",usuarioByApikey.getUsername(), username);
        }catch (Exception e){
            e.printStackTrace();
            return estado.get();
        }
        return "pendiente";
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

    @GetMapping("/get_solicitudes_de_amistad")
    @ResponseBody
    public List<Usuario> getSolicitudes(
            @RequestParam(name = "apikey",required = true) String apikey
    ){
        List<Usuario> amigos = new ArrayList<>();
        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);

        jdbcTemplate.query("SELECT SEARCHER.* FROM `Usuario` AS SEARCHER JOIN `ListaAmigos` ON SEARCHER.`username` = `ListaAmigos`.`username1` JOIN `Usuario` AS FRIENDS ON FRIENDS.`username` = `ListaAmigos`.`username2` WHERE SEARCHER.username != ? AND FRIENDS.username = ? AND `ListaAmigos`.`estado` = 'pendiente'", (rs, rowNum) -> {
            Usuario e = new Usuario(rs.getString("username"), (ArrayList<EquipoCargado>)  null, "pendiente", rs.getInt("pk1"), rs.getInt("pk2"), rs.getInt("pk3"));
            amigos.add(e);
            return null;
        },usuarioByApikey.getUsername(),usuarioByApikey.getUsername());
        amigos.forEach(am -> {
            equipoDao.cargarEquipos(am, usuarioByApikey);
        });
        return amigos;
    }


    @GetMapping("/buscar_usuario")
    @ResponseBody
    public Usuario buscarUsuario(
            @RequestParam(name = "apikey",required = false) String apikey,
            @RequestParam(name = "username",required = true) String username
    ){
        Usuario usuarioByApikey = usuarioDao.getUsuarioByApikey(apikey);
        AtomicReference<Usuario> e = new AtomicReference<>();
        jdbcTemplate.query("SELECT username, pk1, pk2,pk3 FROM Usuario WHERE username = ?", (rs, rowNum) -> {
            e.set(new Usuario(rs.getString("username"), (ArrayList<EquipoCargado>) null, null, rs.getInt("pk1"), rs.getInt("pk2"), rs.getInt("pk3")));
            if(usuarioByApikey!=null) {
                try {
                    String estado = jdbcTemplate.queryForObject("SELECT ListaAmigos.estado FROM ListaAmigos WHERE ListaAmigos.username1 = ? AND ListaAmigos.username2 = ? OR ListaAmigos.username2 = ? AND ListaAmigos.username1 = ?", String.class, usuarioByApikey.getUsername(), username, usuarioByApikey.getUsername(), username);
                    if (estado != null) {
                        e.get().setEstadoAmistad(estado);
                        if (e.get().getEstadoAmistad().equals("pendiente") && !rs.getBoolean("enviada"))
                            e.get().setEstadoAmistad("recibida");
                    }
                }catch (EmptyResultDataAccessException ignored){
                }
            }
            return null;
        },username);

        return equipoDao.cargarEquipos(e.get(),usuarioByApikey);
    }

}
