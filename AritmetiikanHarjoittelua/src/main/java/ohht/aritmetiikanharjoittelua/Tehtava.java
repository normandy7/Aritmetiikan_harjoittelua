
package ohht.aritmetiikanharjoittelua;

import java.util.Random;

public class Tehtava {
    private int ekaLuku;
    private int tokaLuku;
    private Laskutoimitus laskutoimitus;
    
    public Tehtava() {
        Random arpa = new Random();
        this.ekaLuku = arpa.nextInt(100);
        this.tokaLuku = arpa.nextInt(100);
        this.laskutoimitus = Laskutoimitus.arvoLaskutoimitus();
    }
    
    public int getEkaLuku() {
        return ekaLuku;
    }
    
    public int getTokaLuku() {
        return tokaLuku;
    }
    
    public Laskutoimitus getLaskutoimitus() {
        return laskutoimitus;
    }
    
    @Override
    public String toString() {
        return "Laske: "+ekaLuku+" "+laskutoimitus+" "+tokaLuku;
    }
}