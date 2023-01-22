package com.sfkao.pokeviewerbackend.backend.modelo;

public class EquipoCargado {

    public String id;
    public String name;
    public String usuario;
    public PokemonMinimo[] pokemons;
    public int likes;
    public int favoritos;
    public boolean dadoLike = false;
    public boolean dadoFavoritos = false;

    public EquipoCargado(String id, String name, String usuario) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
        pokemons = new PokemonMinimo[6];
    }

    public EquipoCargado(String id, String name, String usuario, int likes, int favoritos, boolean dadoLike, boolean dadoFavoritos) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
        this.likes = likes;
        this.favoritos = favoritos;
        this.dadoLike = dadoLike;
        this.dadoFavoritos = dadoFavoritos;
        pokemons = new PokemonMinimo[6];
    }

    public EquipoCargado cargarPokemon(int id, int pos){
        pokemons[pos] = com.sfkao.pokeviewerbackend.backend.modelo.PokemonMinimo.load(id);
        return this;
    }

}
