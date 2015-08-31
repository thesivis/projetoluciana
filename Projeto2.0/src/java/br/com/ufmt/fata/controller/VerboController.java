/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import static br.com.ufmt.fata.controller.PrincipalController.copyFile;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.VerboDaoImp;
import br.com.ufmt.fata.obj.Verbo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author vicentejr
 */
@ViewScoped
@ManagedBean
public class VerboController implements Serializable{
    private List<Verbo> verboList;
    private List<Verbo> verboListFiltred;
    private final VerboDaoImp verboCon = new VerboDaoImp();
    private UploadedFile file;
    private Verbo verboFile = new Verbo();
     
    @PostConstruct
    public void init(){
        verboList = verboCon.list();
    }
    
    public void onRowEdit(RowEditEvent event){
        verboCon.update((Verbo)event.getObject());
        FacesMessage message = new FacesMessage("Verbo id:",((Verbo)event.getObject()).getId() + " Alterado com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowCancel(RowEditEvent event){
        FacesMessage message = new FacesMessage("Nenhum dado foi alterado!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    
    }
    
    public void onRowDelet(Verbo verbo){
        FacesMessage message = new FacesMessage(verbo.getPalavra()+" foi removido");
        FacesContext.getCurrentInstance().addMessage(null, message);
        verboCon.remove(verbo);
    }
    
    public void gravar(){
        fileUpload();
        verboCon.save(verboFile);
    }
    
    public void fileUpload(){ 
        verboFile.setUrl(removerAcentos(file.getFileName()));
        FacesMessage msg = new FacesMessage("Enviado! ", file.getFileName() + " foi salvo com sucesso!.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);      
        try {
            copyFile(verboFile.getUrl(), file.getInputstream());
        } catch (IOException e) {
        }
 
    }  
    
    public List<Verbo> getVerboList() {
        return verboList;
    }

    public void setVerboList(List<Verbo> verboList) {
        this.verboList = verboList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Verbo getVerboFile() {
        return verboFile;
    }

    public void setVerboFile(Verbo verboFile) {
        this.verboFile = verboFile;
    }

    public List<Verbo> getVerboListFiltred() {
        return verboListFiltred;
    }

    public void setVerboListFiltred(List<Verbo> verboListFiltred) {
        this.verboListFiltred = verboListFiltred;
    }
    
    
}
