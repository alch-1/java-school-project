package main.java.object.cityfarmer;
public class Pumpkin extends Crop {
    private final String name = "Pumpkin";
    private final int cost = 30;
    private final int time = 60;
    private final int xp = 5;
    private final int minYield = 5;
    private final int maxYield = 200;
    private final int salePrice = 20;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getXP() {
        return xp;
    }

    @Override
    public int getMinYield() {
        return minYield;
    }

    @Override
    public int getMaxYield() {
        return maxYield;
    }

    @Override
    public int getSalePrice() {
        return salePrice;
    }
}