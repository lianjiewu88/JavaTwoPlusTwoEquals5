package covariance;

abstract class Animal{
	protected String name;
	public Animal(String name){
		this.name = name;
	}
	abstract protected void shout();
}