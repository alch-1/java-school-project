package main.java.object.cityfarmer;
public class Papaya extends Crop {
    private final String name = "Papaya";
    private final int cost = 20;
    private final int time = 30;
    private final int xp = 8;
    private final int minYield = 75;
    private final int maxYield = 100;
    private final int salePrice = 15;

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