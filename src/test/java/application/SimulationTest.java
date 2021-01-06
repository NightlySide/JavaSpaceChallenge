package application;

import modele.*;
import modele.Object;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SimulationTest {
    @Test
    public void testLoadItems() throws IOException, InvalidJSONFileException {
        Simulation simulation = new Simulation();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        ObjectManagement om = new ObjectManagement("src/test/res/TestPhase-2.txt", new Phase("phase2"));

        Assert.assertNotNull(om.getObjects());
    }

    @Test
    public void testLoadU1() throws IOException, InvalidJSONFileException {
        Simulation simulation = new Simulation();

        ObjectManagement om = new ObjectManagement("src/test/res/TestPhase-2.txt", new Phase("phase2"));

        ArrayList<U1> rockets1Phase1 = simulation.loadU1(om.getObjects());

        Assert.assertNotNull(rockets1Phase1);
    }

    @Test
    public void testLoadU2() throws IOException, InvalidJSONFileException {
        Simulation simulation = new Simulation();
        ObjectManagement om = new ObjectManagement("src/test/res/TestPhase-2.txt", new Phase("phase2"));

        ArrayList<Object> phase2Items = om.getObjects();

        ArrayList<U2> rockets2Phase1 = simulation.loadU2(phase2Items);

        Assert.assertNotNull(rockets2Phase1);
    }

    @Test
    public void testRunSimulation() throws IOException, InvalidJSONFileException {
        Simulation simulation = new Simulation();
        ObjectManagement om = new ObjectManagement("src/test/res/TestPhase-1.txt", new Phase("phase1"));

        ArrayList<U1> rockets1Phase1 = simulation.loadU1(om.getObjects());

        int budget = simulation.runSimulation(rockets1Phase1).budget;

        Assert.assertNotSame(budget,0);
    }
}
