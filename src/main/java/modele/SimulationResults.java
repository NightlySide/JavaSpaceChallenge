package modele;

public class SimulationResults {

    public int nbMortHumains = 0;
    public int nbExplosionsFusees = 0;
    public int nbLancementFusees = 0;
    public int budget = 0;

    public SimulationResults() {

    }

    public String toString() {
        String res = String.format("Résultats de simulation :%n");
        res += String.format("\tNombre de morts : %d%n", nbMortHumains);
        res += String.format("\tNombre de fusées lancées : %d%n", nbLancementFusees);
        res += String.format("\tNombre d'explositions : %d%n", nbExplosionsFusees);
        res += String.format("\tCoût de la mission : %dk€", budget);

        return res;
    }
}
