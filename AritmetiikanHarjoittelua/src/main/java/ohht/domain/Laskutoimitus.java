
package ohht.domain;

/**
 * Laskutoimituksia on kolme, joista yksi valitaan satunnaisesti kunkin tehtävän
 * laskutoimitukseksi.
 */

public enum Laskutoimitus {
    YHTEEN,
    VAHENNYS,
    KERTO;
    
    public static Laskutoimitus arvoLaskutoimitus() {
        return values()[(int) (Math.random()*values().length)];
    }
    
    @Override
    public String toString() {
        if (this==YHTEEN) {
            return "+";
        } else if (this==VAHENNYS) {
            return "-";
        } else {
            return "×";
        }
    }
    
}