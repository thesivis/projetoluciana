/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.ent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author vicentejr
 */
@Entity
public class Sujeito implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sujeitoId;
    private String palavra;
    private String sexo;
    private String url;
    private String conjugacao;
    private String tempo;
    
    @ManyToMany(mappedBy = "sujeitos")
    private List<PastaDeComunicacao> pastaDeComunicacaos;
    
    

    public Long getSujeitoId() {
        return sujeitoId;
    }

    public void setSujeitoId(Long sujeitoId) {
        this.sujeitoId = sujeitoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sujeitoId != null ? sujeitoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the sujeitoId fields are not set
        if (!(object instanceof Sujeito)) {
            return false;
        }
        Sujeito other = (Sujeito) object;
        if ((this.sujeitoId == null && other.sujeitoId != null) || (this.sujeitoId != null && !this.sujeitoId.equals(other.sujeitoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufmt.fata.ent.Sujeito[ id=" + sujeitoId + " ]";
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

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public List<PastaDeComunicacao> getPastaDeComunicacaos() {
        return pastaDeComunicacaos;
    }

    public void setPastaDeComunicacaos(List<PastaDeComunicacao> pastaDeComunicacaos) {
        this.pastaDeComunicacaos = pastaDeComunicacaos;
    }
    
}
