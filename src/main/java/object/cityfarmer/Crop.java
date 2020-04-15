package main.java.object.cityfarmer;

/**
 * Fruit
 */
public abstract class Crop {
    public abstract String getName();
    public abstract int getCost();
    public abstract int getTime(); // in minutes
    public abstract int getXP();
    public abstract int getMinYield();
    public abstract int getMaxYield();
    public abstract int getSalePrice();
}