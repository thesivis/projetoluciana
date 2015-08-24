/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;
 
import static br.com.ufmt.fata.controller.PrincipalController.FATA_DIR;
import static br.com.ufmt.fata.controller.PrincipalController.removerAcentos;
import br.com.ufmt.fata.dao.JDBCSujeitoDAO;
import br.com.ufmt.fata.obj.Sujeito;
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
public class SujeitoController  implements Serializable{
    private List<Sujeito> sujeitoList;
    private List<Sujeito> sujeitoListFiltred;
    private final JDBCSujeitoDAO sujeitoCon = new JDBCSujeitoDAO();
    private UploadedFile file;
    private Sujeito sujeitoFile = new Sujeito();
   
    @PostConstruct
    public void init(){
        sujeitoList = sujeitoCon.listar();
    }
    
    public void onRowEdit(RowEditEvent event){
        sujeitoCon.editar((Sujeito)event.getObject());
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
        sujeitoCon.remover(sujeito);
    }
    
    public void gravar(){
        fileUpload();
        sujeitoCon.inserir(sujeitoFile);
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
