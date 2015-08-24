/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import static br.com.ufmt.fata.controller.PrincipalController.FATA_DIR;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.JDBCComplementoDAO;
import br.com.ufmt.fata.obj.Complemento;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
public class ComplementoController implements Serializable{
    private List<Complemento> complementoList;
    private List<Complemento> complementoListFiltred;
    private final JDBCComplementoDAO complementoCon = new JDBCComplementoDAO();
    private UploadedFile file;
    private Complemento complementoFile = new Complemento();
    
    @PostConstruct
    public void init(){
        complementoList = complementoCon.listar();
    }
    
    public void onRowEdit(RowEditEvent event){
        complementoCon.editar((Complemento)event.getObject());
        FacesMessage message = new FacesMessage("Complemento id:",((Complemento)event.getObject()).getId() + " Alterado com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowCancel(RowEditEvent event){
        FacesMessage message = new FacesMessage("Nenhum dado foi alterado!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    
    }
    
    public void onRowDelet(Complemento complemento){
        FacesMessage message = new FacesMessage(complemento.getPalavra()+" foi removido");
        FacesContext.getCurrentInstance().addMessage(null, message);
        complementoCon.remover(complemento);
    }
    
    public void gravar(){
        fileUpload();
        complementoCon.inserir(complementoFile);
    }
    
    public void fileUpload(){ 
        complementoFile.setUrl(removerAcentos(file.getFileName()));
        FacesMessage msg = new FacesMessage("Enviado! ", file.getFileName() + " foi salvo com sucesso!.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);      
        try {
            copyFile(complementoFile.getUrl(), file.getInputstream());
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
    
    public Collection listaDiretorios(File path) { 
  
        Collection listaVolta = new ArrayList();  
        File[] files = path.listFiles();  


        for (File arq : files) {
            if (arq.isDirectory()) {
                Collection lista = listaDiretorios(arq);
                if (lista.size() > 0) listaVolta.addAll(lista);
            }else{
                listaVolta.add(arq);
            }
        }  
    return listaVolta;  
    } 
    
    public List<Complemento> getComplementoList() {
        return complementoList;
    }

    public void setComplementoList(List<Complemento> complementoList) {
        this.complementoList = complementoList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Complemento getComplementoFile() {
        return complementoFile;
    }

    public void setComplementoFile(Complemento complementoFile) {
        this.complementoFile = complementoFile;
    }

    public List<Complemento> getComplementoListFiltred() {
        return complementoListFiltred;
    }

    public void setComplementoListFiltred(List<Complemento> complementoListFiltred) {
        this.complementoListFiltred = complementoListFiltred;
    }
    
    
}
