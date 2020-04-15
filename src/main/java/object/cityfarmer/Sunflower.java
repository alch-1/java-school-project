package main.java.object.cityfarmer;
public class Sunflower extends Crop {
    private final String name = "Sunflower";
    private final int cost = 40;
    private final int time = 120;
    private final int xp = 20;
    private final int minYield = 15;
    private final int maxYield = 20;
    private final int salePrice = 40;

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