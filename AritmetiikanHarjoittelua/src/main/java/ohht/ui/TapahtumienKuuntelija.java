
package ohht.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import ohht.domain.Tehtava;
import ohht.sovelluslogiikka.Peruskierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.domain.TilastojenKeraaja;
import ohht.sovelluslogiikka.Kierros;

/**
 * Luokka toimii tapahtumankäsittelijänä.
 * Tapahtumana toimii "Submit"-napin painaus. Luokka käsittelee lisäksi tekstikentän syötteen
 * sekä hallitsee, mitä käyttöliittymän kolmessa tekstialueessa näytetään.
 */
class TapahtumienKuuntelija implements ActionListener {
    private final JLabel tilastokentta;
    private final JLabel ilmoituskentta;
    private final JLabel tehtavakentta;
    private final JTextField syottokentta;
    private final JButton vastausnappi;
    private final JButton uusiPeruskierrosnappi;
    
    private Peruskierros peruskierros;
    private Tehtava tehtava;
    private final Uusintakierros uusintakierros;
    private final TilastojenKeraaja tilastojenKeraaja;
    
    private int syote;
    private boolean uusintaKaynnissa;
    
    public TapahtumienKuuntelija(JLabel tilastokentta, JLabel ilmoituskentta, JLabel tehtavakentta, JTextField syottokentta, JButton vastausnappi, JButton uusiPeruskierrosnappi, Peruskierros peruskierros) {
        this.tilastokentta = tilastokentta;
        this.ilmoituskentta = ilmoituskentta;
        this.tehtavakentta = tehtavakentta;
        this.syottokentta = syottokentta;
        this.vastausnappi = vastausnappi;
        this.uusiPeruskierrosnappi = uusiPeruskierrosnappi;
        
        this.peruskierros = peruskierros;
        this.tehtava = peruskierros.getTehtavat().get(0);
        this.uusintakierros = new Uusintakierros();
        this.tilastojenKeraaja = new TilastojenKeraaja();
        
        this.syote = 0;
        this.uusintaKaynnissa = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==uusiPeruskierrosnappi) {
            alustaUusiPeruskierros();
        }
        
        if (ae.getSource()==vastausnappi) {
            try {
                syote = Integer.parseInt(syottokentta.getText());
            } catch (NumberFormatException e) {
                ilmoituskentta.setText("You're supposed to enter a number, duh.");
                syottokentta.setText("");
                return;
            }
        }
        
        syottokentta.setText("");
        
        if (!uusintaKaynnissa) {
            tarkistaPeruskierroksenVastaus();
            
            if (peruskierros.onkoKierroksenViimeinenTehtava(tehtava)) {
                tilastojenKeraaja.lisaaPeruskierros();
                tilastojenKeraaja.nollaaKierroksenTulos();
                
                if (loytyykoUusittavia()) {
                    aloitaUusintakierros();
                } else {
                    ilmoituskentta.setText("All correct, eh? Click 'New Round' and see if your luck lasts.");
                    loppunapit();
                }
                
            } else {
                tehtava = seuraavaTehtava(peruskierros);
                tehtavakentta.setText(haeTehtavanNumero(peruskierros, tehtava)+". "+tehtava.toString());
            }
        } else {
            if (!tasmaakoUusittuVastaus()) {
                return;
            }
            
            if (uusintakierros.onkoKierroksenViimeinenTehtava(tehtava)) {
                tilastojenKeraaja.lisaaUusintakierros();
                ilmoituskentta.setText("There we go. Press 'New Round' if you want to have another go.");
                loppunapit();
            } else {
                tehtava = seuraavaTehtava(uusintakierros);
                tehtavakentta.setText(haeTehtavanNumero(uusintakierros, tehtava)+". "+tehtava.toString());
            }
        }
        
        paivitaTilastot();
    }
    
    private int haeTehtavanNumero(Kierros kierros, Tehtava tehtava) {
        return kierros.getTehtavanIndeksi(tehtava)+1;
    }
    
    private Tehtava ensimmainenTehtava(Kierros kierros) {
        return kierros.getTehtavat().get(0);
    }
    
    private Tehtava seuraavaTehtava(Kierros kierros) {
        int tehtavaNumero = kierros.getTehtavanIndeksi(tehtava);
        return kierros.getTehtavat().get(tehtavaNumero+1);
    }

    private void paivitaTilastot() {
        String tilastot = "Correct answers: "+tilastojenKeraaja.getOikeinVastatut()+"/"+tilastojenKeraaja.getVastatut()+". "
                        +"Completed rounds: "+tilastojenKeraaja.getPeruskierrokset()+" basic, "+tilastojenKeraaja.getUusintakierrokset()+" retrials.";
        tilastokentta.setText(tilastot);
    }

    private void alustaUusiPeruskierros() {
        uusintaKaynnissa = false;
        peruskierros = new Peruskierros();
        tehtava = ensimmainenTehtava(peruskierros);
        
        ilmoituskentta.setText("Here we go again; you know the drill.");
        tehtavakentta.setText("1. "+tehtava.toString());
        vastausnappi.setEnabled(true);
        uusiPeruskierrosnappi.setEnabled(false);
    }

    private boolean loytyykoUusittavia() {
        return (!uusintakierros.getTehtavat().isEmpty());
    }
    
    private void aloitaUusintakierros() {
        uusintaKaynnissa = true;
        tehtava = ensimmainenTehtava(uusintakierros);
        ilmoituskentta.setText(uusintakierros.uusittavienMaarastaRiippuvaViesti());
        tehtavakentta.setText("1. "+ensimmainenTehtava(uusintakierros).toString());
    }

    private void loppunapit() {
        uusiPeruskierrosnappi.setEnabled(true);
        vastausnappi.setEnabled(false);
    }

    private void tarkistaPeruskierroksenVastaus() {
        if (syote==tehtava.getVastaus()) {
            tilastojenKeraaja.lisaaVastaus();
        } else {
            tilastojenKeraaja.lisaaVaaraVastaus();
            uusintakierros.lisaaUusittavaksi(tehtava);
        }
    }

    private boolean tasmaakoUusittuVastaus() {
        if (syote!=tehtava.getVastaus()) {
            ilmoituskentta.setText(uusintakierros.arvoFacepalmVastaus());
            return false;
        }
        return true;
    }
    
}