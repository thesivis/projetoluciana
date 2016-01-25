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
import javax.faces.bean.SessionScoped;
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
@ManagedBean
@SessionScoped
public class CadastroPacienteController implements Serializable {

    private final PastaDeComunicacaoDaoImp pastaComunicacaoDao;

    private List<Sujeito> sujeitoListAd;
    private List<Verbo> verboListAd;
    private List<Complemento> complementoListAd;
    private List<Sujeito> sujeitoListRem;
    private List<Verbo> verboListRem;
    private List<Complemento> complementoListRem;

    protected static PastaDeComunicacao pastaFile;
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
        CadastroPacienteController.pastaFile = new PastaDeComunicacao();

    }

    public void onNewPasta() {
        CadastroPacienteController.pastaFile = new PastaDeComunicacao();
    }

    public void onClickAdicionar() {
        if (!sujeitoListAd.isEmpty()) {
            //Laço para verificar se o sujeito já existe na pasta do paciente
            for (int i = 0; i < sujeitoListAd.size(); i++) {
                if (pastaFile.getSujeitos().contains(sujeitoListAd.get(i))) {
                    sujeitoListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getSujeitos().addAll(sujeitoListAd);
        }
        if (!verboListAd.isEmpty()) {
            for (int i = 0; i < verboListAd.size(); i++) {
                if (pastaFile.getVerbos().contains(verboListAd.get(i))) {
                    verboListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getVerbos().addAll(verboListAd);
        }
        if (!complementoListAd.isEmpty()) {
            for (int i = 0; i < complementoListAd.size(); i++) {
                if (pastaFile.getComplementos().contains(complementoListAd.get(i))) {
                    complementoListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getComplementos().addAll(complementoListAd);
        }

        this.add = false;
    }

    public void onClickRemover() {
        if (!sujeitoListRem.isEmpty()) {
            for (int i = 0; i < sujeitoListAd.size(); i++) {
                if (pastaFile.getSujeitos().contains(sujeitoListAd.get(i))) {
                    sujeitoListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getSujeitos().removeAll(sujeitoListRem);
        }
        if (!verboListRem.isEmpty()) {
            for (int i = 0; i < verboListAd.size(); i++) {
                if (pastaFile.getVerbos().contains(verboListAd.get(i))) {
                    verboListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getVerbos().removeAll(verboListRem);
        }
        if (!complementoListRem.isEmpty()) {
            for (int i = 0; i < complementoListAd.size(); i++) {
                if (pastaFile.getComplementos().contains(complementoListAd.get(i))) {
                    complementoListAd.remove(i);
                }
            }
            CadastroPacienteController.pastaFile.getComplementos().removeAll(complementoListRem);
        }

        this.add = false;
    }

    //Método chamados quando selecionar um checkbox referente a uma imagemm
    public void onAddSuj(Sujeito suj) {
        if (add && !sujeitoListAd.contains(suj)) {
            this.sujeitoListAd.add(suj);
        } else {
            this.sujeitoListAd.remove(suj);
        }
    }

    public void onAddVerb(Verbo verb) {
        if (add && !verboListAd.contains(verb)) {
            this.verboListAd.add(verb);
        } else {
            this.verboListAd.remove(verb);
        }
    }

    public void onAddComp(Complemento comp) {
        if (add && !complementoListAd.contains(comp)) {
            this.complementoListAd.add(comp);
        } else {
            this.complementoListAd.remove(comp);
        }
    }

    public void onRemSuj(Sujeito suj) {
        if (add) {
            this.sujeitoListRem.add(suj);
        } else {
            this.sujeitoListRem.remove(suj);
        }
    }

    public void onRemVerb(Verbo verb) {
        if (add) {
            this.verboListRem.add(verb);
        } else {
            this.verboListRem.remove(verb);
        }
    }

    public void onRemComp(Complemento comp) {
        if (add) {
            this.complementoListRem.add(comp);
        } else {
            this.complementoListRem.remove(comp);
        }
    }

    public void gravar() {
        if (file.getSize() != 0) {
            fileUpload();
        } else if (pastaFile.getFotoUrl() == null) {
            pastaFile.setFotoUrl("user.png");
        }
        pastaComunicacaoDao.save(pastaFile);
        ActiveUserController.userActive = pastaFile;
        this.add = false;
    }

    public void fileUpload() {
        pastaFile.setFotoUrl(removerAcentos(file.getFileName()));
        try {
            copyFile(pastaFile.getFotoUrl(), file.getInputstream());
        } catch (IOException e) {
        }

    }

    public PastaDeComunicacao getPastaFile() {
        return pastaFile;
    }

    public void setPastaFile(PastaDeComunicacao pastaFile) {
        CadastroPacienteController.pastaFile = pastaFile;
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
