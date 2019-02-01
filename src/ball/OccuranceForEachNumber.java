package ball;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

/* example: 13 occurs 15 time
 * 			2  occurs 12 time
 */

public class OccuranceForEachNumber
{
	private HashMap<Integer,Integer> OccuranceEachDigit = null;
	
	private HashMap<Integer,Vector<Integer>> CondensedOccurance = null;
	
	public OccuranceForEachNumber()
	{
		OccuranceEachDigit = new HashMap<Integer,Integer>();
	}
	public boolean isNumberExist(int Number)
	{
		return OccuranceEachDigit.containsKey(Number);
	}
	public void updateNumberOccurance(int Number)
	{
		int CurrentOccurance = OccuranceEachDigit.get(Number);
		CurrentOccurance++;
		OccuranceEachDigit.put(Number,CurrentOccurance);
	}
	
	public void initialNumberOccurance(int Number)
	{
		OccuranceEachDigit.put(Number, 1);
	}
	
	public void ListOccuranceForEachNumber()
	{
		Set<Entry<Integer, Integer>> set = OccuranceEachDigit.entrySet();
		Iterator<Entry<Integer, Integer>> itor = set.iterator();
		while(itor.hasNext())
		{
		   Entry<Integer, Integer> entry = itor.next();
		   int Digit = entry.getKey();
		   System.out.println("Number: " + Digit + " Occurance: " +  entry.getValue() );		   
		}
	}
	
	public void condense()
	{
		if (CondensedOccurance != null )
			CondensedOccurance.clear();
		CondensedOccurance = new HashMap<Integer,Vector<Integer>>();
		Set<Entry<Integer, Integer>> set = OccuranceEachDigit.entrySet();
		Iterator<Entry<Integer, Integer>> itor = set.iterator();
		while(itor.hasNext())
		{
			Entry<Integer, Integer> entry = itor.next();
			int NumberwithOccurance = entry.getKey();
			int Occurance = entry.getValue();
			if( CondensedOccurance.containsKey(entry.getValue()) == false)
			{
				Vector<Integer> NumberListWithSameOccurance = new Vector<Integer>();	
				NumberListWithSameOccurance.add(NumberwithOccurance);
				CondensedOccurance.put(Occurance, NumberListWithSameOccurance);
			}
			else
			{
				Vector<Integer> existingNumberList = CondensedOccurance.get(Occurance);
				existingNumberList.add(NumberwithOccurance);
				CondensedOccurance.put(Occurance, existingNumberList);
			}
		}
		Set<Entry<Integer, Vector<Integer>>> Revertset = CondensedOccurance.entrySet();
		Iterator<Entry<Integer, Vector<Integer>>> Revertitor = Revertset.iterator();
		while(Revertitor.hasNext())
		{
			Entry<Integer, Vector<Integer>> entry = Revertitor.next();
			System.out.println("Occruance: " + entry.getKey());
			for( int i = 0 ; i < entry.getValue().size(); i ++)
			{
				System.out.println("Number with same Occurance: " + entry.getValue().elementAt(i));
			}
		}
	}
	
	
}