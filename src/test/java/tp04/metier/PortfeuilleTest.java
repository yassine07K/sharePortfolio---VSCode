public class PortfeuilleTest {
//Test sur l'achat d'actions avec une quantité inférieure ou égale à 0.
@Test
void testAcheterActionNegative() {
    Client c = new client("testnom","testprenom");
    portfeuille p = new portfeuille();
    Action action = new Action("TestA");
    
    //le situation = 0
    p.acheter(action, 0);
    assertNull(p.mapLignes.get(action), "le qte doit plus de 0!");
    
    //le situation < 0
    p.acheter(action, -5);
    assertNull(p.mapLignes.get(action), "le qte doit plus de 0!");
}
