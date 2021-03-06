package application;

import modele.Object;
import org.junit.*;
import static org.junit.Assert.*;

public class TestItems {

    Object item1;
    Object item2;

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
        item1 = new Object("Premier Item", 50, 50, 1, "low");
        item2 = new Object("Deuxieme Item", 200, 200, 1, "low");
    }

    @Test
    public void type_test() {
        System.out.println("Début du test de typage ...");
        assertTrue(item1 instanceof Object);

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

    @Test
    public void variables() {
        System.out.println("Début du test des variables ...");
        assertEquals(item1.getPrice(), 50);
        item1.setPrice(200);
        assertEquals(item1.getPrice(), 200);

        assertEquals(item1.getQuantity(), 1);
        item1.setQuantity(2);
        assertEquals(item1.getQuantity(), 2);

        assertEquals(item1.getPriority(), "low");
        item1.setPriority("high");
        assertEquals(item1.getPriority(), "high");
    }

    @Test
    public void toStringTest() {
        System.out.println("Début du test du toString ...");
        assertEquals("Object", item1.toString().substring(0, 6));
    }
}
