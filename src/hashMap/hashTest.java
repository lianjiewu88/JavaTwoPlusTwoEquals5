package hashMap;

public class hashTest {

	static private int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
	
	static private int indexFor(int h, int length) {
	     return h & (length-1);
	}
	        
	public static void main(String[] args) {
		for( int i = 0; i < 10; i++){
			System.out.println(hash(i));
		}
		System.out.println("indexFor");
		System.out.println(indexFor(11, 8));// 3
		System.out.println(indexFor(12, 8));// 4
	}
}
