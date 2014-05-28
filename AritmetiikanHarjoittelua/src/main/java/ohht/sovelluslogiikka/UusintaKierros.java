
package ohht.sovelluslogiikka;

import java.util.*;

public class UusintaKierros {
    private List<Tehtava> uusittavatTehtavat;
    
    public UusintaKierros() {
        uusittavatTehtavat = new ArrayList<Tehtava>();
    }
    
    public List<Tehtava> getUusittavatTehtavat() {
        return uusittavatTehtavat;
    }
    
    public void lisaaUusittavaksi(Tehtava tehtava) {
        uusittavatTehtavat.add(tehtava);
    }
    
    private void tyhjennaUusittavat() {
        uusittavatTehtavat.clear();
    }
    
    public void suorita() {
        Scanner lukija = new Scanner(System.in);
        
        if (getUusittavatTehtavat().size()==1) {
            System.out.println("Guess no-one's perfect. Try this one again:");
        } else if (getUusittavatTehtavat().size()==2) {
            System.out.println("Numbers not your thing? Try these two again:");
        } else {
            System.out.println("Can't tell if trolling.");
            System.out.println("Here are the ones you botched - get a grip or prepare for an infinite arithmetic loop.");
            System.out.println("(I'm not going anywhere.)");
        }
        
        for (Tehtava tehtava: getUusittavatTehtavat()) {
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
        System.out.println("There we go.");
        tyhjennaUusittavat();
        
    }
    
    private int lueLuku(Scanner lukija) {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                return luku;
            } catch (Exception e) {
                System.out.println("You're supposed to enter a number, duh.");
            }
        }
    }

    private void arvoFacepalmVastaus() {
        Random arpa = new Random();
        int luku = arpa.nextInt(5);
        if (luku==0) {
            System.out.println("Really?");
        } else if (luku==1) {
            System.out.println("<facepalm>");
        } else if (luku==2) {
            System.out.println("Can you at least try?");
        } else if (luku==3) {
            System.out.println("NO.");
        } else {
            System.out.println("How about... no?");
        }
    }
}
