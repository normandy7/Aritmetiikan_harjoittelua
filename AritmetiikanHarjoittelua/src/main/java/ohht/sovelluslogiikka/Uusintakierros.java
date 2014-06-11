
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;

/**
 * Luokka kuvaa uusintakierrosta. Uusintakierroksen ilmentymä kerää talteen uusittavat
 * tehtävät, jotka tyhjennetään uusintakierroksen lopuksi.
 */
public class Uusintakierros implements Kierros {
    /**
     * Lista, johon kunkin uusintakierroksen tehtävät tallennetaan.
     */
    private final List<Tehtava> tehtavat;
    private final Random arpoja;
    
    /**
     * Parametriton konstruktori luo tehtävien tallennusta varten ArrayListin.
     */
    public Uusintakierros() {
        tehtavat = new ArrayList<Tehtava>();
        arpoja = new Random();
    }
    
    @Override
    public List<Tehtava> getTehtavat() {
        return tehtavat;
    }
    
    /**
     * Lisää parametrina annetun tehtätän uusittaviin tehtäviin.
     * @param tehtava Uusittava tehtävä
     */
    public void lisaaUusittavaksi(Tehtava tehtava) {
        tehtavat.add(tehtava);
    }
    
    /**
     * Tarkistaa uusittavien tehtävien määrän ja palauttaa määrästä riippuen
     * kolmesta eri vaihtoehdosta tietty viesti.
     * 
     * @return uusittavien määrästä riippuva viesti
     */
    public String uusittavienMaarastaRiippuvaViesti() {
        if (tehtavat.size()==1) {
            return "Guess no-one's perfect. Try this one again:";
        } else if (tehtavat.size()==2) {
            return "Numbers not your thing? Try these two again:";
        } else {
            return "Can't tell if trolling. Try these again:";
        }
    }
    
    /**
     * Tyhjentää uusittavien tehtävän listan.
     */
    public void tyhjennaUusittavat() {
        tehtavat.clear();
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
     * Metodi tarkistaa, onko parametrina syötetty tehtävä uusintakierroksen viimeinen.
     * @param tehtava Tarkistettava tehtävä
     * @return onko viimeinen tehtävä
     */
    @Override
    public boolean onkoKierroksenViimeinenTehtava(Tehtava tehtava) {
        return (getTehtavanIndeksi(tehtava)+1)==tehtavat.size();
    }
    
    /**
     * Metodi arpoo viestin, joka näytetään käyttäjälle mikäli uusintatehtävään
     * vastataan väärin.
     * @return viesti
     */
    public String arvoFacepalmVastaus() {
        int luku = arpoja.nextInt(8);
        if (luku==0) {
            return "Really?";
        } else if (luku==1) {
            return "<facepalm>";
        } else if (luku==2) {
            return "Can you at least try?";
        } else if (luku==3) {
            return "NO.";
        } else if (luku==4) {
            return "How about-- no?";
        } else if (luku==5) {
            return "In a parallel universe, maybe.";
        } else if (luku==6) {
            return "Not even funny.";
        } else {
            return "What?";
        }
    }
}