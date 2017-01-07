# InvoicingTest_00

Initial code

# InvoicingTest_01

only difference:

* assertTrue("Invoice should have 1 item", false);
* fail("Invoice should have 1 item"); 

# InvoicingTest_02

method extraction: add a new method assertDocumentItemEquals

# InvoicingTest_03

add some cleanup logic in finally block

# InvoicingTest_04

method extraction: instance initialization is done in createXXX method

# InvoicingTest_05

move initialization logic to @before, and cleanup in @after

# InvoicingTest_06

add a new member attribute private List<Object> testObjects;
when instance is created, add instance to this list. So in @after, the instance could be 
cleaned up generically.



