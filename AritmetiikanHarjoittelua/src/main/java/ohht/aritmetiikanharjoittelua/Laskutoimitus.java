
package ohht.aritmetiikanharjoittelua;

public enum Laskutoimitus {
    YHTEEN,
    VAHENNYS,
    KERTO,
    JAKO;
    
    public static Laskutoimitus arvoLaskutoimitus() {
        return values()[(int) (Math.random() * values().length)];
    }
    
    @Override
    public String toString() {
        if (this==VAHENNYS) {
            return "-";
        } else if (this==KERTO) {
            return "×";
        } else if (this==KERTO) {
            return "÷";
        }
        return "+";
    }
    
}