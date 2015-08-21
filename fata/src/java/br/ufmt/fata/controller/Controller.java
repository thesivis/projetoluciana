/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author raphael
 */
public class Controller implements Serializable {

    protected final List<SelectItem> filterLockedOptions = new ArrayList<SelectItem>(Arrays.asList(new SelectItem("", "Selecione"), new SelectItem("Masculino", "Masculino"), new SelectItem("Feminino", "Feminino")));
    protected Part file;

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<SelectItem> getFilterLockedOptions() {
        return filterLockedOptions;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}
