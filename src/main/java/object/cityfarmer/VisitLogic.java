package main.java.object.cityfarmer;

import java.util.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.object.*;
// v1.0 - first commit
// v1.1 - add updateExpAndGold
/**
 * Visit
 * 
 * INPUT: Takes in a friend's username
 * Uses DAO to access friend's title, gold, and lands
 * Sends data to DAO to update self and friend's crops (if stealing crops)
 * OUTPUT: 
 */
public class VisitLogic {
    public static ArrayList<User> getFriends(String username) {
        /**
         * @param username - name of the user whose friends we want to retrive
         * @return - ArrayList of Users
        */

        ArrayList<User> uList = FriendDAO.retrieveAllFriends(username);
        return uList;
    }

    public static ArrayList<Land> getLands(String username) {
        /**
         * @return - list of lands belonging to user
         */
        return LandDAO.retrieveLandsByUsername(username);
    }

    public static ArrayList<List<Object>> stealFrom(Farmer thief, Farmer stolenFromFarmer) {
        /**
         * @param thief - username of user that is stealing
         * @param username - name of user you are stealing crops from
         * @return - ArrayList of
         * 1. number of crops stolen
         * 2. name of crop stolen
         * 3. xp gained
         * 4. gold gained
         * 
         * The total amount of produce available that all your friends (in total) 
         * can steal from you is 20% of your produce per plot of land.
         * 
         * During the visit, he can "steal" a small quantity (up to 5% inclusive) 
         * of his friend's produce (if they are not harvested yet). 
         * 
         * For each plot of land, generate a random number(between 1 and 5) 
         * to determine the amount available for theft. 
         * 
         * You can only steal once per batch of crop planted.
         * 
         * In the event that the remaining quantity available for theft is lesser 
         * than the random number generated, use the smaller of the two values.
         */
        String username = stolenFromFarmer.getUserName();
        if (username == null) {
            System.out.println("Username is null!");
            return null;
        }
        ArrayList<List<Object>> toReturn = new ArrayList<>();
        ArrayList<Land> landList = getLands(username); // get all of user's lands
        boolean canHarvest = false;
        // Random rand = new Random();

        // iterate through each land in landList
        for (Land land : landList) {
            int quantity = land.getQuantity();
            // compute stolen %
            double stolenPercent = land.getStolen() / (double) quantity; // percent of crops already stolen; this can't be higher than 20.
            /** TODO - check if can be harvested */
            if (land.canHarvest() && stolenPercent < 20.0) { // crop can be harvested, and stolen % is less than 20 => can be stolen
                // note down land_number to update stolen later
                int landNumber = land.getLandNumber();

                int lowerBound = (int) (20.0 - stolenPercent); // always use this if generated number is larger
                // int randSteal = rand.nextInt(4) + 1; // random from 1 to 5  
                int randSteal = Helper.getRandomNumberInRange(1, 5); // random from 1 to 5
                // calculate number of crops to steal. randsteal cannot be greater.
                int toSteal = Math.min(randSteal, lowerBound);
                // 2. steal the crop
                boolean stealSuccess = LandDAO.reduceCrop(toSteal, username, landNumber);
                boolean updateStolenSuccess = LandDAO.updateStolen(toSteal, username, landNumber);
                
                // generate list to add to final list
                ArrayList<Object> innerList = new ArrayList<>();
                if (stealSuccess && updateStolenSuccess) {
                    // 3. calculate how much exp and gold is stolen
                    // we need to get the crop first
                    Crop stolenCrop = LandDAO.getCrop(username, landNumber);
                    String cropName = stolenCrop.getName();
                    int exp = stolenCrop.getXP() * toSteal;
                    double gold = (double) stolenCrop.getSalePrice() * (double) toSteal;
                    
                    // update straightaway
                    FarmerDAO.increaseXP(exp, thief.getUserName());
                    FarmerDAO.increaseGold(gold, thief.getUserName());


                    // add to innerList
                    innerList.add(toSteal);
                    innerList.add(cropName);
                    innerList.add(exp);
                    innerList.add(gold);

                    // print output
                    System.out.println("You have successfully stolen " + toSteal + " " + cropName + " for " + exp + " XP, and " + gold + " gold.");

                    // add to final list
                    toReturn.add(innerList);
                } else {
                    System.out.println("Error! stealSuccess:" + stealSuccess + ", updateStolenSuccess:" + updateStolenSuccess);
                }
            }
        }

        return toReturn;
    }
}