package quiz;

import java.util.HashMap;
import java.util.Map;

public class NPE {

	public static void main(String[] args) {
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		// Boolean b = (map != null ? map.get("test") : false);
		Boolean b = (map != null ? map.get("test") : Boolean.FALSE);
		System.out.println(b);
	}

}
