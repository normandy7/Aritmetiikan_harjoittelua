package ohht.sovelluslogiikka;

import java.util.*;

public class Harjoittelukierros {
    private List<Tehtava> tehtavat;
    private int oikeinVastatut;
    private Uusintakierros uusintaKierros;
    
    public Harjoittelukierros() {
        tehtavat = luoTehtavat();
        oikeinVastatut = 0;
    }

    private List<Tehtava> luoTehtavat() {
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
    
    public void tulostaViimeKierroksenTulos() {
        System.out.println("************************");
        System.out.println("Your result: "+getViimeKierroksenOikeinVastatut()+"/10");
        System.out.println("************************");
    }
}