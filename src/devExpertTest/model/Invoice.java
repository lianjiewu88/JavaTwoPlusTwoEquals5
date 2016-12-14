package devExpertTest.model;

public class Invoice extends BaseDocument {

	public Invoice(Customer customer) {
		super(customer);
	}

	public void addItemQuantity(Product item, double quantity, double discount) {
		this.getItems().add(new DocumentItem(this, item, quantity, discount));
	}

	public void removeItem(int index) {
		this.getItems().remove(index);
	}
}
