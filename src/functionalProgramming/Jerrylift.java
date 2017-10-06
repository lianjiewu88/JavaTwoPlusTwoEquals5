package functionalProgramming;

import java.util.Optional;
import java.util.function.BiFunction;

public interface Jerrylift {
    static <R, T, Z> BiFunction<Optional<T>, Optional<R>, Optional<Z>> lift(
    		BiFunction<? super T, ? super R, ? extends Z> function) {
        return (left, right) -> left.flatMap(leftVal -> 
        right.map(rightVal -> function.apply(leftVal, rightVal)));
    }
}