package devExpertTest.model;

public class Product {

	private int _id;
	private String _name;
	private double _price;
	
	public Product(int id, String name, double price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}

	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	
	public double getPrice() {
		return _price;
	}
	public void setPrice(double price) {
		this._price = price;
	}
}
