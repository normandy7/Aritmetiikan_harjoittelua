package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;

/**
 * Luokka kuvaa yhtä kymmenen tehtävän peruskierrosta.
 */

public class Peruskierros {
    private List<Tehtava> tehtavat;
    private int oikeinVastatut;
    
    public Peruskierros() {
        tehtavat = haeKymmenenUuttaTehtavaa();
        oikeinVastatut = 0;
    }

    private List<Tehtava> haeKymmenenUuttaTehtavaa() {
        List<Tehtava> tehtavat = new ArrayList<Tehtava>();

        for (int i = 0; i < 10; i++) {
            tehtavat.add(new Tehtava());
        }

        return tehtavat;
    }
    
    public List<Tehtava> getTehtavat() {
        return tehtavat;
    }
    
    public void lisaaOikeaVastaus() {
        oikeinVastatut++;
    }
    
    private int getViimeKierroksenOikeinVastatut() {
        return oikeinVastatut;
    }
    
    public String alkuTervehdys() {
        return "Welcome! Solve the tasks one at a time.";
    }
    
}