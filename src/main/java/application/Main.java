package application;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = simulation.loadItems("Phase-1.txt");
        ArrayList<Item> phase2Items = simulation.loadItems("Phase-2.txt");

        ArrayList<U1> rocketsPhase1 = simulation.loadU1(phase1Items);
        ArrayList<U2> rocketsPhase2 = simulation.loadU2(phase2Items);

        int budgetPhase1 = simulation.runSimulation(rocketsPhase1);
        int budgetPhase2 = simulation.runSimulation(rocketsPhase2);

        System.out.printf("Budget : Phase1 : %d | Phase 2 : %d | Total : %d %n",
                            budgetPhase1,
                            budgetPhase2,
                            budgetPhase1 + budgetPhase2);
    }
}
