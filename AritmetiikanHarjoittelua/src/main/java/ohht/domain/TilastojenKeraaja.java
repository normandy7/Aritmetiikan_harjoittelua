
package ohht.domain;

/**
 * Luokka kerää talteen tiedot kokonaisen harjoittelusession suoritetuista kierroksista sekä
 * oikeista ja vääristä vastauksista.
 */

public class TilastojenKeraaja {
    /**
     * Vastauksien lukumäärä.
     */
    private int vastatut;
    /**
     * Väärien vastauksien lukumäärä.
     */
    private int vaarinVastatut;
    /**
     * Suoritettujen peruskierroksien lukumäärä.
     */
    private int peruskierrokset;
    /**
     * Suoritettujen uusintakierroksien lukumäärä.
     */
    private int uusintakierrokset;
    
    /**
     * Parametriton konstruktori asettaa kunkin oliomuuttujan arvoksi aluksi 0.
     */
    public TilastojenKeraaja() {
        this.vastatut = 0;
        this.vaarinVastatut = 0;
        this.peruskierrokset = 0;
        this.uusintakierrokset = 0;
    }
    
    public int getVaarinVastatut() {
        return vaarinVastatut;
    }

    public int getVastatut() {
        return vastatut;
    }

    public int getPeruskierrokset() {
        return peruskierrokset;
    }

    public int getUusintakierrokset() {
        return uusintakierrokset;
    }
    
    /**
     * Metodi palauttaa oikeiden vastauksien määrän vähentämällä kaikkien vastauksien
     * määrästä väärien vastauksien määrän.
     * 
     * @return oikeiden vastauksien lukumäärä
     */
    public int getOikeinVastatut() {
        return vastatut - vaarinVastatut;
    }
    
    /**
     * Kasvattaa (ei-väärien) vastauksien lukumäärää yhdellä.
     */
    public void lisaaVastaus() {
        vastatut++;
    }
    
    /**
     * Kasvattaa sekä vastauksien että väärien vastauksien lukumäärää yhdellä.
     */
    public void lisaaVaaraVastaus() {
        vastatut++;
        vaarinVastatut++;
    }
    
    /**
     * Kasvattaa suoritettujen peruskierroksien määrää yhdellä.
     */
    public void lisaaPeruskierros() {
        peruskierrokset++;
    }
    
    /**
     * Kasvattaa suoritettujen uusintakierroksien määrää yhdellä.
     */
    public void lisaaUusintakierros() {
        uusintakierrokset++;
    }
    
    /**
     * Asettaa vastauksien lukumääriä keräävien muuttujien arvoksi 0.
     */
    public void nollaaKierroksenTulos() {
        vastatut = 0;
        vaarinVastatut = 0;
    }
}
