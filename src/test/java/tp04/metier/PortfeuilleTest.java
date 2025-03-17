public class PortfeuilleTest {
@Test
void testAcheterActionNegative() {
    Client c = new client("testnom","testprenom");
    portfeuille p = new portfeuille();
    Action action = new Action("TestA");
    p.acheter(action, 0);
    assertNull(p.mapLignes.get(action), "le qte doit plus de 0!");
    p.acheter(action, -5);
    assertNull(p.mapLignes.get(action), "le qte doit plus de 0!");
    c.AcheterPortefueille(p);
}
}
