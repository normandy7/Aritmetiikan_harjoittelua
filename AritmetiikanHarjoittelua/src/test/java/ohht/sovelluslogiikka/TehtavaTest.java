
package ohht.sovelluslogiikka;

import ohht.sovelluslogiikka.Laskutoimitus;
import ohht.sovelluslogiikka.Tehtava;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TehtavaTest {
    
    Tehtava tehtava;
    int ekaLuku;
    int tokaLuku;
    
    @Before
    public void setUp() {
        tehtava = new Tehtava();
        ekaLuku = tehtava.getEkaLuku();
        tokaLuku = tehtava.getTokaLuku();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void merkkijonoesitysMeneeOikein() {
        Laskutoimitus lt = tehtava.getLaskutoimitus();
        assertEquals("Laske: "+ekaLuku+" "+lt+" "+tokaLuku, tehtava.toString());
    }
    
    @Test
    public void vastausLasketaanOikein() {
        int vastaus = 0;
        
        if (tehtava.getLaskutoimitus()==Laskutoimitus.YHTEEN) {
            vastaus = ekaLuku+tokaLuku;
        } else if (tehtava.getLaskutoimitus()==Laskutoimitus.VAHENNYS) {
            vastaus = ekaLuku-tokaLuku;
        } else if (tehtava.getLaskutoimitus()==Laskutoimitus.KERTO) {
            vastaus = ekaLuku*tokaLuku;
        } else {
            vastaus = ekaLuku/tokaLuku;
        }
        
        assertEquals(vastaus, tehtava.getVastaus());
    }
}
