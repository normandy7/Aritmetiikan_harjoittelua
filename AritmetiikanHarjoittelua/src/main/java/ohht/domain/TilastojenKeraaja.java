
package ohht.domain;

/**
 * Luokka kerää talteen tiedot kokonaisen harjoittelusession suoritetuista kierroksista sekä
 * oikeista ja vääristä vastauksista.
 */

public class TilastojenKeraaja {
    /**
     * Käynnissä olevan kierroksen vastauksien lukumäärä.
     */
    private int nykyisenKierroksenVastatut;
    /**
     * Käynnissä olevan kierroksen oikeiden vastauksien lukumäärä.
     */
    private int nykyisenKierroksenOikeinVastatut;
    /**
     * Koko harjoittelusession vastauksien lukumäärä.
     */
    private int vastatutYhteensa;
    /**
     * Koko harjoittelusession oikeiden vastauksien lulumäärä.
     */
    private int oikeinVastatutYhteensa;
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
        this.nykyisenKierroksenVastatut = 0;
        this.nykyisenKierroksenOikeinVastatut = 0;
        this.peruskierrokset = 0;
        this.uusintakierrokset = 0;
    }
    
    public int getNykyisenKierroksenOikeinVastatut() {
        return nykyisenKierroksenOikeinVastatut;
    }

    public int getNykyisenKierroksenVastatut() {
        return nykyisenKierroksenVastatut;
    }

    public int getOikeinVastatutYhteensa() {
        return oikeinVastatutYhteensa;
    }

    public int getVastatutYhteensa() {
        return vastatutYhteensa;
    }

    public int getPeruskierrokset() {
        return peruskierrokset;
    }

    public int getUusintakierrokset() {
        return uusintakierrokset;
    }
    
    /**
     * Kasvattaa vastauksien lukumäärää yhdellä.
     */
    public void lisaaVastaus() {
        nykyisenKierroksenVastatut++;
        vastatutYhteensa++;
    }
    
    /**
     * Kasvattaa sekä vastauksien että oikeiden vastauksien lukumäärää yhdellä.
     */
    public void lisaaOikeaVastaus() {
        nykyisenKierroksenVastatut++;
        nykyisenKierroksenOikeinVastatut++;
        vastatutYhteensa++;
        oikeinVastatutYhteensa++;
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
     * Asettaa nykyisen kierroksen vastauksien lukumääriä keräävien muuttujien arvoksi 0.
     */
    public void nollaaKierroksenTulos() {
        nykyisenKierroksenVastatut = 0;
        nykyisenKierroksenOikeinVastatut = 0;
    }
}