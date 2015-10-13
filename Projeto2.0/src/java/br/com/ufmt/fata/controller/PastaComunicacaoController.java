/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import static br.com.ufmt.fata.controller.PrincipalController.copyFile;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.PastaDeComunicacaoDaoImp;
import br.com.ufmt.fata.ent.PastaDeComunicacao;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author vicentejr
 */
@ManagedBean
@ViewScoped
public class PastaComunicacaoController implements Serializable{
    
    private List<PastaDeComunicacao> list;
    private PastaDeComunicacaoDaoImp pastaComunicacaoDao = new PastaDeComunicacaoDaoImp();
    private PastaDeComunicacao pastaFile= new PastaDeComunicacao();
    private PastaDeComunicacao paciente= new PastaDeComunicacao();
    private boolean pacienteAtivo;
    private UploadedFile file;
   
    public PastaComunicacaoController() {
   
    }
    
    @PostConstruct
    public void init(){
        this.list = pastaComunicacaoDao.list();
    }

    public void gravar(){
        fileUpload();
        pastaComunicacaoDao.save(pastaFile);
        ActiveUserController.userActive = pastaFile;
        ActiveUserController.userCreated = true;
        
        
    }
    
    public void fileUpload(){ 
        pastaFile.setFotoUrl(removerAcentos(file.getFileName()));  
        try {
            copyFile(pastaFile.getFotoUrl(), file.getInputstream());
        } catch (IOException e) {
        }
 
    }  
    
    
    
    public void onRowSelect(PastaDeComunicacao pacienteSelect){
        paciente = pacienteSelect;
        pacienteAtivo = true;
        System.out.println("Selecionado");
    }
    public void onRowDelete(PastaDeComunicacao pacienteSelect){
        pastaComunicacaoDao.remove(pacienteSelect);  
    }
    
    public List<PastaDeComunicacao> getList() {
        return list;
    }

    public void setList(List<PastaDeComunicacao> list) {
        this.list = list;
    }

    public PastaDeComunicacao getPastaFile() {
        return pastaFile;
    }

    public void setPastaFile(PastaDeComunicacao pastaFile) {
        this.pastaFile = pastaFile;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public PastaDeComunicacao getPaciente() {
        return paciente;
    }

    public void setPaciente(PastaDeComunicacao paciente) {
        this.paciente = paciente;
    }

    public boolean isPacienteAtivo() {
        return pacienteAtivo;
    }

    public void setPacienteAtivo(boolean pacienteAtivo) {
        this.pacienteAtivo = pacienteAtivo;
    }  
}
