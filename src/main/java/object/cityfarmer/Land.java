package main.java.object.cityfarmer;
import java.sql.*;
import java.time.*;

/**
 * Land
 */
public class Land {
    private String username;
    private int landNumber;
    private String cropName; 
    private int quantity; // number of crops (?)
    private int stolen = 0; // number of products stolen for this plot of land
    private Timestamp harvestTime; // need to harvest after harvestTime...
    private Timestamp wiltTime; // ...but before wiltTime

    public Land(String username, int landNumber, String cropName, int quantity, Timestamp harvestTime, Timestamp wiltTime) {
        this.username = username;
        this.landNumber = landNumber;
        this.cropName = cropName;
        this.quantity = quantity;
        this.harvestTime = harvestTime;
        this.wiltTime = wiltTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStolen() {
        return stolen;
    }

    public int getLandNumber() {
        return landNumber;
    }

    public Timestamp harvestTime() {
        return harvestTime;
    }

    public String getCropName() {
        return cropName;
    }

    public boolean canHarvest() {
        /**
         * @return boolean - whether the crops on this particular land can be harvested
         */
        Timestamp now = Helper.now();
        if (now.after(harvestTime) && now.before(wiltTime)) {
            return true;
        }
        return false;
    }

    public String getProgress() {
        if (this.canHarvest()) {
            Crop crop = Helper.getCrop(cropName);
            Timestamp now = Helper.now();
            double nowDouble = (double) now.getTime();  // time now in milliseconds
            
            double timeNeededToHarvest = (double) crop.getTime() * 60 * 1000; // time taken to harvest, in milliseconds
            
            double harvestDouble = (double) harvestTime.getTime(); // time when crop can be harvested, in milliseconds

            double timeLeftDouble = harvestDouble - nowDouble; // time left till harvest

            // let's say we have 100 ms left to harvest.
            // if the total time needed to harvest is 300ms, we are (300 - 100) * 300 / 100 = 200 * 300 / 100 = 67% done
            int percentageDone = (int) ((timeNeededToHarvest - timeLeftDouble) * timeNeededToHarvest / 100);
            String doneString = String.valueOf(percentageDone);
            int timesToPrintHash = Character.getNumericValue(doneString.charAt(0));
            int timesToPrintDash = 10 - timesToPrintHash;
            String toReturn = cropName + "\t" + "[";
            for (int i = 0; i < timesToPrintHash; i++) {
                toReturn += "#";
            }
            for (int j = 0; j < timesToPrintDash; j++) {
                toReturn += "-";
            }
            toReturn += "] " + percentageDone + "%";
            return toReturn;
        }
        return "null";
    }
    
    // private Crop getCrop(String input) { // not working
    //     if (input.equals("Papaya")) {
    //         Papaya p = new Papaya();
    //         return (Crop) p;
    //     } else if (input == "Pumpkin") {
    //         Pumpkin p2 = new Pumpkin();
    //         return (Crop) p2;
    //     } else if (input == "Watermelon") {
    //         Watermelon w = new Watermelon();
    //         return (Crop) w;
    //     } 
    // }

    // public boolean 

    // 
}