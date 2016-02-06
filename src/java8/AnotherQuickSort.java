package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnotherQuickSort {
	
	public List<Comparable> sort(List<Comparable> elements) {  
	    if (elements.size() == 0) 
	    	return elements;

	    int pivot = 1;
	    @SuppressWarnings("unchecked")
		Stream<Comparable> lesser = (Stream<Comparable>) elements.stream().filter(x -> x.compareTo(pivot) < 0)
	    .collect(Collectors.toList());

	    Stream<Comparable> greater = (Stream<Comparable>) elements.stream().filter(x -> x.compareTo(pivot) >= 0).collect(Collectors.toList());

	    List<Comparable> sorted = new ArrayList<Comparable>();
	    // sorted.addAll(sort(lesser));
	    sorted.add(pivot);
	    // sorted.addAll(sort(greater));

	    return sorted;

	}
}
