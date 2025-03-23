package tp04.metier;
import java.beans.Transient;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class JourTest {
@Test
void testJourEqualsAndHashCode() {
    Jour j1 = new Jour(2);
    Jour j2 = new Jour(2);
    Assertions.assertEquals(j1, j2);
    Assertions.assertEquals(j1.hashCode(), j2.hashCode());
}

}
