package devExpertTest.model;

public class Book extends Product {

	private String _isbn;
	
	public Book(int id, String name, double price) {
		super(id, name, price);
	}
	
	public Book(String isbn, String name, double price) {
		super(0, name, price);
		this.setISBN(isbn);
	}

	public String getISBN() {
		return _isbn;
	}

	public void setISBN(String isbn) {
		this._isbn = isbn;
	}

}
