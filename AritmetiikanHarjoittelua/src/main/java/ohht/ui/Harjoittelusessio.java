
package ohht.ui;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
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
     * Peruskierros-luokkaa tarvitaan ennen ensimmäistä tapahtumaa, joten uusi Peruskierros-
     * ilmentymä luodaan Harjoittelusession parametrittomassa konstruktorissa.
     */
    public Harjoittelusessio() {
        peruskierros = new Peruskierros();
    }

    @Override
    public void run() {
        frame = new JFrame("Arithmetic Loop");
        
        frame.setPreferredSize(new Dimension(400, 160));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(4,1));
        
        JLabel ilmoituskentta = new JLabel("Solve the tasks one at a time.");
        JLabel tilastokentta = new JLabel("(Your statistics will show here.)");
        
        String ekaTehtava = peruskierros.getTehtavat().get(0).toString();
        JLabel tehtavakentta = new JLabel("1. "+ekaTehtava);

        JPanel syottokomponentit = new JPanel(new GridLayout(1,3));
        JTextField syottokentta = new JTextField();
        JButton vastausnappi = new JButton("Submit");
        JButton uusiPeruskierrosnappi = new JButton("New Round");
        uusiPeruskierrosnappi.setEnabled(false);
        
        TapahtumienKuuntelija kuuntelija = new TapahtumienKuuntelija(ilmoituskentta, tilastokentta, tehtavakentta, syottokentta, vastausnappi, uusiPeruskierrosnappi, peruskierros);
        vastausnappi.addActionListener(kuuntelija);
        uusiPeruskierrosnappi.addActionListener(kuuntelija);
        
        syottokomponentit.add(syottokentta);
        syottokomponentit.add(vastausnappi);
        syottokomponentit.add(uusiPeruskierrosnappi);
        
        container.add(ilmoituskentta);
        container.add(tilastokentta);
        container.add(tehtavakentta);
        container.add(syottokomponentit);
    }

    public JFrame getFrame() {
        return frame;
    }
}