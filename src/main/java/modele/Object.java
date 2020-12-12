package modele;

public class Object {
    private final String name;
    private final int price;
    private final int weight;
    private final int quantity;
    private final String priority;

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

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPriority() {
        return priority;
    }
}
