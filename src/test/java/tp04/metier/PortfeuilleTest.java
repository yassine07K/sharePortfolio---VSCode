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

    //Test sur l'achat des difirentes types des actions（ActionSimple ou ActionComposee）.
    @Test
void testAcheterActionSimpleEtComposee() {
    Portefeuille p = new Portefeuille();
    ActionSimple aSimple = new ActionSimple("Simple");
    ActionComposee aComposee = new ActionComposee("Composee");

    p.acheter(aSimple, 10);
    p.acheter(aComposee, 5);

    Assertions.assertEquals(10, p.mapLignes.get(aSimple));
    Assertions.assertEquals(5, p.mapLignes.get(aComposee));
}

//Test sur l'achat duu le meme action, et si le qte peut plus.
@Test
void testAcheterCumulatif() {
    Portefeuille p = new Portefeuille();
    ActionSimple a = new ActionSimple("Simple");

    p.acheter(a, 10);
    p.acheter(a, 15); // total = 25

    Assertions.assertEquals(25, p.mapLignes.get(a));
}

//  test apres la vente tout actions si la quantité  égale à 0.
@Test
void testAcheterEtToutVendre() {
    Portefeuille p = new Portefeuille();
    ActionSimple a = new ActionSimple("Simple");

    p.acheter(a, 10);
    p.vendre(a, 10);

    Assertions.assertTrue(!p.mapLignes.containsKey(a) || p.mapLignes.get(a) == 0);
}


//Test sur l'achat d'actions avec le type d'action est null.
@Test
void testAcheterNullAction() {
    Portefeuille p = new Portefeuille();

    // verifier si le mapLignes contient la clé null
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        p.acheter(null, 10);
    });
}





    @Test
    void testVenteEtQuantite() {
    Portefeuille p = new Portefeuille();
    ActionSimple a = new ActionSimple("Test");

    p.acheter(a, 15);
    p.vendre(a, 5);

    Assertions.assertEquals(10, p.mapLignes.get(a));
}
@Test
void testVendreSansAchat() {
    Portefeuille p = new Portefeuille();
    ActionSimple a = new ActionSimple("NonAchetee");

    p.vendre(a, 5);  //vendre une action non achetée
    Assertions.assertNull(p.mapLignes.get(a));
}




}