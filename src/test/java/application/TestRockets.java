package application;

import junit.framework.TestCase;
import org.junit.*;

public class TestRockets extends TestCase {

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before)
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
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
    public void nomdutest1() {
        // code qui teste une chose, appelé "test1".
        // Le code contient généralement une assertion pour vérifier si une condition est vraie ou fausse.
    }

    @Test
    public void nomdutest2() {
        // code qui teste autre chose, appelé "test2"
        // Le code contient généralement une assertion pour vérifier si une condition est vraie ou fausse.
    }


}
