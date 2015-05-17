package com.medieval;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Answer2 {
	
	
	public static void main(String[] args) throws IOException {
		
		List<Integer[]> permuations = Permutations.get(Integer.class, new Integer(1),new Integer(2),new Integer(3),new Integer(5));
		
		System.out.print(permuations.size());
		System.out.println("");
		for (Integer[] perm:permuations ){
			System.out.print("{");
			for(int i=0 ; i< perm.length; i++){
				System.out.print(perm[i]+",");
			}
			System.out.print("}");
			System.out.println("");
		}
	}
}
	
	
	
	
	
	
	


