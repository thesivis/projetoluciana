/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import br.com.ufmt.fata.dao.PastaDeComunicacaoDaoImp;
import br.com.ufmt.fata.ent.Complemento;
import br.com.ufmt.fata.ent.PastaDeComunicacao;
import br.com.ufmt.fata.ent.Sujeito;
import br.com.ufmt.fata.ent.Verbo;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vicentejr
 */
@ManagedBean
@SessionScoped
public class ActiveUserController implements Serializable{
    private PastaDeComunicacao userActive;
    private PastaDeComunicacaoDaoImp comunicacaoDaoImp = new PastaDeComunicacaoDaoImp();
    
    public void userSelect(PastaDeComunicacao pastaSelect){
        this.userActive = pastaSelect;
    }
    
    public void onSujSelect(Sujeito sujeito){
        this.userActive.getSujeitos().add(sujeito);
        comunicacaoDaoImp.save(userActive);
    }
    public void onSujDelete(Sujeito sujeito){
        this.userActive.getSujeitos().remove(sujeito);
    }
    public void onVerbSelect(Verbo verbo){
        this.userActive.getVerbos().add(verbo);
        comunicacaoDaoImp.save(userActive);
    }
    public void onVerbDelete(Verbo verbo){
        this.userActive.getVerbos().remove(verbo);
    }
    public void onCompSelect(Complemento complemento){
        this.userActive.getComplementos().add(complemento);
        comunicacaoDaoImp.save(userActive);
    }
    public void onCompDelete(Complemento complemento){
        this.userActive.getComplementos().remove(complemento);
    }  

    public PastaDeComunicacao getUserActive() {
        return userActive;
    }

    public void setUserActive(PastaDeComunicacao userActive) {
        this.userActive = userActive;
    }
    
}
