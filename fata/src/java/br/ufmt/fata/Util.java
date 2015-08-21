/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata;

import br.ufmt.fata.entities.Entity;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author raphael
 */
@ManagedBean(name = "util")
public class Util {

    public static String FATA_DIR;

    static {
        URL location = Util.class.getProtectionDomain().getCodeSource().getLocation();
//        FATA_DIR = location.getFile().replace("/WEB-INF/classes", "/upload");

        FATA_DIR = System.getenv("FATA_DIR");
        if (FATA_DIR == null) {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                FATA_DIR = "C:\\ProgramData\\Fata";
            } else {
                FATA_DIR = "/opt/fata";
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

    @PersistenceUnit(unitName = "fataPU")
    private EntityManagerFactory emf;

    @Produces
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void dispose(@Disposes EntityManager em) {
        em.close();
    }

    public static void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            File f = new File(Util.FATA_DIR + fileName);
//            System.out.println("Path: " + f.getPath());
//            System.out.println(System.getProperty("jboss.server.data.dir"));
            OutputStream out = new FileOutputStream(f);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

//            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String verifyImage(Entity item) {
        File image = new File(Util.FATA_DIR + item.getUrl());
        if (image.exists()) {
            return getRequest() + item.getUrl();
        }

        return "upload/semfoto.jpg";
    }

    public static void deleteFile(Entity item) {
        File image = new File(Util.FATA_DIR + item.getUrl());
        if (image.exists()) {
            image.delete();
        }
    }

    public static String falar(String fala) {
        String name = fala.replaceAll("[ ]+", "");
        name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        File targetFile = new File(Util.FATA_DIR + "/" + name + ".wav");
        if (!targetFile.exists()) {
            InputStream sound = null;
            try {
                Audio audio = Audio.getInstance();
                sound = audio.getAudio(fala, Language.PORTUGUESE);

                OutputStream outStream = new FileOutputStream(targetFile);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = sound.read(bytes)) != -1) {
                    outStream.write(bytes, 0, read);
                }

                outStream.close();

//            audio.play(sound);
            } catch (IOException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JavaLayerException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    sound.close();
                } catch (IOException ex) {
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        name = getRequest() + "/" + name + ".wav";
        return name;
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
}
