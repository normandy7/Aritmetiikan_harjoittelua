package ohht.aritmetiikanharjoittelua;

import ohht.sovelluslogiikka.Tehtava;
import ohht.sovelluslogiikka.HarjoitteluKierros;

/**
 * Ohjelma generoi käyttäjälle laskuharjoituksia. Käyttäjälle
 * näytetään laskutoimitus, jonka yhteydessä ratkaisu syötetään.
 * Kun vastaus on annettu, ruudulle ilmestyy uusi tehtävä.
 * 
 * Tehtävät annetaan kymmenen tehtävän ryhmissä. Ryhmän päätteeksi
 * käyttäjälle annetaan uudelleen ratkottavaksi ne tehtävät, joihin
 * vastattiin väärin. Uusintakierroksen aikana seuraavaan tehtävään
 * ei päästä, kunnes tehtävään on annettu oikea vastaus.
 * 
 * Käyttäjän antamat vastaukset kerätään tilastoiksi, joita käyttäjän
 * on mahdollista tarkastella tehtäväryhmän päätteeksi. Uusintaan
 * menneistä tehtävistä ei kerätä tilastoja.
 * 
 */

public class Main {
    public static void main( String[] args ) {
        HarjoitteluKierros kierros = new HarjoitteluKierros();
        
        for (Tehtava t: kierros.getTehtavat()) {
            System.out.println(t);
        }
    }
}