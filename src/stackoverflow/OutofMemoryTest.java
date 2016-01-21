package stackoverflow;

import java.util.ArrayList;

public class OutofMemoryTest {

	 private static ArrayList<Integer> bigArray = new ArrayList<Integer>();

	 public static void main(String[] args) {
	  Runtime.getRuntime().addShutdownHook(new Thread() {
	   @Override
	   public void run() {
	    System.out.println("We have accumulated " + bigArray.size()
	      + " entries");
	   }
	  });
	  
	  for(int i = 0; ;i++) {
	   bigArray.add(i);
	  }
	 }
	}
