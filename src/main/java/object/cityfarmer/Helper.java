package main.java.object.cityfarmer;

import java.util.*;
import java.sql.*;

// Helper functions for cityfarmer

public class Helper{
	public static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}

    public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static Crop getCrop(String input) {
		if (input.equals("Papaya")) {
			return new Papaya();
		} else if (input.equals("Watermelon")) {
			return new Watermelon();
		} else if (input.equals("Sunflower")) {
			return new Sunflower();
		} else if (input.equals("Pumpkin")) {
			return new Pumpkin();
		}
		return null; // invalid input
	}

}