package tp04.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tp04.metier.ActionSimple;
import tp04.metier.Cours;
import tp04.metier.Jour;

public class ActionSimpleTest {

    private ActionSimple actionSimple;
    
    private Jour jour1;
    private Jour jour2;
    private Jour jour3;

    /**
     * Méthode exécutée avant chaque test (@Before) :
     * Elle initialise les objets nécessaires pour que
     * chaque test commence dans un état vierge.
     */
    @Before
    public void setUp() {
        // Instanciation d'une ActionSimple
        actionSimple = new ActionSimple("ActionTest");
        
        // Trois jours distincts pour différents scénarios
        jour1 = new Jour(2025, 100);
        jour2 = new Jour(2025, 101);
        jour3 = new Jour(2026, 1);
    }

    /**
     * Test direct de la classe Cours : on vérifie le constructeur,
     * les getters (getJour, getValeur) et le setter (setValeur).
     */
    @Test
    public void testCoursGetSetValeur() {
        // On crée un Jour et un Cours( jour, 123.45f )
        Jour j = new Jour(2030, 50);
        Cours c = new Cours(j, 123.45f);

        // 1) Vérifier le jour et la valeur initiale
        assertEquals("Le jour doit être celui passé au constructeur", j, c.getJour());
        assertEquals("La valeur doit être 123.45", 123.45f, c.getValeur(), 0.0001f);

        // 2) Modification de la valeur via setValeur
        c.setValeur(999.99f);
        assertEquals("Après setValeur(999.99f), la valeur doit être 999.99", 
                     999.99f, c.getValeur(), 0.0001f);
    }

    /**
     * 1) Test de la User Story:
     * "En tant que client, je souhaite connaître la valeur d’une action pour un jour donné."
     * 
     * On enregistre un cours pour jour1, puis on récupère la valeur
     * pour vérifier qu'elle correspond à la valeur enregistrée (150.5).
     */
    @Test
    public void testValeurActionSimple_jourExiste() {
        actionSimple.enrgCours(jour1, 150.5f);

        float valeur = actionSimple.valeur(jour1);

        assertEquals("La valeur doit être 150.5 pour jour1", 
                     150.5f, valeur, 0.0001f);
    }

    /**
     * 2) Vérifie qu'on obtient 0 si aucun cours n'a été enregistré pour ce jour.
     */
    @Test
    public void testValeurActionSimple_jourInexistant() {
        // On enregistre pour jour1 seulement
        actionSimple.enrgCours(jour1, 200f);

        // On tente de récupérer la valeur pour jour2
        float valeur = actionSimple.valeur(jour2);

        assertEquals("La valeur doit être 0 si le jour n'existe pas dans mapCours", 
                     0f, valeur, 0.0001f);
    }

    /**
     * 3) Vérifie qu'on peut enregistrer plusieurs jours différents 
     *    et récupérer les valeurs correctes.
     */
    @Test
    public void testValeurActionSimple_plusieursJours() {
        actionSimple.enrgCours(jour1, 100f);
        actionSimple.enrgCours(jour2, 200f);
        actionSimple.enrgCours(jour3, 300f);

        float valeurJ1 = actionSimple.valeur(jour1);
        float valeurJ2 = actionSimple.valeur(jour2);
        float valeurJ3 = actionSimple.valeur(jour3);

        assertEquals("Jour1 => 100f", 100f, valeurJ1, 0.0001f);
        assertEquals("Jour2 => 200f", 200f, valeurJ2, 0.0001f);
        assertEquals("Jour3 => 300f", 300f, valeurJ3, 0.0001f);
    }

    /**
     * 4) Vérifie que si on enregistre deux fois un cours pour le même jour,
     *    selon le code actuel, la seconde tentative n'a AUCUN effet,
     *    car la condition "if (containsKey(j) == false)" empêche la mise à jour.
     *
     * Scénario :
     *  - D'abord on enregistre 100 pour jour1
     *  - Puis on enregistre 500 pour le même jour1
     *  - On vérifie que la valeur finale RESTE à 100
     */
    @Test
    public void testValeurActionSimple_pasDeMiseAJourSiDejaExistant() {
 
        actionSimple.enrgCours(jour1, 100f);  // 1re insertion
        actionSimple.enrgCours(jour1, 500f);  // 2e insertion (censée être ignorée)

        float valeur = actionSimple.valeur(jour1);

        // On s'attend à ce que ça reste 100, pas 500
        assertEquals("Le code actuel n'autorise pas la mise à jour, donc toujours 100.",
                     100f, valeur, 0.0001f);
    }

    /**
     * 5) Test avec un jour "négatif" (noJour < 0), pour voir si
     *    le code gère ce cas de façon particulière ou non.
     */
    @Test
    public void testValeurActionSimple_jourNegatif() {
        Jour jourNegatif = new Jour(2025, -10);
        actionSimple.enrgCours(jourNegatif, 999.9f);

        float valeur = actionSimple.valeur(jourNegatif);

        assertEquals("Même si le jour est négatif, on doit récupérer la valeur enregistrée", 
                     999.9f, valeur, 0.0001f);
    }
}
