package java8.stream;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamTest {

	public static void main(String args[]) {
		System.out.println("Creating list");
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			strings.add("Item " + i);
		}
		
		strings.stream().forEach(str -> System.out.println(str));
		
		// parallel
		/* 发现处理过程以某种方式在循环跳动。这是因为在运行时将数据划分成了多个块
		 * 
		 * 将数据块分配给合适的处理器去处理。只有当所有块都处理完成了，才会执行之后的代码。本质上讲，
		 * 这是在调用 forEach() 方法时，将整个过程是根据需要来进行划分了。现在，这么做可能会提高性能，也可能不会。
		 * 这依赖于数据集的大小以及你硬件的性能。通过这个例子，也可以看 出，如果需要按照添加的顺序挨个处理每一项，
		 * 那么并行流可能就不合适了。
		 * 
		 * 串行流能保证每次运行的顺序是一致的。但并行流，从定义上讲，是一种更有效率的方式。所以并行流在聚合操作的时候非常有效。
		 * 很适合将集合作为一个整体考虑，然后在该集合上进行一些聚合操作的情况。
		 */
		System.out.println("Another");
		strings.stream().parallel().forEach(str -> System.out.println(str));
		
		Object obj = strings.stream().parallel();
		System.out.println(obj);
		
		// java.util.stream.ReferencePipeline $Head@2d6a9952
	}
}
