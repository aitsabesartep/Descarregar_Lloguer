/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descarregar_imatges;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sansobarcelo
 */
public class Tractar_Resposta {

    private StringBuffer text;
    private String nom;
    private String nom_img;

    public Tractar_Resposta(StringBuffer s, String n) {
        text = s;
        nom = "Resultats/" + n;
        nom_img = nom + "/img";
        extreure_info();
    }

    public void extreure_info() {
        crearCarpeta();
        getTitol();
        getUbicacio();
        getImg();
        getCoordenades();
        getCaracteristiques();
    }

    private void getTitol() {
        System.out.println(getEntreTags("<title>Finca auf Mallorca: ", " in ", text.toString()));
    }

    private void getUbicacio() {
        System.out.println(getEntreTags(" in ", " günstig mieten", text.toString()));
    }

    private void getImg() {
        String text = this.text.toString();
        String i1;
        int id = 0;
        while (text.contains("<link rel=\"image_src\" href=\"")) {
            i1 = "";
            i1 = getEntreTags("<link rel=\"image_src\" href=\"", "\" />", text);
            guardarImatge(i1, id);
            text = eliminarText(i1 + "\" />", text);
            id++;
        }
    }

    private void getCoordenades() {
    }

    private void getCaracteristiques() {
    }

    private String getEntreTags(String tag1, String tag2, String t) {
        t = t.substring(t.indexOf(tag1) + tag1.length(), t.length());
        t = t.substring(0, t.indexOf(tag2));
        return t;
    }

    private String eliminarText(String tag, String text) {
        text = text.substring(text.indexOf(tag) + tag.length(), text.length());
        return text;
    }

    private void guardarImatge(String dir, int id) {
        try {
            URL url = new URL(dir);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(nom_img + "/img" + id + ".jpg"));

            for (int i; (i = in.read()) != -1;) {
                out.write(i);
            }
            in.close();
            out.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Tractar_Resposta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tractar_Resposta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearCarpeta() {
        File theDir = new File(nom);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
        }
        File theDir1 = new File(nom_img);
        // if the directory does not exist, create it
        if (!theDir1.exists()) {
            boolean result = false;
            try {
                theDir1.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
        }
    }

}
