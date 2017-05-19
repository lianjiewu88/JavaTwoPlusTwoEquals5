package corresponding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CorrespondingTest {
	
	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) {
		List<Developer> developers = new ArrayList<Developer>();
		developers.add(new Developer("Jerry", "ABAP", 2000));
		developers.add(new Developer("Tom", "Java", 2050));

		List<PreSales> preSales = new ArrayList<PreSales>();
		preSales.add(new PreSales("Jerry"));
		preSales.add(new PreSales("Tom"));
		
		CL_MAPPING[] mapping = new CL_MAPPING[2];
		
		mapping[0] = new CL_MAPPING("focusLanguage", "focusArea", null);
		
		Function<Integer, Integer> salaryDouble = e -> e * 2;
		mapping[1] = new CL_MAPPING("salary", "salaryPlusBonus", salaryDouble);
		
		CL_JAVA_CORRESPONDING mappingExecutor = CL_JAVA_CORRESPONDING.CREATE(developers, preSales, mapping);
		List<PreSales> mappedPresales = (List<PreSales>) mappingExecutor.execute();
		mappedPresales.forEach(System.out::println);
		
	}

}
