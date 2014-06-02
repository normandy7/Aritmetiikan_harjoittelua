
package ohht.sovelluslogiikka;

import java.util.List;
import ohht.sovelluslogiikka.Harjoittelukierros;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HarjoittelukierrosTest {
    
    public HarjoittelukierrosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void konstruktoriLuoOikeankokoisenListan() {
        Harjoittelukierros kierros = new Harjoittelukierros();
        List<Tehtava> lista = kierros.getTehtavat();
        
        assertEquals(10, lista.size());
    }
}
