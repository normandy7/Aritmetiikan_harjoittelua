
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeruskierrosTest {
    Peruskierros pk;
    List<Tehtava> tehtavat;
    Tehtava eka;
    Tehtava vika;
    
    @Before
    public void setUp() {
        pk = new Peruskierros();
        tehtavat = pk.getTehtavat();
        eka = pk.getTehtavat().get(0);
        vika = pk.getTehtavat().get(9);
    }
    
    @Test
    public void kierroksessaKymmenenTehtavaa() {
        assertEquals(10, tehtavat.size());
    }
    
    @Test
    public void oikeaVastausLisataanOikein() {
        pk.lisaaOikeaVastaus();
        pk.lisaaOikeaVastaus();
        assertEquals(2, pk.getOikeinVastatut());
    }
    
    @Test
    public void oikeaIndeksiPalautetaan() {
        assertEquals(0, pk.getTehtavanIndeksi(eka));
    }
    
    @Test
    public void huomaaJosKierroksenViimeinenTehtava() {
        assertEquals(true, pk.onkoKierroksenViimeinenTehtava(vika));
    }
    
    @Test
    public void huomaaJosEiKierroksenViimeinenTehtava() {
        assertEquals(false, pk.onkoKierroksenViimeinenTehtava(eka));
    }
    
    @Test
    public void alkuilmoitusTulostuu() {
        assertEquals("Solve the ten tasks one at a time.", pk.alkuilmoitus());
    }
}
