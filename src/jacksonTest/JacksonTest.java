package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	private static void string2Object() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
        String DATA = "{\r\n" + 
                "  \"id\": 123,\r\n" + 
                "  \"name\": \"The Best Product\",\r\n" + 
                "  \"brand\": {\r\n" + 
                "     \"id\": 234,\r\n" + 
                "     \"content\": \"ACME Products\"\r\n" + 
                "  }\r\n" + 
                "}";

        ProductTest productTest = objectMapper.readValue(DATA, ProductTest.class);
        System.out.println(productTest.toString());
	}
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	string2Object();
    }

}