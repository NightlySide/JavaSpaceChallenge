package application;

public abstract class Rocket implements SpaceShip {

    private final int price;
    private int weight;
    private final int maxWeight;

    public Rocket(int price, int weight, int maxWeight) {
        this.price = price;
        this.weight = weight;
        this.maxWeight = maxWeight;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean launch()
    {
        return true;
    }

    public boolean land()
    {
        return true;
    }

    public boolean canCarry(Item item)
    {
        return this.weight < item.getWeight();
    }

    public void carry(Item item)
    {
        this.weight = item.getWeight();
    }
}
