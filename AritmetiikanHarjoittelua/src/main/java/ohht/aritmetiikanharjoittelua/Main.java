package ohht.aritmetiikanharjoittelua;

import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.HarjoitteluKierros;
import ohht.sovelluslogiikka.TilastojenKeraaja;


public class Main {
    public static void main( String[] args ) {
        HarjoitteluKierros kierros = new HarjoitteluKierros();
        
        for (Tehtava t: kierros.getTehtavat()) {
            System.out.println(t);
        }
        
        TilastojenKeraaja tk = new TilastojenKeraaja();
        tk.lisaaOikeaVastaus();
        tk.lisaaOikeaVastaus();
        tk.lisaaVaaraVastaus();
        tk.lisaaVaaraVastaus();
        
        System.out.println(tk.oikeidenVastauksienOsuusProsentteina());
    }
}