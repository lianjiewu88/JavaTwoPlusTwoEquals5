package bigFileZ;
//https://github.com/mark-zheng/sap/blob/master/com/mark/sap/Main.java
	
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("please input the file path: ");
		String path = br.readLine();
		WordStat fileHandler = new WordStat();
		fileHandler.init(new File(path));
        		
		while (true) {			
	        System.out.print("please input the word: ");
			String word = br.readLine();			
			if (word.equals("-1")) {				
				break;
			} else {
				System.out.println(fileHandler.queryWord(word));
			}
		}
	}

}
