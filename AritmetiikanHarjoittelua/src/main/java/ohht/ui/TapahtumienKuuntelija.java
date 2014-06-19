
package ohht.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ohht.domain.Tehtava;
import ohht.domain.TilastojenKeraaja;
import ohht.sovelluslogiikka.Peruskierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.sovelluslogiikka.Kierros;

/**
 * Luokka käsittelee kaikki tapahtumat ja säätelee ohjelman kulun.
 */
class TapahtumienKuuntelija implements ActionListener {
    /**
     * Tekstikenttä, joka näyttää nykyisen kierroksen vastaustilanteen.
     */
    private final JLabel kierroksenTehtavatilastot;
    /**
     * Tekstikenttä, joka näyttää koko harjoittelusession vastaustilanteen.
     */
    private final JLabel kaikkiTehtavatilastot;
    /**
     * Tekstikenttä, joka näyttää suoritetut perus- ja uusintakierrokset.
     */
    private final JLabel kierrostilastot;
    /**
     * Tekstikenttä, johon ilmestyy tarvittaessa tilapäisiä ilmoituksia.
     */
    private final JLabel ilmoituskentta;
    /**
     * Tekstikenttä, jossa näytetään uusintakierroksen aikana kunkin tehtävän
     * yhteydessä käyttäjän aiemmin syöttämä väärä vastaus.
     */
    private final JLabel aiempiVastaus;
    /**
     * Tekstikenttä johon ilmestyy ratkaistava tehtävä.
     */
    private final JLabel tehtavakentta;
    /**
     * Kenttä, johon käyttäjä syöttää vastauksensa.
     */
    private final JTextField syottokentta;
    /**
     * Nappi, jota painamalla käyttäjä lähettää syötteensä ohjelmalle.
     */
    private final JButton vastausnappi;
    /**
     * Nappi, jota painamalla uusi peruskierros käynnistetään.
     */
    private final JButton uusiPeruskierrosnappi;
    /**
     * Käsiteltävä peruskierros.
     */
    private Peruskierros peruskierros;
    /**
     * Käsiteltävä tehtävä.
     */
    private Tehtava tehtava;
    /**
     * Käsiteltävä uusintakierros.
     */
    private final Uusintakierros uusintakierros;
    /**
     * Käsiteltävä tilastojen kerääjä.
     */
    private final TilastojenKeraaja tilastojenKeraaja;
    /**
     * Käyttäjän viimeisin syöte.
     */
    private int syote;
    /**
     * Sisältää tiedon siitä, onko käynnissä perus- vai uusintakierros.
     */
    private boolean uusintaKaynnissa;
    
    /**
     * Luokan konstruktori saa parametrikseen graafisen käyttöliittymän komponenttien
     * lisäksi Peruskierros- sekä TilastojenKeraaja-oliot. Ensimmäiseksi käsiteltäväksi
     * tehtäväksi asetetaan aluksi parametrina syötetyn peruskierroksen ensimmäinen tehtävä.
     * 
     * @param kierroksenTehtavatilastot Käynnissä olevan kierroksen vastaustilanne
     * @param kaikkiTehtavatilastot Harjoittelusession vastaustilanne
     * @param kierrostilastot Harjoittelusession kierrostilastot
     * @param ilmoituskentta Ohjelman tilapäiset ilmoitukset
     * @param tehtavakentta Ratkaistava tehtävä
     * @param syottokentta Käyttäjän syöttökenttä
     * @param vastausnappi Nappi, jota käytetään syötteen lähetykseen
     * @param uusiPeruskierrosnappi Nappi, jolla aloitetaan uusi peruskierros
     * @param peruskierros Harjoittelusession ensimmäinen peruskierros
     */
    public TapahtumienKuuntelija(JLabel kierroksenTehtavatilastot, JLabel kaikkiTehtavatilastot, JLabel kierrostilastot, JLabel ilmoituskentta, JLabel aiempiVastaus, JLabel tehtavakentta, JTextField syottokentta, JButton vastausnappi, JButton uusiPeruskierrosnappi, Peruskierros peruskierros, TilastojenKeraaja tilastojenKeraaja) {
        this.kierroksenTehtavatilastot = kierroksenTehtavatilastot;
        this.kaikkiTehtavatilastot = kaikkiTehtavatilastot;
        this.kierrostilastot = kierrostilastot;
        this.ilmoituskentta = ilmoituskentta;
        this.aiempiVastaus = aiempiVastaus;
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
                ilmoituskentta.setText("You're supposed to enter an integer, duh.");
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
                aiempiVastaus.setText("");
                loppunakyma();
            } else {
                tehtava = getSeuraavaTehtava(uusintakierros);
                aiempiVastaus.setText("(you answered: "+tehtava.getKayttajanSyottamaVastaus()+")");
                tehtavakentta.setText(getTehtavanNumero(uusintakierros, tehtava)+". "+tehtava.toString());
            }
        }
        
        paivitaTilastokentat();
        
    }
    
    /**
     * Virheettömän peruskierroksen tai suoritetun uusintakierroksen lopuksi käyttäjä
     * voi aloittaa uuden peruskierroksen, jonka tämä metodi alustaa.
     * Käytännössä "New Round"-napin painaus on muodollisuus, sillä uuden kierroksen
     * aloittaminen on tässä vaiheessa ainut mahdollinen tapa jatkaa eteenpäin.
     */
    private void alustaUusiPeruskierros() {
        peruskierros = new Peruskierros();
        tehtava = ensimmainenTehtava(peruskierros);
        tilastojenKeraaja.nollaaKierroksenTulos();
        uusintaKaynnissa = false;
        
        ilmoituskentta.setText("Here we go again; you know the drill.");
        tehtavakentta.setText("1. "+tehtava.toString());
        syottokentta.setEnabled(true);
        vastausnappi.setEnabled(true);
        uusiPeruskierrosnappi.setEnabled(false);
        
        paivitaTilastokentat();
    }
    
    /**
     * Käyttäjän syöte verrataan tehtävän vastaukseen. Jos vastaus on oikein, tilastojen
     * kerääjä tallentaa vastauksen. Jos vastaus on väärä, kerääjä saa siitäkin tiedon,
     * tehtävä lisätään uusittavaksi ja käyttäjän vastaus tallentuu tehtävän oliomuuttujaan.
     */
    private void tarkistaPeruskierroksenVastaus() {
        if (syote==tehtava.getVastaus()) {
            tilastojenKeraaja.lisaaOikeaVastaus();
        } else {
            tilastojenKeraaja.lisaaVastaus();
            uusintakierros.lisaaUusittavaksi(tehtava);
            tehtava.setKayttajanSyottamaVastaus(syote);
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
     * Jos uusittavia tehtäviä löytyy, uusintakierros alustetaan tämän metodin avulla.
     */
    private void aloitaUusintakierros() {
        uusintaKaynnissa = true;
        tehtava = ensimmainenTehtava(uusintakierros);
        
        ilmoituskentta.setText(uusintakierros.uusittavienMaarastaRiippuvaViesti());
        aiempiVastaus.setText("(you answered: "+tehtava.getKayttajanSyottamaVastaus()+")");
        tehtavakentta.setText("1. "+ensimmainenTehtava(uusintakierros).toString());
    }
    
    /**
     * Metodi palauttaa parametrina syötetyn kierroksen ensimmäisen tehtävän.
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
     * Metodi palauttaa parametrina syötetyn kierroksen seuraavana vuorossa olevan tehtävän.
     * @param kierros Kierros, jonka tehtävä haetaan
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
     * ei etene, ennen kuin syöte ja vastaus täsmäävät.
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
     * Metodi hakee senhetkiset tilastot ja päivittää käyttöliittymän tilastokentät.
     */
    private void paivitaTilastokentat() {
        kierroksenTehtavatilastot.setText("Correct answers in this round: "+tilastojenKeraaja.getNykyisenKierroksenOikeinVastatut()+"/"+tilastojenKeraaja.getNykyisenKierroksenVastatut());
        kaikkiTehtavatilastot.setText("Correct answers overall: "+tilastojenKeraaja.getOikeinVastatutYhteensa()+"/"+tilastojenKeraaja.getVastatutYhteensa());
        kierrostilastot.setText("Completed rounds: "+tilastojenKeraaja.getPeruskierrokset()+" basic, "+tilastojenKeraaja.getUusintakierrokset()+" retrials");
    }
}