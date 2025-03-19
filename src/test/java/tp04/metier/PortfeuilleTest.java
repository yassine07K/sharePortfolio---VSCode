package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PortfeuilleTest {
    //Test sur l'achat d'actions avec une quantité inférieure ou égale à 0.
    @Test
    void testAcheterActionNegative() {
        Client c = new Client("testnom","testprenom");
        Portefeuille p = new Portefeuille();
        Action action = new ActionSimple("TestA");
        
        //le situation = 10
        p.acheter(action, 10);
        assertEquals(10,p.mapLignes.get(action), "le qte doit plus de 0!");
        
        //le situation < 0
        // p.acheter(action, -5);
        // assertNull(p.mapLignes.get(action), "le qte doit plus de 0!");
    }



}