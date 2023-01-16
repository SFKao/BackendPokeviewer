package com.sfkao.pokeviewerbackend.backend.modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {

    private String username;
    private String email;
    private String apikey;

    private ArrayList<Equipo> equipos;

    private String estadoAmistad;

    private int pk1 = 0,pk2 = 0,pk3 = 0;

    public Usuario(String username, String email, String apikey) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
    }

    public Usuario(String username, String email, String apikey, int pk1, int pk2, int pk3) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
    }

    public Usuario(String username, String email, String apikey, ArrayList<Equipo> equipos, String estadoAmistad, int pk1, int pk2, int pk3) {
        this.username = username;
        this.email = email;
        this.apikey = apikey;
        this.equipos = equipos;
        this.estadoAmistad = estadoAmistad;
        this.pk1 = pk1;
        this.pk2 = pk2;
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

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public int getPk1() {
        return pk1;
    }

    public void setPk1(int pk1) {
        this.pk1 = pk1;
    }

    public int getPk2() {
        return pk2;
    }

    public void setPk2(int pk2) {
        this.pk2 = pk2;
    }

    public int getPk3() {
        return pk3;
    }

    public void setPk3(int pk3) {
        this.pk3 = pk3;
    }

    public String getEstadoAmistad() {
        return estadoAmistad;
    }

    public void setEstadoAmistad(String estadoAmistad) {
        this.estadoAmistad = estadoAmistad;
    }
}
