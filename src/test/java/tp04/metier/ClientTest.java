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

import org.junit.jupiter.api.Test;

public class ClientTest {


    @Test
    void testAcheter() {
        final Client client = new Client("nom", "prenom");
        final Action action = new ActionImpl("Action1");
        
        ActionSimple actions1 = new ActionSimple("Action2");
        ActionSimple actions2 = new ActionSimple("Action3");
        ActionSimple actions3 = new ActionSimple("Action4");
        ActionSimple actions4 = new ActionSimple("Action5");

        Jour j = new Jour(1, 1);
        Jour j1 = new Jour(2,2);
        Jour j2 = new Jour(2, 3);
        actions1.enrgCours(j, 10);
        actions1.enrgCours(j1, 20);

        actions2.enrgCours(j2, 22);

        actions3.enrgCours(j, 30);
        actions3.enrgCours(j2, 40);

        actions4.enrgCours(j2, 1);

        ActionComposee AC = new ActionComposee("ActionComposee1");
        AC.enrgComposition(actions4, 30);
        AC.enrgComposition(actions1, 70);


        client.creerPortefeuille("monPorte");

        assertEquals(true,client.creerPortefeuille("monPorte"), "une erreur est survenue lors de la création du portefeuille");
        assertEquals(true, client.acheter("monPorte",action, 10), "L'action achetée doit être la même que celle passée en paramètre");

        assertEquals(true,client.acheter("monPorte", AC, 10), "L'action achetée doit être la même que celle passée en paramètre");
        assertEquals(true,client.acheter("monPorte", actions1, 10), "L'action achetée doit être la même que celle passée en paramètre");
        assertEquals(true,client.acheter("monPorte", actions4, 10), "L'action achetée doit être la même que celle passée en paramètre");

        assertEquals(true, client.removePortefeuille("monPorte"), "Le portefeuille doit être supprimé");

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
