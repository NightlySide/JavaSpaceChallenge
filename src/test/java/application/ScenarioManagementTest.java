package application;

import modele.InvalidJSONFileException;
import modele.Rocket;
import modele.Scenario;
import modele.ScenarioManagement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class ScenarioManagementTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================ScenarManag TEST==================");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================FIN TEST ScenarManag================");
    }

    @Test
    public void singleton() {
        System.out.println("Début du test de singleton ...");
        ScenarioManagement first = ScenarioManagement.getInstance();
        ScenarioManagement second = ScenarioManagement.getInstance();
        assertEquals(first, second);
    }

    @Test
    public void frompath() throws ParseException, InvalidJSONFileException, IOException {
        System.out.println("Début du test de géné depuis path ...");
        ScenarioManagement.getInstance().fromPath("res/Scenario.json");
        assertNotNull(ScenarioManagement.getInstance().getScenario());

        Scenario before = ScenarioManagement.getInstance().getScenario();
        ScenarioManagement.getInstance().fromScenario(ScenarioManagement.getInstance().getScenario());
        assertEquals(before, ScenarioManagement.getInstance().getScenario());
    }

    @Test
    public void save() throws IOException, ParseException, InvalidJSONFileException {
        System.out.println("Début du test de save ...");
        ScenarioManagement.getInstance().fromPath("res/Scenario.json");
        Scenario before = ScenarioManagement.getInstance().getScenario();
        ScenarioManagement.getInstance().saveScenario("res/testScenario.json");
        //ScenarioManagement.getInstance().fromPath("res/testScenario.json");

        assertEquals(ScenarioManagement.getInstance().getScenario(), before);
    }

    @Test
    public void edit() throws ParseException, InvalidJSONFileException, IOException {
        System.out.println("Début du test d'edition ...");
        ScenarioManagement.getInstance().fromPath("res/Scenario.json");
        assertEquals(ScenarioManagement.getInstance().editScenario(ScenarioManagement.getInstance().getScenario()), 1);
        ScenarioManagement.getInstance().getJSONObject().put("type", "garbage");
        assertThrows(InvalidJSONFileException.class, () -> ScenarioManagement.getInstance().editScenario(ScenarioManagement.getInstance().getScenario()));
    }
}
