package modele;

public class Object {
    private String name;
    private int price;
    private int weight;
    private int quantity;
    private String priority;

    /*
        Constructeur : Obect()
        ------------------------------------------
        Classe permettant de contenir les données d'un objet à envoyer
        sur mars.
        Chacune des méthodes correspond à une variable du fichier Object.json
    */
    public Object(String name, int price, int weight, int quantity, String priority) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }
    public void setName(String value) { name = value; }

    public int getPrice() {
        return price;
    }
    public void setPrice(int value) { price = value; }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int value) { weight = value; }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int value) { quantity = value; }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String value) { priority = value; }

    @Override
    public String toString() {
        return "Object{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", priority='" + priority + '\'' +
                '}';
    }
}
