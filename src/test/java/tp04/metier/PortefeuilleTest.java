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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PortefeuilleTest {

private Client client;
private List<Portefeuille> listePortefeuilles;
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
    
      /* Création du client */
      client = new Client("NomClient", "PrenomClient");
      List<Portefeuille> listePortefeuillesTest = client.getListePortefeuilles();
  }

@Test 
void TestCreationPortefeuilleValide() {


    Portefeuille portefeuille = new Portefeuille("Mon Portefeuille");
    portefeuille.setProprietaire(client);
    
    assertNotNull(portefeuille, "Le portefeuille ne doit pas etre null");
    assertEquals("Mon Portefeuille", portefeuille.getTitre(), "Le titre du portefeuille doit être correct");
    assertEquals(client, portefeuille.getProprietaire());
}
@Test 
void testCreationPortefeuilleInvalide() {

Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
    new Portefeuille(null);
});
assertEquals("Le titre ne peut être null", exception1.getMessage());

Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
    new Portefeuille(" ");
});
assertEquals("Le titre ne peut être vide", exception2.getMessage());

}
@Test
void testCreationPortefeuilleAvecNomExistant_ShouldNotPass(){

    /* Création du portefeuille */
    Portefeuille portefeuilleA = new Portefeuille("Mon Portefeuille");
    /* Assignement d'un propriétaire */
    portefeuilleA.setProprietaire(client);
    /* Association du portefeuille à la listePortefeuille du client */
    client.addPortefeuille(portefeuilleA);
    Portefeuille portefeuilleB = new Portefeuille("Mon Portefeuille");
    client.addPortefeuille(portefeuilleB);

    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
    portefeuilleB.setProprietaire(client);
    });
    assertEquals("Ce titre existe parmi vos portefeuilles", exception1.getMessage());
  }

    @Test
    public void testConstructeur() {
        assertEquals("Portefeuille1", portefeuille.getTitre(),"Le nom du portefeuille doit être Portefeuille1");
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

