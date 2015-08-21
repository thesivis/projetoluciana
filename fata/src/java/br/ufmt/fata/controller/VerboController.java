/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.controller;

import br.ufmt.fata.Util;
import br.ufmt.fata.dao.VerboDAO;
import br.ufmt.fata.entities.Verbo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author raphael
 */
@ManagedBean(name = "verboController")
public class VerboController extends Controller {

    @Inject
    private VerboDAO dao;
    @Inject
    private Verbo entity;

    private ListDataModel<Verbo> list;

    @PostConstruct
    public void init() {
        list = new ListDataModel(dao.select("palavra"));
    }

    public String salvar() {
        try {
            dao.salvar(entity);
            if (file != null) {
//                System.out.println("File:" + file.getSubmittedFileName());
                String[] vet = file.getSubmittedFileName().split("\\.");
                String url = "/verbo" + entity.getId() + "." + vet[vet.length - 1];
                Util.copyFile(url, file.getInputStream());
                entity.setUrl(url);
                dao.salvar(entity);
            }
            entity = null;
            file = null;
            init();

            FacesMessage msg = new FacesMessage("Salvo com Sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            FacesMessage msg = new FacesMessage("Falha ao salvar arquivo!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(VerboController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Verbo getEntity() {
        return entity;
    }

    public void setEntity(Verbo entity) {
        this.entity = entity;
    }

    public ListDataModel<Verbo> getList() {
        return list;
    }

    public String delete(Verbo remove) {
        Util.deleteFile(remove);
        dao.delete(remove.getId());
        init();
        return "";
    }

    public void onRowEdit(Verbo novo) {
        if (dao.salvar(novo)) {
            FacesMessage msg = new FacesMessage("Editado com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
