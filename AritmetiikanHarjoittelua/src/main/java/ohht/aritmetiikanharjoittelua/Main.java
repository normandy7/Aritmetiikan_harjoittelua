package ohht.aritmetiikanharjoittelua;

import java.util.Scanner;
import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.HarjoitteluKierros;
import ohht.sovelluslogiikka.TilastojenKeraaja;


public class Main {
    public static void main( String[] args ) {
        Scanner lukija = new Scanner(System.in);
        AritmetiikanHarjoittelua ah = new AritmetiikanHarjoittelua();
        
        while (true) {
            ah.kaynnista();
            System.out.println("If you change your mind and want to go again, enter 'I'm a masochist'.");
            String syote = lukija.nextLine();
            if (syote.equals("I'm a masochist")) {
                continue;
            } else {
                break;
            }
        }
        
    }
}