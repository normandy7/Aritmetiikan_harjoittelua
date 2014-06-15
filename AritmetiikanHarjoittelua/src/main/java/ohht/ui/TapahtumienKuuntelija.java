
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
 * Tapahtumana toimii joko "Submit" tai "New Round" -napin painaus.
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
    
    /**
     * Luokan konstruktori saa parametrikseen graafisen käyttöliittymän komponenttien
     * lisäksi Peruskierros- sekä TilastojenKeraaja-oliot. Ensimmäiseksi käsiteltäväksi
     * Tehtava-olioksi asetetaan aluksi parametrina syötetyn peruskierroksen ensimmäinen
     * tehtävä.
     * 
     * Boolean -oliomuuttuja uusintaKaynnissa kuvaa, onko kyseessä on uusinta- vai
     * peruskierros, mikä säätelee osittain ohjelman toimintaa.
     * 
     * @param tilastokentta Tekstikenttä, jossa tilastot näkyvät
     * @param ilmoituskentta Tekstikenttä, johon ohjelman ilmoitukset ilmestyvät
     * @param tehtavakentta Tekstikenttä, johon ratkaistava tehtävä ilmestyy
     * @param syottokentta Kenttä, johon käyttäjän syöte tulee
     * @param vastausnappi Nappi, jota käytetään syötteen lähetykseen
     * @param uusiPeruskierrosnappi Nappi, jolla aloitetaan uusi peruskierros
     * @param peruskierros Harjoittelusession ensimmäinen peruskierros
     */
    public TapahtumienKuuntelija(JLabel tilastokentta, JLabel ilmoituskentta, JLabel tehtavakentta, JTextField syottokentta, JButton vastausnappi, JButton uusiPeruskierrosnappi, Peruskierros peruskierros, TilastojenKeraaja tilastojenKeraaja) {
        this.tilastokentta = tilastokentta;
        this.ilmoituskentta = ilmoituskentta;
        this.tehtavakentta = tehtavakentta;
        this.syottokentta = syottokentta;
        this.vastausnappi = vastausnappi;
        this.uusiPeruskierrosnappi = uusiPeruskierrosnappi;
        
        this.peruskierros = peruskierros;
        this.tilastojenKeraaja = tilastojenKeraaja;
        this.tehtava = peruskierros.getTehtavat().get(0);
        this.uusintakierros = new Uusintakierros();
        
        this.syote = 0;
        this.uusintaKaynnissa = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==uusiPeruskierrosnappi) {
            alustaUusiPeruskierros();
            return;
        }
        
        if (ae.getSource()==vastausnappi) {
            try {
                syote = Integer.parseInt(syottokentta.getText());
                ilmoituskentta.setText("");
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
                
                if (loytyykoUusittavia()) {
                    aloitaUusintakierros();
                } else {
                    ilmoituskentta.setText("All correct, eh? Click 'New Round' and see if your luck lasts.");
                    loppunakyma();
                }
                
            } else {
                tehtava = getSeuraavaTehtava(peruskierros);
                tehtavakentta.setText(getTehtavanNumero(peruskierros, tehtava)+". "+tehtava.toString());
            }
        } else {
            if (!tasmaakoUusittuVastaus()) {
                return;
            }
            
            if (uusintakierros.onkoKierroksenViimeinenTehtava(tehtava)) {
                uusintakierros.tyhjennaUusittavat();
                tilastojenKeraaja.lisaaUusintakierros();
                ilmoituskentta.setText("There we go. Press 'New Round' if you want to have another go.");
                loppunakyma();
            } else {
                tehtava = getSeuraavaTehtava(uusintakierros);
                tehtavakentta.setText(getTehtavanNumero(uusintakierros, tehtava)+". "+tehtava.toString());
            }
        }
        
        paivitaTilastokentta();
        
    }
    
    /**
     * Virheettömän peruskierroksen tai suoritetun uusintakierroksen lopuksi käytyäjä
     * voi aloittaa uuden peruskierroksen, jonka tämä metodi alustaa.
     */
    private void alustaUusiPeruskierros() {
        peruskierros = new Peruskierros();
        tehtava = ensimmainenTehtava(peruskierros);
        tilastojenKeraaja.nollaaKierroksenTulos();
        uusintaKaynnissa = false;
        
        paivitaTilastokentta();
        ilmoituskentta.setText("Here we go again; you know the drill.");
        tehtavakentta.setText("1. "+tehtava.toString());
        syottokentta.setEnabled(true);
        
        vastausnappi.setEnabled(true);
        uusiPeruskierrosnappi.setEnabled(false);
    }
    
    /**
     * Käyttäjän syöte verrataan tehtävän vastaukseen. Jos vastaus on oikein,
     * tilastonkerääjä tallentaa vastauksen. Jos vastaus on väärä, tilastonkerääjä
     * saa siitäkin tiedon ja tehtävä lisätään uusittavaksi.
     */
    private void tarkistaPeruskierroksenVastaus() {
        if (syote==tehtava.getVastaus()) {
            tilastojenKeraaja.lisaaVastaus();
        } else {
            tilastojenKeraaja.lisaaVaaraVastaus();
            uusintakierros.lisaaUusittavaksi(tehtava);
        }
    }

    /**
     * Metodi tarkistaa, löytyykö uusittavia tehtäviä.
     * @return onko uusittavien tehtävien lista tyhjä
     */
    private boolean loytyykoUusittavia() {
        return (!uusintakierros.getTehtavat().isEmpty());
    }
    
    /**
     * Jos uusittavia tehtäviä löytyy, uusintakierros alustetaan seuraavan
     * metodin avulla.
     */
    private void aloitaUusintakierros() {
        uusintaKaynnissa = true;
        tehtava = ensimmainenTehtava(uusintakierros);
        
        ilmoituskentta.setText(uusintakierros.uusittavienMaarastaRiippuvaViesti());
        tehtavakentta.setText("1. "+ensimmainenTehtava(uusintakierros).toString());
    }
    
    /**
     * Metodi palauttaa parametrina syötetyn kierroksen ensimmäinen tehtävä.
     * @param kierros Kierros, jonka ensimmäinen tehtävä halutaan
     * @return ensimmäinen tehtävä
     */
    private Tehtava ensimmainenTehtava(Kierros kierros) {
        return kierros.getTehtavat().get(0);
    }

    /**
     * Kun uusintakierros tai virheetön peruskierros on suoritettu, käyttäjän on
     * mahdollista aloittaa uusi kierros. Silloin syöttökenttä ja "Submit" -nappi
     * ovat poissa käytöstä ja toisaalta "New Round" -nappi painettavissa.
     */
    private void loppunakyma() {
        tehtavakentta.setText("");
        syottokentta.setEnabled(false);
        vastausnappi.setEnabled(false);
        uusiPeruskierrosnappi.setEnabled(true);
    }
    
    /**
     * Metodi palauttaa parametrina syötetyn kierroksen seuraava tehtävä.
     * @param kierros Kierros, jonka seuraava tehtävä halutaan
     * @return kierroksen seuraava tehtävä
     */
    private Tehtava getSeuraavaTehtava(Kierros kierros) {
        int tehtavaNumero = kierros.getTehtavanIndeksi(tehtava);
        return kierros.getTehtavat().get(tehtavaNumero+1);
    }
    
    /**
     * Metodi palauttaa parametrina syötetyn tehtävän järjestysnumeron parametrina
     * syötetyssä kierroksessa.
     * @param kierros
     * @param tehtava
     * @return tehtävän järjestysnumero kierroksessa
     */
    private int getTehtavanNumero(Kierros kierros, Tehtava tehtava) {
        return kierros.getTehtavanIndeksi(tehtava)+1;
    }

    /**
     * Syöte verrataan uusittavan tehtävän vastaukseen. Tehtävää uusittaessa ohjelma
     * ei etene, ennen kuin oikea vastaus syötetään.
     * @return syötettiinkö oikea vastaus
     */
    private boolean tasmaakoUusittuVastaus() {
        if (syote!=tehtava.getVastaus()) {
            ilmoituskentta.setText(uusintakierros.arvoFacepalmIlmoitus());
            return false;
        }
        ilmoituskentta.setText("");
        return true;
    }
    
    /**
     * Metodi hakee senhetkiset tilastot ja päivittää käyttöliittymän tilastokenttää
     * sen mukaan.
     */
    private void paivitaTilastokentta() {
        String tilastot = "Correct answers in this round: "+tilastojenKeraaja.getOikeinVastatut()+"/"+tilastojenKeraaja.getVastatut()+". "
                        +"Completed rounds: "+tilastojenKeraaja.getPeruskierrokset()+" basic, "+tilastojenKeraaja.getUusintakierrokset()+" retrials.";
        tilastokentta.setText(tilastot);
    }
}