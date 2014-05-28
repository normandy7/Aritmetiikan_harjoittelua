
package ohht.sovelluslogiikka;

public class TilastojenKeraaja {
    private int vastatut;
    private int vaarinVastatut;
    private int harjoitusKierrokset;
    private int uusintaKierrokset;
    
    public TilastojenKeraaja() {
        this.vastatut = 0;
        this.vaarinVastatut = 0;
        this.harjoitusKierrokset = 0;
        this.uusintaKierrokset = 0;
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
        return harjoitusKierrokset;
    }

    public int getUusintaKierrokset() {
        return uusintaKierrokset;
    }
    
    public void lisaaOikeaVastaus() {
        vastatut++;
    }
    
    public void lisaaVaaraVastaus() {
        vastatut++;
        vaarinVastatut++;
    }
    
    public void lisaaHarjoitusKierros() {
        harjoitusKierrokset++;
    }
    
    public void lisaaUusintaKierros() {
        uusintaKierrokset++;
    } 
    
    public void tulostaTilastot() {
        System.out.println("************ STATISTICS ************");
        System.out.println("Correct answers total: "+getOikeinVastatut());
        System.out.println("Wrong answers total: "+getVaarinVastatut());
        System.out.println("************************************");
        System.out.println("Practice runs: "+getHarjoitusKierrokset());
        System.out.println("Retrial runs: "+getUusintaKierrokset());
    }
}
