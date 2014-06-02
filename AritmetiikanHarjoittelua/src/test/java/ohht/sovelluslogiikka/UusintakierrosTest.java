
package ohht.sovelluslogiikka;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UusintakierrosTest {
    Uusintakierros uk;
    Tehtava t1;
    Tehtava t2;
    Tehtava t3;
    
    @Before
    public void setUp() {
        uk = new Uusintakierros();
        t1 = new Tehtava();
        t2 = new Tehtava();
        t3 = new Tehtava();
    }

    @Test
    public void testLisaaUusittavaksi() {
        uk.lisaaUusittavaksi(t1);
        uk.lisaaUusittavaksi(t2);
        uk.lisaaUusittavaksi(t3);
        List<Tehtava> uusittavat = new ArrayList<>();
        uusittavat.add(t1);
        uusittavat.add(t2);
        uusittavat.add(t3);
        
        assertEquals(uusittavat,uk.getUusittavatTehtavat());
    }

    @Test
    public void testSuorita() {

    }
    
}
