package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private static final Simulation simulation = new Simulation();

    public static Simulation getSimulation() {
        return simulation;
    }

    public static void main(String[] args) throws InvalidJSONFileException, IllegalAccessException, InstantiationException {


        //Phase phase = new Phase("phase2",new ArrayList<>());
        //new ObjectManagement(phase).addObjects(new Object("Nothingtest",500,0,2,"low"));

        Phase phase1 = new Phase("phase1");
        phase1 = new ObjectManagement(phase1).getPhase();
        System.out.println(phase1);

        Phase phase2 = new Phase("phase2");
        phase2 = new ObjectManagement(phase2).getPhase();
        System.out.println(phase2);

        List<Object> allObjects = new ArrayList<>();
        allObjects.addAll(phase1.getObjects());
        allObjects.addAll(phase2.getObjects());

        U1 r1 = new U1();
        U2 r2 = new U2();
        List<Rocket> allRocket = new ArrayList<>();
        allRocket.add(r1);
        allRocket.add(r2);

        System.out.println(allObjects);

        System.out.println(Filling.fifoFilling(allObjects,allRocket));

        allObjects = new ArrayList<>();
        allObjects.addAll(phase1.getObjects());
        allObjects.addAll(phase2.getObjects());

        System.out.println(Filling.moreSpaceFilling(allObjects,allRocket));

        allObjects = new ArrayList<>();
        allObjects.addAll(phase1.getObjects());
        allObjects.addAll(phase2.getObjects());

        System.out.println(Filling.packetFilling(allObjects,allRocket));

        /**
        ScenarioManagement scenarioManagement = new ScenarioManagement();
        Scenario sc = scenarioManagement.getScenario();
        System.out.println(sc);
        sc.setPercentage_fill(0.9);
        sc.setPercentage_u1(0.5);
        scenarioManagement.editScenario(sc);
        System.out.println(sc);
        **/


        ArrayList<Object> phase1Items = new ObjectManagement("res/Phase-1.txt", new Phase("phase 1")).getObjects();
        ArrayList<Object> phase2Items = new ObjectManagement("res/Phase-2.txt", new Phase("phase 2")).getObjects();

        /*
            SIMULATION WITH ONLY U1
         */
        ArrayList<U1> rockets1Phase1 = simulation.loadRocket(phase1Items, U1.class);
        ArrayList<U1> rockets1Phase2 = simulation.loadRocket(phase2Items, U1.class);

        double budget1Phase1 = simulation.runSimulation(rockets1Phase1).budget;
        double budget1Phase2 = simulation.runSimulation(rockets1Phase2).budget;

        System.out.printf("Budget U1 : Phase1 : %d | Phase 2 : %d | Total : %d %n",
                            budget1Phase1,
                            budget1Phase2,
                            budget1Phase1 + budget1Phase2);

        /*
            SIMULATION WITH ONLY U2
         */
        ArrayList<U2> rockets2Phase1 = simulation.loadRocket(phase1Items, U2.class);
        ArrayList<U2> rockets2Phase2 = simulation.loadRocket(phase2Items, U2.class);

        double budget2Phase1 = simulation.runSimulation(rockets2Phase1).budget;
        double budget2Phase2 = simulation.runSimulation(rockets2Phase2).budget;

        System.out.printf("Budget U2 : Phase1 : %d | Phase 2 : %d | Total : %d %n",
                budget2Phase1,
                budget2Phase2,
                budget2Phase1 + budget2Phase2);
    }
}
