/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import br.com.ufmt.fata.dao.ComplementoDaoImp;
import br.com.ufmt.fata.dao.SujeitoDaoImp;
import br.com.ufmt.fata.dao.VerboDaoImp;
import com.gtranslate.Audio;
import br.com.ufmt.fata.ent.Complemento;
import br.com.ufmt.fata.ent.Sujeito;
import br.com.ufmt.fata.ent.Verbo;
import com.gtranslate.Language;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vicentejr
 */
@ManagedBean
@ViewScoped
public class PrincipalController implements Serializable {

    Sujeito selectSujeito;
    Verbo selectVerbo;
    Complemento selectComplemento;
    SujeitoDaoImp sujeitoController = new SujeitoDaoImp();
    VerboDaoImp verboController = new VerboDaoImp();
    ComplementoDaoImp complementoController = new ComplementoDaoImp();
    List<Sujeito> sujeitoList;
    List<Verbo> verboList;
    List<Complemento> complementoList;
    private List<String> acaoBotao = new ArrayList();
    InputStream sound;
    private String nomeAudio;
    

    private List<List<String>> listaPronome = new ArrayList<>();

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
        sujeitoList = sujeitoController.list();
        verboList = verboController.list();
        complementoList = complementoController.list();
        acaoBotao.add("Play");
        acaoBotao.add("Limpar");
    }

    public void fileUpload(String nomeArq) {

        copyFile(nomeArq + ".mp3", sound);

    }

    public static void copyFile(String fileName, InputStream in) {
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
    
    public void  onClickSuj(Sujeito suj){
        this.selectSujeito = suj;
        falar();
        System.out.println("Sujeito selecionado");
    }
    public void  onClickVerb(Verbo verb){
        this.selectVerbo = verb;
        falar();
        System.out.println("Verbo selecionado");
    }
    public void  onClickComp(Complemento comp){
        this.selectComplemento = comp;
        falar();
        System.out.println("Complemento selecionado");
    }
    public String frase(){
        String fraseFala = selectSujeito.getPalavra();
        if(selectSujeito.getSujeitoId() == 1 ||selectSujeito.getSujeitoId() == 2 || selectSujeito.getSujeitoId() == 3){
            if("Passado".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPasprimpessoa();
            }else if("Presente".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPreprimpessoa();
            }else{
                fraseFala = fraseFala+" "+selectVerbo.getFutprimpessoa();
            }
        
        }else if(selectSujeito.getSujeitoId() == 4 ||selectSujeito.getSujeitoId() == 5 || selectSujeito.getSujeitoId() == 6){
            if("Passado".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPassegpessoa();
            }else if("Presente".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPresegpessoa();
            }else{
                fraseFala = fraseFala+" "+selectVerbo.getFutsegpessoa();
            }
        }else{
            if("Passado".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPastercpessoa();
            }else if("Presente".equals(selectSujeito.getTempo())){
                fraseFala = fraseFala+" "+selectVerbo.getPretercpessoa();
            }else{
                fraseFala = fraseFala+" "+selectVerbo.getFuttercpessoa();
            }
        }
        return fraseFala;
    }
    
    public void falar() {
        Audio audio = Audio.getInstance();
        try {
            if (selectSujeito != null && selectVerbo != null) {
                if (selectComplemento != null) {
                    nomeAudio = frase()+ " " + selectComplemento.getPalavra();
                } else {
                    nomeAudio = frase();
                }
                sound = audio.getAudio(nomeAudio + "&client=", Language.PORTUGUESE);
                nomeAudio = removerAcentos(nomeAudio);
                System.out.println(nomeAudio);
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

    public Sujeito getSelectSujeito() {
        return selectSujeito;
    }

    public void setSelectSujeito(Sujeito selectSujeito) {
        this.selectSujeito = selectSujeito;
    }

    public Verbo getSelectVerbo() {
        return selectVerbo;
    }

    public void setSelectVerbo(Verbo selectVerbo) {
        this.selectVerbo = selectVerbo;
    }

    public Complemento getSelectComplemento() {
        return selectComplemento;
    }

    public void setSelectComplemento(Complemento selectComplemento) {
        this.selectComplemento = selectComplemento;
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
    
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }   

    public List<List<String>> getListaPronome() {
        return listaPronome;
    }

    public void setListaPronome(List<List<String>> listaPronome) {
        this.listaPronome = listaPronome;
    }

    public String getNomeAudio() {
        return nomeAudio;
    }

    public void setNomeAudio(String nomeAudio) {
        this.nomeAudio = nomeAudio;
    }

    public List<String> getAcaoBotao() {
        return acaoBotao;
    }

    public void setAcaoBotao(List<String> acaoBotao) {
        this.acaoBotao = acaoBotao;
    }
}
