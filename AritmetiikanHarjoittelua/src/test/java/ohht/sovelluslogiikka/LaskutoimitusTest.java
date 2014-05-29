
package ohht.sovelluslogiikka;

import ohht.sovelluslogiikka.Laskutoimitus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskutoimitusTest {
    
    @Before
    public void setUp() {
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
    
}