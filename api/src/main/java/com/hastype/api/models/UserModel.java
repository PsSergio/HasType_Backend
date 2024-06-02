package com.hastype.api.models;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name="User")
public class UserModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

//    @Column(name = "email")
    private String email;

//    @Column(name = "nome")
    private String nome;

//    @Column(name = "senha")
    private String senha;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean validaLogin(String _nome, String _senha){
        return this.nome == _nome && this.senha == _senha;
    }

}
