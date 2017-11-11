package twoColorBall;

public class Result
{
	private int[] Highest;
	private int[] Lowest;
	private int[] Medium;
	
	public Result()
	{
		Highest = new int[Configuration.MaxDigit];
		Lowest = new int[Configuration.MaxDigit];
		Medium = new int[Configuration.MaxDigit];		
	}
	
	public int getNumberfromHighestbyDigit(int index)
	{
		return Highest[index];
	}
	
	public void Update( int index, int value, int strategy)
	{
		switch ( strategy )
		{
			case Configuration.HIGHEST_RATE:
				Highest[index] = value;
				break;
			case Configuration.LOWEST_RATE:
				Lowest[index] = value;
				break;
			case Configuration.MEDIUM_RATE:
				Medium[index] = value;
				break;
		}
	}
	public int getNumberfromLowestbyDigit(int index)
	{
		return Lowest[index];
	}
	
	public int getNumberfromMediumbyDigit(int index)
	{
		return Medium[index];
	}
	
	public void setBlueBallwithStrategy( int strategy,int blue)
	{
		switch  (strategy)
		{
			case Configuration.HIGHEST_RATE:
				Highest[Configuration.MaxDigit-1] = blue;
				break;
			case Configuration.MEDIUM_RATE:
				Medium[Configuration.MaxDigit-1] = blue;
				break;
			case Configuration.LOWEST_RATE:
				Lowest[Configuration.MaxDigit-1] = blue;
				break;
		}
	}
	
	public void displayResult()
	{
		String result = "";
		System.out.println("***************** HIGH ****************");
		for( int i = 0 ; i < Configuration.MaxDigit;i++)
		{
			if ( i == Configuration.MaxDigit - 1 )
				result = result + Highest[i];
			else
				result = result + Highest[i] + ",";
		}
		System.out.println(result);
		System.out.println("***************** LOW ****************");
		result = "";
		for( int i = 0 ; i < Configuration.MaxDigit;i++)
		{
			if ( i == Configuration.MaxDigit - 1 )
				result = result + Lowest[i];
			else
				result = result + Lowest[i] + ",";
		}
		System.out.println(result);
		result = "";
		System.out.println("***************** MEDIUM ****************");
		for( int i = 0 ; i < Configuration.MaxDigit;i++)
		{
			if ( i == Configuration.MaxDigit - 1 )
				result = result + Medium[i];
			else
				result = result + Medium[i] + ",";
		}
		System.out.println(result);
	}
}