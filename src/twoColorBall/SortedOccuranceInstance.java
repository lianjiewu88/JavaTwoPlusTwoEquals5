package twoColorBall;

import java.util.Vector;

public class SortedOccuranceInstance
{
	private int Occurance;
	private Vector<Integer> NumberListwithSameOccurance;
	
	public SortedOccuranceInstance( int occurance, Vector<Integer> NumberList)
	{
		Occurance = occurance;
		NumberListwithSameOccurance = NumberList;
	}
	public int getOccurance()
	{
		return Occurance;
	}
	public Vector<Integer> getNumberListwithSameOccurance()
	{
		return NumberListwithSameOccurance;
	}
}