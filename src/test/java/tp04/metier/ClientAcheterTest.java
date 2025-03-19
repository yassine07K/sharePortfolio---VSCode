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

public class ClientAcheterTest {


    @Test
    void testAcheter() {
        final Client client = new Client("nom", "prenom");
        final Action action = new ActionImpl("Action1");
        
        client.creerPortefeuille("monPorte");
        assertEquals(true,client.creerPortefeuille("monPorte"), "une erreur est survenue lors de la création du portefeuille");
        assertEquals(true, client.acheter("monPorte",action, 10), "L'action achetée doit être la même que celle passée en paramètre");

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
