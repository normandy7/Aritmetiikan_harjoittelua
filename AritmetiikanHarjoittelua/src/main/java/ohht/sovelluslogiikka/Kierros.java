
package ohht.sovelluslogiikka;

import java.util.List;
import ohht.domain.Tehtava;

/**
 * Rajapinta, jonka sekä Peruskierros että Uusintakierros toteuttavat ja joka
 * määrittelee kolme metodia: kierrokseen tallennetut tehtävät palauttavan metodin,
 * tietyn tehtävän järjestysnumeron palauttavan metodin sekä metodin, joka tarkistaa,
 * onko parametrina annettu tehtävä kierroksen viimeinen.
 */
public interface Kierros {
    List<Tehtava> getTehtavat();
    int getTehtavanIndeksi(Tehtava tehtava);
    boolean onkoKierroksenViimeinenTehtava(Tehtava tehtava);
}