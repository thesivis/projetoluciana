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
public class Verbo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long verboId;
    private String palavra;
    private String pasprimpessoa;
    private String passegpessoa;
    private String pastercpessoa;
    private String preprimpessoa;
    private String presegpessoa;
    private String pretercpessoa;
    private String futprimpessoa;
    private String futsegpessoa;
    private String futtercpessoa;
    private String url;
    
    @ManyToMany(mappedBy = "verbos")
    private List<PastaDeComunicacao> pastaDeComunicacaos;
    

    public Long getVerboId() {
        return verboId;
    }

    public void setVerboId(Long verboId) {
        this.verboId = verboId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verboId != null ? verboId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the verboId fields are not set
        if (!(object instanceof Verbo)) {
            return false;
        }
        Verbo other = (Verbo) object;
        if ((this.verboId == null && other.verboId != null) || (this.verboId != null && !this.verboId.equals(other.verboId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufmt.fata.ent.Verbo[ id=" + verboId + " ]";
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPasprimpessoa() {
        return pasprimpessoa;
    }

    public void setPasprimpessoa(String pasprimpessoa) {
        this.pasprimpessoa = pasprimpessoa;
    }

    public String getPassegpessoa() {
        return passegpessoa;
    }

    public void setPassegpessoa(String passegpessoa) {
        this.passegpessoa = passegpessoa;
    }

    public String getPastercpessoa() {
        return pastercpessoa;
    }

    public void setPastercpessoa(String pastercpessoa) {
        this.pastercpessoa = pastercpessoa;
    }

    public String getPreprimpessoa() {
        return preprimpessoa;
    }

    public void setPreprimpessoa(String preprimpessoa) {
        this.preprimpessoa = preprimpessoa;
    }

    public String getPresegpessoa() {
        return presegpessoa;
    }

    public void setPresegpessoa(String presegpessoa) {
        this.presegpessoa = presegpessoa;
    }

    public String getPretercpessoa() {
        return pretercpessoa;
    }

    public void setPretercpessoa(String pretercpessoa) {
        this.pretercpessoa = pretercpessoa;
    }

    public String getFutprimpessoa() {
        return futprimpessoa;
    }

    public void setFutprimpessoa(String futprimpessoa) {
        this.futprimpessoa = futprimpessoa;
    }

    public String getFutsegpessoa() {
        return futsegpessoa;
    }

    public void setFutsegpessoa(String futsegpessoa) {
        this.futsegpessoa = futsegpessoa;
    }

    public String getFuttercpessoa() {
        return futtercpessoa;
    }

    public void setFuttercpessoa(String futtercpessoa) {
        this.futtercpessoa = futtercpessoa;
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
