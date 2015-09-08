/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.ent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author vicentejr
 */
@Entity
public class PastaDeComunicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pastaComunicacaoId;
    private String nome;
    private String dataNasc;
    private String sexo;
    private String fotoUrl;
    
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "pastaSujeito",
            joinColumns = @JoinColumn(name = "pastaComunicacaoId"),
            inverseJoinColumns = @JoinColumn(name = "sujeitoId"))
    private Set<Sujeito> sujeitos = new HashSet();
    @ManyToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "pastaVerbo",
            joinColumns = @JoinColumn(name = "pastaComunicacaoId"),
            inverseJoinColumns = @JoinColumn(name = "verboId"))
    private Set<Verbo> verbos = new HashSet();
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "pastaComplemento",
            joinColumns = @JoinColumn(name = "pastaComunicacaoId"),
            inverseJoinColumns = @JoinColumn(name = "complementoId"))
    private Set<Complemento> complementos = new HashSet();

    public Long getPastaComunicacaoId() {
        return pastaComunicacaoId;
    }

    public void setPastaComunicacaoId(Long pastaComunicacaoId) {
        this.pastaComunicacaoId = pastaComunicacaoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pastaComunicacaoId != null ? pastaComunicacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pastaComunicacaoId fields are not set
        if (!(object instanceof PastaDeComunicacao)) {
            return false;
        }
        PastaDeComunicacao other = (PastaDeComunicacao) object;
        if ((this.pastaComunicacaoId == null && other.pastaComunicacaoId != null) || (this.pastaComunicacaoId != null && !this.pastaComunicacaoId.equals(other.pastaComunicacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufmt.fata.ent.PastaDeComunicacao[ id=" + pastaComunicacaoId + " ]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Sujeito> getSujeitos() {
        return sujeitos;
    }

    public void setSujeitos(Set<Sujeito> sujeitos) {
        this.sujeitos = sujeitos;
    }

    public Set<Verbo> getVerbos() {
        return verbos;
    }

    public void setVerbos(Set<Verbo> verbos) {
        this.verbos = verbos;
    }

    public Set<Complemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(Set<Complemento> complementos) {
        this.complementos = complementos;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
    
}
