package fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciTest {
	
	static Map<Integer, Integer> cache = new HashMap<>();
	static int fibonacci(int i) {
	    if (i == 0)
	        return i;
	 
	    if (i == 1)
	        return 1;
	 
	    return cache.computeIfAbsent(i, (key) -> {
	        System.out.println(
	            "Slow calculation of " + key);
	 
	        return fibonacci(i - 2) + fibonacci(i - 1);
	    });
	}
	
	static int fibonacciJava7(int i) {
	    if (i == 0)
	        return i;
	 
	    if (i == 1)
	        return 1;
	 
	    Integer result = cache.get(i);
	    if (result == null) {
	        synchronized (cache) {
	            result = cache.get(i);
	 
	            if (result == null) {
	                System.out.println(
	                    "Slow calculation of " + i);
	 
	                result = fibonacci(i - 2) 
	                       + fibonacci(i - 1);
	                cache.put(i, result);
	            }
	        }
	    }
	 
	    return result;
	}
	
	public static void main(String[] args) {
	    for (int i = 0; i < 10; i++)
	        System.out.println(
	            "f(" + i + ") = " + fibonacciJava7(i));
	}
}
