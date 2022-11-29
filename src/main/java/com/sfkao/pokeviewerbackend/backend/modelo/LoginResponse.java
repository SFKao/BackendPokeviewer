package com.sfkao.pokeviewerbackend.backend.modelo;

public class LoginResponse {

    private Usuario usuario;
    private int code;
    private String response;

    public LoginResponse(Usuario usuario, int code, String response) {
        this.usuario = usuario;
        this.code = code;
        this.response = response;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LoginResponde{" +
                "usuario=" + usuario +
                ", code=" + code +
                ", response='" + response + '\'' +
                '}';
    }
}
