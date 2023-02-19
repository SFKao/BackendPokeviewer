package com.sfkao.pokeviewerbackend.backend.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    private String username;
    private String email;
    private String apikey;

    private List<EquipoCargado> equipos;

    private String estadoAmistad;
    private PokemonMinimo pk1,pk2,pk3;

    private int likes, favoritos;



    public Usuario(String username, String email, String apikey) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
    }


    public Usuario(String username, ArrayList<EquipoCargado> equipos, String estadoAmistad) {
        this.username = username;
        this.equipos = equipos;
        this.estadoAmistad = estadoAmistad;
    }

    public Usuario(String username, ArrayList<EquipoCargado> equipos, String estadoAmistad, int pk1id, int pk2id, int pk3id) {
        this.username = username;
        this.equipos = equipos;
        this.estadoAmistad = estadoAmistad;
        pk1 = PokemonMinimo.load(pk1id);
        pk2 = PokemonMinimo.load(pk2id);
        pk3 = PokemonMinimo.load(pk3id);
    }

    public Usuario(String username, String apikey, ArrayList<EquipoCargado> equipos, String estadoAmistad, int pk1id, int pk2id, int pk3id) {
        this.username = username;
        this.equipos = equipos;
        this.estadoAmistad = estadoAmistad;
        this.apikey = apikey;
        pk1 = PokemonMinimo.load(pk1id);
        pk2 = PokemonMinimo.load(pk2id);
        pk3 = PokemonMinimo.load(pk3id);
    }

    public Usuario(String username, String apikey, String email, int pk1id, int pk2id, int pk3id) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
        pk1 = PokemonMinimo.load(pk1id);
        pk2 = PokemonMinimo.load(pk2id);
        pk3 = PokemonMinimo.load(pk3id);
    }

    public Usuario(String username, ArrayList<EquipoCargado> equipos, String estadoAmistad, int pk1id, int pk2id, int pk3id, int likes, int favs) {
        this.username = username;
        this.equipos = equipos;
        this.estadoAmistad = estadoAmistad;
        pk1 = PokemonMinimo.load(pk1id);
        pk2 = PokemonMinimo.load(pk2id);
        pk3 = PokemonMinimo.load(pk3id);
        this.likes = likes;
        this.favoritos = favs;
    }

    public PokemonMinimo getPk1() {
        return pk1;
    }

    public void setPk1(PokemonMinimo pk1) {
        this.pk1 = pk1;
    }

    public PokemonMinimo getPk2() {
        return pk2;
    }

    public void setPk2(PokemonMinimo pk2) {
        this.pk2 = pk2;
    }

    public PokemonMinimo getPk3() {
        return pk3;
    }

    public void setPk3(PokemonMinimo pk3) {
        this.pk3 = pk3;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", apikey='" + apikey + '\'' +
                '}';
    }

    public List<EquipoCargado> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoCargado> equipos) {
        this.equipos = equipos;
    }

    public String getEstadoAmistad() {
        return estadoAmistad;
    }

    public void setEstadoAmistad(String estadoAmistad) {
        this.estadoAmistad = estadoAmistad;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }
}
