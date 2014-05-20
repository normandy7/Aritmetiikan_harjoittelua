
package ohht.aritmetiikanharjoittelua;

import java.util.ArrayList;
import java.util.List;

public class HarjoitteluKierros {
    private List<Tehtava> tehtavat;
    
    public HarjoitteluKierros() {
        this.tehtavat = luoTehtavat();
    }
    
    public List<Tehtava> getTehtavat() {
        return tehtavat;
    }

    private List<Tehtava> luoTehtavat() {
        List<Tehtava> tehtavaLista = new ArrayList<Tehtava>();

        for (int i = 0; i < 10; i++) {
            tehtavaLista.add(new Tehtava());
        }

        return tehtavaLista;
    }
}