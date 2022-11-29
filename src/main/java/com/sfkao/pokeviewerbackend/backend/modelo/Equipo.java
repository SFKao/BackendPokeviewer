package com.sfkao.pokeviewerbackend.backend.modelo;

import java.util.Date;
import java.util.Objects;

public class Equipo {

    private String id;
    private String nombre;
    private Usuario usuario;
    private Date fecha;
    private int pokemon1 = 0,pokemon2 = 0,pokemon3 = 0,pokemon4 = 0,pokemon5 = 0,pokemon6 = 0;

    public Equipo(String id, String nombre, Date fecha, int pokemon1, int pokemon2, int pokemon3, int pokemon4, int pokemon5, int pokemon6) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.pokemon3 = pokemon3;
        this.pokemon4 = pokemon4;
        this.pokemon5 = pokemon5;
        this.pokemon6 = pokemon6;
    }

    public Equipo(String id, String nombre, Usuario usuario, Date fecha, int pokemon1, int pokemon2, int pokemon3, int pokemon4, int pokemon5, int pokemon6) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.fecha = fecha;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.pokemon3 = pokemon3;
        this.pokemon4 = pokemon4;
        this.pokemon5 = pokemon5;
        this.pokemon6 = pokemon6;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(int pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public int getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(int pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public int getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(int pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public int getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(int pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public int getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(int pokemon5) {
        this.pokemon5 = pokemon5;
    }

    public int getPokemon6() {
        return pokemon6;
    }

    public void setPokemon6(int pokemon6) {
        this.pokemon6 = pokemon6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", pokemon1=" + pokemon1 +
                ", pokemon2=" + pokemon2 +
                ", pokemon3=" + pokemon3 +
                ", pokemon4=" + pokemon4 +
                ", pokemon5=" + pokemon5 +
                ", pokemon6=" + pokemon6 +
                '}';
    }
}
