package generic;


class Tuple2<T1, T2> {
    // Lots of bloat, see missing case classes
	Tuple2(T1 t1, T2 t2){
		System.out.println("constructor called");
		m1 = t1;
		m2 = t2;
	}
	
	private T1 m1;
	private T2 m2;
	
	@Override 
	public String toString(){
		String result = "T1: " + m1 + " T2: " + m2;
		System.out.println(result);
		return result;
	}
}

class Tuple3<T1, T2, T3> {
    // Bloat bloat bloat
}


public class TurpleInScala {

	private static void test(){
		Tuple2<Integer, String> t1 = new Tuple2<Integer, String>(1, "A");
		System.out.println("Jerry: " + t1);
		
		/*
		 * ugly!!!
		Tuple3<Integer, String, Tuple2<Integer, String>> t2 = 
				new Tuple3<Integer, String, Tuple2<Integer, String>>(1, "A", new Tuple2<>(2, "B"));
				*/
	}
	public static void main(String[] args) {
		test();
	}

}
