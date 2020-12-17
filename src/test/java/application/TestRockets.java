package application;

import modele.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestRockets {

    private U1 rocketU1;
    private U1 rocketU1_bis;
    private U2 rocketU2;
    private U2 rocketU2_bis;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================ROCKET TEST==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================FIN TEST ROCKET================");
    }

    @Before
    public void setUp() throws Exception {
        // Code exécuté avant chaque test
        rocketU1 = new U1(100, 10000,18000);
        rocketU1_bis = new U1(100, 100,1800);

        rocketU2 = new U2(120,18000,29000);
        rocketU2_bis = new U2(120,18000,29000);
    }

    @After
    public void tearDown() throws Exception {
        // Code exécuté après chaque test
    }

    @Test
    public void type_test() {
        System.out.println("Début du test de typage ...");
        // on vérifie qu'ils appartienent aux bonnes classes
        assertEquals(rocketU1.getClass(), U1.class);
        assertEquals(rocketU2.getClass(), U2.class);

        // et qu'ils soient bien héritiers de rocket
        assertTrue(rocketU1 instanceof Rocket);
        assertTrue(rocketU2 instanceof Rocket);
    }

    @Test
    public void attributes_test() {
        System.out.println("Début du test des attributs ...");
        assertEquals(rocketU1.getPrice(), rocketU1_bis.getPrice());
        assertNotEquals(rocketU1.getPrice(), rocketU2.getPrice());

        assertEquals(rocketU2.getWeight(), rocketU2_bis.getWeight());
    }

    @Test
    public void item_can_carry_test() {
        System.out.println("Début du test de la capacité de transport des objets ...");

        Item lightItem = new Item("Item léger", 20);
        Item heavyItem = new Item("Item trop lourd", 2200000);

        // on vérifie que les rockets puissent porter les items légers
        assertTrue(rocketU1.canCarry(lightItem));
        assertTrue(rocketU2.canCarry(lightItem));
        // mais ne peuvent pas porter les items trop lourds
        assertFalse(rocketU1.canCarry(heavyItem));
        assertFalse(rocketU2.canCarry(heavyItem));
    }

    @Test
    public void item_carry_test() {
        System.out.println("Début du test de transport des objets ...");

        Item lightItem = new Item("Item léger", 20);

        int initalWeight1 = rocketU1.getWeight();
        int initalWeight2 = rocketU2.getWeight();
        rocketU1.carry(lightItem);
        rocketU2.carry(lightItem);

        assertTrue(rocketU1.getWeight() > initalWeight1);
        assertTrue(rocketU2.getWeight() > initalWeight2);
    }

    @Test
    public void crash_distribution_test() {
        System.out.println("Début du test de distribution de crash ...");
        rocketU1.setWeight(rocketU1.getMaxWeight());

        DistributionType distoType = DistributionType.EXPONENTIAL;

        int nbLaunch = 10000;
        double chance_of_crash = rocketU1.computeCrashChance(distoType, rocketU1.getCrashPercentAtFullLaunch());
        double esperance = nbLaunch * chance_of_crash;
        double ecartType = Math.sqrt(esperance * (1 - chance_of_crash));

        int crashes = 0;
        for (int i = 0; i < nbLaunch; i++) {
            if (!rocketU1.launch(distoType)) crashes++;
        }

        System.out.println("Nb lancements : " + nbLaunch);
        System.out.println("Espérance : " + esperance);
        System.out.println("Ecart-type : " + ecartType);
        System.out.println("Nb crashs : " + crashes);

        assertTrue((esperance - 3 * ecartType < crashes && crashes < esperance + 3 * ecartType));
    }
}
