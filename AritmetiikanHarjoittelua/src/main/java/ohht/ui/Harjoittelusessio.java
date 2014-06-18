
package ohht.ui;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import ohht.domain.TilastojenKeraaja;
import ohht.sovelluslogiikka.Peruskierros;

/**
 * Harjoittelusessio on tämän ohjelman Runnable -rajapinta toteuttava luokka.
 * Se luo graafisen käyttöliittymän, jossa on kolme tekstialuetta, tekstin syöttökenttä,
 * vastauksien lähetysnappi sekä uuden peruskierroksen käynnistävä nappi.
 */
public class Harjoittelusessio implements Runnable {
    /**
     * Harjoittelusession JFrame -oliomuuttuja.
     */
    private JFrame frame;
    /**
     * Harjoittelusession Peruskierros -oliomuuttuja.
     */
    private Peruskierros peruskierros;
    /**
     * Harjoittelusession TilastojenKeraaja -oliomuuttuja.
     */
    private TilastojenKeraaja tilastojenKeraaja;
    
    /**
     * Peruskierros-luokkaa tarvitaan ennen ensimmäistä tapahtumaa, joten uusi Peruskierros-
     * ilmentymä luodaan Harjoittelusession parametrittomassa konstruktorissa.
     */
    public Harjoittelusessio() {
        peruskierros = new Peruskierros();
        tilastojenKeraaja = new TilastojenKeraaja();
    }

    @Override
    public void run() {
        frame = new JFrame("Arithmetic Loop");
        
        frame.setPreferredSize(new Dimension(480, 260));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(8,1));
        
        JLabel ohjeet = new JLabel("Welcome to the Arithmetic Loop! Close the window to exit.");
        
        JLabel ilmoituskentta = new JLabel(peruskierros.alkuilmoitus());
        JLabel aiempiVastaus = new JLabel("");
        
        String ekaTehtava = peruskierros.getTehtavat().get(0).toString();
        JLabel tehtavakentta = new JLabel("1. "+ekaTehtava);

        JPanel syottokomponentit = new JPanel(new GridLayout(1,3));
        JTextField syottokentta = new JTextField();
        JButton vastausnappi = new JButton("Submit");
        JButton uusiPeruskierrosnappi = new JButton("New Round");
        uusiPeruskierrosnappi.setEnabled(false);
        
        JLabel kierroksenTehtavatilastot = new JLabel("Correct answers in this round:");
        JLabel kaikkiTehtavatilastot = new JLabel("Correct answers overall:");
        JLabel kierrostilastot = new JLabel("Completed rounds:");
        
        TapahtumienKuuntelija kuuntelija = new TapahtumienKuuntelija(kierroksenTehtavatilastot, kaikkiTehtavatilastot, kierrostilastot, ilmoituskentta, aiempiVastaus, tehtavakentta, syottokentta, vastausnappi, uusiPeruskierrosnappi, peruskierros, tilastojenKeraaja);
        vastausnappi.addActionListener(kuuntelija);
        uusiPeruskierrosnappi.addActionListener(kuuntelija);
        
        syottokomponentit.add(syottokentta);
        syottokomponentit.add(vastausnappi);
        syottokomponentit.add(uusiPeruskierrosnappi);
        
        container.add(ohjeet);
        container.add(ilmoituskentta);
        container.add(aiempiVastaus);
        container.add(tehtavakentta);
        
        container.add(syottokomponentit);
        
        container.add(kierroksenTehtavatilastot);
        container.add(kaikkiTehtavatilastot);
        container.add(kierrostilastot);
    }

    public JFrame getFrame() {
        return frame;
    }
}