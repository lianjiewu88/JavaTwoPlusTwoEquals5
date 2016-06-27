package array;

public class multiDimensionArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] array = {{1,2,3}, {4,5,6}, {7,8,9}}; // ok
		int [][][] array2 = {{ {1,2,3}}, {{4,5,6}}, {{7,8,9}}};
		int size = array2.length;
		System.out.println("size: " + size);
		
		for( int i = 0; i < size; i++){
			int[][] twoDimension = array2[i];
			System.out.println("inner size: " + twoDimension.length);
			for( int j = 0; j < twoDimension.length; j++){
				int [] threeDimension = twoDimension[j];
				for( int z = 0; z < threeDimension.length; z++){
					System.out.println("i,j,j: " + "[" + i + "],[" + j + "],[" + z + "]:->" + 
							threeDimension[z]);
				}
			}
		}
	}

}
