
package ohht.aritmetiikanharjoittelua;

import java.util.*;
import ohht.sovelluslogiikka.HarjoitteluKierros;
import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.TilastojenKeraaja;
import ohht.sovelluslogiikka.UusintaKierros;

public class AritmetiikanHarjoittelua {
    private TilastojenKeraaja tilastojenKeraaja;
    private HarjoitteluKierros harjoitteluKierros;
    private UusintaKierros uusintaKierros;
    private Scanner lukija;
    
    public AritmetiikanHarjoittelua() {
        tilastojenKeraaja = new TilastojenKeraaja();
        uusintaKierros = new UusintaKierros();
        lukija = new Scanner(System.in);
    }
    
    public void kaynnista() {
        tulostaTervehdys();
        
        while (true) {
            suoritaHarjoitteluKierros();
            
            if (uusitaan()) {
                uusintaKierros.suorita();
                tilastojenKeraaja.lisaaUusintaKierros();
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
        System.out.println(" Welcome! Let's practise some arithmetic.");
        System.out.println(" Here are ten tasks for you to solve.");
        System.out.println("******************************************");
    }

    private void suoritaHarjoitteluKierros() {
        harjoitteluKierros = new HarjoitteluKierros();
        
        for (Tehtava tehtava: harjoitteluKierros.getTehtavat()) {
            int tehtavanumero = harjoitteluKierros.getTehtavat().indexOf(tehtava)+1;
            System.out.print(tehtavanumero+". "+tehtava);
            int syote = lueLuku();
            
            if (syote!=tehtava.getVastaus()) {
                tilastojenKeraaja.lisaaVaaraVastaus();
                uusintaKierros.lisaaUusittavaksi(tehtava);
            } else {
                tilastojenKeraaja.lisaaOikeaVastaus();
                harjoitteluKierros.lisaaOikeaVastaus();
            }
        }
        
        tilastojenKeraaja.lisaaHarjoitusKierros();
        harjoitteluKierros.tulos();
        
    }
    
    private boolean uusitaan() {
        return (!uusintaKierros.getUusittavatTehtavat().isEmpty());
    }

    private boolean lopetetaan() {
        while (true) {
            System.out.println("You finished?");
            System.out.println("y = yes");
            System.out.println("n = no");
            
            String lopetetaanko = lukija.nextLine();
            if (lopetetaanko.equals("y")) {
                return true;
            } else if (lopetetaanko.equals("n")) {
                return false;
            }
            System.out.println("Just answer the question.");
        }
    }

    private int lueLuku() {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                return luku;
            } catch (Exception e) {
                System.out.println("You're supposed to enter a number, duh.");
            }
        }
    }
}
