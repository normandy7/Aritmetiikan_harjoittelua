
package ohht.sovelluslogiikka;

import ohht.domain.Tehtava;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UusintakierrosTest {
    Uusintakierros uk;
    Tehtava t1;
    Tehtava t2;
    Tehtava t3;
    List<Tehtava> lista;
    
    @Before
    public void setUp() {
        uk = new Uusintakierros();
        t1 = new Tehtava();
        t2 = new Tehtava();
        t3 = new Tehtava();
        
        uk.lisaaUusittavaksi(t1);
        uk.lisaaUusittavaksi(t2);
        uk.lisaaUusittavaksi(t3);
        
        lista = new ArrayList<>();
        
    }

    @Test
    public void tehtavienLisaaminenToimii() {
        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
        
        assertEquals(lista, uk.getTehtavat());
    }

    @Test
    public void oikeaIndeksiPalautetaan() {
        assertEquals(0,uk.getTehtavanIndeksi(t1));
    }
    
    @Test
    public void huomaaJosKierroksenViimeinenTehtava() {
        assertEquals(true, uk.onkoKierroksenViimeinenTehtava(t3));
    }
    
    @Test
    public void huomaaJosEiKierroksenViimeinenTehtava() {
        assertEquals(false, uk.onkoKierroksenViimeinenTehtava(t2));
    }
    
    @Test
    public void viestiValitaanOikeinKunYksiUusittava() {
        Uusintakierros uk2 = new Uusintakierros();
        uk2.lisaaUusittavaksi(t1);
        
        assertEquals("Guess no-one's perfect. Try this one again:", uk2.uusittavienMaarastaRiippuvaViesti());
    }
    
    @Test
    public void viestiValitaanOikeinKunKaksiUusittavaa() {
        Uusintakierros uk3 = new Uusintakierros();
        uk3.lisaaUusittavaksi(t1);
        uk3.lisaaUusittavaksi(t2);
        
        assertEquals("Numbers not your thing? Try these two again:", uk3.uusittavienMaarastaRiippuvaViesti());
    }
    
    @Test
    public void viestiValitaanOikeinKunKolmeUusittavaa() {
        assertEquals("Can't tell if trolling. Try these again:", uk.uusittavienMaarastaRiippuvaViesti());
    }
    
    @Test
    public void viestiValitaanOikeinKunVaikkapaSeitsemanUusittavaa() {
        for (int i = 0; i < 4; i++) {
            uk.lisaaUusittavaksi(new Tehtava());
        }
        
        assertEquals("Can't tell if trolling. Try these again:", uk.uusittavienMaarastaRiippuvaViesti());
    }
    
    @Test
    public void uusittavatTyhjentyvat() {
        uk.tyhjennaUusittavat();
        assertEquals(true, uk.getTehtavat().isEmpty());
    }
}
