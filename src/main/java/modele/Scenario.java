package modele;

public class Scenario {
    private final double percentage_u1;
    private final double percentage_fill;
    private final String algo_fill;

    public Scenario(double percentage_u1, double percentage_fill, String algo_fill) {
        this.percentage_u1 = percentage_u1;
        this.percentage_fill = percentage_fill;
        this.algo_fill = algo_fill;
    }

    public double getPercentage_u1() {
        return percentage_u1;
    }

    public double getPercentage_fill() {
        return percentage_fill;
    }

    public String getAlgo_fill() {
        return algo_fill;
    }
}
