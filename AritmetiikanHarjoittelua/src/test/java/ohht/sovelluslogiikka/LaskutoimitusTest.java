
package ohht.sovelluslogiikka;

import ohht.sovelluslogiikka.Laskutoimitus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskutoimitusTest {
    
//    Laskutoimitus lt;
    
    @Before
    public void setUp() {
//        lt = Laskutoimitus.arvoLaskutoimitus();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void yhteenlaskuTulostuuOikein() {
        assertEquals("+",Laskutoimitus.YHTEEN.toString());
    }
    
    @Test
    public void vahennyslaskuTulostuuOikein() {
        assertEquals("-",Laskutoimitus.VAHENNYS.toString());
    }
    
    @Test
    public void kertolaskuTulostuuOikein() {
        assertEquals("×",Laskutoimitus.KERTO.toString());
    }
    
    @Test
    public void jakolaskuTulostuuOikein() {
        assertEquals("÷",Laskutoimitus.JAKO.toString());
    }
}
