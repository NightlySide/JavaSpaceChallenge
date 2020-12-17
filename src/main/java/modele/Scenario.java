package modele;

public class Scenario {
    private double percentage_u1;
    private double percentage_fill;
    private String algo_fill;
    private DistributionType crashDistoType;

    public Scenario(double percentage_u1, double percentage_fill, String algo_fill, DistributionType distributionType) {
        this.percentage_u1 = percentage_u1;
        this.percentage_fill = percentage_fill;
        this.algo_fill = algo_fill;
        this.crashDistoType = distributionType;
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

    public DistributionType getCrashDistoType() { return crashDistoType; }

    public void setPercentage_u1(double percentage_u1) {
        this.percentage_u1 = percentage_u1;
    }

    public void setPercentage_fill(double percentage_fill) {
        this.percentage_fill = percentage_fill;
    }

    public void setAlgo_fill(String algo_fill) {
        this.algo_fill = algo_fill;
    }

    public void setCrashDistoType(DistributionType crashDistoType) { this.crashDistoType = crashDistoType; }

    @Override
    public String toString() {
        return "Scenario{" +
                "percentage_u1=" + percentage_u1 +
                ", percentage_fill=" + percentage_fill +
                ", algo_fill='" + algo_fill + '\'' +
                ", crashDistroType=" + crashDistoType +
                '}';
    }
}
