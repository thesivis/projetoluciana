/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.obj;

/**
 *
 * @author vicentejr
 */
public class Verbo {
    private int id;
    private String palavra;
    private String url;
    private String primPessoaPlural;
    private String primPessoaSingular;
    private String tercPessoaSingular;
    
    public Verbo(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrimPessoaPlural() {
        return primPessoaPlural;
    }

    public void setPrimPessoaPlural(String primPessoaPlural) {
        this.primPessoaPlural = primPessoaPlural;
    }

    public String getPrimPessoaSingular() {
        return primPessoaSingular;
    }

    public void setPrimPessoaSingular(String primPessoaSingular) {
        this.primPessoaSingular = primPessoaSingular;
    }

    public String getTercPessoaSingular() {
        return tercPessoaSingular;
    }

    public void setTercPessoaSingular(String tercPessoaSingular) {
        this.tercPessoaSingular = tercPessoaSingular;
    }

    
}
