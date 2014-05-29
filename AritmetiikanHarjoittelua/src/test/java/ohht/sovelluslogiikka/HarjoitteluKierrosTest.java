
package ohht.sovelluslogiikka;

import java.util.List;
import ohht.sovelluslogiikka.HarjoitteluKierros;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HarjoitteluKierrosTest {
    
    public HarjoitteluKierrosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void konstruktoriLuoOikeankokoisenListan() {
        HarjoitteluKierros kierros = new HarjoitteluKierros();
        List<Tehtava> lista = kierros.getTehtavat();
        
        assertEquals(10, lista.size());
    }
}
