
package ohht.sovelluslogiikka;
import java.util.Random;
import ohht.sovelluslogiikka.Laskutoimitus;

public class Tehtava {
    private int ekaLuku;
    private int tokaLuku;
    private Laskutoimitus laskutoimitus;
    private int vastaus;
    
    public Tehtava() {
        Random arpa = new Random();
        this.ekaLuku = arpa.nextInt(100);
        this.tokaLuku = arpa.nextInt(100);
        this.laskutoimitus = Laskutoimitus.arvoLaskutoimitus();
        this.vastaus = laskeVastaus();
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
    
    public int getVastaus() {
        return vastaus;
    }
    
    @Override
    public String toString() {
        return "Laske: "+ekaLuku+" "+laskutoimitus+" "+tokaLuku;
    }

    private int laskeVastaus() {
        if (laskutoimitus==Laskutoimitus.YHTEEN) {
            return ekaLuku+tokaLuku;
        } else if (laskutoimitus==Laskutoimitus.VAHENNYS) {
            return ekaLuku-tokaLuku;
        } else if (laskutoimitus==Laskutoimitus.KERTO) {
            return ekaLuku*tokaLuku;
        } else {
            return ekaLuku/tokaLuku;
        }
    }
}