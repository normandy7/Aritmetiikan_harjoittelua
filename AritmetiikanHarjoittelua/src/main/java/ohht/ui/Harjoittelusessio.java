
package ohht.ui;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import ohht.sovelluslogiikka.Peruskierros;
import ohht.sovelluslogiikka.Uusintakierros;
import ohht.sovelluslogiikka.TilastojenKeraaja;

/**
 * Harjoittelusessio on ohjelman Runnable -rajapinnan toteuttava luokka.
 * Se luo graafisen käyttöliittymän, jossa on kolme tekstialuetta, tekstin syötekenttä
 * sekä vastausnappi.
 */

public class Harjoittelusessio implements Runnable {
    private JFrame frame;
    private Peruskierros peruskierros;
    private final Uusintakierros uusintakierros;
    private final TilastojenKeraaja tilastojenKeraaja;
    
    public Harjoittelusessio() {
        peruskierros = new Peruskierros();
        uusintakierros = new Uusintakierros();
        tilastojenKeraaja = new TilastojenKeraaja();
        
    }

    @Override
    public void run() {
        frame = new JFrame("AHa - Aritmetiikan Harjoittelua");
        
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(4,1));
        
        JLabel tekstikentta = new JLabel("Welcome!");
        JLabel ilmoituskentta = new JLabel("[notifications appear here]");
        JLabel tehtavakentta = new JLabel("1. "+peruskierros.getTehtavat().get(0).toString());
        
        JPanel vastauskomponentit = new JPanel(new GridLayout(1,2));
        JTextField syotekentta = new JTextField();
        JButton vastausnappi = new JButton("Submit");
        
        VastauksenKuuntelija kuuntelija = new VastauksenKuuntelija(tekstikentta, ilmoituskentta, tehtavakentta, syotekentta, vastausnappi, peruskierros, uusintakierros, tilastojenKeraaja);
        vastausnappi.addActionListener(kuuntelija);
        
        vastauskomponentit.add(syotekentta);
        vastauskomponentit.add(vastausnappi);
        
        container.add(tekstikentta);
        container.add(ilmoituskentta);
        container.add(tehtavakentta);
        container.add(vastauskomponentit);
        
    }

    public JFrame getFrame() {
        return frame;
    }
}