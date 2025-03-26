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
        Client client = new Client("Dupont", "Jean");//creer un client
        Assertions.assertEquals("Dupont", client.getNom());//verifier le nom
        Assertions.assertEquals("Jean", client.getPrenom());//verifier le prenom
    }

    //Tester si les champs du client ont des valeurs par défaut après la création.
    @Test
    void testConstructorAndGetters() {
        Client c = new Client("Jean", "Dupont");

        Assertions.assertEquals("Jean", c.getNom());
        Assertions.assertEquals("Dupont", c.getPrenom());
        Assertions.assertNull(c.getAdresse());//verifier l'adresse = null
        Assertions.assertFalse(c.isConnected());//verifier la connexion = false
    }

    //Tester les setters, si les valeurs sont bien ajoutées.
    @Test
    void testSetters() {
        Client c = new Client("Test", "User");

        //ajouter les valeurs
        c.setAdresse("123 Rue");
        c.setVille("Paris");
        c.setCodePostal("75000");
        c.setTelephone("0600000000");
        c.setEmail("test@email.com");
        c.setDateNaissance("2000-01-01");
        c.setPassword("secret");

        //verifier les valeurs
        Assertions.assertEquals("123 Rue", c.getAdresse());
        Assertions.assertEquals("Paris", c.getVille());
        Assertions.assertEquals("75000", c.getCodePostal());
        Assertions.assertEquals("0600000000", c.getTelephone());
        Assertions.assertEquals("test@email.com", c.getEmail());
        Assertions.assertEquals("2000-01-01", c.getDateNaissance());
        Assertions.assertEquals("secret", c.getPassword());
    }

    //Tester la connexion et la déconnexion du client.
    @Test
    void testConnexionStatus() {
        Client c = new Client("A", "B");

        //verifier la connexion, si le client est connecté
        c.setConnected();
        Assertions.assertTrue(c.isConnected());

        //verifier la deconnexion, si le client est deconnecté
        c.setDisconnected();
        Assertions.assertFalse(c.isConnected());
    }

    //Tester l'achat d'un portefeuille par un client.
    @Test
    void testAcheterPortefeuille() {
        Client c = new Client("A", "B");
        Portefeuille p = new Portefeuille();

        c.AcheterPortefueille(p);

        Assertions.assertTrue(true); // no exception
    }

    //Tester l'information d'un portefeuille par un client.
    @Test
    void testToStringFormat() {

        //ajouter les valeurs
        Client c = new Client("Nom", "Prenom");
        c.setAdresse("Adr");
        c.setVille("Ville");
        c.setCodePostal("12345");
        c.setTelephone("0123456789");
        c.setEmail("mail@example.com");
        c.setDateNaissance("1990-01-01");
        c.setPassword("mdp");

        //verifier les valeurs
        String result = c.toString();
        Assertions.assertTrue(result.contains("Nom"));
        Assertions.assertTrue(result.contains("Prenom"));
        Assertions.assertTrue(result.contains("mail@example.com"));
    }

    

}
