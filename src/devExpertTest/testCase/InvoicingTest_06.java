package  devExpertTest.testCase;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import devExpertTest.model.*;


public class InvoicingTest_06 {

	private static final double VAL_QUANTITY = 5, VAL_DISCOUNT = 30, VAL_UNIT_PRICE = 38.99;
	
	Address billingAddress = null; 
	Address shippingAddress = null;      
	Customer customer = null; 
	Book book = null; 
	Invoice invoice = null;
	
	private List<Object> testObjects;
	
	@Before
	public void setUp() throws Exception {      
		testObjects = new ArrayList<Object>();
		             
	    customer = createCustomer();         
	    book 	 = createBook(VAL_UNIT_PRICE);
	    invoice  = createInvoice(customer);
	}
	
	@After
	public void tearDown() {      
		for(Object item : testObjects) {
			deleteObject(item);         
		}   
	}
	
	@Test
	public void testAddItemQuantity(){      
   
      invoice.addItemQuantity(book, VAL_QUANTITY, VAL_DISCOUNT);

      assertEquals("number of items" , 1, invoice.getItems().size());
      
      DocumentItem docItemExpected = new DocumentItem(invoice, book, VAL_QUANTITY, VAL_DISCOUNT);
      DocumentItem docItemActual = invoice.getItems().get(0);
      
      assertDocumentItemEquals(docItemExpected, docItemActual);
	}
	
	private Address createBillingAddress() {
		billingAddress = new Address("Heidelbergerstrasse 16", "69190", "Walldorf", "BW", "Germany");      
		registerTestObject(billingAddress);
		return billingAddress;
	}
	
	private Address createShippingAddress() {
		shippingAddress = new Address("Hauptstrasse 5", "69190", "Walldorf", "BW", "Germany");
		registerTestObject(shippingAddress);
		return shippingAddress;
	}
	
	private Customer createCustomer() {
		customer = new Customer(99, "Astor Books Store",  createBillingAddress(), createShippingAddress());
		registerTestObject(customer);
		return customer;
	}
	
	private Book createBook(double price) {
		book = new Book("978-0321146533", "Test Driven Development: By Example", price);
		registerTestObject(book);
		return book;
	}
	
	private Invoice createInvoice(Customer customer) {
		invoice = new Invoice(createCustomer());
		registerTestObject(invoice);
		return invoice;
	}
	
	private void deleteObject(Object object) {
		object = null;
	}
	
	private void registerTestObject(Object testObject) {      
		testObjects.add(testObject);   
	}
	
	private void assertDocumentItemEquals(DocumentItem expected, DocumentItem actual) {
		assertEquals("book", expected.getItem(), actual.getItem());
		assertEquals("quantity", expected.getQuantity(), actual.getQuantity(), 0);
		assertEquals("discount", expected.getDiscount(), actual.getDiscount(), 0);
		assertEquals("unit price", expected.getItem().getPrice(), actual.getItem().getPrice(), 0);
		assertEquals("extended price", expected.getExtendedPrice(), actual.getExtendedPrice(), 0);
	}
	
}
