
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;

/**
 * Luokka kuvaa uusintakierrosta. Uusintakierroksen ilmentymä kerää talteen uusittavat
 * tehtävät, jotka tyhjennetään uusintakierroksen lopuksi.
 */
public class Uusintakierros {
    /**
     * Lista, johon kunkin uusintakierroksen tehtävät tallennetaan.
     */
    private final List<Tehtava> uusittavatTehtavat;
    
    /**
     * Parametriton konstruktori luo tehtävien tallennusta varten ArrayListin.
     */
    public Uusintakierros() {
        uusittavatTehtavat = new ArrayList<Tehtava>();
    }
    
    public List<Tehtava> getUusittavatTehtavat() {
        return uusittavatTehtavat;
    }
    
    /**
     * Lisää parametrina annetun tehtätän uusittaviin tehtäviin.
     * @param tehtava Uusittava tehtävä
     */
    public void lisaaUusittavaksi(Tehtava tehtava) {
        uusittavatTehtavat.add(tehtava);
    }
    
    /**
     * Tarkistaa uusittavien tehtävien määrän ja palauttaa määrästä riippuen
     * kolmesta eri vaihtoehdosta tietty viesti.
     * 
     * @return uusittavien määrästä riippuva viesti
     */
    public String sopivaViesti() {
        if (uusittavatTehtavat.size()==1) {
            return "Guess no-one's perfect. Try this one again:";
        } else if (uusittavatTehtavat.size()==2) {
            return "Numbers not your thing? Try these two again:";
        } else {
            return "Can't tell if trolling. Try these again:";
        }
    }
    
    /**
     * Tyhjentää uusittavien tehtävän listan.
     */
    public void tyhjennaUusittavat() {
        uusittavatTehtavat.clear();
    }
    
    /**
     * Metodi saa parametrinaan tehtävän ja palauttaa tehtävän järjestysnumeron
     * tehtävälistassa siten, että ensimmäisen tehtävän numero on 0, toisen 1, jne.
     * @param tehtava
     * @return tehtävän numero
     */
    public int getTehtavanNumero(Tehtava tehtava) {
        return uusittavatTehtavat.indexOf(tehtava);
    }
    
    /**
     * Metodi tarkistaa, onko parametrina syötetty tehtävä uusintakierroksen viimeinen.
     * @param tehtava Tarkistettava tehtävä
     * @return onko viimeinen tehtävä
     */
    public boolean onkoKierroksenViimeinenTehtava(Tehtava tehtava) {
        return getTehtavanNumero(tehtava)==uusittavatTehtavat.size();
    }
}