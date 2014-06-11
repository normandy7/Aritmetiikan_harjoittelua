
package ohht.domain;

/**
 * Laskutoimituksia on kolme, joista yksi valitaan satunnaisesti kunkin tehtävän
 * laskutoimitukseksi.
 */

public enum Laskutoimitus {
    YHTEEN,
    VAHENNYS,
    KERTO;
    
    /**
     * Metodi arpoo laskutoimituksen satunnaisesti ja palauttaa sen.
     * @return laskutoimitus
     */
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