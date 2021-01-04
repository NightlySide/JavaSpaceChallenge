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
