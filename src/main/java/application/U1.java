package application;

public class U1 extends Rocket {

    public U1()
    {
        super(100, 10,18);
    }

    public U1(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight);
    }

    @Override
    public boolean launch()
    {
        double chance_of_crash = 1/100 * (this.getWeight() / this.getMaxWeight());
        return !(Math.random() < chance_of_crash);
    }

    @Override
    public boolean land()
    {
        double chance_of_crash = 5/100 * (this.getWeight() / this.getMaxWeight());
        return !(Math.random() < chance_of_crash);
    }
}
