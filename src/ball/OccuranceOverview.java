package ball;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class OccuranceOverview
{
	private HashMap<Integer,OccuranceForEachNumber> OccuranceOverview = null;
	public OccuranceOverview()
	{
		OccuranceOverview = new HashMap<Integer,OccuranceForEachNumber>();
		for ( int i = 0 ; i < Configuration.MaxDigit; i++ )
		{
			OccuranceForEachNumber Occurance4EachNumber = new OccuranceForEachNumber();
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
		Set<Entry<Integer, OccuranceForEachNumber>> set = OccuranceOverview.entrySet();
		Iterator<Entry<Integer, OccuranceForEachNumber>> itor = set.iterator();
		while(itor.hasNext())
		{
		   Entry<Integer, OccuranceForEachNumber> entry = itor.next();
		   int Digit = entry.getKey();
		   System.out.println("**************** Digit: " + Digit + " Information Begin! *************");
		   entry.getValue().ListOccuranceForEachNumber();
		   System.out.println("**************** Condensed Information! **********");
		   entry.getValue().condense();
		}
	}
}