
package ohht.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TehtavaTest {
    
    Tehtava tehtava;
    int ekaLuku;
    int tokaLuku;
    int syote;
    
    @Before
    public void setUp() {
        tehtava = new Tehtava();
        ekaLuku = tehtava.getEkaLuku();
        tokaLuku = tehtava.getTokaLuku();
        syote = tehtava.getKayttajanSyottamaVastaus();
    }

    @Test
    public void merkkijonoesitysMeneeOikein() {
        Laskutoimitus laskutoimitus = tehtava.getLaskutoimitus();
        assertEquals(ekaLuku+" "+laskutoimitus+" "+tokaLuku+" = ", tehtava.toString());
    }
    
    @Test
    public void yhteenlaskuAntaaOikeanVastauksen() {
        if (tehtava.getLaskutoimitus()==Laskutoimitus.YHTEEN) {
            assertEquals(ekaLuku+tokaLuku, tehtava.getVastaus());
        }
    }
    
    @Test
    public void vahennyslaskuAntaaOikeanVastauksen() {
        if (tehtava.getLaskutoimitus()==Laskutoimitus.VAHENNYS) {
            assertEquals(ekaLuku-tokaLuku, tehtava.getVastaus());
        }
    }
    
    @Test
    public void kertolaskuAntaaOikeanVastauksen() {
        if (tehtava.getLaskutoimitus()==Laskutoimitus.KERTO) {
            assertEquals(ekaLuku*tokaLuku, tehtava.getVastaus());
        }
    }
    
    @Test
    public void kayttajanSyoteTallennetaan() {
        tehtava.setKayttajanSyottamaVastaus(4);
        assertEquals(4, tehtava.getKayttajanSyottamaVastaus());
    }
    
}