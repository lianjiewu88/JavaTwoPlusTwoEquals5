package ball;

public class MainFrame
{
	public static void main(String[] args) 
	{
		Processor processor = new Processor();
		HistoryRecordEntry entry = new HistoryRecordEntry();
		
		// fill history data
		//2003001
		processor.insert(10, 11, 12, 13, 26, 28, 11);
		processor.insert( 4,  9, 19, 20, 21, 26, 12);
		processor.insert( 1,  7, 10, 23, 28, 32, 16);
		processor.insert( 4,  6,  7, 10, 13, 25,  3);
		processor.insert( 4,  6, 15, 17, 30, 31, 16);
		processor.insert( 1,  3, 10, 21, 26, 27,  6);
		processor.insert( 1,  9, 19, 21, 23, 26,  7);
		processor.insert( 5,  8,  9, 14, 17, 23,  8);
		processor.insert( 5,  9, 18, 20, 22, 30,  9);
		processor.insert( 1,  2,  8, 13, 17, 24, 13);
		processor.insert( 4,  5, 11, 12, 30, 32, 15);
		processor.insert( 2, 12, 16, 17, 27, 30, 12); // 2003012
		processor.insert( 8, 13, 17, 21, 23, 32, 12);
		processor.insert( 3,  5,  7,  8, 21, 31,  2);
		processor.insert( 4, 11, 19, 25, 26, 32, 13);
		processor.insert(11, 17, 28, 30, 31, 33,  6);
		processor.insert( 5,  8, 18, 23, 25, 31,  6); // 2003017

		System.out.println("Current Number: " + processor.getTotalRecordNumber());
		processor.start();
	}
}
