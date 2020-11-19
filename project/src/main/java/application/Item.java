package application;

public class Item {
    private String name;
    private int weight;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Item() { }
    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}
