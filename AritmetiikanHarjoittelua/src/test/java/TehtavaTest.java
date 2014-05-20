
import ohht.aritmetiikanharjoittelua.Laskutoimitus;
import ohht.aritmetiikanharjoittelua.Tehtava;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TehtavaTest {
    
    public TehtavaTest() {
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
    public void merkkijonoesitysMeneeOikein() {
        Tehtava tehtava = new Tehtava();
        int eka = tehtava.getEkaLuku();
        int toka = tehtava.getTokaLuku();
        Laskutoimitus lt = tehtava.getLaskutoimitus();
        
        assertEquals("Laske: "+eka+" "+lt+" "+toka, tehtava.toString());
    }
}
