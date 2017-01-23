package optionalTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class ABAPOptional {

    private static void OptionalTest(){
    	Optional<String> anotherName = Optional.of("Jerry's setting of springmvcDispatcherServlet and configure the mapping");
    	Optional<String> shortName = anotherName.filter((value) -> value.length() < 6);
    	
    	System.out.println(shortName);
    	System.out.println(shortName.orElse("The name is greater than 6 characters"));
    }
    
	public static void main(String[] args) {
		OptionalTest();
	}

}
