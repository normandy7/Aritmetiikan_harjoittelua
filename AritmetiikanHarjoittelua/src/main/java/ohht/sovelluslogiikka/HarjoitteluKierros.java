
package ohht.sovelluslogiikka;

import java.util.*;

public class HarjoitteluKierros {
    private List<Tehtava> tehtavat;
    private int oikeinVastatut;
    
    public HarjoitteluKierros() {
        this.tehtavat = luoTehtavat();
    }

    private List<Tehtava> luoTehtavat() {
        List<Tehtava> tehtavaLista = new ArrayList<Tehtava>();

        for (int i = 0; i < 10; i++) {
            tehtavaLista.add(new Tehtava());
        }

        return tehtavaLista;
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
    
    public void tulos() {
        System.out.println("************************");
        System.out.println(" Your result: "+getViimeKierroksenOikeinVastatut()+"/10");
        System.out.println("************************");
    }
}