
package ohht.sovelluslogiikka;

import java.util.*;

public class Uusintakierros {
    private final List<Tehtava> uusittavatTehtavat;
    private final Scanner lukija;
    private final Random arpoja;
    
    public Uusintakierros() {
        uusittavatTehtavat = new ArrayList<Tehtava>();
        lukija = new Scanner(System.in);
        arpoja = new Random();
    }
    
    public List<Tehtava> getUusittavatTehtavat() {
        return uusittavatTehtavat;
    }
    
    public void lisaaUusittavaksi(Tehtava tehtava) {
        uusittavatTehtavat.add(tehtava);
    }
    
    public void suorita() {
        
        System.out.println(sopivaViesti());
        
        for (Tehtava tehtava: uusittavatTehtavat) {
            System.out.print(tehtava);
            
            while (true) {
                int syote = lueLuku(lukija);
                
                if (syote==tehtava.getVastaus()) {
                    break;
                } else {
                    arvoFacepalmVastaus();
                }
            }
            
        }
        
        System.out.println("***************************");
        System.out.println("There we go.");
        
    }
    
    private String sopivaViesti() {
        if (getUusittavatTehtavat().size()==1) {
            return "Guess no-one's perfect. Try this one again:";
        } else if (getUusittavatTehtavat().size()==2) {
            return "Numbers not your thing? Try these two again:";
        } else {
            return "Can't tell if trolling.\n" +
            "Here are the ones you botched: you can't proceed to the next task until you've\n" +
            "answered correctly, so get a grip if you ever want out of the loop.";
        }
    }
    
    private int lueLuku(Scanner lukija) {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                return luku;
            } catch (NumberFormatException e) {
                System.out.println("You're supposed to enter a number, duh.");
            }
        }
    }

    private void arvoFacepalmVastaus() {
        int luku = arpoja.nextInt(8);
        if (luku==0) {
            System.out.println("Really?");
        } else if (luku==1) {
            System.out.println("<facepalm>");
        } else if (luku==2) {
            System.out.println("Can you at least try?");
        } else if (luku==3) {
            System.out.println("NO.");
        } else if (luku==4) {
            System.out.println("How about... no?");
        } else if (luku==5) {
            System.out.println("In a parallel universe, maybe.");
        } else if (luku==6) {
            System.out.println("Not even funny.");
        } else {
            System.out.println("What?");
        }
    }
}