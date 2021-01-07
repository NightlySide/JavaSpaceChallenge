package application;

import modele.Phase;
import modele.Object;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class PhaseTest {


    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================phase test==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================fin phase================");
    }

    @Before
    public void setUp() throws Exception {
        // Code exécuté avant chaque test
    }

    @After
    public void tearDown() throws Exception {
        // Code exécuté après chaque test
    }

    @Test
    public void distribTypeTest() {
        ArrayList<Object> object = new ArrayList<>();
        Phase phase1 = new Phase("name1", object);
        Phase phase2 = new Phase("name");
        Object object1 = new Object("nom", 1000, 1000, 1, "LOW");

        assertNotEquals(phase1, phase2);
        assertEquals(phase1.getName(), "name1");
        phase1.addObject(object1);
        phase2.setObject(object);
        assertEquals(phase2.getObjects(), object);
        assertEquals("name : " + phase1.getName() + "\n-    " + object1.toString() + "\n", phase1.toString() );
        object.add(object1);
        assertEquals(phase1.getObjects(), object);

    }

}
