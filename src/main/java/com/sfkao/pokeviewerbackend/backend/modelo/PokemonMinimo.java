package com.sfkao.pokeviewerbackend.backend.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class PokemonMinimo {
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

    private static HashMap<Integer, PokemonMinimo> pokemonMinimoMap;

    public static PokemonMinimo load(int id){
        if(pokemonMinimoMap == null){
            pokemonMinimoMap = new HashMap<>();
            try {
                String json = new String(PokemonMinimo.class.getResourceAsStream("/pokemons.json").readAllBytes());
                PokemonMinimo[] pok = new ObjectMapper().readValue(json,PokemonMinimo[].class);
                for (com.sfkao.pokeviewerbackend.backend.modelo.PokemonMinimo pokemonMinimo : pok)
                    pokemonMinimoMap.put(pokemonMinimo.id, pokemonMinimo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pokemonMinimoMap.get(id);
    }

    }
