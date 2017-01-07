package devExpertTest.testCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;
import devExpertTest.model.*;

public class InvoicingTest_02 {

	@Test
	public void testAddItemQuantity(){      

		final double VAL_QUANTITY = 5, VAL_DISCOUNT = 30, VAL_UNIT_PRICE = 38.99;
		
		Address billingAddress = null; Address shippingAddress = null;      
		Customer customer = null; Book book = null; Invoice invoice = null;      

	   try {                
	      billingAddress = new Address("Heidelbergerstrasse 16", "69190", "Walldorf", "BW", "Germany");         
	      shippingAddress = new Address("Hauptstrasse 5", "69190", "Walldorf", "BW", "Germany");         
	      customer = new Customer(99, "Astor Books Store",  billingAddress, shippingAddress);         
	      book = new Book("978-0321146533", "Test Driven Development: By Example", VAL_UNIT_PRICE);
	      invoice = new Invoice(customer);

	      invoice.addItemQuantity(book, VAL_QUANTITY, VAL_DISCOUNT);

	      if(!invoice.isEmpty()) {
	    	  DocumentItem docItemExpected = new DocumentItem(invoice, book, VAL_QUANTITY, VAL_DISCOUNT);
	    	  DocumentItem docItemActual = invoice.getItems().get(0);
	    	  assertDocumentItemEquals(docItemExpected, docItemActual);
	      } else {
	         fail("Invoice should have 1 item"); 
	      }
	   } finally { 
	      // Teardown 
	   }
	}
	
	private void assertDocumentItemEquals(DocumentItem expected, DocumentItem actual) {
		assertEquals("book", expected.getItem(), actual.getItem());
		assertEquals("quantity", expected.getQuantity(), actual.getQuantity(), 0);
		assertEquals("discount", expected.getDiscount(), actual.getDiscount(), 0);
		assertEquals("unit price", expected.getItem().getPrice(), actual.getItem().getPrice(), 0);
		assertEquals("extended price", expected.getExtendedPrice(), actual.getExtendedPrice(), 0);
	}
}
