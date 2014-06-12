
package ohht.ui;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import ohht.domain.TilastojenKeraaja;
import ohht.sovelluslogiikka.Peruskierros;

/**
 * Harjoittelusessio on ohjelman Runnable -rajapinnan toteuttava luokka.
 * Se luo graafisen käyttöliittymän, jossa on kolme tekstialuetta, tekstin syötekenttä
 * sekä vastausnappi.
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
        
        frame.setPreferredSize(new Dimension(470, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(4,1));
        
        JLabel tilastokentta = new JLabel("Welcome! Get started to see your statistics here.");
        JLabel ilmoituskentta = new JLabel(peruskierros.alkuilmoitus());
        
        String ekaTehtava = peruskierros.getTehtavat().get(0).toString();
        JLabel tehtavakentta = new JLabel("1. "+ekaTehtava);

        JPanel syottokomponentit = new JPanel(new GridLayout(1,3));
        JTextField syottokentta = new JTextField();
        JButton vastausnappi = new JButton("Submit");
        JButton uusiPeruskierrosnappi = new JButton("New Round");
        uusiPeruskierrosnappi.setEnabled(false);
        
        TapahtumienKuuntelija kuuntelija = new TapahtumienKuuntelija(tilastokentta, ilmoituskentta, tehtavakentta, syottokentta, vastausnappi, uusiPeruskierrosnappi, peruskierros, tilastojenKeraaja);
        vastausnappi.addActionListener(kuuntelija);
        uusiPeruskierrosnappi.addActionListener(kuuntelija);
        
        syottokomponentit.add(syottokentta);
        syottokomponentit.add(vastausnappi);
        syottokomponentit.add(uusiPeruskierrosnappi);
        
        container.add(tilastokentta);
        container.add(ilmoituskentta);
        container.add(tehtavakentta);
        container.add(syottokomponentit);
    }

    public JFrame getFrame() {
        return frame;
    }
}