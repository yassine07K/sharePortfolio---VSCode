package tp04.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import tp04.metier.Cours;
import tp04.metier.Jour;

public class CoursTest {

    /**
     * Vérifie que le constructeur de la classe Cours initialise
     * correctement les attributs jour et valeur.
     * On s'assure que les valeurs passées en paramètre sont bien affectées.
     */
    @Test
    public void testConstructeur() {
        Jour j = new Jour(2025, 50);
        Cours c = new Cours(j, 123.45f);

        assertEquals("Le jour du cours doit être celui passé au constructeur",
                     j, c.getJour());
        assertEquals("La valeur doit être celle passée au constructeur",
                     123.45f, c.getValeur(), 0.0001f);
    }

    /**
     * Vérifie le bon fonctionnement des accesseurs (getters).
     * Le test vérifie que getJour() renvoie le bon objet Jour,
     * et que getValeur() renvoie la valeur correcte.
     */
    @Test
    public void testGetters() {
        Jour j = new Jour(2025, 70);
        Cours c = new Cours(j, 456.78f);

        Jour jourObtenu = c.getJour();
        float valeurObtenue = c.getValeur();

        assertEquals("getJour() doit retourner l'objet Jour correct",
                     j, jourObtenu);
        assertEquals("getValeur() doit retourner la valeur correcte",
                     456.78f, valeurObtenue, 0.0001f);
    }

    /**
     * Vérifie que la méthode setValeur() permet bien de modifier
     * la valeur du cours après sa création.
     * On vérifie que la nouvelle valeur est correctement prise en compte.
     */
    @Test
    public void testSetValeur() {
        Jour j = new Jour(2025, 80);
        Cours c = new Cours(j, 10.0f);

        c.setValeur(999.99f);

        assertEquals("Après setValeur(999.99f), getValeur() doit être 999.99",
                     999.99f, c.getValeur(), 0.0001f);
    }
}
