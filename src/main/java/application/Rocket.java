package application;

/*
    Classe abstraite : Rocket
    -------------------------
    Classe abstraite implémentant les méthodes de Spaceship.
    Cette classe permet de généraliser le code concernant l'utilisation des rockets
    cependant on souhaite que cette classe ne soit jamais initialisée d'où son abstraction.
 */
public abstract class Rocket implements SpaceShip {

    private int price;
    private int weight;
    private int maxWeight;

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

    /* Getter / Setter du poids maximal de la fusée */
    public int getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /*
        Méthode : launch()
        ------------------
        Permet de savoir si une fusée s'est bien lancée ou bien si elle s'est crashée.

        returns: la fusée s'est bien lancée
     */
    public boolean launch()
    {
        return true;
    }

    /*
        Méthode : land()
        ----------------
        Permet de savoir si une fusée a bien atterit ou bien si elle s'est crashée.

        returns: la fusée a bien atterit
     */
    public boolean land()
    {
        return true;
    }

    /*
        Méthode : canCarry(Item)
        ------------------------
        Vérifie si la fusée est capable de transporter l'objet "item".

        item (Item): l'objet à transporter

        returns: est-ce que la fusée peut l'emporter ou non
     */
    public boolean canCarry(Item item)
    {
        return this.weight + item.getWeight() <= this.getMaxWeight();
    }

    /*
        Méthode : carry(Item)
        ---------------------
        Fait transporter à la fusée un nouvel objet "item"

        item (Item): l'objet à transporter
     */
    public void carry(Item item)
    {
        this.weight += item.getWeight();
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
    public Rocket(int price, int weight, int maxWeight) {
        this.price = price;
        this.weight = weight;
        this.maxWeight = maxWeight;
    }
    public Rocket() {}
}
