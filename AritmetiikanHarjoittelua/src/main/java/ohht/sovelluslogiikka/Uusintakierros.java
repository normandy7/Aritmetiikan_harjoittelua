
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;

/**
 * Luokka kuvaa uusintakierrosta. Uusintakierroksen ilmentymä kerää talteen uusittavat
 * tehtävät, jotka tyhjennetään uusintakierroksen lopuksi.
 */

public class Uusintakierros {
    private final List<Tehtava> uusittavatTehtavat;
    
    
    public Uusintakierros() {
        uusittavatTehtavat = new ArrayList<Tehtava>();
    }
    
    public List<Tehtava> getUusittavatTehtavat() {
        return uusittavatTehtavat;
    }
    
    public void lisaaUusittavaksi(Tehtava tehtava) {
        uusittavatTehtavat.add(tehtava);
    }
    
    public String sopivaViesti() {
        if (uusittavienMaara()==1) {
            return "Guess no-one's perfect. Try this one again:";
        } else if (uusittavienMaara()==2) {
            return "Numbers not your thing? Try these two again:";
        } else {
            return "Can't tell if trolling. Try these again:";
        }
    }

    private int uusittavienMaara() {
        return uusittavatTehtavat.size();
    }
}