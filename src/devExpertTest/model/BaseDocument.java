package devExpertTest.model;

import java.util.LinkedList;

public abstract class BaseDocument {

	private Customer _customer;	
	private LinkedList<DocumentItem> _items = new LinkedList<DocumentItem>();

	
	public BaseDocument(Customer customer) {
		this.setCustomer(customer);
	}
	
	public Customer getCustomer() {
		return _customer;
	}

	public void setCustomer(Customer customer) {
		_customer = customer;
	}
	
	public LinkedList<DocumentItem> getItems() {
		return _items;
	}

	public DocumentItem getItem(int index) {
		return this._items.get(index);
	}
	
	public void addItem(DocumentItem item) {
		this._items.add(item);
	}
	
	public void addItem(Product item, double quantity, double discount) {
		this._items.add(new DocumentItem(this, item, quantity, discount));
	}
	
	public boolean isEmpty() {
		return _items.size() == 0;
	}
	
}
