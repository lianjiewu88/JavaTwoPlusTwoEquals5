package java8.forHybrisCodeReview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Country{
	private String mCountryCode;
	private int mRank;
	public Country(String mCode, int rank){
		this.mCountryCode = mCode;
		this.mRank = rank;
	}
	
	public String getCode(){
		return this.mCountryCode;
	}
	
	@Override
    public boolean equals(Object other) {
        if( other == null)
        	return false;
        
        if ( !(other instanceof Country) )
        	return false;
        Country country = (Country)other;
        return this.mCountryCode.equals(country.mCountryCode) && this.mRank == country.mRank;
    }
	
	@Override
    public int hashCode() {
        return this.mCountryCode.hashCode()*37 + this.mRank;
    }
}
public class MapTest{
	public List<Country> getCountry(){
		List<Country> countries = new ArrayList<Country>();
		
		Country country = new Country("ZH", 1);
		countries.add(country);
		
		country = new Country("US", 2);
		countries.add(country);
		
		country = new Country("JP", 3);
		countries.add(country);
		return countries;
	}
	
	public Map<String, Country> getCountryDataMap(List<Country> countryList)
	{
		final Map<String, Country> countryDataMap = new HashMap<>();
		for (final Country countryData : countryList){
			countryDataMap.put(countryData.getCode(), countryData);
		}
		return countryDataMap;
	}
	
	public Map<String, Country> getCountryDataMap2(List<Country> countryList)
	{
		final Map<String, Country> countryDataMap = new HashMap<>();
		countryList.forEach(country-> countryDataMap.put(country.getCode(), country));
		return countryDataMap;
	}
	
	// forEach之所以被称为内部遍历器，原因在于一旦它开始执行了，那么遍历操作就不能够被轻易中断。
	public boolean assertEqual(Map<String, Country> map1, Map<String, Country> map2){
		if( map1.size() != map2.size())
			return false;
		final boolean equal = true;
		map1.forEach((key, value)->
		{
			System.out.println("key of map1:" + key);
			Country country = map2.get(key);
			if( !value.equals(country)){
				
			}
				// Void methods cannot return a value
				// return false;
				// equal = false; // cannot change final
		});
		return equal;
	}
	
	public boolean assertEqual2(Map<String, Country> map1, Map<String, Country> map2){
		if( map1.size() != map2.size())
			return false;
		for (Map.Entry<String,Country> entry : map1.entrySet()) {
		    String key = entry.getKey();
		    Country country = entry.getValue();
		    Country country2 = map2.get(key);
		    if( !country.equals(country2))
		    	return false;
		}
		return true;
    }

	public static void main(String[] arg){
		MapTest maptest = new MapTest();
		List<Country> testdata = maptest.getCountry();
		
		Map<String, Country> result1 = maptest.getCountryDataMap(testdata);
		Map<String, Country> result2 = maptest.getCountryDataMap(testdata);
		
		List<Country> filterResult = testdata.stream().filter((country) -> country.getCode().equals("US")).collect(Collectors.toList());
		System.out.println("size: " + filterResult.size());
		filterResult.forEach(System.out::println);
		System.out.println(maptest.assertEqual2(result1, result2));
	}
}