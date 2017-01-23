package optionalTest;

import java.util.Optional;

public class ABAPOptional {

    private static void test1(){
    	Optional<String> anotherName = Optional.of("Jerry's setting of springmvcDispatcherServlet and configure the mapping");
    	Optional<String> shortName = anotherName.filter((value) -> value.length() < 6);
    	
    	System.out.println(shortName);
    	System.out.println(shortName.orElse("The name is greater than 6 characters"));
    }
    
	public static void main(String[] args) {
		test1();
	}

}
