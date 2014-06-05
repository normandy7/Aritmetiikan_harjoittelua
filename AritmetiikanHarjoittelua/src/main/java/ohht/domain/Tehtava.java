
package ohht.domain;

import java.util.Random;

/**
 * Luokan ilmentymä kuvaa yhtä tehtävää, jolle valitaan satunnaisesti kaksi lukua
 * sekä suoritettava laskutoimitus.
 */

public class Tehtava {
    private int ekaLuku;
    private int tokaLuku;
    private Laskutoimitus laskutoimitus;
    
    public Tehtava() {
        Random arpa = new Random();
        this.ekaLuku = arpa.nextInt(10);
        this.tokaLuku = arpa.nextInt(10);
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
    
    public int getVastaus() {
        if (laskutoimitus==Laskutoimitus.YHTEEN) {
            return ekaLuku+tokaLuku;
        } else if (laskutoimitus==Laskutoimitus.VAHENNYS) {
            return ekaLuku-tokaLuku;
        } else {
            return ekaLuku*tokaLuku;
        }
    }
    
    @Override
    public String toString() {
        return ekaLuku+" "+laskutoimitus+" "+tokaLuku+" = ";
    }

}