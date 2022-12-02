package com.sfkao.pokeviewerbackend.backend.modelo;

public class UsuarioSQL{
    public String username;
    public String email;
    public String saltedHash;
    public String salt;
    public String apikey;

    public UsuarioSQL(String username, String email, String saltedHash, String salt, String apikey) {
        this.username = username;
        this.email = email;
        this.saltedHash = saltedHash;
        this.salt = salt;
        this.apikey = apikey;
    }

    public Usuario toUser(){
        return new Usuario(username,email,apikey);
    }
}
