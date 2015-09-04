package sort;

public class shilSort {

	public static void main(String[] args) {

		int a[] = {1,54,6,3,78,34,12,45,56,100};
		double d1 = a.length;
		int temp = 0;
		System.out.println("begin...");
		while(true) {
			d1 = Math.ceil( d1/2 );
			int d = (int) d1;
			for(int x = 0; x < d; x++){
				for( int i = x + d; i < a.length; i += d){
					int j = i - d;
					temp = a[i];
					for(; j >= 0 && temp < a[j]; j -= d){
						a[j+d] = a[j];
					}
					System.out.println("Jerry insert value: " + temp + 
						" to Position: < " + (j+d) + " >");
					a[j+d] = temp;
				}
			}

			if( d == 1){
				break;
			}
		}
		for(int i = 0; i < a.length;i++){
			System.out.println(a[i]);
		}
	}

}
