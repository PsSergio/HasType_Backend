package com.hastype.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name="User")
public class UserModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

}
