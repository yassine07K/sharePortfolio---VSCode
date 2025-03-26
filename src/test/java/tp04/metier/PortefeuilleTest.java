package tp04.metier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp04.metier.Action;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;

import java.util.HashMap;
import java.util.Map;

public class PortefeuilleTest {

    private Portefeuille portefeuille;
    private ActionSimple action1;
    private ActionSimple action2;
    private Jour jour;

    @BeforeEach
    public void setUp() {
        portefeuille = new Portefeuille("Portefeuille1");
        action1 = new ActionSimple("Action1");
        action2 = new ActionSimple("Action2");
        jour = new Jour(1,1); 
    }

    @Test
    public void testConstructeur() {
        assertEquals("Portefeuille1", portefeuille.getNomPortefeuille(),"Le nom du portefeuille doit être Portefeuille1");
        assertTrue(portefeuille.getMapLignes().isEmpty());
    }

    @Test
    public void testAcheter() {
        portefeuille.acheter(action1, 10);
        assertTrue(portefeuille.getMapLignes().containsKey(action1));
        assertEquals(10, portefeuille.getMapLignes().get(action1).intValue(),"La quantité doit être égale à 10");

        portefeuille.acheter(action1, 5);
        assertEquals(15, portefeuille.getMapLignes().get(action1).intValue(),"La quantité doit être ajoutée à la quantité existante");
    }

    @Test
    public void testVendre() {
        portefeuille.acheter(action1, 10);
        portefeuille.vendre(action1, 5);
        assertEquals(5, portefeuille.getMapLignes().get(action1).intValue());

        portefeuille.vendre(action1, 5);
        assertFalse(portefeuille.getMapLignes().containsKey(action1),"L'action doit être supprimée du portefeuille");
    }



    @Test
    public void testToString() {
        portefeuille.acheter(action1, 10);
        String expected = "Portefeuille1{Action1=10}";
        assertEquals(expected, portefeuille.toString(),"Erreur dans la méthode toString");
    }


}