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
        super(price, weight, maxWeight);
    }
    public U2()
    {
        super(120,18000,29000);
    }

    @Override
    public boolean launch()
    {
        // On calcule le risque de se crasher
        double chance_of_crash = 4.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }

    @Override
    public boolean land()
    {
        // On calcule le risque de se crasher
        double chance_of_crash = 8.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }
}
