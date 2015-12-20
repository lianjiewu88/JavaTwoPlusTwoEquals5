package quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak2 {

	public List<String> wordBreak(String s, Set<String> dict) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        List<String> defaultList = new ArrayList<String>();
        defaultList.add("");
        map.put(-1, defaultList);
        
        return getAllPossibleSentences(s, dict, s.length()-1, map);
    }
     
    private List<String> getAllPossibleSentences(String s, Set<String> dict, int pos, Map<Integer, List<String>> map){
    	System.out.println(" in getAllPossibleSentences, s: " + s + " pos: " + pos);
        if (map.containsKey(pos)){
        	if( pos != -1){
        		System.out.println(" map contains key on position: " + pos);
        		List<String> result = map.get(pos);
        		System.out.println("ready to return all possibles for position: " + pos);
        		result.forEach(System.out::println);
        	}
        	else{
        		List<String> result = map.get(pos);
        		System.out.println("pos is -1, we can return now and have a rest! return result size: " + result.size());
        	}
            return map.get(pos);
        }
         
        String sub = s.substring(0, pos+1); // 子串
        System.out.println("Sub string: " + sub);
        List<String> list = new ArrayList<String>();
        for (String word : dict) {
            if (sub.endsWith(word)) { 
            	System.out.println("Sub String ends with word in Dict!: " + word);
                int firstSegEnd = pos - word.length();
                System.out.println(" current position: " + pos + " word length: " + word.length());
                System.out.println("first Seg end: " + firstSegEnd);
                if (firstSegEnd <-1) // no solution found
                    continue;
                
                System.out.println("RECURSIVELY call, new position: " + firstSegEnd);
                List<String> subList = getAllPossibleSentences(s, dict, firstSegEnd, map);
                System.out.println("result from getAllPossible with position: " + firstSegEnd);
                for (String str : subList) {
                	System.out.println("item in result list: " + str + "Current word in dic: " + word);
                	String insert = null;
                    if ("".equals(str)){
                    	System.out.println("result list's item is STRING.space, so I directly insert end word to solution list: "
                    			+ word);
                    	insert = word;
                        list.add(insert);
                    }
                    else{
                    	insert = str + " " + word;
                        list.add(insert);
                    }
                    System.out.println("Possible solution FOUND:!" + insert);
                }
            } // if
        }// for
         
        System.out.println("insert map with position: " + pos + " possible list: " + list.size());
        map.put(pos, list);
        return list;
    }
	public static void main(String[] args) {
		WordBreak2 tool = new WordBreak2();
		
		String s = "catsanddog";
		HashSet<String> set = new HashSet<String>();
		set.add("cat");
		set.add("cats");
		set.add("and");
		set.add("sand");
		set.add("dog");
		
		List<String> result = tool.wordBreak(s, set);
		result.forEach( item -> System.out.println("result: " + item));

	}

}
