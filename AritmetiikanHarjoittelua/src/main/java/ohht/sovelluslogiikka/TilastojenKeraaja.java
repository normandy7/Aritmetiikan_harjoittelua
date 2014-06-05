
package ohht.sovelluslogiikka;

/**
 * Luokka kerää talteen tiedot kokonaisen harjoittelusession suoritetuista kierroksista sekä
 * oikeista ja vääristä vastauksista.
 */

public class TilastojenKeraaja {
    private int vastatut;
    private int vaarinVastatut;
    private int harjoituskierrokset;
    private int uusintakierrokset;
    
    public TilastojenKeraaja() {
        this.vastatut = 0;
        this.vaarinVastatut = 0;
        this.harjoituskierrokset = 0;
        this.uusintakierrokset = 0;
    }
    
    public int getVaarinVastatut() {
        return vaarinVastatut;
    }

    public int getVastatut() {
        return vastatut;
    }
    
    public int getOikeinVastatut() {
        return vastatut - vaarinVastatut;
    }

    public int getHarjoitusKierrokset() {
        return harjoituskierrokset;
    }

    public int getUusintaKierrokset() {
        return uusintakierrokset;
    }
    
    public void lisaaOikeaVastaus() {
        vastatut++;
    }
    
    public void lisaaVaaraVastaus() {
        vastatut++;
        vaarinVastatut++;
    }
    
    public void lisaaPeruskierros() {
        harjoituskierrokset++;
    }
    
    public void lisaaUusintakierros() {
        uusintakierrokset++;
    } 
}
