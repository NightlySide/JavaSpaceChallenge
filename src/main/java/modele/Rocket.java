package modele;

import java.util.ArrayList;
import java.util.Random;

/*
    Classe abstraite : Rocket
    -------------------------
    Classe abstraite implémentant les méthodes de Spaceship.
    Cette classe permet de généraliser le code concernant l'utilisation des rockets
    cependant on souhaite que cette classe ne soit jamais initialisée d'où son abstraction.
 */
public abstract class Rocket implements SpaceShip {

    protected final Random alea;
    private int price;
    private int weight;
    private int maxWeight;
    private double crashPercentAtFullLand = 5.0;
    private double crashPercentAtFullLaunch = 5.0;
    private ArrayList<Object> cargo;

    /* Getter / Setter du prix */
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    /* Getter / Setter du poids de la fusée */
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Object> getCargo() {
        return cargo;
    }
    public void setCargo(ArrayList<Object> cargo) {
        this.cargo = cargo;
    }

    /* Getter / Setter du poids maximal de la fusée */
    public int getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getCrashPercentAtFullLand() {
        return crashPercentAtFullLand;
    }
    public void setCrashPercentAtFullLand(double crashPercentAtFullLand) {
        this.crashPercentAtFullLand = crashPercentAtFullLand;
    }

    public double getCrashPercentAtFullLaunch() {
        return crashPercentAtFullLaunch;
    }
    public void setCrashPercentAtFullLaunch(double crashPercentAtFullLaunch) {
        this.crashPercentAtFullLaunch = crashPercentAtFullLaunch;
    }

    public double computeCrashChance(DistributionType crashDistroType, double riskAtFullCapacity) {
        // On calcule le risque de se crasher
        double chance_of_crash;
        switch (crashDistroType) {
            case EXPONENTIAL:
                chance_of_crash = CrashDistribution.ExponentialDistributionChance(this, riskAtFullCapacity, 5);
                break;
            case SIGMOID:
                chance_of_crash = CrashDistribution.SigmoidDistributionChance(this, riskAtFullCapacity, 3);
                break;
            default:
                chance_of_crash = CrashDistribution.LinearDistributionChance(this, riskAtFullCapacity);
        }
        return chance_of_crash;
    }

    /*
        Méthode : launch()
        ------------------
        Permet de savoir si une fusée s'est bien lancée ou bien si elle s'est crashée.

        returns: la fusée s'est bien lancée
     */
    public boolean launch(DistributionType crashDistroType)
    {
        return (this.alea.nextDouble() >= computeCrashChance(crashDistroType, this.getCrashPercentAtFullLaunch()));
    }
    public boolean launch() { return true; }

    /*
        Méthode : land()
        ----------------
        Permet de savoir si une fusée a bien atterit ou bien si elle s'est crashée.

        returns: la fusée a bien atterit
     */
    public boolean land(DistributionType crashDistroType)
    {
        return (this.alea.nextDouble() >= computeCrashChance(crashDistroType, this.getCrashPercentAtFullLaunch()));
    }
    public boolean land() { return true; }

    /*
        Méthode : canCarry(Item)
        ------------------------
        Vérifie si la fusée est capable de transporter l'objet "item".

        item (Item): l'objet à transporter

        returns: est-ce que la fusée peut l'emporter ou non
     */
    public boolean canCarry(Object obj)
    {
        return this.weight + obj.getWeight() <= this.getMaxWeight();
    }

    /*
        Méthode : carry(Item)
        ---------------------
        Fait transporter à la fusée un nouvel objet "item"

        item (Item): l'objet à transporter
     */
    public void carry(Object obj)
    {
        this.cargo.add(obj);
        this.weight += obj.getWeight();
    }

    /*
        Constructeur
        ------------
        Crée une instance de la classe Rocket à partir des attributs de l'objet
        ou bien par un constructeur vide

        price (int): le prix de lancement de la fusée
        weight (int): le poids initial de la fusée
        maxWeight (int): le poids maximal que la fusée peut transporter
     */
    public Rocket(int price, int weight, int maxWeight, double crashPercentAtFullLaunch, double crashPercentAtFullLand) {
        this.price = price;
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.crashPercentAtFullLand = crashPercentAtFullLand;
        this.crashPercentAtFullLaunch = crashPercentAtFullLaunch;
        this.cargo = new ArrayList<>();

        this.alea = new Random();
    }
}
