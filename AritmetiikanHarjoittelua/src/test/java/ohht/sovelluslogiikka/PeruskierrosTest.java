
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeruskierrosTest {
    
    public PeruskierrosTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void konstruktoriLuoOikeankokoisenListan() {
        Peruskierros kierros = new Peruskierros();
        List<Tehtava> lista = kierros.getTehtavat();
        
        assertEquals(10, lista.size());
    }
}
