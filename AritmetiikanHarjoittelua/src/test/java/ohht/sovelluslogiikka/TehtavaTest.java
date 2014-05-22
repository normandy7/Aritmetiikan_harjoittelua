package ohht.sovelluslogiikka;


import ohht.sovelluslogiikka.Laskutoimitus;
import ohht.sovelluslogiikka.Tehtava;
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
    
    @Test
    public void vastausLasketaanOikein() {
        Tehtava tehtava = new Tehtava();
        int eka = tehtava.getEkaLuku();
        int toka = tehtava.getTokaLuku();
        int vastaus = 0;
        
        if (tehtava.getLaskutoimitus()==Laskutoimitus.YHTEEN) {
            vastaus = eka+toka;
        } else if (tehtava.getLaskutoimitus()==Laskutoimitus.VAHENNYS) {
            vastaus = eka-toka;
        } else if (tehtava.getLaskutoimitus()==Laskutoimitus.KERTO) {
            vastaus = eka*toka;
        } else {
            vastaus = eka/toka;
        }
        
        assertEquals(vastaus, tehtava.getVastaus());
    }
}
