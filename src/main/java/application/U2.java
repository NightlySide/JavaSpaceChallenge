package application;

public class U2 extends Rocket{

    public U2()
    {
        super(120,18,29);
    }

    public U2(int price, int weight, int maxWeight) {
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
