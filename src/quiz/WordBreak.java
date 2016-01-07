package quiz;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {

	public boolean wordBreak(String s, Set<String> dict) {
	        // boolean数组 mem[i]表示[0,i)是否存在通路（0<=i<=str.length）
	        boolean[] mem = new boolean[s.length()+1];
	        mem[0] = true;
	         
	        // mem[i] = {所有 mem[k] && ([k,i)∈dict) 中只要有一个为true，就为true}（其中0<=k<i）
	        for (int i = 0; i <= s.length(); i++) {
	            for (int k = 0; k < i; k++ ) {
	            	System.out.println("i: " + i + " k: " + k);
	                String str = s.substring(k, i);
	                System.out.println("Scan sub string: " + str);
	                System.out.println("Does Dic contain sub string? " + dict.contains(str));
	                if( dict.contains(str)) {
	                	System.out.println("i: " + i + " k: " + k + " mem[k]: " + mem[k]);
	                }
	                mem[i] = mem[k] && dict.contains(str);
	                 
	                if (mem[i])
	                    break;
	            }
	        }
	        
	        for( int n = 0; n < mem.length; n++){
	        	System.out.println("i: " + n + " status: " + mem[n]);
	        }
	        return mem[s.length()];
	    }
	
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("Hello");
		dict.add("World");
		
		WordBreak tool = new WordBreak();
		boolean result = tool.wordBreak("HelloaWorld",dict );
		System.out.println(result);
	}
}
