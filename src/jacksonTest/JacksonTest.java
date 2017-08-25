package jacksonTest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	private static void object2String() throws JsonProcessingException{
		BrandName b = new BrandName(11, "sap");
		ProductTest p = new ProductTest(12, "Jerry", b);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = mapper.writeValueAsString(p);
		System.out.println("JSON: " + jsonInString);
		
	}
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	// string2Object();
    	object2String();
    }

}