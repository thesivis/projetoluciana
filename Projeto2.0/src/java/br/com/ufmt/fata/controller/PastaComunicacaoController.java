/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import static br.com.ufmt.fata.controller.PrincipalController.copyFile;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.PastaDeComunicacaoDaoImp;
import br.com.ufmt.fata.ent.Complemento;
import br.com.ufmt.fata.ent.PastaDeComunicacao;
import br.com.ufmt.fata.ent.Sujeito;
import br.com.ufmt.fata.ent.Verbo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private UploadedFile file;
    private List<Sujeito> sujeitoListAd = new ArrayList<>();
    private List<Verbo> verboListAd = new ArrayList<>();
    private List<Complemento> complementoListAd = new ArrayList<>();
    private List<Sujeito> sujeitoListRem = new ArrayList<>();
    private List<Verbo> verboListRem = new ArrayList<>();
    private List<Complemento> complementoListRem = new ArrayList<>();
   
    public PastaComunicacaoController() {
   
    }
    
    public void onNewPasta(){
        this.pastaFile = new PastaDeComunicacao();
    }
    
    @PostConstruct
    public void init(){
        this.list = pastaComunicacaoDao.list();
    }
    
    public void gravar(){
        if(file.getSize()!= 0){
             fileUpload();
        }else{
            pastaFile.setFotoUrl("user.png");
        }
        pastaComunicacaoDao.save(pastaFile);
        onImgSelect();
        onImgSelectedRemove();
        ActiveUserController.userActive = pastaFile;   
    }
    
    public void onImgSelect(){
        System.out.println("onImgSelect");
        if(!sujeitoListAd.isEmpty()){
            this.pastaFile.getSujeitos().addAll(sujeitoListAd);
        }
        if(!verboListAd.isEmpty()){
            this.pastaFile.getVerbos().addAll(verboListAd);
        }
        if(!complementoListAd.isEmpty()){
            this.pastaFile.getComplementos().addAll(complementoListAd);
        }
        System.out.println("Foi save A");
        pastaComunicacaoDao.save(this.pastaFile);
    }
    
    public void onImgSelectedRemove(){
        if(!sujeitoListRem.isEmpty()){
            this.pastaFile.getSujeitos().removeAll(sujeitoListRem);
        }
        if(!verboListRem.isEmpty()){
            this.pastaFile.getVerbos().removeAll(verboListRem);
        }
        if(!complementoListRem.isEmpty()){
            this.pastaFile.getComplementos().removeAll(complementoListRem);
        }
        System.out.println("Foi save R");
        pastaComunicacaoDao.save(this.pastaFile);
    }
    
    public void fileUpload(){ 
        pastaFile.setFotoUrl(removerAcentos(file.getFileName()));  
        try {
            copyFile(pastaFile.getFotoUrl(), file.getInputstream());
        } catch (IOException e) {
        }
 
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

    public List<Sujeito> getSujeitoListAd() {
        return sujeitoListAd;
    }

    public void setSujeitoListAd(List<Sujeito> sujeitoListAd) {
        this.sujeitoListAd = sujeitoListAd;
    }

    public List<Verbo> getVerboListAd() {
        return verboListAd;
    }

    public void setVerboListAd(List<Verbo> verboListAd) {
        this.verboListAd = verboListAd;
    }

    public List<Complemento> getComplementoListAd() {
        return complementoListAd;
    }

    public void setComplementoListAd(List<Complemento> complementoListAd) {
        this.complementoListAd = complementoListAd;
    }

    public List<Sujeito> getSujeitoListRem() {
        return sujeitoListRem;
    }

    public void setSujeitoListRem(List<Sujeito> sujeitoListRem) {
        this.sujeitoListRem = sujeitoListRem;
    }

    public List<Verbo> getVerboListRem() {
        return verboListRem;
    }

    public void setVerboListRem(List<Verbo> verboListRem) {
        this.verboListRem = verboListRem;
    }

    public List<Complemento> getComplementoListRem() {
        return complementoListRem;
    }

    public void setComplementoListRem(List<Complemento> complementoListRem) {
        this.complementoListRem = complementoListRem;
    }
    
}
