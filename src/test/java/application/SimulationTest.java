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

        ObjectManagement om = new ObjectManagement("res/Objects.json", new Phase("phase2"));

        Assert.assertNotNull(om.getObjects());
    }

    @Test
    public void testLoadU1() throws IOException, InvalidJSONFileException, IllegalAccessException, InstantiationException {
        Simulation simulation = new Simulation();

        ObjectManagement om = new ObjectManagement("res/Objects.json", new Phase("phase2"));

        ArrayList<U1> rockets1Phase1 = simulation.loadRocket(om.getObjects(), U1.class);

        Assert.assertNotNull(rockets1Phase1);
    }

    @Test
    public void testLoadU2() throws IOException, InvalidJSONFileException, IllegalAccessException, InstantiationException {
        Simulation simulation = new Simulation();
        ObjectManagement om = new ObjectManagement("res/Objects.json", new Phase("phase2"));

        ArrayList<Object> phase2Items = om.getObjects();

        ArrayList<U2> rockets2Phase1 = simulation.loadRocket(phase2Items, U2.class);

        Assert.assertNotNull(rockets2Phase1);
    }

    @Test
    public void testRunSimulation() throws IOException, InvalidJSONFileException, IllegalAccessException, InstantiationException {
        Simulation simulation = new Simulation();
        ObjectManagement om = new ObjectManagement("res/Objects.json", new Phase("phase1"));

        ArrayList<U1> rockets1Phase1 = simulation.loadRocket(om.getObjects(), U1.class);

        double budget = simulation.runSimulation(rockets1Phase1).budget;

        Assert.assertNotSame(budget,0);
    }
}
