package twoColorBall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class OccuranceOverview
{
	private HashMap<Integer,OccuranceForEachNumber> OccuranceOverview = null;
	
	private Result result = null;
	
	
	public OccuranceOverview()
	{
		OccuranceOverview = new HashMap<Integer,OccuranceForEachNumber>();
		for ( int i = 0 ; i < Configuration.MaxDigit; i++ )
		{
			OccuranceForEachNumber Occurance4EachNumber = new OccuranceForEachNumber(i);
			OccuranceOverview.put(i,Occurance4EachNumber);
		}
	}
	
	public OccuranceForEachNumber getOccuranceInstanceByDigit(int Digit)
	{
		return OccuranceOverview.get(Digit);
	}
	
	public void updateDigitOccurance(int Digit,OccuranceForEachNumber OccuranceInstance)
	{
		OccuranceOverview.put(Digit, OccuranceInstance);
	}
	public void listOccuranceForEachDigit()
	{
		int index = 0;
		result = new Result();
		Set<Entry<Integer, OccuranceForEachNumber>> set = OccuranceOverview.entrySet();
		Iterator<Entry<Integer, OccuranceForEachNumber>> itor = set.iterator();
		// digit 0,1,2,3,4,5,6
		while(itor.hasNext())
		{
		   Entry<Integer, OccuranceForEachNumber> entry = itor.next();
		   //int Digit = entry.getKey();
		   entry.getValue().ListOccuranceForEachNumber();
		   entry.getValue().condense();
		   entry.getValue().sort();
		   if ( index == OccuranceOverview.size() - 1 )
		   {
			   System.out.println("Blue!!");
			   int blue = entry.getValue().getNumberforBlueByIndex(Configuration.HIGHEST_RATE);
			   result.setBlueBallwithStrategy(Configuration.HIGHEST_RATE, blue);
			   System.out.println("Highest: " + blue);
			   
			   blue = entry.getValue().getNumberforBlueByIndex(Configuration.LOWEST_RATE);
			   result.setBlueBallwithStrategy(Configuration.LOWEST_RATE, blue);
			   System.out.println("Lowest: " + blue);
			   
			   blue = entry.getValue().getNumberforBlueByIndex(Configuration.MEDIUM_RATE);
			   result.setBlueBallwithStrategy(Configuration.MEDIUM_RATE, blue);
			   System.out.println("Medium: " + blue);   
		   }
		   index++;
		}
		
		// set for Red ball 0 ~5
		setRedBall();
		result.displayResult();
	}
	
	public void setRedBall()
	{
		Set<Entry<Integer, OccuranceForEachNumber>> set = OccuranceOverview.entrySet();
		Iterator<Entry<Integer, OccuranceForEachNumber>> itor = set.iterator();
		// digit 0,1,2,3,4,5
		while(itor.hasNext())
		{
		   Entry<Integer, OccuranceForEachNumber> entry = itor.next();
		   int Digit = entry.getKey();
		   if ( Digit == Configuration.MaxDigit -1)
			   return;
		   System.out.println("In Red Ball,Digit: " + Digit );
		   entry.getValue().setResultReference(result);
		   entry.getValue().setRedBallbyIndex(Digit);
		}
		   /*entry.getValue().condense();
		   entry.getValue().sort();
		   if ( index == OccuranceOverview.size() - 1 )
		   {
			   System.out.println("Blue!!");
			   int blue = entry.getValue().getNumberforBlueByIndex(Configuration.HIGHEST_RATE);
			   result.setBlueBallwithStrategy(Configuration.HIGHEST_RATE, blue);
			   System.out.println("Highest: " + blue);
			   
			   blue = entry.getValue().getNumberforBlueByIndex(Configuration.LOWEST_RATE);
			   result.setBlueBallwithStrategy(Configuration.LOWEST_RATE, blue);
			   System.out.println("Lowest: " + blue);
			   
			   blue = entry.getValue().getNumberforBlueByIndex(Configuration.MEDIUM_RATE);
			   result.setBlueBallwithStrategy(Configuration.MEDIUM_RATE, blue);
			   System.out.println("Medium: " + blue);   
		   }*/
	}
}