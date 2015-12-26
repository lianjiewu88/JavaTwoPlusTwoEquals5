package quiz;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class StrangeSort65 {

	public static void main(String[] args) {
		Random rnd = new Random();
		Integer[] arr = new Integer[100];
		int x = -2000000000;
		int z = 2000000000;
		System.out.println("Strange: " +  ( x - z ) );
		for( int i = 0; i < arr.length; i++)
		  arr[i] = rnd.nextInt();
		Comparator<Integer> cmp = new Comparator<Integer>(){
		    public int compare(Integer i1, Integer i2) {
		        return i2 - i1;
		    }
		  };
		 // this can work
		  Arrays.sort(arr, Collections.reverseOrder());
		 // Arrays.sort(arr, cmp);
		 for( int i = 0 ; i < arr.length; i++){
			 System.out.println("sorted: " + arr[i]);
		 }
		 /* public int compare2(Integer i1, Integer i2) {
   			return (i2 < i1 ? -1: (i2 == i1 ? 0:1));
			}
		*/
	}

}
