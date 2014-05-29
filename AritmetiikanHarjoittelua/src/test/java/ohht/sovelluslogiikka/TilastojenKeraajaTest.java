
package ohht.sovelluslogiikka;

import ohht.sovelluslogiikka.TilastojenKeraaja;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TilastojenKeraajaTest {
    
    TilastojenKeraaja tilastojenKeraaja;
    
    @Before
    public void setUp() {
        tilastojenKeraaja = new TilastojenKeraaja();
        
        for (int i = 0; i < 4; i++) {
            tilastojenKeraaja.lisaaOikeaVastaus();
        }
        
        for (int i = 0; i < 6; i++) {
            tilastojenKeraaja.lisaaVaaraVastaus();
        }
    }

    @Test
    public void oikeatVastauksetLisataanOikein() {
        assertEquals(4,tilastojenKeraaja.getOikeinVastatut());
    }
    
    @Test
    public void vaaratVastauksetLisataanOikein() {
        assertEquals(6,tilastojenKeraaja.getVaarinVastatut());
    }
    
    @Test
    public void kumpikinVastausTallentuu() {
        assertEquals(10,tilastojenKeraaja.getVastatut());
    }
}
