package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Article {
	private String author;
	private String tag;
	private int rank;

	public Article(String _author, String _tag, int _rank) {
		this.author = _author;
		this.tag = _tag;
		this.rank = _rank;
	}

	@Override
	public String toString() {
		return String.format("Article author: " + this.author + " tag: "
				+ this.tag + " rank: " + this.rank);
	}

	public String getAuthor() {
		return this.author;
	}

	public String getTag() {
		return this.tag;
	}

	public int getRank() {
		return this.rank;
	}
}

/*
 * Jerry 2016-01-21 12:24PM Function signature This is a functional interface
 * whose functional method is apply(Object).
 * 
 * Parameters: <T> the type of the input to the function <R> the type of the
 * result of the function Since: 1.8
 */
public class FunctionCombine {

	private static List<Article> getTestData() {
		ArrayList<Article> data = new ArrayList<Article>();
		data.add(new Article("Jerry", "ABAP", 1));
		data.add(new Article("Jerry", "Scala", 3));
		data.add(new Article("Jerry", "JavaScript", 2));
		data.add(new Article("Orlando", "Swift", 4));
		return data;
	}

	/*
	 * Jerry 2016-01-21 12:58PM be careful about this syntax: ? is necessary
	 * 如果用了::, 前面就不应该用lamda
	 */
	private static void printList(List<? extends Object> list) {
		list.forEach(a -> {
			System.out.println("list item: " + a);
		});

		/*
		 * cannot insert any new element except null The method add(capture#2-of
		 * ? extends Object) in the type List<capture#2-of ? extends Object> is
		 * not applicable for the arguments (String) list.add("1");
		 */
	}

	private static void insertList(List<? super Number> list) {
		// can insert
		list.add(1.2f);
		list.add(1);
	}

	private static void superTest() {
		ArrayList<Number> a = new ArrayList<Number>();
		insertList(a);
		printList(a);
	}

	public static void combineTest() {
		Function<Integer, Integer> times2 = e -> e * 2;

		Function<Integer, Integer> squared = e -> e * e;

		// 先执行参数，再执行调用者
		/*
		 * 1. 4 * 4 = 16 16 * 2 = 32
		 */
		System.out.println("result: " + times2.compose(squared).apply(4)); // 32

		/*
		 * 先执行调用者： 4 * 2 = 8 再执行then传入的function 8 * 8 = 64
		 */
		System.out.println("result: " + times2.andThen(squared).apply(4)); // 64
	}

	/*
	 * use this way to get a stream: Stream<Person> people = Stream.of(new
	 * Person("Paul", 24), new Person("Mark", 30), new Person("Will", 28)); 由于
	 * BiFunction 接收两个参数，它只提供 andThen 函数。你不能将一个函数的结果放在一个接收两个参数的函数中，因此没有 compose
	 * 函数。
	 */

	public static void articleTest() {
		BiFunction<String, List<Article>, List<Article>> byAuthor = (name,
				articles) -> articles.stream()
				.filter(a -> a.getAuthor().equals(name))
				.collect(Collectors.toList());

		BiFunction<String, List<Article>, List<Article>> byTag = (tag, articles) -> articles
				.stream().filter(a -> a.getTag().equals(tag))
				.collect(Collectors.toList());

		List<Article> result = byAuthor.apply("Jerry", getTestData());
		printList(result);

		// Jerry 2016-01-21 13:47PM - comparator需要返回一个int
		Function<List<Article>, List<Article>> sortByRank =

		articles -> articles.stream()
				.sorted((x, y) -> y.getRank() - x.getRank())
				.collect(Collectors.toList());
		List<Article> Rankresult = sortByRank.apply(getTestData());
		System.out.println("rank sort result");
		printList(Rankresult);
		
		Function<List<Article>, Optional<Article>> first = a -> a.stream().findFirst();
		
		Function<List<Article>, Optional<Article>> top = first.compose(sortByRank);
		
		Optional<Article> topArticle = top.apply(getTestData());
		System.out.println("top article: " + topArticle);
		
		
		// find Jerry's top article:
		// 接受两个输入，author和list本身
		BiFunction<String, List<Article>, Optional<Article>> topByAuthor =  
			    byAuthor.andThen(sortByRank).andThen(first);
		System.out.println("Jerry's top article: " + topByAuthor.apply("Jerry", getTestData()).get());
		
	}

	public static void main(String[] args) {
		// combineTest();
		articleTest();
		superTest();
	}

}
