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

    








    public class ActionImpl extends Action {
        public ActionImpl(String libelle) {
            super(libelle);                      
        }

        public float valeur(Jour j) {
            return 0.0F;
        }
    }

}
