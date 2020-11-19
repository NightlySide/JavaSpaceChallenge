package application;

/*
    Interface : Spaceship
    ---------------------
    Interface permettant de spécifier les attributs qui consituent un vaisseau
    spatial comme son lancement, son attérissage ainsi que sa capacité à transporter
    des objets.
 */
public interface SpaceShip {

    /*
        Méthode : launch()
        ------------------
        Permet de savoir si une fusée s'est bien lancée ou bien si elle s'est crashée.

        returns: la fusée s'est bien lancée
    */
    boolean launch();

    /*
        Méthode : land()
        ----------------
        Permet de savoir si une fusée a bien atterit ou bien si elle s'est crashée.

        returns: la fusée a bien atterit
     */
    boolean land();

    /*
        Méthode : canCarry(Item)
        ------------------------
        Vérifie si la fusée est capable de transporter l'objet "item".

        item (Item): l'objet à transporter

        returns: est-ce que la fusée peut l'emporter ou non
     */
    boolean canCarry(Item item);

    /*
        Constructeur
        ------------
        Crée une instance de la classe Rocket à partir des attributs de l'objet
        ou bien par un constructeur vide

        price (int): le prix de lancement de la fusée
        weight (int): le poids initial de la fusée
        maxWeight (int): le poids maximal que la fusée peut transporter
     */
    void carry(Item item);
}
