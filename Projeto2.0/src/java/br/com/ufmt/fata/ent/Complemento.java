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
public class Complemento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long complementoId;
    private String palavra;
    private String sexo;
    private String url;
    
    @ManyToMany(mappedBy = "complementos")
    private List<PastaDeComunicacao> pastaDeComunicacaos;
    

    public Long getComplementoId() {
        return complementoId;
    }

    public void setComplementoId(Long complementoId) {
        this.complementoId = complementoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complementoId != null ? complementoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the complementoId fields are not set
        if (!(object instanceof Complemento)) {
            return false;
        }
        Complemento other = (Complemento) object;
        if ((this.complementoId == null && other.complementoId != null) || (this.complementoId != null && !this.complementoId.equals(other.complementoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufmt.fata.ent.Complemento[ id=" + complementoId + " ]";
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

    public List<PastaDeComunicacao> getPastaDeComunicacaos() {
        return pastaDeComunicacaos;
    }

    public void setPastaDeComunicacaos(List<PastaDeComunicacao> pastaDeComunicacaos) {
        this.pastaDeComunicacaos = pastaDeComunicacaos;
    }
    
}
