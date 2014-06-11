
package ohht.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

import ohht.domain.Tehtava;
import ohht.sovelluslogiikka.Peruskierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.domain.TilastojenKeraaja;

/**
 * Luokka toimii tapahtumankäsittelijänä.
 * Tapahtumana toimii "Submit"-napin painaus. Luokka käsittelee lisäksi tekstikentän syötteen
 * sekä hallitsee, mitä käyttöliittymän kolmessa tekstialueessa näytetään.
 */
class TapahtumienKuuntelija implements ActionListener {
    private JLabel ilmoituskentta;
    private JLabel tilastokentta;
    private JLabel tehtavakentta;
    private JTextField syottokentta;
    private JButton vastausnappi;
    private JButton uusiPeruskierrosnappi;
    
    private Peruskierros peruskierros;
    private Tehtava tehtava;
    private final Uusintakierros uusintakierros;
    private final TilastojenKeraaja tilastojenKeraaja;
    
    private final Random arpoja;
    private int syote;
    private boolean uusintaKaynnissa;
    
    public TapahtumienKuuntelija(JLabel ilmoituskentta, JLabel tilastokentta, JLabel tehtavakentta, JTextField syottokentta, JButton vastausnappi, JButton uusiPeruskierrosnappi, Peruskierros peruskierros) {
        this.ilmoituskentta = ilmoituskentta;
        this.tilastokentta = tilastokentta;
        this.tehtavakentta = tehtavakentta;
        this.syottokentta = syottokentta;
        this.vastausnappi = vastausnappi;
        this.uusiPeruskierrosnappi = uusiPeruskierrosnappi;
        
        this.peruskierros = peruskierros;
        this.tehtava = peruskierros.getTehtavat().get(0);
        this.uusintakierros = new Uusintakierros();
        this.tilastojenKeraaja = new TilastojenKeraaja();
        
        this.arpoja = new Random();
        this.syote = 0;
        this.uusintaKaynnissa = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // jos "New Round"-nappia painettiin
        if (ae.getSource()==uusiPeruskierrosnappi) {
            alustaUusiPeruskierros();
            return;
        }
        
        // jos syöte on numero, se tallentuu ja jatketaan
        try {
            syote = Integer.parseInt(syottokentta.getText());
        } catch (NumberFormatException e) {
            ilmoituskentta.setText("You're supposed to enter a number, duh.");
            syottokentta.setText("");
            return;
        }
        
        // käynnissä peruskierros
        if (!uusintaKaynnissa) {
           if (syote==tehtava.getVastaus()) {
                tilastojenKeraaja.lisaaVastaus();
           } else {
               tilastojenKeraaja.lisaaVaaraVastaus();
               uusintakierros.lisaaUusittavaksi(tehtava);
           }
           
           if (peruskierros.onkoKierroksenViimeinenTehtava(tehtava)) {
               if (loytyykoUusittavia()) {
                   uusintaKaynnissa = true;
                   tehtavakentta.setText(uusintakierros.getUusittavatTehtavat().get(0).toString());
               }
               uusiPeruskierrosnappi.setEnabled(true);
           }
           
           tehtava = seuraava(tehtava);
           
           
        }
        
        
        
        // tapahtuu joka tapauksessa
        paivitaTilastot();
        syottokentta.setText("");
    }
    
    private Tehtava seuraava(Tehtava tehtava) {
        int tehtavaNumero = peruskierros.getTehtavanNumero(tehtava);
        return peruskierros.getTehtavat().get(tehtavaNumero+1);
    }

    private void paivitaTilastot() {
        String tilastot = "Correct answers: "+tilastojenKeraaja.getOikeinVastatut()+"/"+tilastojenKeraaja.getVastatut()+"<html>\n</html>"
                        +"Completed rounds: "+tilastojenKeraaja.getPeruskierrokset()+" basic, "+tilastojenKeraaja.getUusintakierrokset()+" retrials.";
        tilastokentta.setText(tilastot);
    }

    private void alustaUusiPeruskierros() {
        peruskierros = new Peruskierros();
        uusintaKaynnissa = false;
        ilmoituskentta.setText("Here we go again; you know the drill.");
        uusiPeruskierrosnappi.setEnabled(false);
    }

    private boolean loytyykoUusittavia() {
        return (!uusintakierros.getUusittavatTehtavat().isEmpty());
    }
    
    private void aloitaUusintakierros() {
        ilmoituskentta.setText(uusintakierros.sopivaViesti());
        tehtavakentta.setText("uusinnan eka tehtävä");
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