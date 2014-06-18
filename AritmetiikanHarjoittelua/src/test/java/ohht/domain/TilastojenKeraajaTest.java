
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
            tilastojenKeraaja.lisaaOikeaVastaus();
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
        assertEquals(6, tilastojenKeraaja.getNykyisenKierroksenOikeinVastatut());
    }
    
    @Test
    public void kumpikinVastausTallentuu() {
        assertEquals(10, tilastojenKeraaja.getNykyisenKierroksenVastatut());
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
    public void kierroksenOikeatVastauksetNollaantuvat() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(0, tilastojenKeraaja.getNykyisenKierroksenOikeinVastatut());
    }
    
    @Test
    public void kierroksenKaikkiVastauksetNollaantuvat() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(0, tilastojenKeraaja.getNykyisenKierroksenVastatut());
    }
    
    @Test
    public void nollaaminenEiVaikutaVastauksiinYhteensa() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(10, tilastojenKeraaja.getVastatutYhteensa());
    }
    
    @Test
    public void nollaaminenEiVaikutaOikeisiinVastauksiinYhteensa() {
        tilastojenKeraaja.nollaaKierroksenTulos();
        assertEquals(6, tilastojenKeraaja.getOikeinVastatutYhteensa());
    }
}