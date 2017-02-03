package wikitool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* find out uncoherent wiki sub page */
public class WikiTool {

	public static void main(String[] args) {
		System.out.println("ok");
		WikiTool i = new WikiTool();
		i.run();
	}
	
	private final String PAGE = "Page:";
	private final int LENGTH = PAGE.length();
	
	private String mConfigureFile = "C:\\temp\\wiki.txt";
	private ArrayList<String> pageCollection = new ArrayList<String>();
	private int counter = 1;
	
	public void run(){
		readConfig();
		parse();
	}
	
	private boolean isValidPage(String line){
		return line.contains(PAGE) && line.contains(".");
	}
	
	private int getPageNumber(String line) {
		int start = line.indexOf(PAGE) + LENGTH;
		int end = line.indexOf(".");
		String value = line.substring(start, end);
		int result = Integer.parseInt(value.trim());
		//System.out.println("parse result: " + result );
		return result;
	}
	
	private void parse() {
		for( int i = 0; i < pageCollection.size(); i++) {
			int current = getPageNumber(pageCollection.get(i));
			if( counter != current ){
				System.out.println("missing count: " + counter);
				counter = current;
			}
			counter++;
		}
	}
	private void readConfig(){
		try{
			FileInputStream f = new FileInputStream(mConfigureFile);
			@SuppressWarnings("resource")
			BufferedReader dr = new BufferedReader(new InputStreamReader(f));  
			String line = null;
			while ( ( line = dr.readLine() )!= null ){
				//System.out.println(line);
				if( isValidPage(line))
					pageCollection.add(line);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}
