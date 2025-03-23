/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tp04.metier;
import java.beans.Transient;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class ClientTest {
    //test de la méthode getNom
    @Test
    void testClientGetters() {
        Client client = new Client("Dupont", "Jean");
        Assertions.assertEquals("Dupont", client.getNom());
        Assertions.assertEquals("Jean", client.getPrenom());
    }

    //
    @Test
    void testConstructorAndGetters() {
        Client c = new Client("Jean", "Dupont");

        Assertions.assertEquals("Jean", c.getNom());
        Assertions.assertEquals("Dupont", c.getPrenom());
        Assertions.assertNull(c.getAdresse());
        Assertions.assertFalse(c.isConnected());
    }

    @Test
    void testSetters() {
        Client c = new Client("Test", "User");

        c.setAdresse("123 Rue");
        c.setVille("Paris");
        c.setCodePostal("75000");
        c.setTelephone("0600000000");
        c.setEmail("test@email.com");
        c.setDateNaissance("2000-01-01");
        c.setPassword("secret");

        Assertions.assertEquals("123 Rue", c.getAdresse());
        Assertions.assertEquals("Paris", c.getVille());
        Assertions.assertEquals("75000", c.getCodePostal());
        Assertions.assertEquals("0600000000", c.getTelephone());
        Assertions.assertEquals("test@email.com", c.getEmail());
        Assertions.assertEquals("2000-01-01", c.getDateNaissance());
        Assertions.assertEquals("secret", c.getPassword());
    }
    @Test
    void testConnexionStatus() {
        Client c = new Client("A", "B");

        c.setConnected();
        Assertions.assertTrue(c.isConnected());

        c.setDisconnected();
        Assertions.assertFalse(c.isConnected());
    }

    @Test
    void testAcheterPortefeuille() {
        Client c = new Client("A", "B");
        Portefeuille p = new Portefeuille();

        c.AcheterPortefueille(p);

        Assertions.assertTrue(true); // no exception
    }

    @Test
    void testToStringFormat() {
        Client c = new Client("Nom", "Prenom");
        c.setAdresse("Adr");
        c.setVille("Ville");
        c.setCodePostal("12345");
        c.setTelephone("0123456789");
        c.setEmail("mail@example.com");
        c.setDateNaissance("1990-01-01");
        c.setPassword("mdp");

        String result = c.toString();
        Assertions.assertTrue(result.contains("Nom"));
        Assertions.assertTrue(result.contains("Prenom"));
        Assertions.assertTrue(result.contains("mail@example.com"));
    }

    

}
