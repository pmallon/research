package com.medieval;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Permutations<T>{
	
	 private Permutations() {}

	    public static <T> List<T[]> get(Class<T> itemClass, T... itemsPool) {
	        return get(itemsPool.length, itemClass, itemsPool);
	    }

	    public static <T> List<T[]> get(int size, Class<T> itemClass, T... itemsPool) {
	        if (size < 1) {
	            return new ArrayList<T[]>();
	        }

	        int itemsPoolCount = itemsPool.length;

	        List<T[]> permutations = new ArrayList<T[]>();
	        for (int i = 0; i < Math.pow(itemsPoolCount, size); i++) {
	            T[] permutation = (T[]) Array.newInstance(itemClass, size);
	            for (int j = 0; j < size; j++) {
	                // Pick the appropriate item from the item pool given j and i
	                int itemPoolIndex = (int) Math.floor((double) (i % (int) Math.pow(itemsPoolCount, j + 1)) / (int) Math.pow(itemsPoolCount, j));
	                permutation[j] = itemsPool[itemPoolIndex];
	            }
	            permutations.add(permutation);
	        }

	        return permutations;
	    }

}
