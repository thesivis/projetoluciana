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
    private List<Sujeito> sujeitoListSelect;
    private List<Verbo> verboListSelect;
    private List<Complemento> complementoListSelect;
    protected static PastaDeComunicacao userActive;
    private PastaDeComunicacaoDaoImp comunicacaoDaoImp = new PastaDeComunicacaoDaoImp();
    protected static boolean userCreated;
    
    public void onImgSelect(){
        System.out.println(sujeitoListSelect.size());
        if(!sujeitoListSelect.isEmpty()){
            userActive.getSujeitos().addAll(sujeitoListSelect);
        }
        if(!verboListSelect.isEmpty()){
            userActive.getVerbos().addAll(verboListSelect);
        }
        if(!complementoListSelect.isEmpty()){
            userActive.getComplementos().addAll(complementoListSelect);
        }
        comunicacaoDaoImp.save(userActive);
        userCreated = false;
    }
    
    public void userSelect(PastaDeComunicacao pastaSelect){
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

    public List<Sujeito> getSujeitoListSelect() {
        return sujeitoListSelect;
    }

    public void setSujeitoListSelect(List<Sujeito> sujeitoListSelect) {
        this.sujeitoListSelect = sujeitoListSelect;
    }

    public List<Verbo> getVerboListSelect() {
        return verboListSelect;
    }

    public void setVerboListSelect(List<Verbo> verboListSelect) {
        this.verboListSelect = verboListSelect;
    }

    public List<Complemento> getComplementoListSelect() {
        return complementoListSelect;
    }

    public void setComplementoListSelect(List<Complemento> complementoListSelect) {
        this.complementoListSelect = complementoListSelect;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
       this.userCreated = userCreated;
    }
    
}
