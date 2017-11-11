package twoColorBall.test;

import java.util.Vector;

public class SortedNumberInstance
{
	private int Occurance;
	private Vector<Integer> NumberListwithSameOccurance;
	
	public SortedNumberInstance( int occurance, Vector<Integer> NumberList)
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