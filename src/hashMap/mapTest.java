package hashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class mapTest {

	public static void main(String[] args) {
		Map<Integer, String> a = new HashMap<Integer, String>();
		a.put(11, "Jerry");
		a.put(22, "Tom");
		a.put(33, "Jim");
		
		Collection<String> valueCollection =  a.values();
		
		// features.forEach(System.out::println);
		for( String item : valueCollection){ 
			System.out.println("value in HashMap: " + item);
		}
		
		for (Entry<Integer, String> entry : a.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
//		Java8 Streams & Lambda expressions demo			
//		productOrderQuantityMap = CachedDB
		Stream<String> filterResult = a.values().stream().filter( b -> b.equals("Jerry"));
		// filterResult.forEach(System.out::println); // only return Jerry
		
		/* (params) -> expression
		(params) -> statement
		(params) -> { statements } */
		
		/* if you are working on a closed stream, you will meet with error below:
		 * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
	at java.util.stream.AbstractPipeline.evaluate(Unknown Source)
	at java.util.stream.ReferencePipeline.forEach(Unknown Source)
	at hashMap.mapTest.main(mapTest.java:37)
		 */
		// filterResult.forEach( (each) -> System.out.println("filter result: " + each ));
		
		/* Returns an equivalent stream that is parallel. May return itself, either because the stream was already parallel, or because the underlying stream state was modified to be parallel. 
This is an intermediate operation.
Returns:a parallel stream
		 */
		Stream<Map.Entry<Integer, String>> newStream = a.entrySet().stream().parallel();
		System.out.println("Count: " + newStream.count()); // 3
		
		/* 
		 * Returns a stream consisting of the results of replacing each element of this 
		 * stream with the contents of a mapped stream produced by applying the provided 
		 * mapping function to each element. Each mapped stream is 
		 * closed after its contents have been placed into this stream. 
		 * (If a mapped stream is null an empty stream is used, instead.) 

			This is an intermediate operation.
			Parameters:<R> The element type of the new streammapper a non-interfering, stateless function to apply to each element which produces a stream of new valuesReturns:the new stream@apiNoteThe flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream, and then flattening the resulting elements into a new stream. 
			Examples. 

			If orders is a stream of purchase orders, and each purchase order contains a collection of line items, then the following produces a stream containing all the line items in all the orders: 
orders.flatMap(order -> order.getLineItems().stream())...

If path is the path to a file, then the following produces a stream of the words contained in that file: 
Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
     Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
The mapper function passed to flatMap splits a line, using a simple regular expression, into an array of words, and then creates a stream of words from that array.
		 */
		
		
		
		
//				.getPurchaseOrderMap()
//				.values()
//				.stream()
//				.parallel()
//				.flatMap(
//						po -> po.getProductQuantityMap().entrySet()
//								.stream())
//				.collect(
//						Collectors.groupingBy(
//								t -> t.getKey(),
//								Collectors
//										.summingInt(t -> ((Entry<Integer, Integer>) t)
//												.getValue())));
		
	}

}
