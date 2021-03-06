package modele;

import java.util.function.Function;

/*
    Classe : U1
    -----------
    La classe U1 décrit les fusées de type U1. Elle hérite de la classe Rocket
    et implémente toutes ses méthodes.
 */
public class U1 extends Rocket {

    /*
        Constructeur
        ------------
        Permet de créer une instance de la classe U1 à partir de ses caractéristiques
        ou bien à partir d'un constructeur vide qui créera une instance de U1 avec
        des valeurs par défaut.

        price (int): le prix de lancement de la fusée
        weight (int): le poids initial de la fusée
        maxWeight (int): le poids maximal que la fusée peut transporter
     */
    public U1(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight, 5.0, 5.0);
    }
    public U1()  {
        super(100, 10000,18000, 5.0, 5.0);
    }

    @Override
    public boolean launch()
    {
        return this.launch(DistributionType.LINEAR);
    }

    @Override
    public boolean land()
    {
        return this.land(DistributionType.LINEAR);
    }
}
