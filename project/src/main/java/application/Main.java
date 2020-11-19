package application;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        ArrayList<Item> phase1Items = new ArrayList<>();
        ArrayList<Item> phase2Items = new ArrayList<>();
        try {
            phase1Items = simulation.loadItems("Phase-1.txt");
            phase2Items = simulation.loadItems("Phase-2.txt");
        } catch (Exception e) {
            System.out.println("Files not found");
            e.printStackTrace();
        }

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
