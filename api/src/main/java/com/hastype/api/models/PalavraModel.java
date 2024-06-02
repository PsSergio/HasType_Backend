package com.hastype.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Palavra")
public class PalavraModel {

    @Id
    private Integer id;

    private String palavraNormal;

    private String palavraTraduzida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalavraNormal() {
        return palavraNormal;
    }

    public void setPalavraNormal(String palavraNormal) {
        this.palavraNormal = palavraNormal;
    }

    public String getPalavraTraduzida() {
        return palavraTraduzida;
    }

    public void setPalavraTraduzida(String palavraTraduzida) {
        this.palavraTraduzida = palavraTraduzida;
    }

    public boolean validaResposta(String respostaUser){
        return this.palavraTraduzida == respostaUser;
    }

}
