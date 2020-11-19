package application;

public class U1 extends Rocket {


    public U1(int price, int weight, int maxWeight) {
        super(price, weight, maxWeight);
    }

    @Override
    public boolean launch()
    {
        return true;
    }

    @Override
    public boolean land()
    {
        return true;
    }
}
