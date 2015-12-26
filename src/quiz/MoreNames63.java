package quiz;

import java.util.HashMap;
import java.util.Map;

public class MoreNames63 {

	private Map<String, String> m = new HashMap<String, String>();
    public void MoreNames63() { // this method has a constructor name 
       m.put("1", "a");
       m.put("1", "b");
    }
  
    public int size() {
       return m.size();
    }
    
	public static void main(String[] args) {
		MoreNames63 i = new MoreNames63();
		System.out.println(i.size()); // 0

	}

}
