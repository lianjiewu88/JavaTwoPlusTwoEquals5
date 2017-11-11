package twoColorBall.test;

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
		
		// test sort by occurance
		int size = revert.size();
		int[] OccuranceList = new int[size];
		int index = 0;
		Revertset = revert.entrySet();
		Revertitor = Revertset.iterator();
		while(Revertitor.hasNext())
		{
			Entry<Integer, Vector<Integer>> entry = Revertitor.next();
			OccuranceList[index++] = entry.getKey();
		}
		for( int i = 0 ; i < size ; i++)
		{
			for ( int j = i ; j < size; j++)
			{
				if( OccuranceList[i] < OccuranceList[j])
				{
					int temp = OccuranceList[j];
					OccuranceList[j] = OccuranceList[i];
					OccuranceList[i] = temp;
				}
			}
		}
		System.out.println("***************this is sorted result!!**************");
		Vector<SortedNumberInstance> sorted = new Vector<SortedNumberInstance>();
		for( int i = 0 ; i < size ; i++)
		{
			int sortedOccurance = OccuranceList[i];
			Vector<Integer> duplicateList = revert.get(sortedOccurance);
			SortedNumberInstance instance = new SortedNumberInstance(sortedOccurance,duplicateList);
			sorted.add(instance);
		}
		
		for( int i = 0 ; i < sorted.size(); i ++)
		{
			System.out.println("Sorted Occurance: " + sorted.elementAt(i).getOccurance());
			for ( int j = 0 ; j < sorted.elementAt(i).getNumberListwithSameOccurance().size(); j++)
			{
				System.out.println(" Number with same Occurance: " + sorted.elementAt(i).getNumberListwithSameOccurance().elementAt(j));
			}
		}

		
	}
}