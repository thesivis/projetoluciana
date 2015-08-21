/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author raphael
 */
@javax.persistence.Entity
@Table(name = "verbo")
public class Verbo implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String palavra;
    private String primeiraPessoaSingular;
    private String terceiraPessoaSingular;
    private String terceiraPessoaPlural;
    private String url;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPrimeiraPessoaSingular() {
        return primeiraPessoaSingular;
    }

    public void setPrimeiraPessoaSingular(String primeiraPessoaSingular) {
        this.primeiraPessoaSingular = primeiraPessoaSingular;
    }

    public String getTerceiraPessoaSingular() {
        return terceiraPessoaSingular;
    }

    public void setTerceiraPessoaSingular(String terceiraPessoaSingular) {
        this.terceiraPessoaSingular = terceiraPessoaSingular;
    }

    public String getTerceiraPessoaPlural() {
        return terceiraPessoaPlural;
    }

    public void setTerceiraPessoaPlural(String terceiraPessoaPlural) {
        this.terceiraPessoaPlural = terceiraPessoaPlural;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
