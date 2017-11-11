package twoColorBall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import twoColorBall.numberTool.RandomGenerator;


/* example: 13 occurs 15 time
 * 			2  occurs 12 time
 */

public class OccuranceForEachNumber
{
	private HashMap<Integer,Integer> OccuranceEachDigit = null;
	
	private HashMap<Integer,Vector<Integer>> CondensedOccurance = null;
	
	private Result resultReference = null;
	private int currentDigit = -1;
	
	private Vector<SortedOccuranceInstance> sorted = null;
	
	public OccuranceForEachNumber(int Digit)
	{
		OccuranceEachDigit = new HashMap<Integer,Integer>();
		currentDigit = Digit;
	}
	public boolean isNumberExist(int Number)
	{
		return OccuranceEachDigit.containsKey(Number);
	}
	public void setResultReference( Result reference)
	{
		resultReference = reference;
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
		   String value = "Number: " + Digit + " Occurance: " +  entry.getValue();
		   Configuration.trace(value);
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
			String value = "[Condense]Digit: " + currentDigit + "\tOccurance: " + entry.getKey();
			Configuration.trace(value);
			for( int i = 0 ; i < entry.getValue().size(); i ++)
			{
				value = "[Condense]Number with same Occurance: " + entry.getValue().elementAt(i);
				Configuration.trace(value);
			}
		}
	}
	
	public void setRedBallbyIndex(int index)
	{
		for( int i = 0; i < index; i++)
		{
			removeDuplicateEntryAccordingtoResultTable(i,Configuration.HIGHEST_RATE);
			removeDuplicateEntryAccordingtoResultTable(i,Configuration.LOWEST_RATE);
			removeDuplicateEntryAccordingtoResultTable(i,Configuration.MEDIUM_RATE);
		}
		condense();
		sort();
		// now it is impossible to still have duplicate data, so reuse logic for Blue ball
		int redHigh = getNumberforBlueByIndex(Configuration.HIGHEST_RATE);
		int redLow  = getNumberforBlueByIndex(Configuration.LOWEST_RATE);
		int redMedi = getNumberforBlueByIndex(Configuration.MEDIUM_RATE);
		resultReference.Update(index,redHigh,Configuration.HIGHEST_RATE);
		resultReference.Update(index,redLow, Configuration.LOWEST_RATE);
		resultReference.Update(index, redMedi, Configuration.MEDIUM_RATE);
	}
	
	private void removeDuplicateEntryAccordingtoResultTable( int index, int strategy)
	{
		int resultNumber = 0;
		switch (strategy)
		{
			case Configuration.HIGHEST_RATE:
				resultNumber = resultReference.getNumberfromHighestbyDigit(index);
				break;
			case Configuration.LOWEST_RATE:
				resultNumber = resultReference.getNumberfromLowestbyDigit(index);
				break;
			case Configuration.MEDIUM_RATE:
				resultNumber = resultReference.getNumberfromMediumbyDigit(index);
		}
		if ( resultNumber == 0 )
			return;
		if ( OccuranceEachDigit.containsKey(resultNumber) == true )
		{
			OccuranceEachDigit.remove(resultNumber);
			/*System.out.println("Removing: " + resultNumber + " since it is duplicated with Result Table Index: " + index);
			System.out.println("Deleting......................");
			System.out.println("Delete OK.");*/
		}
	}
	
	public void sort()
	{
		/* must happened after condense !!!!!!!!!! */
		int size = CondensedOccurance.size();
		int[] OccuranceList = new int[size];
		int index = 0;
		Set<Entry<Integer, Vector<Integer>>> Revertset = CondensedOccurance.entrySet();
		Iterator<Entry<Integer, Vector<Integer>>> Revertitor = Revertset.iterator();
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
		System.out.println("*************** This Is Sorted Result!! **************");
		sorted = new Vector<SortedOccuranceInstance>();
		for( int i = 0 ; i < size ; i++)
		{
			int sortedOccurance = OccuranceList[i];
			Vector<Integer> duplicateList = CondensedOccurance.get(sortedOccurance);
			SortedOccuranceInstance instance = new SortedOccuranceInstance(sortedOccurance,duplicateList);
			sorted.add(instance);
		}
		
		for( int i = 0 ; i < sorted.size(); i ++)
		{
			System.out.println("[Sort]Digit: " + currentDigit + "\tSorted Occurance: " + sorted.elementAt(i).getOccurance());
			for ( int j = 0 ; j < sorted.elementAt(i).getNumberListwithSameOccurance().size(); j++)
			{
				System.out.println(" Number with same Occurance: " + sorted.elementAt(i).getNumberListwithSameOccurance().elementAt(j));
			}
		}
	}
	
	public int getNumberforBlueByIndex(int strategy)
	{
		// prerequisite: condense and sort are called !!!!!
		int index = -1;
		switch (strategy)
		{
			case Configuration.HIGHEST_RATE:
				index = 0;
				sorted.elementAt(index);
				break;
			case Configuration.LOWEST_RATE:
				index = sorted.size() - 1;
				break;
			case Configuration.MEDIUM_RATE:
				index = sorted.size() / 2;
				if ( index == 0 )
					index = 1;
				if ( index == sorted.size())
				{
					System.out.println("Dumping!!!!!!");
					System.exit(-1);
				}
				break;
			default:
				assert 1 == 0;		
		}
		SortedOccuranceInstance OccuranceFieldList = sorted.elementAt(index);
		int size = OccuranceFieldList.getNumberListwithSameOccurance().size();
		int random = RandomGenerator.getRandom(size);
		return OccuranceFieldList.getNumberListwithSameOccurance().elementAt(random);
	}
	
}