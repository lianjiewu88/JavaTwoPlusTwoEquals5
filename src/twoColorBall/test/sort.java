package twoColorBall.test;

public class sort
{
	public static void main(String[] args) 
	{
		int[] a = new int[8]; 
		a[0] = 1;
		a[1] = 3;
		a[2] = 2;
		a[3] = 0;
		a[4] = 6;
		a[5] = 8;
		a[6] = 5;
		a[7] = 7;
		
		for( int i = 0 ; i < 8 ; i++)
		{
			for ( int j = i ; j < 8; j++)
			{
				if( a[i] < a[j])
				{
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		
		for( int i = 0 ; i < 8 ; i++)
		{
			System.out.println("Number: " + a[i]);
		}
		
	}
}