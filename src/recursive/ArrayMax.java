package recursive;

public class ArrayMax {
	public static void main(String[] rags) {
		// int[] aim = new int[100];
		int[] aim = { 1,4,3,10,34,1,4 };
		int point = 0;
		int max = aim[0];
		max = getMax(max, point, aim);
		
		System.out.println("Max: " + max);
	}

	public static int getMax(int max, int point, int[] aim) {
		if (point == aim.length)
			return max;
		max = max >= aim[point] ? max : aim[point];
		return getMax(max, point + 1, aim);
	}
}
