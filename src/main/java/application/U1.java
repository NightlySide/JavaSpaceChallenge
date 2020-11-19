package application;

public class U1 extends Rocket {

    public U1()
    {
        super(100, 10000,18000);
    }

    public U1(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight);
    }

    @Override
    public boolean launch()
    {
        double chance_of_crash = 5.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }

    @Override
    public boolean land()
    {
        double chance_of_crash = 5.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }
}
