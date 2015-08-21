/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import com.gtranslate.Audio;
import br.com.ufmt.fata.dao.JDBCComplementoDAO;
import br.com.ufmt.fata.dao.JDBCSujeitoDAO;
import br.com.ufmt.fata.dao.JDBCVerboDAO;
import br.com.ufmt.fata.obj.Complemento;
import br.com.ufmt.fata.obj.Sujeito;
import br.com.ufmt.fata.obj.Verbo;
import com.gtranslate.Language;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.Normalizer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author vicentejr
 */
@ManagedBean
@ViewScoped
public class PrincipalController implements Serializable {

    Sujeito objDropedSuj;
    Verbo objDropedVerb;
    Complemento objDropedComp;
    JDBCSujeitoDAO sujeitoController = new JDBCSujeitoDAO();
    JDBCVerboDAO verboController = new JDBCVerboDAO();
    JDBCComplementoDAO complementoController = new JDBCComplementoDAO();
    List<Sujeito> sujeitoList;
    List<Verbo> verboList;
    List<Complemento> complementoList;
    InputStream sound;
    String nomeAudio;

    public static String FATA_DIR;

    static {
        FATA_DIR = System.getenv("FATA_DIR");
        if (FATA_DIR == null) {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                FATA_DIR = "C:\\ProgramData\\Fata\\";
            } else {
                FATA_DIR = "/opt/fata/";
            }
            File dir = new File(FATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    public String getFataDir() {
        return FATA_DIR;
    }

    @PostConstruct
    public void init() {
        sujeitoList = sujeitoController.listar();
        verboList = verboController.listar();
        complementoList = complementoController.listar();
    }

    public void fileUpload(String nomeArq) {

        copyFile(nomeArq + ".mp3", sound);

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            System.out.println(fileName);
            FileOutputStream out = new FileOutputStream(new File(FATA_DIR + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("Arquivo Criado!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onDropSuj(DragDropEvent event) {
        objDropedSuj = ((Sujeito) event.getData());
        falar();
    }

    public void onDropVerb(DragDropEvent event) {
        objDropedVerb = ((Verbo) event.getData());
        falar();
    }

    public void onDropComp(DragDropEvent event) {
        objDropedComp = ((Complemento) event.getData());
        falar();
    }

    public Sujeito getObjDropedSuj() {
        return objDropedSuj;
    }

    public void setObjDropedSuj(Sujeito objDropedSuj) {
        this.objDropedSuj = objDropedSuj;
    }

    public Verbo getObjDropedVerb() {
        return objDropedVerb;
    }

    public void setObjDropedVerb(Verbo objDropedVerb) {
        this.objDropedVerb = objDropedVerb;
    }

    public Complemento getObjDropedComp() {
        return objDropedComp;
    }

    public void setObjDropedComp(Complemento objDropedComp) {
        this.objDropedComp = objDropedComp;
    }

    public List<Sujeito> getSujeitoList() {
        return sujeitoList;
    }

    public void setSujeitoList(List<Sujeito> sujeitoList) {
        this.sujeitoList = sujeitoList;
    }

    public List<Verbo> getVerboList() {
        return verboList;
    }

    public void setVerboList(List<Verbo> verboList) {
        this.verboList = verboList;
    }

    public List<Complemento> getComplementoList() {
        return complementoList;
    }

    public void setComplementoList(List<Complemento> complementoList) {
        this.complementoList = complementoList;
    }

    public InputStream getSound() {
        return sound;
    }

    public void setSound(InputStream sound) {
        this.sound = sound;
    }

    public String getNomeAudio() {
        return nomeAudio;
    }

    public void setNomeAudio(String nomeAudio) {
        this.nomeAudio = nomeAudio;
    }

    public void falar() {
        Audio audio = Audio.getInstance();
        try {
            if (objDropedSuj != null && objDropedVerb != null) {
                if (objDropedComp != null) {
                    nomeAudio = objDropedSuj.getPalavra() + " " + objDropedVerb.getPalavra() + " " + objDropedComp.getPalavra();
                } else {
                    nomeAudio = objDropedSuj.getPalavra() + " " + objDropedVerb.getPalavra();
                }
                System.out.println(nomeAudio);
                sound = audio.getAudio(nomeAudio + "&client=", Language.PORTUGUESE);

                nomeAudio = nomeAudio.replaceAll("[ ]+", "");
                nomeAudio = Normalizer.normalize(nomeAudio, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                fileUpload(nomeAudio);

            }
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getRequest() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String path = "";
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            path = req.getRequestURL().toString().replace(req.getRequestURI(), "");
        }
        return path + "/fataimg";
    }

    public String getPath() {
        return getRequest() + "/";
    }
}
