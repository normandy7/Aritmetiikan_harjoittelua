package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;

/**
 * Luokka kuvaa yhtä kymmenen tehtävän peruskierrosta.
 */

public class Peruskierros implements Kierros {
    /**
     * Lista, joka sisältää kymmenen tehtävää.
     */
    private List<Tehtava> tehtavat;
    /**
     * Kokonaisluku, joka kuvaa kierroksen oikeiden vastauksien lukumäärää.
     */
    private int oikeinVastatut;
    
    /**
     * Parametriton konstruktori luo Peruskierros-ilmentymän, jonka tehtävälista generoidaan
     * luokansisäisen metodin avulla ja oikeiden vastauksien määräksi asetetaan aluksi 0.
     */
    public Peruskierros() {
        tehtavat = haeKymmenenUuttaTehtavaa();
        oikeinVastatut = 0;
    }
    
    /**
     * Metodi luo kymmenen uutta tehtävää, kerää ne ArrayList:iksi ja palauttaa
     * listan.
     * @return tehtävälista
     */
    private List<Tehtava> haeKymmenenUuttaTehtavaa() {
        List<Tehtava> tehtavat = new ArrayList<Tehtava>();

        for (int i = 0; i < 10; i++) {
            tehtavat.add(new Tehtava());
        }

        return tehtavat;
    }
    
    @Override
    public List<Tehtava> getTehtavat() {
        return tehtavat;
    }
    
    public int getOikeinVastatut() {
        return oikeinVastatut;
    }
    
    /**
     * Kasvattaa kierroksen oikeiden vastauksien lukumäärää yhdellä.
     */
    public void lisaaOikeaVastaus() {
        oikeinVastatut++;
    }
    
    /**
     * Metodi saa parametrinaan tehtävän ja palauttaa tehtävän järjestysnumeron
     * tehtävälistassa siten, että ensimmäisen tehtävän numero on 0, toisen 1, jne.
     * @param tehtava
     * @return tehtävän numero
     */
    @Override
    public int getTehtavanIndeksi(Tehtava tehtava) {
        return tehtavat.indexOf(tehtava);
    }
    
    /**
     * Metodi tarkistaa, onko parametrina syötetty tehtävä kierroksen viimeinen.
     * @param tehtava Tarkistettava tehtävä
     * @return onko viimeinen tehtävä
     */
    @Override
    public boolean onkoKierroksenViimeinenTehtava(Tehtava tehtava) {
        return getTehtavanIndeksi(tehtava)==9;
    }
    
    /**
     * Peruskierroksen alkuun tulostettava ilmoitus.
     * @return ilmoitus
     */
    public String alkuilmoitus() {
        return "Solve the ten tasks one at a time.";
    }
}