package devExpertTest.model;

public class DocumentItem {

	private BaseDocument _docParent;
	private Product _item;
	private double _quantity;
	private double _discount;
	
	public DocumentItem(BaseDocument document, Product item, double quantity, double discount) {
		this.setParent(document);
		this.setItem(item);
		this.setQuantity(quantity);
		this.setDiscount(discount);
	}
	
	public Product getItem() {
		return _item;
	}
	public void setItem(Product item) {
		this._item = item;
	}
	
	public double getQuantity() {
		return _quantity;
	}
	public void setQuantity(double quantity) {
		this._quantity = quantity;
	}

	public void setDiscount(double discount) {
		this._discount = discount;
	}
	
	public double getDiscount() {
		return _discount;
	}

	public double getExtendedPrice() {
		return (this.getItem().getPrice() * this.getQuantity()) * (getDiscount() / 100);
	}

	public BaseDocument getParent() {
		return _docParent;
	}

	protected void setParent(BaseDocument document) {
		this._docParent = document;
	}
}
