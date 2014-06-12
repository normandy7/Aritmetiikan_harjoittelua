
package ohht.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class LaskutoimitusTest {

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