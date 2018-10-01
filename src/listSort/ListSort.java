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
	
	private static void newprint(List<String> list){
		list.forEach(System.out::println);
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
	
	private static void java8sort(){
		
		List<String> list = (List) Arrays.asList("2", "3", "1", "4");

		list.sort((String o1, String o2) -> Integer.valueOf(o1) - Integer.valueOf(o2));
		print(list);
	}
	
	private static void java8sort2(){
		
		List<String> list = (List) Arrays.asList("2", "3", "1", "4");

		list.sort(Comparator.comparing(Integer::valueOf));
		newprint(list);
	}

	public static void main(String[] args) {
		java8sort2();
	}
}
