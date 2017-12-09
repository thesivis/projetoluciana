/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import br.com.ufmt.fata.dao.ComplementoDaoImp;
import br.com.ufmt.fata.dao.SujeitoDaoImp;
import br.com.ufmt.fata.dao.VerboDaoImp;
import br.com.ufmt.fata.ent.Complemento;
import br.com.ufmt.fata.ent.Sujeito;
import br.com.ufmt.fata.ent.Verbo;
import com.darkprograms.speech.synthesiser.SynthesiserV2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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

    //Sintetizador de Voz
    private SynthesiserV2 sv;
    private static final String key = "AIzaSyByJC5bT6IZT2fosRQk6f_4wnnyTc3me6M";
    
    //Linha e coluna do sistema de varredura 
    private int row;
    private int col;

    
    //Frase que será montada
    private String strTextToSpeech;
    
    //Palavras Selecionadas
    private Sujeito selectSujeito;
    private Verbo selectVerbo;
    private List<Complemento> selectComplemento;
    private String selectTempo;

    //Objetos dao
    private final SujeitoDaoImp sujeitoController;
    private final VerboDaoImp verboController;
    private final ComplementoDaoImp complementoController;

    //Lista de Tempo Verbal
    private List<String> tempoList;

    //Lista de Palavras do Banco de dados
    List<Sujeito> sujeitoList;
    List<Verbo> verboList;
    List<Complemento> complementoList;
    private List<List<String>> listaPronome;

    //Diretório do Projeto
    public static String FATA_DIR;

    
    @ManagedProperty(value="#{activeUserController}")
    private ActiveUserController activeUserController;

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

    public PrincipalController() {
        this.selectComplemento = new ArrayList<>();
        this.sujeitoController = new SujeitoDaoImp();
        this.verboController = new VerboDaoImp();
        this.complementoController = new ComplementoDaoImp();
        this.tempoList = new ArrayList();
        this.listaPronome = new ArrayList<>();
        this.activeUserController = new ActiveUserController();
    }

    public String getFataDir() {
        return FATA_DIR;
    }

    @PostConstruct
    public void init() {
        sv = new SynthesiserV2(key);
        sujeitoList = sujeitoController.list();
        verboList = verboController.list();
        complementoList = complementoController.list();
        tempoList.add("Passado");
        tempoList.add("Presente");
        tempoList.add("Futuro");
    }
    
    public void onClickSelecionar(){
        try {
            if(activeUserController.userActive.getNome() == null || activeUserController.userActive == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pastaComunicacao.xhtml");
                System.out.println("Usuário já ativo");
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/prancha.xhtml");
                System.out.println("Usuário não ativo");
            }

        } catch (IOException ex) {
            Logger.getLogger(PastaComunicacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    /**
     * Método para criar arquivo de um objeto InpurStream e salvar em formato de
     * audio mp3.
     *
     * @param nomeArq Nome para o arquivo de audio.
     * @param sound Ojeto <b>InputStream</b> do audio.
     */
    public void fileUpload(String nomeArq, InputStream sound) {
        copyFile(nomeArq +"VEL"+activeUserController.userActive.getVelocidadeVoz()+".mp3", sound);
    }

    /**
     * Método que copia o arquivo para a pasta fata do servidor.
     *
     * @param fileName Nome para o arquivo que será salvo.
     * @param in Objeto Stream do arquivo.
     */
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

    /**
     * Método executado ao clicar na área da varredura, realizando o calculo das
     * posições selecionadas e atribuindo palavras selecionadas, caso seja
     * seleção já está no complemento, é verificado se foi selecionado a
     * primeira linha nas quais estão ações a serem executadas e não seleção de
     * palavras.
     */
    public void onClickPage() {
        strTextToSpeech = null;
        System.err.println("row:" + this.row + " col:" + this.col);
        if (this.row != 0 && this.col != 0) {
            int position = (this.row - 1) * 5 + this.col-1 ;
            if (selectSujeito == null && position < sujeitoList.size()) {
                this.selectSujeito = activeUserController.userActive.getSujeitos().get(position);
                System.out.println(this.selectSujeito.getPalavra());
            }else if (selectVerbo == null) {
                System.out.println("Verbos row:"+this.row);
                if (this.row == 1) {
                    if (this.col == 1) {
                        System.out.println("Falar");
                        falar();
                    }else if(this.col == 2){
                        System.out.println("Limpar");
                    }else{
                        System.out.println("Sair");
                    }
                } else{

                    /**
                     * O calculo da posição é realizado novamente levando em
                     * conta uma nova linha que possui funçõe a serem executadas
                     * no sistema de varredura.
                     */
                    position = (this.row - 2) * 5 + this.col - 1;
                    System.out.println("new position: "+position);
                    this.selectVerbo = activeUserController.userActive.getVerbos().get(position); 
                    System.out.println("Posição: "+position+" Max: "+activeUserController.userActive.getVerbos().size());
                    System.out.println("Verb: "+activeUserController.userActive.getVerbos().get(position).getPalavra());
                    
                }
            } else if (selectTempo == null && position < tempoList.size()) {
                this.selectTempo = tempoList.get(position);
                System.out.println(this.selectTempo);
            } else {
                if (this.row == 1) {
                    if (this.col == 1) {
                        falar();
                        System.out.println("Falar");
                    }else if(this.col == 2){
                        System.out.println("Limpar");
                    }else{
                        System.out.println("Sair");
                    }
                } else{

                    /**
                     * O calculo da posição é realizado novamente levando em
                     * conta uma nova linha que possui funçõe a serem executadas
                     * no sistema de varredura.
                     */
                    position = (this.row - 2) * 5 + this.col - 1;
                    System.out.println("Posição: "+position+" Max: "+activeUserController.userActive.getComplementos().size());
                    System.out.println("Comp: "+activeUserController.userActive.getComplementos().get(position).getPalavra());
                    this.selectComplemento.add(activeUserController.userActive.getComplementos().get(position));
                    
                }
            }
        }
    }

    /**
     * Método para montar frase que será montada.
     *
     * @return String - Frase
     */
    public String frase() {
        String fraseFala = selectSujeito.getPalavra();
        if(selectVerbo != null){
            if ("Primeira".equals(selectSujeito.getPronome())) {
                if ("Passado".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPasprimpessoa();
                }
                if ("Presente".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPreprimpessoa();
                }
                if ("Futuro".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getFutprimpessoa();
                }
            } else if ("Segunda".equals(selectSujeito.getPronome())) {
                if ("Passado".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPassegpessoa();
                }
                if ("Presente".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPresegpessoa();
                }
                if ("Futuro".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getFutsegpessoa();
                }
            } else {
                if ("Passado".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPastercpessoa();
                }
                if ("Presente".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getPretercpessoa();
                }
                if ("Futuro".equals(this.selectTempo)) {
                    fraseFala = fraseFala + " " + this.selectVerbo.getFuttercpessoa();
                }
            }
        }
        return fraseFala;
    }

    /**
     * Método que verifica se já existe o arquivo de audio referente a frase,
     * caso não haja, será chamado o método da API do Google que sintetiza o a
     * String em arquivo de audio, salvando-o logo em seguida.
     */
    public void falar() {
        try {
            if (selectSujeito != null) {
                    this.strTextToSpeech = frase();
                if (selectComplemento.size() > 0) {
                    for (int i = 0; i < selectComplemento.size(); i++) {
                        this.strTextToSpeech += " " + selectComplemento.get(i).getPalavra();
                    }

                }
                
                if (!ArquivoController.existeArquivo(this.strTextToSpeech+"VEL"+activeUserController.userActive.getVelocidadeVoz()+".mp3")) {
                    System.out.println(this.strTextToSpeech);
                    sv.setSpeed(activeUserController.userActive.getVelocidadeVoz()*2/100.0);
                    fileUpload(this.strTextToSpeech, sv.getMP3Data(this.strTextToSpeech));         
                } else {
                    System.out.println(this.strTextToSpeech+"VEL"+activeUserController.userActive.getVelocidadeVoz()+" Já existe!");
                }

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

    /**
     * Método para trantar String removendo acentos.
     *
     * @param str String - String a ser tratada.
     * @return String Não contendo acentos e caracteres especiais.
     */
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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

    public List<List<String>> getListaPronome() {
        return listaPronome;
    }

    public void setListaPronome(List<List<String>> listaPronome) {
        this.listaPronome = listaPronome;
    }

    public String getSelectTempo() {
        return selectTempo;
    }

    public void setSelectTempo(String selectTempo) {
        this.selectTempo = selectTempo;
    }

    public List<String> getTempoList() {
        return tempoList;
    }

    public void setTempoList(List<String> tempoList) {
        this.tempoList = tempoList;
    }

    public List<Complemento> getSelectComplemento() {
        return selectComplemento;
    }

    public void setSelectComplemento(List<Complemento> selectComplemento) {
        this.selectComplemento = selectComplemento;
    }

    public String getTextToSpeech() {
        return this.strTextToSpeech;
    }

    public void setTextToSpeech(String textToSpeech) {
        this.strTextToSpeech = textToSpeech;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ActiveUserController getActiveUserController() {
        return activeUserController;
    }

    public void setActiveUserController(ActiveUserController activeUserController) {
        this.activeUserController = activeUserController;
    }
  
}
