package com.windsoft.se.project.usuario;

/**
 * Created by mattheusbrito on 01/12/2017.
 */

public class Usuario {

    private String nome;

    public Usuario(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String novoNome){
        this.nome = novoNome;
    }

}
