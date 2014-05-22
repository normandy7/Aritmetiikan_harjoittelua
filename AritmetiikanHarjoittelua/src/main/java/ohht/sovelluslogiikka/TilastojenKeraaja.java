
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
    
    public double oikeidenVastauksienOsuusProsentteina() {
        return ((1.0*(getOikeinVastatut()))/(1.0*(this.getVastatut())))*100.0;
    }
    
    public void tulostaTilastot() {
        System.out.println("************ TILASTOT ************");
        System.out.println(" Vastasit oikein "+getOikeinVastatut()+" kertaa.");
        System.out.println(" Väärin vastasit "+getVaarinVastatut()+" kertaa.");
        System.out.println(" Onnistumisprosentti: "+oikeidenVastauksienOsuusProsentteina()+"%");
        System.out.println("**********************************");
        System.out.println(" Harjoituskierrokset yhteensä: "+getHarjoitusKierrokset());
        System.out.println(" Uusintakierroksrt yhteensä: "+getUusintaKierrokset());
    }
}
