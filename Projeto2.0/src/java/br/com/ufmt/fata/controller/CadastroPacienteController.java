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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.UploadedFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vicentejr
 */
@ViewScoped
@ManagedBean
public class CadastroPacienteController implements Serializable {

    private final PastaDeComunicacaoDaoImp pastaComunicacaoDao;

    private List<Sujeito> sujeitoListAd;
    private List<Verbo> verboListAd;
    private List<Complemento> complementoListAd;
    private List<Sujeito> sujeitoListRem;
    private List<Verbo> verboListRem;
    private List<Complemento> complementoListRem;

    private UploadedFile file;
    private boolean add;

    public CadastroPacienteController() {
        this.pastaComunicacaoDao = new PastaDeComunicacaoDaoImp();
        this.complementoListRem = new ArrayList<>();
        this.verboListRem = new ArrayList<>();
        this.sujeitoListRem = new ArrayList<>();
        this.complementoListAd = new ArrayList<>();
        this.verboListAd = new ArrayList<>();
        this.sujeitoListAd = new ArrayList<>();

    }

    public void onNewPasta() {
        ActiveUserController.userActive = new PastaDeComunicacao();
        ActiveUserController.novaPasta = true;
    }
    
       public void gravar() {
        if (file != null && file.getSize() != 0) {
            fileUpload();
        } else if (ActiveUserController.userActive.getFotoUrl() == null) {
            ActiveUserController.userActive.setFotoUrl("user.png");
        }
        pastaComunicacaoDao.save(ActiveUserController.userActive);
        if(ActiveUserController.novaPasta){
            onClickAdicionar();
            ActiveUserController.novaPasta = false;
        }
    }

    public void onClickAdicionar() {
        if (!sujeitoListAd.isEmpty()) {
            //Laço para verificar se o sujeito já existe na pasta do paciente
            for (int i = 0; i < sujeitoListAd.size(); i++) {
                if (ActiveUserController.userActive.getSujeitos().contains(sujeitoListAd.get(i))) {
                    sujeitoListAd.remove(i);
                }
            }
            ActiveUserController.userActive.getSujeitos().addAll(sujeitoListAd);
        }
        if (!verboListAd.isEmpty()) {
            for (int i = 0; i < verboListAd.size(); i++) {
                if (ActiveUserController.userActive.getVerbos().contains(verboListAd.get(i))) {
                    verboListAd.remove(i);
                }
            }
            ActiveUserController.userActive.getVerbos().addAll(verboListAd);
        }
        if (!complementoListAd.isEmpty()) {
            for (int i = 0; i < complementoListAd.size(); i++) {
                if (ActiveUserController.userActive.getComplementos().contains(complementoListAd.get(i))) {
                    complementoListAd.remove(i);
                }
            }
            ActiveUserController.userActive.getComplementos().addAll(complementoListAd);
        }
//        pastaComunicacaoDao.save(ActiveUserController.userActive);
        this.add = false;
    }

    public void onClickRemover() {
        if (!sujeitoListRem.isEmpty()) {
            for (int i = 0; i < sujeitoListAd.size(); i++) {
                if (ActiveUserController.userActive.getSujeitos().contains(sujeitoListAd.get(i))) {
                    sujeitoListAd.remove(i);
                }
            }
            ActiveUserController.userActive.getSujeitos().removeAll(sujeitoListRem);
        }
        if (!verboListRem.isEmpty()) {
            for (int i = 0; i < verboListAd.size(); i++) {
                if (ActiveUserController.userActive.getVerbos().contains(verboListAd.get(i))) {
                    verboListAd.remove(i);
                }
            }
            ActiveUserController.userActive.getVerbos().removeAll(verboListRem);
        }
        if (!complementoListRem.isEmpty()) {
            for (int i = 0; i < complementoListAd.size(); i++) {
                if (ActiveUserController.userActive.getComplementos().contains(complementoListAd.get(i))) {
                    complementoListAd.remove(i);
                }
            }
           ActiveUserController.userActive.getComplementos().removeAll(complementoListRem);
        }
        pastaComunicacaoDao.save(ActiveUserController.userActive);
        this.add = false;
    }

    //Método chamados quando selecionar um checkbox referente a uma imagemm
    public void onAddSuj(Sujeito suj) {
        if (add && !sujeitoListAd.contains(suj)) {
            this.sujeitoListAd.add(suj);
        } else {
            this.sujeitoListAd.remove(suj);
        }
        this.add = false;
    }

    public void onAddVerb(Verbo verb) {
        if (add && !verboListAd.contains(verb)) {
            this.verboListAd.add(verb);
        } else {
            this.verboListAd.remove(verb);
        }
        this.add = false;
    }

    public void onAddComp(Complemento comp) {
        if (add && !complementoListAd.contains(comp)) {
            this.complementoListAd.add(comp);
        } else {
            this.complementoListAd.remove(comp);
        }
        this.add = false;
    }

    public void onRemSuj(Sujeito suj) {
        if (add) {
            this.sujeitoListRem.add(suj);
        } else {
            this.sujeitoListRem.remove(suj);
        }
        this.add = false;
    }

    public void onRemVerb(Verbo verb) {
        if (add) {
            this.verboListRem.add(verb);
        } else {
            this.verboListRem.remove(verb);
        }
        this.add = false;
    }

    public void onRemComp(Complemento comp) {
        if (add) {
            this.complementoListRem.add(comp);
        } else {
            this.complementoListRem.remove(comp);
        }
        this.add = false;
    }

    public void fileUpload() {
        ActiveUserController.userActive.setFotoUrl(removerAcentos(file.getFileName()));
        try {
            copyFile(ActiveUserController.userActive.getFotoUrl(), file.getInputstream());
        } catch (IOException e) {
        }

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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

}
