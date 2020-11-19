package application;

public abstract class Rocket implements SpaceShip {
    public boolean launch()
    {
        return true;
    }

    public boolean land()
    {
        return true;
    }

    public boolean canCarry()
    {
        return true;
    }

    public void carry()
    {
    }
}
