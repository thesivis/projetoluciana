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
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vicentejr
 */
@ManagedBean
@SessionScoped
public class ActiveUserController implements Serializable{
   
    protected static PastaDeComunicacao userActive;
    private PastaDeComunicacaoDaoImp comunicacaoDaoImp = new PastaDeComunicacaoDaoImp();
    protected static boolean userCreated;
    
    public static void userSelect(PastaDeComunicacao pastaSelect){
        userActive = pastaSelect;
    }
    
    public void onSujSelect(Sujeito sujeito){
        userActive.getSujeitos().add(sujeito);
        comunicacaoDaoImp.save(userActive);
    }
    public void onSujDelete(Sujeito sujeito){
        userActive.getSujeitos().remove(sujeito);
        comunicacaoDaoImp.save(userActive);
    }
    public void onVerbSelect(Verbo verbo){
        userActive.getVerbos().add(verbo);
        comunicacaoDaoImp.save(userActive);
    }
    public void onVerbDelete(Verbo verbo){
        userActive.getVerbos().remove(verbo);
        comunicacaoDaoImp.save(userActive);
    }
    public void onCompSelect(Complemento complemento){
        userActive.getComplementos().add(complemento);
        comunicacaoDaoImp.save(userActive);
    }
    public void onCompDelete(Complemento complemento){
        userActive.getComplementos().remove(complemento);
        comunicacaoDaoImp.save(userActive);
    }  
   
    public PastaDeComunicacao getUserActive() {
        return userActive;
    }

    public void setUserActive(PastaDeComunicacao userActive) {
        this.userActive = userActive;
    }
    
    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
       this.userCreated = userCreated;
    }

}
