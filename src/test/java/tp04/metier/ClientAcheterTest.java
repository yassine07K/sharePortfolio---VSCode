package tp04.metier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClientAcheterTest {


    @Test
    void testAcheter() {
        final Client client = new Client("nom", "prenom");
        final Action action = new ActionImpl("Action1");

        client.acheter(action, 10);

        assertEquals(true, client, "L'action achetée doit être la même que celle passée en paramètre");
    }

    public class ActionImpl extends Action {


        public ActionImpl(String libelle) {
            super(libelle);
            
            
        }

        public float valeur(Jour j) {
            return 0.0F;
        }
    }

}
