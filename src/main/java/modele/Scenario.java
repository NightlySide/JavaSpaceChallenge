package modele;

import org.json.JSONObject;

public class Scenario {
    /*
    la Classe Scenario permet de définir les différents paramètres de simulations définis par les combobox de la page d'acceuil.
    */
    private double percentage_u1;
    private double percentage_fill;
    private FillingType algo_fill;
    private DistributionType crashDistoType;
    private String rocketP1;
    private String rocketP2;


    public Scenario(double percentage_u1, double percentage_fill, FillingType algo_fill, DistributionType distributionType, String rocketP1, String rocketP2) {
        this.percentage_u1 = percentage_u1;
        this.percentage_fill = percentage_fill;
        this.algo_fill = algo_fill;
        this.crashDistoType = distributionType;
        this.rocketP1 = rocketP1;
        this.rocketP2 = rocketP2;

    }

    public static Scenario fromJsonObject(JSONObject jsonObject) throws InvalidJSONFileException {
        String value = (String) jsonObject.get("type");
        if (value.compareTo("scenario")==0) {
            JSONObject sc = (JSONObject) jsonObject.get("params");
            return new Scenario(
                    sc.getDouble("percentage_u1"),
                    sc.getDouble("percentage_fill"),
                    FillingType.valueOf(((String) sc.get("algo_fill")).toUpperCase()),
                    DistributionType.valueOf((sc.getString("crash_distro_type")).toUpperCase()),
                    sc.getString("rocket_P1"),
                    sc.getString("rocket_P2")
                    );
        }
        else
        {
            throw new InvalidJSONFileException("Mauvais type de fichier");
        }
    }

    public double getPercentage_u1() {
        return percentage_u1;
    }

    public double getPercentage_fill() {
        return percentage_fill;
    }

    public FillingType getAlgo_fill() {
        return algo_fill;
    }

    public DistributionType getCrashDistoType() { return crashDistoType; }

    public String getRocketP1() { return rocketP1; }

    public String getRocketP2() { return rocketP2; }

    public void setPercentage_u1(double percentage_u1) {
        this.percentage_u1 = percentage_u1;
    }

    public void setPercentage_fill(double percentage_fill) {
        this.percentage_fill = percentage_fill;
    }

    public void setAlgo_fill(FillingType algo_fill) {
        this.algo_fill = algo_fill;
    }

    public void setCrashDistoType(DistributionType crashDistoType) { this.crashDistoType = crashDistoType; }

    public void setRocketP1(String rocketP1) { this.rocketP1 = rocketP1; }

    public void setRocketP2(String rocketP2) { this.rocketP2 = rocketP2; }

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
