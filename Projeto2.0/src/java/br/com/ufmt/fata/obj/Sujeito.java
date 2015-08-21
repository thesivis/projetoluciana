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
public class Sujeito{
    private int id;
    private String palavra;
    private String sexo;
    private String url;
    private String conjugacao;

    public Sujeito() {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConjugacao() {
        return conjugacao;
    }

    public void setConjugacao(String conjugacao) {
        this.conjugacao = conjugacao;
    }
    
}
