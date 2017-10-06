package functionalProgramming;

import java.util.Optional;

// from www.rowkey.me/blog/2017/08/18/lift-functions/
/*Java8中的Stream和Optional给我们带来了函数式编程的乐趣，但Java仍然缺少很多函数编程的关键特性。
 * Lambda表达式、Optional和Stream只是函数式编程的冰山一角。
 * 这也导致了varvr和functionlajava这些类库的出现，他们都源于Haskell这个纯函数式编程语言。

如果想要更加地“函数式”编程，那么首先要注意的是不要过早的中断monad(一种设计模式，
表示将一个运算过程通过函数拆解成互相连接的多个步骤。只要提供下一步运算所需的函数，
整个运算就会自动进行下去, Optional、Stream都是monad)，比如，很多人经常会在还不需要的时候就调用了
Optional.get()和Stream.collect()提前终止monad。
*/
public class OptionTest1 {

	public Optional<Double> divideFirstTwo(JerryNumberProvider numberProvider, JerryMath math) {
		    Optional<Integer> first = numberProvider.getNumber();
		    Optional<Integer> second = numberProvider.getNumber();
		    if(first.isPresent() && second.isPresent()) {
		        double result = math.divide(first.get(), second.get());
		        return Optional.of(result);
		    } else {
		        return Optional.empty();
		    }
	}
	
	public Optional<Double> divideFirstTwo2(JerryNumberProvider numberProvider, JerryMath math) {
	    return numberProvider.getNumber().flatMap(first -> numberProvider.getNumber()
	                                     .map(second -> math.divide(first, second)));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
