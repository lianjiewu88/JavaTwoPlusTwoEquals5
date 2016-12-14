package devExpertTest.model;

public class Address {

	private String _street;
	private String _ZIP;
	private String _city;
	private String _state;
	private String _country;
	
	public Address(String street, String zip, String city, String state, String country) {
		this.setStreet(street);
		this.setZIP(zip);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
	}
	
	public String getStreet() {
		return _street;
	}
	public void setStreet(String _street) {
		this._street = _street;
	}
	
	public String getZIP() {
		return _ZIP;
	}
	public void setZIP(String _ZIP) {
		this._ZIP = _ZIP;
	}
	
	public String getCity() {
		return _city;
	}
	public void setCity(String _city) {
		this._city = _city;
	}
	
	public String getState() {
		return _state;
	}
	public void setState(String _state) {
		this._state = _state;
	}
	
	public String getCountry() {
		return _country;
	}
	public void setCountry(String _country) {
		this._country = _country;
	}
	
}
