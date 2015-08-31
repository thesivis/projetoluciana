/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;
 
import static br.com.ufmt.fata.controller.PrincipalController.copyFile;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.SujeitoDaoImp;
import br.com.ufmt.fata.obj.Sujeito;
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
public class SujeitoController  implements Serializable{
    private List<Sujeito> sujeitoList;
    private List<Sujeito> sujeitoListFiltred;
    private final SujeitoDaoImp sujeitoCon = new SujeitoDaoImp();
    private UploadedFile file;
    private Sujeito sujeitoFile = new Sujeito();
   
    @PostConstruct
    public void init(){
        sujeitoList = sujeitoCon.list();
    }
    
    public void onRowEdit(RowEditEvent event){
        sujeitoCon.update((Sujeito)event.getObject());
        FacesMessage message = new FacesMessage("Sujeto id:",((Sujeito)event.getObject()).getId() + " Alterado com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowCancel(RowEditEvent event){
        FacesMessage message = new FacesMessage("Nenhum dado foi alterado!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    
    }
    
    public void onRowDelet(Sujeito sujeito){
        FacesMessage message = new FacesMessage(sujeito.getPalavra()+" foi removido");
        FacesContext.getCurrentInstance().addMessage(null, message);
        sujeitoCon.remove(sujeito);
    }
    
    public void gravar(){
        fileUpload();
        sujeitoCon.save(sujeitoFile);
    }
    
    public void fileUpload(){ 
        sujeitoFile.setUrl(removerAcentos(file.getFileName()));
        FacesMessage msg = new FacesMessage("Enviado! ", file.getFileName() + " foi salvo com sucesso!.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);      
        try {
            copyFile(sujeitoFile.getUrl(), file.getInputstream());
        } catch (IOException e) {
        }
 
    }  

    public List<Sujeito> getSujeitoList() {
        return sujeitoList;
    }

    public void setSujeitoList(List<Sujeito> sujeitoList) {
        this.sujeitoList = sujeitoList;
    }

    public Sujeito getSujeitoFile() {
        return sujeitoFile;
    }

    public void setSujeitoFile(Sujeito sujeitoFile) {
        this.sujeitoFile = sujeitoFile;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Sujeito> getSujeitoListFiltred() {
        return sujeitoListFiltred;
    }

    public void setSujeitoListFiltred(List<Sujeito> sujeitoListFiltred) {
        this.sujeitoListFiltred = sujeitoListFiltred;
    }
    
    
    
}
