package modele;

/*
    Classe : U2
    -----------
    La classe U2 décrit les fusées de type U1. Elle hérite de la classe Rocket
    et implémente toutes ses méthodes.
 */
public class U2 extends Rocket{

    /*
        Constructeur
        ------------
        Permet de créer une instance de la classe U2 à partir de ses caractéristiques
        ou bien à partir d'un constructeur vide qui créera une instance de U2 avec
        des valeurs par défaut.

        price (int): le prix de lancement de la fusée
        weight (int): le poids initial de la fusée
        maxWeight (int): le poids maximal que la fusée peut transporter
     */
    public U2(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight, 4.0, 8.0);
    }
    public U2()
    {
        super(120,18000,29000, 4.0, 8.0);
    }

    @Override
    public boolean launch(DistributionType crashDistroType)
    {
        // On calcule le risque de se crasher
        double chance_of_crash;
        switch (crashDistroType) {
            case EXPONENTIAL:
                chance_of_crash = CrashDistribution.ExponentialDistributionChance(this, this.getCrashPercentAtFullLaunch(), 5);
                break;
            default:
                chance_of_crash = CrashDistribution.LinearDistributionChance(this, this.getCrashPercentAtFullLaunch());
        }
        return !(this.alea.nextDouble() < chance_of_crash);
    }

    @Override
    public boolean land(DistributionType crashDistroType)
    {
        // On calcule le risque de se crasher
        double chance_of_crash;
        switch (crashDistroType) {
            case EXPONENTIAL:
                chance_of_crash = CrashDistribution.ExponentialDistributionChance(this, this.getCrashPercentAtFullLand(), 5);
                break;
            default:
                chance_of_crash = CrashDistribution.LinearDistributionChance(this, this.getCrashPercentAtFullLand());
        }
        return !(this.alea.nextDouble() < chance_of_crash);
    }
}
