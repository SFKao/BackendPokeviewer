package com.sfkao.pokeviewerbackend.backend.modelo;

public class UsuarioSQL{
    public String username;
    public String email;
    public String saltedHash;
    public String salt;
    public String apikey;
    public int pk1;
    public int pk2;
    public int pk3;

    public UsuarioSQL(String username, String email, String saltedHash, String salt, String apikey) {
        this.username = username;
        this.email = email;
        this.saltedHash = saltedHash;
        this.salt = salt;
        this.apikey = apikey;
    }

    public UsuarioSQL(String username, String email, String saltedHash, String salt, String apikey, int pk1, int pk2, int pk3) {
        this.username = username;
        this.email = email;
        this.saltedHash = saltedHash;
        this.salt = salt;
        this.apikey = apikey;
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
    }

    public Usuario toUser(){
        return new Usuario(username,email,apikey);
    }
}
