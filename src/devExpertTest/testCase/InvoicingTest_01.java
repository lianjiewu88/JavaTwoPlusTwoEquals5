package devExpertTest.testCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.*;

import devExpertTest.model.*;


public class InvoicingTest_01 {

	@Test
	public void testAddItemQuantity(){      

		final double VAL_QUANTITY = 5, VAL_DISCOUNT = 30, VAL_UNIT_PRICE = 38.99;
		final double VAL_EXTENDED_PRICE = (VAL_QUANTITY * VAL_UNIT_PRICE) * (VAL_DISCOUNT / 100);
		
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
	    	 DocumentItem docItemActual = invoice.getItems().get(0);
	    	 assertEquals("book", book, docItemActual.getItem());
	         assertEquals("quantity", VAL_QUANTITY, docItemActual.getQuantity(), 0);
	         assertEquals("discount", VAL_DISCOUNT, docItemActual.getDiscount(), 0);
	         assertEquals("unit price", VAL_UNIT_PRICE, docItemActual.getItem().getPrice(), 0);
	         assertEquals("extended price", VAL_EXTENDED_PRICE, docItemActual.getExtendedPrice(), 0);
	      } else {
	    	 // check its source code:
	    	 //  assertTrue("Invoice should have 1 item", false);
	         fail("Invoice should have 1 item"); 
	      }
	   } finally { 
	      // Teardown 
	   }
	}
	
}
