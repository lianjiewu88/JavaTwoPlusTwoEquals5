package twoColorBall;


public class HistoryRecordEntry 
{
	private int[] Number;
	
	public HistoryRecordEntry()
	{
		Number = new int[Configuration.MaxDigit];
	}
	public int getNumberFromDigit(int Digit)
	{
		return Number[Digit];
	}
	public void fillData(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7 )
	{
		Number[0] = arg1;
		Number[1] = arg2;
		Number[2] = arg3;
		Number[3] = arg4;
		Number[4] = arg5;
		Number[5] = arg6;
		Number[6] = arg7;
	}
}
