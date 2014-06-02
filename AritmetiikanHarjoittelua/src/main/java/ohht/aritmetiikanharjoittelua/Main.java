package ohht.aritmetiikanharjoittelua;

import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {
        Scanner lukija = new Scanner(System.in);
        Harjoittelusessio sessio = new Harjoittelusessio();
        
        while (true) {
            sessio.kaynnista();
            
            System.out.println("If you change your mind and want to go again, enter 'I'm a masochist'.");
            System.out.println("Enter anything else to quit the program.");
            
            String syote = lukija.nextLine();
            if (!syote.equals("I'm a masochist")) {
                break;
            }
        }
        
    }
}