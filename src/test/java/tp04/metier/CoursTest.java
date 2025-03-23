package tp04.metier;
import java.beans.Transient;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class CoursTest {
    @Test
    void testCours() {
        Jour j = new Jour(1);
        ActionSimple action = new ActionSimple("ActionX");
        Cours cours = new Cours(j, 25.0f);
        action.enregistrerCours(cours);
    
        Assertions.assertEquals(25.0f, action.valeur(j));
    }
    
}
