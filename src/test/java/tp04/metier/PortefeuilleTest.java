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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PortefeuilleTest {

private Client client;
private List<Portefeuille> listePortefeuilles;

@BeforeEach
void setUp() {

    client = new Client("NomClient", "PrenomClient");
    listePortefeuilles = new ArrayList<>();

}

@Test 
void TestCreationPortefeuilleValide() {


    Portefeuille portefeuille = new Portefeuille("Mon Portefeuille");
    portefeuille.setProprietaire(client);

    assertNotNull(portefeuille, "Le portefeuille ne doit pas etre null");
    assertEquals("Mon Portefeuille", portefeuille.getTitre(), "Le titre du portefeuille doit être correct");
    assertEquals(client.toString(), portefeuille.getProprietaire());
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
assertEquals("Le titre ne peut être null", exception2.getMessage());

}





}
