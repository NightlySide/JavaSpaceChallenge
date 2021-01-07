package application;

import modele.Object;
import modele.SimulationResults;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SimuResultsTest {

    SimulationResults results;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================SIMRESULTS TEST==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================FIN TEST SIMRESULTS================");
    }

    @Before
    public void beforeTests() {
        results = new SimulationResults();
    }

    @Test
    public void init() {
        System.out.println("Début du test d'init ...");
        assertEquals(results.budget, 0.0, 0.01);
        assertEquals(results.nbExplosionsFusees, 0.0, 0.01);
        assertEquals(results.nbLancementFusees, 0.0, 0.01);
        assertEquals(results.nbMortHumains, 0.0, 0.01);
    }

    @Test
    public void tostringtest() {
        System.out.println("Début du test toString ...");
        assertEquals("Résultats", results.toString().substring(0, 9));
    }

    @Test
    public void moyennetest() {
        System.out.println("Début du test de moyenne");
        SimulationResults r1 = new SimulationResults();
        SimulationResults r2 = new SimulationResults();
        r1.budget = 0;
        r2.budget = 10;

        SimulationResults moy = SimulationResults.moyenne(new ArrayList<>(Arrays.asList(r1, r2)));
        assertEquals(moy.budget, 5.0, 0.1);
    }

}
