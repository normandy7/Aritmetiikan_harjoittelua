
package ohht.aritmetiikanharjoittelua;

import java.util.*;
import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.Harjoittelukierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.sovelluslogiikka.TilastojenKeraaja;

public class Harjoittelusessio {
    private final TilastojenKeraaja tilastojenKeraaja;
    private Harjoittelukierros harjoittelukierros;
    private Scanner lukija;
    private Uusintakierros uusintakierros;
    
    public Harjoittelusessio() {
        tilastojenKeraaja = new TilastojenKeraaja();
        lukija = new Scanner(System.in);
        uusintakierros = new Uusintakierros();
    }
    
    public TilastojenKeraaja getTilastojenKeraaja() {
        return tilastojenKeraaja;
    }
    
    public void kaynnista() {
        tulostaTervehdys();
        
        while (true) {
            suoritaHarjoittelukierros();
            
            if (uusitaan()) {
                uusintakierros.suorita();
                tilastojenKeraaja.lisaaUusintaKierros();
                tyhjennaUusittavatTehtavat();
            } else {
                System.out.println("Not bad.");
            }
            
            if (lopetetaan()) {
                break;
            } else {
                System.out.println("You asked for it. Here we go again:");
            }
        }
        
        tilastojenKeraaja.tulostaTilastot();
        
    }
    
    private void tulostaTervehdys() {
        System.out.println("******************************************");
        System.out.println("Welcome! Let's practise some arithmetic.");
        System.out.println("Here are ten tasks for you to solve.");
        System.out.println("******************************************");
    }

    public void suoritaHarjoittelukierros() {
        harjoittelukierros = new Harjoittelukierros();
        
        for (Tehtava tehtava: harjoittelukierros.getTehtavat()) {
            tulostaTehtava(tehtava);
            int syote = lueLuku();
            
            if (syote!=tehtava.getVastaus()) {
                tilastojenKeraaja.lisaaVaaraVastaus();
                uusintakierros.lisaaUusittavaksi(tehtava);
            } else {
                tilastojenKeraaja.lisaaOikeaVastaus();
                harjoittelukierros.lisaaOikeaVastaus();
            }
        }
        
        tilastojenKeraaja.lisaaHarjoitusKierros();
        harjoittelukierros.tulostaViimeKierroksenTulos();
        
    }
    
    private void tulostaTehtava(Tehtava tehtava) {
        System.out.print((harjoittelukierros.getTehtavat().indexOf(tehtava)+1)+". "+tehtava);
    }
    
    private int lueLuku() {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                return luku;
            } catch (NumberFormatException e) {
                System.out.println("You're supposed to enter a number, duh.");
            }
        }
    }
    
    private boolean uusitaan() {
        return (!uusintakierros.getUusittavatTehtavat().isEmpty());
    }
    
    private void tyhjennaUusittavatTehtavat() {
        uusintakierros.getUusittavatTehtavat().clear();
    }

    private boolean lopetetaan() {
        System.out.println("You finished?");
        System.out.println("y = yes");
        System.out.println("n = no");
        
        while (true) {
            String lopetetaanko = lukija.nextLine();
            
            if (lopetetaanko.equals("y")) {
                return true;
            } else if (lopetetaanko.equals("n")) {
                return false;
            }
            
            System.out.println("Just answer the question.");
        }
    }
}