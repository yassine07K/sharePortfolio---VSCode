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
package tp04.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tp04.metier.ActionSimple;
import tp04.metier.Jour;

public class ActionSimpleTest {

    // actionSimple : l'objet ActionSimple à tester
    private ActionSimple actionSimple;
    
    // jour1, jour2, jour3 : des objets Jour représentant différents jours
    private Jour jour1;
    private Jour jour2;
    private Jour jour3;

    /**
     * Méthode exécutée avant chaque test (@Before) :
     * Elle initialise les objets nécessaires aux tests
     * afin que chaque méthode de test parte d'une situation "neuve".
     */
    @Before
    public void setUp() {
        // On crée ici une action simple, nommée "ActionTest"
        actionSimple = new ActionSimple("ActionTest");
        
        // On initialise trois jours distincts :
        // - jour1 : année 2025, 100e jour
        // - jour2 : année 2025, 101e jour
        // - jour3 : année 2026, 1er jour
        // Ces valeurs sont choisies arbitrairement pour illustrer différents cas.
        jour1 = new Jour(2025, 100);
        jour2 = new Jour(2025, 101);
        jour3 = new Jour(2026, 1);
    }

    /**
     * 1) Test de la User Story:
     * "En tant que client, je souhaite connaître la valeur d’une action pour un jour donné."
     * 
     * Scénario : On enregistre un cours pour le jour1 (2025, 100).
     *            Ensuite, on vérifie que la valeur renvoyée par valeur(jour1)
     *            correspond exactement à la valeur enregistrée (150.5).
     */
    @Test
    public void testValeurActionSimple_jourExiste() {
        // On enregistre un cours de 150.5 pour le jour1
        actionSimple.enrgCours(jour1, 150.5f);

        // On récupère la valeur de l'action pour le jour1
        float valeur = actionSimple.valeur(jour1);

        // On s'attend à ce que la valeur soit 150.5
        assertEquals("La valeur doit être celle enregistrée (150.5).",
                     150.5f, valeur, 0.0001f);
    }

    /**
     * 2) Vérifie qu'on obtient 0 si aucun cours n'a été enregistré pour le jour en question.
     * 
     * Scénario : On enregistre un cours (200) uniquement pour jour1,
     *            puis on tente de récupérer la valeur pour jour2.
     *            Comme jour2 n'a pas été enregistré, la valeur doit être 0.
     */
    @Test
    public void testValeurActionSimple_jourInexistant() {
        // On enregistre un cours pour jour1 uniquement
        actionSimple.enrgCours(jour1, 200f);

        // On récupère la valeur pour jour2 (non enregistré)
        float valeur = actionSimple.valeur(jour2);

        // Comme aucun cours n'a été fourni pour jour2,
        // la méthode doit retourner 0.
        assertEquals("La valeur doit être 0 si le jour n'existe pas", 
                     0f, valeur, 0.0001f);
    }

    /**
     * 3) Vérifie qu'on peut enregistrer plusieurs jours différents
     *    et récupérer les valeurs correctes pour chacun.
     *
     * Scénario : jour1 = 100, jour2 = 200, jour3 = 300
     *            On vérifie ensuite que la méthode valeur(Jour)
     *            renvoie bien les valeurs correspondantes.
     */
    @Test
    public void testValeurActionSimple_plusieursJours() {
        // On enregistre 3 cours différents :
        actionSimple.enrgCours(jour1, 100f);
        actionSimple.enrgCours(jour2, 200f);
        actionSimple.enrgCours(jour3, 300f);

        // On récupère la valeur pour chaque jour
        float valeurJour1 = actionSimple.valeur(jour1);
        float valeurJour2 = actionSimple.valeur(jour2);
        float valeurJour3 = actionSimple.valeur(jour3);

        // On s'assure que les valeurs correspondent :
        assertEquals("Jour1 => 100f", 100f, valeurJour1, 0.0001f);
        assertEquals("Jour2 => 200f", 200f, valeurJour2, 0.0001f);
        assertEquals("Jour3 => 300f", 300f, valeurJour3, 0.0001f);
    }

    /**
     * 4) Vérifie que si on enregistre deux fois un cours pour le même jour,
     *    c'est bien la dernière valeur qui est prise en compte (mise à jour).
     *
     * Scénario : On enregistre d'abord 100 pour jour1,
     *            puis on enregistre 500 pour le même jour1.
     *            On vérifie à la fin que la valeur pour jour1 est bien 50,
     *            pas 100.
     */
    @Test
    public void testValeurActionSimple_miseAJourCours() {
        actionSimple.enrgCours(jour1, 100f);  // enregistrement initial
        actionSimple.enrgCours(jour1, 50f);  // mise à jour

        // On récupère la valeur pour jour1
        float valeur = actionSimple.valeur(jour1);

        // Comme on a fait un second enregistrement à 50,
        // la valeur finale doit être 50.
        assertEquals("La valeur doit être celle de la dernière mise à jour (50).",
                     50f, valeur, 0.0001f);
    }

    /**
     * 5) on illustre un cas où on enregistre un cours
     *    pour un Jour dont le numéro de jour (noJour) est négatif (-10).
     *    Puis, on vérifie que la méthode donne toujours la valeur enregistrée.
     */
    @Test
    public void testValeurActionSimple_jourNegatif() {
        // Création d'un jour  (numéro -10)
        Jour jourNegatif = new Jour(2025, -10);
        
        // Enregistre un cours de 99.9 sur ce jour
        actionSimple.enrgCours(jourNegatif, 99.9f);

        // On récupère la valeur pour jourNegatif
        float valeur = actionSimple.valeur(jourNegatif);

        // Vérification : la valeur doit être celle enregistrée, 99.9
        assertEquals("Même si le jour est négatif, on doit pouvoir récupérer la valeur.",
                     99.9f, valeur, 0.0001f);
    }
}
