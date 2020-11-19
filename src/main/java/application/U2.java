package application;

import java.util.Random;

public class U2 extends Rocket{

    public U2()
    {
        super(120,18000,29000);
    }

    public U2(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight);
    }

    @Override
    public boolean launch()
    {
        double chance_of_crash = 4.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }

    @Override
    public boolean land()
    {
        double chance_of_crash = 8.0/100.0 * (double) this.getWeight() / (double) this.getMaxWeight();
        return !(Math.random() < chance_of_crash);
    }
}
