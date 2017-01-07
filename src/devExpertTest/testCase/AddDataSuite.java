package devExpertTest.testCase;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import devExpertTest.model.CategoryAddData;

@RunWith(Categories.class)  
@IncludeCategory(CategoryAddData.class)  
@SuiteClasses( { ProposalTest.class}) 

public class AddDataSuite {

}
