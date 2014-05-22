package ohht.aritmetiikanharjoittelua;

import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.HarjoitteluKierros;


public class Main {
    public static void main( String[] args ) {
        HarjoitteluKierros kierros = new HarjoitteluKierros();
        
        for (Tehtava t: kierros.getTehtavat()) {
            System.out.println(t);
        }
    }
}