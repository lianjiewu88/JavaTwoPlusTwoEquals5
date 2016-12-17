package devExpertTest.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import devExpertTest.testCase.InvoicingTest_08;
import devExpertTest.testCase.ProposalTest;

@RunWith(Suite.class)
@SuiteClasses({ ProposalTest.class , InvoicingTest_08.class})

public class DocumentSuite {

}
