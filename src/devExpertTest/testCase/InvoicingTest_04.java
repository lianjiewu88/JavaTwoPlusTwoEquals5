package  devExpertTest.testCase;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import devExpertTest.model.*;

public class InvoicingTest_04 {

	@Test
	public void testAddItemQuantity(){      

		final double VAL_QUANTITY = 5, VAL_DISCOUNT = 30, VAL_UNIT_PRICE = 38.99;
		Customer customer = null; 
		Book book = null; 
		Invoice invoice = null;      

	   try {                     
	      customer = createCustomer();         
	      book = createBook(VAL_UNIT_PRICE);
	      invoice = createInvoice(customer);

	      invoice.addItemQuantity(book, VAL_QUANTITY, VAL_DISCOUNT);

	      assertEquals("number of items" , 1, invoice.getItems().size());
	      
	      DocumentItem docItemExpected = new DocumentItem(invoice, book, VAL_QUANTITY, VAL_DISCOUNT);
	      DocumentItem docItemActual = invoice.getItems().get(0);
	      
	      assertDocumentItemEquals(docItemExpected, docItemActual);
	      
	   } finally { 
	      // Teardown   
		  customer = null; 
		  book = null; 
		  invoice = null;
	   }
	}
	
	private Address createBillingAddress() {
		Address billingAddress = new Address("Heidelbergerstrasse 16", "69190", "Walldorf", "BW", "Germany");      
		return billingAddress;
	}
	
	private Address createShippingAddress() {
		Address shippingAddress = new Address("Hauptstrasse 5", "69190", "Walldorf", "BW", "Germany");    
		return shippingAddress;
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer(99, "Astor Books Store",  createBillingAddress(), createShippingAddress());    
		return customer;
	}
	
	private Book createBook(double price) {
		Book book = new Book("978-0321146533", "Test Driven Development: By Example", price);
		return book;
	}
	
	private Invoice createInvoice(Customer customer) {
		Invoice invoice = new Invoice(customer);
		return invoice;
	}
	
	
	private void assertDocumentItemEquals(DocumentItem expected, DocumentItem actual) {
		assertEquals("book", expected.getItem(), actual.getItem());
		assertEquals("quantity", expected.getQuantity(), actual.getQuantity(), 0);
		assertEquals("discount", expected.getDiscount(), actual.getDiscount(), 0);
		assertEquals("unit price", expected.getItem().getPrice(), actual.getItem().getPrice(), 0);
		assertEquals("extended price", expected.getExtendedPrice(), actual.getExtendedPrice(), 0);
	}
	
}
