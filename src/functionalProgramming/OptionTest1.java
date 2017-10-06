package functionalProgramming;

import java.util.Optional;

// from www.rowkey.me/blog/2017/08/18/lift-functions/

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
