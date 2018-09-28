package listSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListSort {

	private static void print(List<String> list){
		for( int i = 0; i< list.size(); i++){
			System.out.println("Index: " + i + " element: " + list.get(i));
		}
	}
	
	private static void normalSort(){
		
		List<String> list = (List) Arrays.asList("2", "3", "1", "4");

		Comparator<String> comparator = new Comparator<String>() {
		    @Override
		    public int compare(String o1, String o2) {
		        return Integer.valueOf(o1) - Integer.valueOf(o2);
		    }
		};

		list.sort(comparator);
		print(list);
	}
	
	public static void main(String[] args) {
		normalSort();
	}
}
