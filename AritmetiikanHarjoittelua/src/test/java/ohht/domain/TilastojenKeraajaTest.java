
package ohht.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TilastojenKeraajaTest {
    
    TilastojenKeraaja tilastojenKeraaja;
    
    @Before
    public void setUp() {
        tilastojenKeraaja = new TilastojenKeraaja();
        
        for (int i = 0; i < 4; i++) {
            tilastojenKeraaja.lisaaVastaus();
        }
        
        for (int i = 0; i < 6; i++) {
            tilastojenKeraaja.lisaaVaaraVastaus();
        }
        
        for (int i = 0; i < 8; i++) {
            tilastojenKeraaja.lisaaPeruskierros();
        }
        
        for (int i = 0; i < 5; i++) {
            tilastojenKeraaja.lisaaUusintakierros();
        }
    }

    @Test
    public void oikeatVastauksetLisataanOikein() {
        assertEquals(4, tilastojenKeraaja.getOikeinVastatut());
    }
    
    @Test
    public void vaaratVastauksetLisataanOikein() {
        assertEquals(6, tilastojenKeraaja.getVaarinVastatut());
    }
    
    @Test
    public void kumpikinVastausTallentuu() {
        assertEquals(10, tilastojenKeraaja.getVastatut());
    }
    
    @Test
    public void peruskierroksetTallentuvatOikein() {
        assertEquals(8, tilastojenKeraaja.getPeruskierrokset());
    }
    
    @Test
    public void uusintakierroksetTallentuvatOikein() {
        assertEquals(5, tilastojenKeraaja.getUusintakierrokset());
    }
    
    @Test
    public void kierroksenVaaratVastauksetNollaantuvat() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(0, tilastojenKeraaja.getVaarinVastatut());
    }
    
    @Test
    public void kierroksenKaikkiVastauksetNollaantuvat() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(0, tilastojenKeraaja.getVastatut());
    }
}
