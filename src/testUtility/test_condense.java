package testUtility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

public class test_condense
{
	public static void main(String[] args) 
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		HashMap<Integer,Vector<Integer>> revert = new HashMap<Integer,Vector<Integer>>();
		map.put(4, 2);
		map.put(5, 2);
		map.put(1, 6);
		map.put(3, 6);
		map.put(7, 1);
		Set<Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Entry<Integer, Integer>> itor = set.iterator();
		while(itor.hasNext())
		{
			Entry<Integer, Integer> entry = itor.next();
			int NumberwithOccurance = entry.getKey();
			int Occurance = entry.getValue();
			if( revert.containsKey(entry.getValue()) == false)
			{
				Vector<Integer> NumberListWithSameOccurance = new Vector<Integer>();	
				NumberListWithSameOccurance.add(NumberwithOccurance);
				revert.put(Occurance, NumberListWithSameOccurance);
			}
			else
			{
				Vector<Integer> existingNumberList = revert.get(Occurance);
				existingNumberList.add(NumberwithOccurance);
				revert.put(Occurance, existingNumberList);
			}
		}
		Set<Entry<Integer, Vector<Integer>>> Revertset = revert.entrySet();
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