/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descarregar_imatges;

/**
 *
 * @author sansobarcelo
 */
public class Descarregar_imatges {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GET g = new GET("https://www.exklusive-fincas-mallorca.de/objekt/bonaire-blanc.html");
        Tractar_Resposta t = new Tractar_Resposta(g.getPage(), "Bonaire");
    }

}
