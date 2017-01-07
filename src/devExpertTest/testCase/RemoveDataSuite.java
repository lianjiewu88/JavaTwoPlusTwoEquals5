package devExpertTest.testCase;


import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import devExpertTest.model.CategoryRemoveData;

@RunWith(Categories.class)  
@IncludeCategory(CategoryRemoveData.class)  
@SuiteClasses( { ProposalTest.class}) 

public class RemoveDataSuite {

}
