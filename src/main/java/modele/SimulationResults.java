package modele;

import java.util.ArrayList;

public class SimulationResults {

    public double nbMortHumains = 0;
    public double nbExplosionsFusees = 0;
    public double nbLancementFusees = 0;
    public double budget = 0;

    /*
        Constructeur : SimulationResults()
        ------------------------------------------
        Structure de donnée permettant de stocker les statistiques
        d'une simulation donnée
    */
    public SimulationResults() { }

    public String toString() {
        String res = String.format("Résultats de simulation :%n");
        res += String.format("\tNombre de morts : %.2f%n", nbMortHumains);
        res += String.format("\tNombre de fusées lancées : %.2f%n", nbLancementFusees);
        res += String.format("\tNombre d'explosions : %.2f%n", nbExplosionsFusees);
        res += String.format("\tCoût de la mission : %.2fk€", budget);

        return res;
    }

    /*
        Méthode : moyenne(simResList)
        ------------------------------------------
        Permet de calculer la moyenne des statistiques de la simulation.

        simResList (ArrayList<SimulationResults>) : liste de résultats
    */
    public static SimulationResults moyenne(ArrayList<SimulationResults> simResList) {
        SimulationResults res = new SimulationResults();
        int n = simResList.size();
        for (SimulationResults sim : simResList) {
            res.budget += sim.budget;
            res.nbExplosionsFusees += sim.nbExplosionsFusees;
            res.nbLancementFusees += sim.nbLancementFusees;
            res.nbMortHumains += sim.nbMortHumains;
        }

        res.budget /= n;
        res.nbExplosionsFusees /= n;
        res.nbLancementFusees /= n;
        res.nbMortHumains /= n;
        return res;
    }
}
