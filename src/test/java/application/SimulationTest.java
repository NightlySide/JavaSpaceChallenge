package application;

import modele.Item;
import modele.Simulation;
import modele.U1;
import modele.U2;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SimulationTest {
    @Test
    public void testLoadItems() throws IOException {
        Simulation simulation = new Simulation();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        ArrayList<Item> phase1Items = simulation.loadItems("src/test/res/TestPhase-1.txt");

        Assert.assertNotNull(phase1Items);
    }

    @Test
    public void testLoadU1() throws IOException {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = simulation.loadItems("src/test/res/TestPhase-1.txt");

        ArrayList<U1> rockets1Phase1 = simulation.loadU1(phase1Items);

        Assert.assertNotNull(rockets1Phase1);
    }

    @Test
    public void testLoadU2() throws IOException {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase2Items = simulation.loadItems("src/test/res/TestPhase-1.txt");

        ArrayList<U2> rockets2Phase1 = simulation.loadU2(phase2Items);

        Assert.assertNotNull(rockets2Phase1);
    }

    @Test
    public void testRunSimulation() throws IOException {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = simulation.loadItems("src/test/res/TestPhase-1.txt");

        ArrayList<U1> rockets1Phase1 = simulation.loadU1(phase1Items);

        int budget = simulation.runSimulation(rockets1Phase1);

        Assert.assertNotSame(budget,0);
    }
}
