package devExpertTest.model;

public class Customer {
	
	private int _id;
	private String _name;
	
	private Address _shippingAddress;
	private Address _billingAddress;
	
	public Customer(int id, String name, Address shippingAddress, Address billingAddress) {
		this.setId(id);
		this.setName(name);
		this.setBillingAddress(billingAddress);
		this.setShippingAddress(shippingAddress);
	}
	
	public Address getShippingAddress() {
		return _shippingAddress;
	}
	public void setShippingAddress(Address _shippingAddress) {
		this._shippingAddress = _shippingAddress;
	}
	
	public Address getBillingAddress() {
		return _billingAddress;
	}
	public void setBillingAddress(Address _billingAddress) {
		this._billingAddress = _billingAddress;
	}
	public int getId() {
		return _id;
	}
	public void setId(int _id) {
		this._id = _id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	
}
