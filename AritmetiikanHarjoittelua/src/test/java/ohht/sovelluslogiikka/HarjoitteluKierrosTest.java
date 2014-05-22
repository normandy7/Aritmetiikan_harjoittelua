
package ohht.sovelluslogiikka;

import java.util.List;
import ohht.sovelluslogiikka.HarjoitteluKierros;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HarjoitteluKierrosTest {
    
    public HarjoitteluKierrosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriLuoOikeankokoisenListan() {
        HarjoitteluKierros kierros = new HarjoitteluKierros();
        List<Tehtava> lista = kierros.getTehtavat();
        
        assertEquals(10, lista.size());
    }
}
