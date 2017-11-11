package twoColorBall;


import java.util.Vector;

public class Processor
{
	private Vector<HistoryRecordEntry> RecordList = null;
	public Processor()
	{
		RecordList = new Vector<HistoryRecordEntry>();
	}
	public void insert(int arg1, int arg2,int arg3,int arg4,int arg5,int arg6, int arg7)
	{
		HistoryRecordEntry entry = new HistoryRecordEntry();
		entry.fillData(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
		RecordList.add(entry);
	}
	public int getTotalRecordNumber()
	{
		return RecordList.size();
	}
	
	public void start()
	{
		OccuranceForEachNumber Occurance4EachNumber = null;
		OccuranceOverview OccuranceOverviewTool = new OccuranceOverview();
		for ( int i = 0 ; i < RecordList.size(); i++)
		{
			HistoryRecordEntry entry = RecordList.get(i);
			for( int j = 0; j < Configuration.MaxDigit; j++) // 0 ~6 
			{
				int NumberPerDigit = entry.getNumberFromDigit(j); 
				Occurance4EachNumber = OccuranceOverviewTool.getOccuranceInstanceByDigit(j);
				if ( Occurance4EachNumber.isNumberExist(NumberPerDigit) == true )
				{
					Occurance4EachNumber.updateNumberOccurance(NumberPerDigit);
				}
				else
				{
					Occurance4EachNumber.initialNumberOccurance(NumberPerDigit);
				}
				OccuranceOverviewTool.updateDigitOccurance(j,Occurance4EachNumber);
			}
		}
		OccuranceOverviewTool.listOccuranceForEachDigit();
	}
	
	
	
}
