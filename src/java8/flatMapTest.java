package java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Developer {

    @SuppressWarnings("unused")
	private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.languages = new HashSet<>();
        this.name = name;
    }

    public void add(String language) {
        this.languages.add(language);
    }

    public Set<String> getLanguages() {
        return languages;
    }
}


public class flatMapTest {

	public static void main(String[] args) {
	        List<Developer> team = new ArrayList<>();
	        Developer jerry = new Developer("Jerry");
	        jerry.add("clojure");
	        jerry.add("scala");
	        jerry.add("java");
	        jerry.add("go");

	        Developer tom = new Developer("Tom");
	        tom.add("java");
	        tom.add("javascript");

	        team.add(jerry);
	        team.add(tom);

	        Stream<Developer> streamDeveloper = team.stream();
	        
	        /* Returns a stream consisting of the results of applying the given function 
	         * to the elements of this stream. 
			This is an intermediate operation.
			Parameters:<R> The element type of the new streammapper a non-interfering, 
			stateless function to apply to each element
			Returns:the new stream
	         */
	        
	        // 2. for each developer, use map to return all the languages he knows
	        Stream<Object> allLanguageStream = streamDeveloper.map(developer->developer.getLanguages());
	        
	        /* So far, the developer name is lost - isn't included in this stream
	         output: Team language: [groovy, scala, clojure, go]
	         Team language: [java, javascript]
	         not String as I expected in the beginning
	         */

	        /* allLanguage.forEach( language->System.out.println("Team language: " + language + " type: " + 
	        language.getClass().getCanonicalName())); // java.util.HashSet */
	        
	        Stream<Object> flatMapResult = allLanguageStream.flatMap( l -> ((Collection<String>) l).stream());
	        
	        List<Object> teamLanguages = flatMapResult.collect(Collectors.toList());
	        
	        teamLanguages.forEach( a->System.out.println("result: " + a));
	        /*
	        List<String> teamLanguages = team.stream(). // return Stream<Developer>
	                map(d -> d.getLanguages()).
	                flatMap(l -> l.stream()).
	                collect(Collectors.toList());
			*/
	        
	        List<String> teamLanguages2 = team.stream(). // return Stream<Developer>
	                map(d -> d.getLanguages()).
	                flatMap(l -> l.stream()).
	                collect(Collectors.toList());
	        
	        teamLanguages2.forEach(a->System.out.println("blog: " + a));
	}
}
