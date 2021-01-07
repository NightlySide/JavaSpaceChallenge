package application;

import modele.DistributionType;
import modele.Rocket;
import modele.U1;
import modele.U2;
import org.junit.*;
import static org.junit.Assert.*;


public class DistribTypeTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================distribtype test==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================fin distribtype================");
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
        assertEquals(DistributionType.fromText("Linéaire"), DistributionType.LINEAR);
        assertEquals(DistributionType.fromText("Exponentielle"), DistributionType.EXPONENTIAL);
        assertEquals(DistributionType.fromText("Sigmoïde"), DistributionType.SIGMOID);

        System.out.println("=================test from text OK ================");

        assertEquals(DistributionType.EXPONENTIAL.toText(),"Exponentielle");
        assertEquals(DistributionType.LINEAR.toText(),"Linéaire");
        assertEquals(DistributionType.SIGMOID.toText(),"Sigmoïde");

        System.out.println("=================test toText OK ================");

    }

}
