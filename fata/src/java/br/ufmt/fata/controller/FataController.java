/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.controller;

import br.ufmt.fata.Util;
import br.ufmt.fata.dao.ComplementoDAO;
import br.ufmt.fata.dao.SujeitoDAO;
import br.ufmt.fata.dao.VerboDAO;
import br.ufmt.fata.entities.Complemento;
import br.ufmt.fata.entities.Entity;
import br.ufmt.fata.entities.Sujeito;
import br.ufmt.fata.entities.Verbo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author raphael
 */
@ManagedBean(name = "fataController")
@ViewScoped
public class FataController implements Serializable {

    @Inject
    private SujeitoDAO sujeitoDAO;
    @Inject
    private ComplementoDAO complementoDAO;
    @Inject
    private VerboDAO verboDAO;

    private ListDataModel<Entity> lista;
    private String legenda;

    private Sujeito sujeito;
    private Verbo verbo;
    private Complemento complemento;
    private String sound;

    public String selecionar(int selected) {
        sound = null;
        List list = null;
        legenda = null;
        if (selected == 1) {
            list = sujeitoDAO.select("palavra");
            legenda = "Sujeito";
        } else if (selected == 3) {
            list = complementoDAO.select("palavra");
            legenda = "Complemento";
        } else if (selected == 2) {
            list = verboDAO.select("palavra");
            legenda = "Verbo";
        }
        lista = new ListDataModel(list);
        return "";
    }

    public ListDataModel<Entity> getLista() {
        return lista;
    }

    public void setLista(ListDataModel<Entity> lista) {
        this.lista = lista;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public void onImageDropSujeito(DragDropEvent ddEvent) {
        if (legenda != null && legenda.equals("Sujeito")) {
            sujeito = (Sujeito) ddEvent.getData();
        } else {
            FacesMessage msg = new FacesMessage("Sujeito deve estar selecionado!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onImageDropVerbo(DragDropEvent ddEvent) {
        if (legenda != null && legenda.equals("Verbo")) {
            verbo = (Verbo) ddEvent.getData();
        } else {
            FacesMessage msg = new FacesMessage("Verbo deve estar selecionado!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onImageDropComplemento(DragDropEvent ddEvent) {
        if (legenda != null && legenda.equals("Complemento")) {
            complemento = (Complemento) ddEvent.getData();
        } else {
            FacesMessage msg = new FacesMessage("Complemento deve estar selecionado!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Sujeito getSujeito() {
        return sujeito;
    }

    public void setSujeito(Sujeito sujeito) {
        this.sujeito = sujeito;
    }

    public Verbo getVerbo() {
        return verbo;
    }

    public void setVerbo(Verbo verbo) {
        this.verbo = verbo;
    }

    public Complemento getComplemento() {
        return complemento;
    }

    public void setComplemento(Complemento complemento) {
        this.complemento = complemento;
    }

    public String falar() {
        String frase = sujeito.getPalavra() + " " + verbo.getPalavra();

        if (complemento != null) {
            frase = frase + " " + complemento.getPalavra();
        }
        sound = Util.falar(frase);
        return "";
    }

    public String getSound() {
        return sound;
    }

    public String imageCSSSujeito() {
        if (legenda != null && legenda.equals("Sujeito")) {
            return "ui-icon-sujeito";
        }
        return "ui-icon-sujeitodeselected";
    }

    public String imageCSSVerbo() {
        if (legenda != null && legenda.equals("Verbo")) {
            return "ui-icon-verbo";
        }
        return "ui-icon-verbodeselected";
    }

    public String imageCSSComplemento() {
        if (legenda != null && legenda.equals("Complemento")) {
            return "ui-icon-complemento";
        }
        return "ui-icon-complementodeselected";
    }
}
