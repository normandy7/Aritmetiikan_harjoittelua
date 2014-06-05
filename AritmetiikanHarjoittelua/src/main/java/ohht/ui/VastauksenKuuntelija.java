
package ohht.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import ohht.domain.Tehtava;
import ohht.sovelluslogiikka.Peruskierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.sovelluslogiikka.TilastojenKeraaja;

/**
 * Luokka toimii tapahtumankäsittelijänä.
 * Tapahtumana toimii "Submit"-napin painaus. Luokka käsittelee lisäksi tekstikentän syötteen
 * sekä hallitsee, mitä käyttöliittymän kolmessa tekstialueessa näytetään.
 */

class VastauksenKuuntelija implements ActionListener {
    private JLabel tekstikentta;
    private JLabel ilmoituskentta;
    private JLabel tehtavakentta;
    private JTextField syotekentta;
    private JButton vastausnappi;
    
    private Peruskierros peruskierros;
    private Uusintakierros uusintakierros;
    private TilastojenKeraaja tilastojenKeraaja;
    private Random arpoja;
    private int indeksi;
    private boolean onkoUusinta;
    
    public VastauksenKuuntelija(JLabel tekstikentta, JLabel ilmoituskentta, JLabel tehtavakentta, JTextField syotekentta, JButton vastausnappi, Peruskierros peruskierros, Uusintakierros uusintakierros, TilastojenKeraaja tilastojenKeraaja) {
        this.tekstikentta = tekstikentta;
        this.ilmoituskentta = ilmoituskentta;
        this.tehtavakentta = tehtavakentta;
        this.syotekentta = syotekentta;
        this.vastausnappi = vastausnappi;
        this.peruskierros = peruskierros;
        this.uusintakierros = uusintakierros;
        this.tilastojenKeraaja = tilastojenKeraaja;
        this.arpoja = new Random();
        this.indeksi = 0;
        this.onkoUusinta = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        int syote = lueLuku(syotekentta);
        
//        if (syote!=tehtava.getVastaus()) {
//            tilastojenKeraaja.lisaaVaaraVastaus();
//            uusintakierros.lisaaUusittavaksi(tehtava);
//        } else {
//            tilastojenKeraaja.lisaaOikeaVastaus();
//            peruskierros.lisaaOikeaVastaus();
//        }
        
        tarkistaIndeksi();
        
        Tehtava tehtava = haeTehtava();
        
            
//        if (uusitaan()) {
//            aloitaUusintakierros();
//            tilastojenKeraaja.lisaaUusintakierros();
//            tyhjennaUusittavatTehtavat();
//        } else {
//            ilmoituskentta.setText("Not bad.");
//        }
        
        tehtavakentta.setText((indeksi+1)+". "+tehtava);
    }
    
    private Tehtava haeTehtava() {
        if (!onkoUusinta) {
            return peruskierros.getTehtavat().get(indeksi);
        } else {
            return uusintakierros.getUusittavatTehtavat().get(indeksi);
        }
    }
    
    private int lueLuku(JTextField syote) {
        while (true) {
            try {
                int luku = Integer.parseInt(syote.getText());
                return luku;
            } catch (NumberFormatException e) {
                ilmoituskentta.setText("You're supposed to enter a number, duh.");
            }
        }
    }

    public void luoUusiPeruskierros() {
        peruskierros = new Peruskierros();
    }

    private void tarkistaIndeksi() {
        if (indeksi == 9) {
            nollaaIndeksi();
            
            if (joudutaankoUusimaan()) {
                onkoUusinta = true;
            }
            
        } else {
            indeksi++;
        }
    }
    
    private void nollaaIndeksi() {
        indeksi = 0;
    }

    private boolean joudutaankoUusimaan() {
        return (!uusintakierros.getUusittavatTehtavat().isEmpty());
    }
    
    private void tyhjennaUusittavatTehtavat() {
        uusintakierros.getUusittavatTehtavat().clear();
    }
    
    private void aloitaUusintakierros() {
        tekstikentta.setText(uusintakierros.sopivaViesti());
    }
    
    private String arvoFacepalmVastaus() {
        int luku = arpoja.nextInt(8);
        if (luku==0) {
            return "Really?";
        } else if (luku==1) {
            return "<facepalm>";
        } else if (luku==2) {
            return "Can you at least try?";
        } else if (luku==3) {
            return "NO.";
        } else if (luku==4) {
            return "How about-- no?";
        } else if (luku==5) {
            return "In a parallel universe, maybe.";
        } else if (luku==6) {
            return "Not even funny.";
        } else {
            return "What?";
        }
    }
}