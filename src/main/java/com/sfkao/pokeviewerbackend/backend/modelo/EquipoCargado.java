package com.sfkao.pokeviewerbackend.backend.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

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
        pokemons[pos] = PokemonLoader.load(id);
        return this;
    }

    public static class PokemonMinimo{
        public int id;
        public String name;
        public String img;
        public String imgS;

        public PokemonMinimo() {
        }

        public PokemonMinimo(int id, String name, String img, String imgS) {
            this.id = id;
            this.name = name;
            this.img = img;
            this.imgS = imgS;
        }

        @Override
        public String toString() {
            return "PokemonMinimo{" +
                    "id=" + id +
                    ", name=" + name +
                    ", img='" + img + '\'' +
                    ", imgS='" + imgS + '\'' +
                    '}';
        }
    }

    private static class PokemonLoader{
        private static HashMap<Integer, PokemonMinimo> pokemonMinimoMap;

        public static PokemonMinimo load(int id){
            if(pokemonMinimoMap == null){
                pokemonMinimoMap = new HashMap<>();
                try {
                    String json = new String(PokemonLoader.class.getResourceAsStream("/pokemons.json").readAllBytes());
                    PokemonMinimo[] pok = new ObjectMapper().readValue(json, PokemonMinimo[].class);
                    for (PokemonMinimo pokemonMinimo : pok)
                        pokemonMinimoMap.put(pokemonMinimo.id, pokemonMinimo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return pokemonMinimoMap.get(id);
        }

    }

}
