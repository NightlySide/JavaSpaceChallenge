package modele;

public class Object {
    private final String name;
    private final Integer price;
    private final Integer weight;
    private final Integer quantity;
    private final String priority;

    public Object(String name, Integer price, Integer weight, Integer quantity, String priority) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPriority() {
        return priority;
    }

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
