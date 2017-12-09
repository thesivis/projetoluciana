/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.ufmt.ic.fata.dao.PastaDeComunicacaoDaoImp;
import br.ufmt.ic.fata.ent.PastaDeComunicacao;

/**
 *
 * @author vicentejr
 */
@ManagedBean
public class PastaComunicacaoController implements Serializable {

    private List<PastaDeComunicacao> list;
    private List<PastaDeComunicacao> pastaListFiltred;
    private PastaDeComunicacaoDaoImp pastaComunicacaoDao;

    public PastaComunicacaoController() {
        this.pastaComunicacaoDao = new PastaDeComunicacaoDaoImp();

    }

    @PostConstruct
    public void init() {
        this.list = pastaComunicacaoDao.list();
    }

    public void onRowDelete(PastaDeComunicacao pacienteSelect) {
        pastaComunicacaoDao.remove(pacienteSelect);
    }
    
    public List<PastaDeComunicacao> getList() {
        return list;
    }

    public void setList(List<PastaDeComunicacao> list) {
        this.list = list;
    }

    public List<PastaDeComunicacao> getPastaListFiltred() {
        return pastaListFiltred;
    }

    public void setPastaListFiltred(List<PastaDeComunicacao> pastaListFiltred) {
        this.pastaListFiltred = pastaListFiltred;
    }
    
}
