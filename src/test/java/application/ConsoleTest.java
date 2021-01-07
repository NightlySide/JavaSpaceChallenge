package application;

import javafx.scene.control.TextArea;
import modele.Console;
import modele.Object;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ConsoleTest {

    @BeforeClass
    public static void setUpClass() {
        // Code exécuté avant l'exécution du premier test (et de la méthode @Before
        System.out.println("==================CONSOLE TEST==================");
    }

    @AfterClass
    public static void tearDownClass() {
        // Code exécuté après l'exécution de tous les tests
        System.out.println("=================FIN TEST CONSOLE================");
    }

    @Before
    public void beforeTests() {
        Console.getInstance().getLines().clear();
    }

    @Test
    public void singletonTest() {
        System.out.println("Début du test de singleton ...");
        Console first = Console.getInstance();
        Console second = Console.getInstance();

        assertEquals(first, second);
    }

    @Test
    public void addLine() {
        System.out.println("Début du test d'ajout de ligne ...");
        assertEquals(0, Console.getInstance().getLines().size());
        Console.getInstance().addLine("Test");
        assertEquals(1, Console.getInstance().getLines().size());
    }

    @Test
    public void addLines() {
        System.out.println("Début du test d'ajout de lignes ...");
        assertEquals(0, Console.getInstance().getLines().size());

        ArrayList<String> lines = new ArrayList<>(Arrays.asList("Test1", "Test2"));
        Console.getInstance().addLines(lines);
        assertEquals(2, Console.getInstance().getLines().size());

        String[] lines2 = {"Test1", "Test2"};
        Console.getInstance().addLines(lines2);
        assertEquals(4, Console.getInstance().getLines().size());
    }

    @Test
    public void helloWorld() {
        System.out.println("Début du test d'Hello World ...");
        Console.getInstance().printHelloWorld();
        assertEquals(9, Console.getInstance().getLines().size());
    }

    @Test
    public void overflow() {
        System.out.println("Début du test d'overflow ...");
        for (int k = 0; k < Console.getInstance().maxLines + 10; k++)
            Console.getInstance().addLine("Garbage");
        assertEquals(Console.getInstance().maxLines + 1, Console.getInstance().getLines().size());
    }

    @Test
    public void attachTA() {
        System.out.println("Début du test d'attache de TextArea ...");
        Console.getInstance().attachTextarea(null);
        assertNull(Console.getInstance().getAttachedTextArea());
    }
}
