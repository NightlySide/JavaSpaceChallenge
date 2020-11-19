package application;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = simulation.loadItems("res/Phase-1.txt");
        ArrayList<Item> phase2Items = simulation.loadItems("res/Phase-2.txt");

        /*
            SIMULATION WITH ONLY U1
         */
        ArrayList<U1> rockets1Phase1 = simulation.loadU1(phase1Items);
        ArrayList<U1> rockets1Phase2 = simulation.loadU1(phase2Items);

        int budget1Phase1 = simulation.runSimulation(rockets1Phase1);
        int budget1Phase2 = simulation.runSimulation(rockets1Phase2);

        System.out.printf("Budget U1 : Phase1 : %d | Phase 2 : %d | Total : %d %n",
                            budget1Phase1,
                            budget1Phase2,
                            budget1Phase1 + budget1Phase2);

        /*
            SIMULATION WITH ONLY U2
         */
        ArrayList<U2> rockets2Phase1 = simulation.loadU2(phase1Items);
        ArrayList<U2> rockets2Phase2 = simulation.loadU2(phase2Items);

        int budget2Phase1 = simulation.runSimulation(rockets2Phase1);
        int budget2Phase2 = simulation.runSimulation(rockets2Phase2);

        System.out.printf("Budget U2 : Phase1 : %d | Phase 2 : %d | Total : %d %n",
                budget2Phase1,
                budget2Phase2,
                budget2Phase1 + budget2Phase2);
    }
}
