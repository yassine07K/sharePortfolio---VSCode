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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private final Client client = new Client("nom", "prenom");
    private final Action action = new ActionImpl("Action1");
    
    private ActionSimple actions1 = new ActionSimple("Action2");
    private ActionSimple actions2 = new ActionSimple("Action3");
    private ActionSimple actions3 = new ActionSimple("Action4");
    private ActionSimple actions4 = new ActionSimple("Action5");

    private ActionComposee AC = new ActionComposee("ActionComposee1");

    private Jour j = new Jour(1, 1);
    private Jour j1 = new Jour(2,2);
    private Jour j2 = new Jour(2, 3);
    
    private Portefeuille portefeuille;

    @BeforeEach
    public void setUp() {
        actions1.enrgCours(j, 10);
        actions1.enrgCours(j1, 20);

        actions2.enrgCours(j2, 22);

        actions3.enrgCours(j, 30);
        actions3.enrgCours(j2, 40);

        actions4.enrgCours(j2, 1);

        
        AC.enrgComposition(actions4, 30);
        AC.enrgComposition(actions1, 70);
        client.creerPortefeuille("monPorte");
        portefeuille = client.getPortefeuille(0);
    }


    @Test
    public void testAcheter1() {
        assertEquals(true,client.creerPortefeuille("monPorte"), "une erreur est survenue lors de la création du portefeuille");
        assertEquals(true, client.acheter("monPorte",action, 10), "L'action achetée doit être la même que celle passée en paramètre");

        assertEquals(true,client.acheter("monPorte", AC, 10), "L'action achetée doit être la même que celle passée en paramètre");
        assertEquals(true,client.acheter("monPorte", actions1, 10), "L'action achetée doit être la même que celle passée en paramètre");
        assertEquals(true,client.acheter("monPorte", actions4, 10), "L'action achetée doit être la même que celle passée en paramètre");

        assertEquals(true, client.removePortefeuille("monPorte"), "Le portefeuille doit être supprimé");

    }
    @Test
    public void testConstructeur() {
        assertEquals("nom", client.getNom());
        assertEquals("prenom", client.getPrenom());
        assertFalse(client.isConnected());
    }

    @Test
    public void testGetPortefeuille() {
        assertEquals(1, client.getPortefeuilles().size());
        client.creerPortefeuille("Portefeuille1");
        assertEquals(2, client.getPortefeuilles().size());
        assertEquals("Portefeuille1", client.getPortefeuille(1).getNomPortefeuille(),"Erreur dans la méthode getPortefeuille");
    } 

    @Test
    public void testGetPortefeuilles(){
        assertEquals(1, client.getPortefeuilles().size());
        client.creerPortefeuille("Portefeuille1");
        assertEquals(2, client.getPortefeuilles().size());
    }


    @Test
    public void testConnexion() {
        client.setConnected();
        assertTrue(client.isConnected());
        client.setDisconnected();
        assertFalse(client.isConnected());
    }

    @Test
    public void testCreerPortefeuille() {
        assertTrue(client.creerPortefeuille("Portefeuille1"));
        assertEquals(2, client.getPortefeuilles().size(),"Pas bien creer portefeuille");
    }

    @Test
    public void testRemovePortefeuille() {
        client.creerPortefeuille("Portefeuille1");
        assertTrue(client.removePortefeuille("Portefeuille1"));
        assertEquals(1, client.getPortefeuilles().size(),"Pas bien supprimer portefeuille");
    }

    @Test
    public void testGetLocalisationPortefeuilleParNom() {
        client.creerPortefeuille("Portefeuille1");
        assertEquals(1, client.getLocalisationPortefeuilleParNom("Portefeuille1"));
        assertEquals(-1, client.getLocalisationPortefeuilleParNom("Portefeuille2"),"Portefeuille2 n'existe pas");
    }

    @Test
    public void testAcheter() {
        client.creerPortefeuille("Portefeuille1");
        assertTrue(client.acheter("Portefeuille1", action, 10));
        assertFalse(client.acheter("Portefeuille2", action, 10));
        assertFalse(client.acheter("Portefeuille1", action, 0));
    }

    @Test
    public void testExisteAction() {
        client.creerPortefeuille("Portefeuille1");
        client.acheter("Portefeuille1", action, 10);
        assertTrue(client.existeAction("Portefeuille1", action));
        assertFalse(client.existeAction("Portefeuille1", new ActionSimple("Action2")));
        assertFalse(client.existeAction("Portefeuille2", action)); 
    }

    @Test
    public void testToString() {
        client.creerPortefeuille("Portefeuille1");
        String expected = "Client : nom prenom " + client.getPortefeuilles() + "";
        assertEquals(expected, client.toString(),"Erreur dans la méthode toString");
    }
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
        Portefeuille p = new Portefeuille("testPF");

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

    



    public class ActionImpl extends Action {
        public ActionImpl(String libelle) {
            super(libelle);                      
        }

        public float valeur(Jour j) {
            return 0.0F;
        }
    }

}
