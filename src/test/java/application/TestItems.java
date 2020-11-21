package application;

import modele.Item;
import org.junit.*;
import static org.junit.Assert.*;

public class TestItems {

    Item item1;
    Item item2;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================ITEMS TEST==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================FIN TEST ITEMS================");
    }

    @Before
    public void beforeTests() {
        item1 = new Item("Premier Item", 50);
        item2 = new Item("Deuxieme Item", 200);
    }

    @Test
    public void type_test() {
        System.out.println("Début du test de typage ...");
        assertTrue(item1 instanceof Item);

        assertNotEquals(item1, item2);
    }

    @Test
    public void attribute_test() {
        System.out.println("Début du test des attributs ...");

        String nouveauNom = "Mon super item";
        assertNotEquals(item1.getName(), nouveauNom);
        item1.setName(nouveauNom);
        assertEquals(item1.getName(), nouveauNom);
        assertNotEquals(item1.getName(), item2.getName());

        int nouveauPoids = 125;
        assertNotEquals(item2.getWeight(), nouveauPoids);
        item2.setWeight(nouveauPoids);
        assertEquals(item2.getWeight(), nouveauPoids);
        assertNotEquals(item1.getWeight(), item2.getWeight());
    }
}
