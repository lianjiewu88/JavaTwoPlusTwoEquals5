package Interview.jiaqi;

import java.util.HashMap;

// Jerry 2015-09-01 18:41PM unused import
/*
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.text.html.HTMLDocument.Iterator;
*/

public class CountWordsMain {

	static FindWordsImp instance;
	
	Object object;
	
	private static void utilityPrint(String value) {
		System.out.println("ID: " + Thread.currentThread().getId() + " ------:" + value);	
	}
	
	static private int m = 0;
	
	// Jerry 2015-09-03 10:44PM for debugging
	private static void debug(){
		int j = 0;
		for(int i = 0; i < 10; i++) {
			m++;
		}
	}
	
	
	public static void main(String[] args) {
		/* Jerry: unremoved "TODO"
		/TODO Auto-generated method stub*/
		{
			// Jerry why define instance as Static?
			debug();
			instance = new FindWordsImp();
			utilityPrint("Jerry I am in main thread******************");
			instance.FindAndPrintWords("c:\\temp\\test", 3);
			HashMap<String ,Integer> result=instance.GetResult();
			for (String key : result.keySet()) {  
			    Integer value = result.get(key);  
			    System.out.println( key +": "+value);  
			}   
		}
	}
}
