package application;

/*
    Classe: Item
    ------------
    Classe définissant un objet à emporter dans une fusée. Cet objet est décrit par plusieurs
    éléments tels que son nom ou encore son poids
 */
public class Item {
    private String name;
    private int weight;

    /* Getter / Setter du nom de l'objet */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /* Getter / Setter du poids de l'objet */
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /*
        Constructeur
        ------------
        Crée une instance de la classe item à partir des attributs de l'objet
        ou bien par un constructeur vide

        name (String): le nom de l'objet
        weight (int): le poids de l'objet
     */
    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    public Item() { }
}
