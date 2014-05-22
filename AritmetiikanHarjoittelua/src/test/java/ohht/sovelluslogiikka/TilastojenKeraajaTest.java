
package ohht.sovelluslogiikka;

import ohht.sovelluslogiikka.TilastojenKeraaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    @After
    public void tearDown() {
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
    
//    @Test
//    public void vastausProsenttiLasketaanOikein() {
//        assertEquals(40.0,tilastojenKeraaja.oikeidenVastauksienOsuusProsentteina());
//    }
}
