package tp04.metier;
import java.beans.Transient;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class ClientTest {
    @Test
    void testClientGetters() {
        Client client = new Client("Dupont", "Jean");
        Assertions.assertEquals("Dupont", client.getNom());
        Assertions.assertEquals("Jean", client.getPrenom());
    }
    
}
