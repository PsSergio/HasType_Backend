package com.hastype.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name="User")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
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

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean validateUserLogin(String _email, String _senha) {
        System.out.println(email + " - " + _email);
        System.out.println(senha + " - " + _senha);
        return Objects.equals(this.email, _email) && Objects.equals(this.senha, _senha);
    }

}
