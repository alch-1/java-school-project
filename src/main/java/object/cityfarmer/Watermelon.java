package main.java.object.cityfarmer;

public class Watermelon extends Crop {
    private final String name = "Watermelon";
    private final int cost = 50;
    private final int time = 240;
    private final int xp = 1;
    private final int minYield = 5;
    private final int maxYield = 800;
    private final int salePrice = 10;

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