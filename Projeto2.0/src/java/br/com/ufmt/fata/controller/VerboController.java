/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import static br.com.ufmt.fata.controller.PrincipalController.FATA_DIR;
import br.com.ufmt.fata.dao.JDBCVerboDAO;
import br.com.ufmt.fata.obj.Verbo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private final JDBCVerboDAO verboCon = new JDBCVerboDAO();
    private UploadedFile file;
    private Verbo verboFile = new Verbo();
     
    @PostConstruct
    public void init(){
        verboList = verboCon.listar();
    }
    
    public void onRowEdit(RowEditEvent event){
        verboCon.editar((Verbo)event.getObject());
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
        verboCon.remover(verbo);
    }
    
    public void gravar(){
        fileUpload();
        verboCon.inserir(verboFile);
    }
    
    public void fileUpload(){ 
        verboFile.setUrl(file.getFileName());
        FacesMessage msg = new FacesMessage("Enviado! ", file.getFileName() + " foi salvo com sucesso!.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);      
        try {
            copyFile(file.getFileName(), file.getInputstream());
        } catch (IOException e) {
        }
 
    }  
    
    public void copyFile(String fileName, InputStream in) {
           try {
               System.out.println(fileName);
               try (OutputStream out = new FileOutputStream(new File(FATA_DIR + fileName))) {
                   int read = 0;
                   byte[] bytes = new byte[1024];
                   
                   while ((read = in.read(bytes)) != -1) {
                       out.write(bytes, 0, read);
                   }
                   
                   in.close();
                   out.flush();
               }
              
                System.out.println("Arquivo Criado!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
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
