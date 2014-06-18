
package ohht.domain;

import java.util.Random;

/**
 * Luokan ilmentymä kuvaa yhtä tehtävää, jolle valitaan satunnaisesti kaksi lukua
 * sekä suoritettavan laskutoimituksen tyyppi.
 */

public class Tehtava {
    /**
     * Laskutoimituksen ensimmäinen luku.
     */
    private int ekaLuku;
    /**
     * Laskutoimituksen toinen luku.
     */
    private int tokaLuku;
    /**
     * Laskutoimituksen tyyppi.
     */
    private Laskutoimitus laskutoimitus;
    /**
     * Mikäli käyttäjän syöttämä vastaus on väärä, numero tallentuu tehtävän oliomuuttujaksi
     * jotta siihen voidaan viitata myöhemmin.
     */
    private int kayttajanSyottamaVastaus;
    /**
     * Lukujen arpomiseen käytettävä Random -olio.
     */
    private Random arpa = new Random();
    
    /**
     * Parametriton konstruktori arpoo ja asettaa tehtävän ensimmäiseksi ja toiseksi luvuksi
     * satunnaisen kokonaisluvun väliltä [0,9] sekä laskutoimitukseksi satunnaisesti valitun
     * laskutoimituksen.
     */
    public Tehtava() {
        this.ekaLuku = arpa.nextInt(10);
        this.tokaLuku = arpa.nextInt(10);
        this.laskutoimitus = Laskutoimitus.arvoLaskutoimitus();
        this.kayttajanSyottamaVastaus = 100;
    }
    
    public int getEkaLuku() {
        return ekaLuku;
    }
    
    public int getTokaLuku() {
        return tokaLuku;
    }
    
    public Laskutoimitus getLaskutoimitus() {
        return laskutoimitus;
    }
    
    public int getKayttajanSyottamaVastaus() {
        return kayttajanSyottamaVastaus;
    }
    
    /**
     * Tehtävän laskutoimituksen arvo, joka riippuu kummastakin luvusta
     * sekä laskutoimituksen tyypistä.
     * 
     * @return laskutoimituksen arvo
     */
    public int getVastaus() {
        if (laskutoimitus==Laskutoimitus.YHTEEN) {
            return ekaLuku+tokaLuku;
        } else if (laskutoimitus==Laskutoimitus.VAHENNYS) {
            return ekaLuku-tokaLuku;
        } else {
            return ekaLuku*tokaLuku;
        }
    }
    
    /**
     * Tehtävään tallentuu tieto käyttäjän syötteestä.
     * @param syote Käyttäjän syöttämä numero
     */
    public void setKayttajanSyottamaVastaus(int syote) {
        kayttajanSyottamaVastaus = syote;
    }
    
    @Override
    public String toString() {
        return ekaLuku+" "+laskutoimitus+" "+tokaLuku+" = ";
    }
}