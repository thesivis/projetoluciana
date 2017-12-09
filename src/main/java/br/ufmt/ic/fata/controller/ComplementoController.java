/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.controller;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufmt.ic.fata.dao.ComplementoDaoImp;
import br.ufmt.ic.fata.ent.Complemento;

import static br.ufmt.ic.fata.controller.PrincipalController.copyFile;
import static br.ufmt.ic.fata.controller.PrincipalController.removerAcentos;

/**
 *
 * @author vicentejr
 */
@ViewScoped
@ManagedBean
public class ComplementoController implements Serializable {

    private List<Complemento> complementoList;
    private List<Complemento> complementoListFiltred;
    private final ComplementoDaoImp complementoCon;
    private UploadedFile file;
    private Complemento complementoFile;

    public ComplementoController() {
        this.complementoFile = new Complemento();
        this.complementoCon = new ComplementoDaoImp();
    }

    @PostConstruct
    public void init() {
        complementoList = complementoCon.list();
    }

    public void onRowEdit(RowEditEvent event) {
        complementoCon.update((Complemento) event.getObject());
        FacesMessage message = new FacesMessage("Complemento id:", ((Complemento) event.getObject()).getComplementoId() + " Alterado com Sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        init();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage message = new FacesMessage("Nenhum dado foi alterado!");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void onRowDelet(Complemento complemento) {
        FacesMessage message = new FacesMessage(complemento.getPalavra() + " foi removido");
        FacesContext.getCurrentInstance().addMessage(null, message);
        complementoCon.remove(complemento);
        init();
    }

    public void onNewComplemento() {
        this.complementoFile = new Complemento();
    }

    public void gravar() {
        if (file != null) {
            if (file.getSize() != 0) {
                fileUpload();
            }
        }
        complementoCon.save(complementoFile);
        init();

    }

    public void fileUpload() {
        complementoFile.setUrl(removerAcentos(file.getFileName()));
        FacesMessage msg = new FacesMessage("Enviado! ", file.getFileName() + " foi salvo com sucesso!.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            copyFile(complementoFile.getUrl(), file.getInputstream());
        } catch (IOException e) {
        }

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
