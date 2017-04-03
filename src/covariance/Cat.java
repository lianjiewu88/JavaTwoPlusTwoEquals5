package covariance;

public class Cat extends Animal{

	public Cat(String name) {
		super(name);
	}
	@Override
	protected void shout() {
		System.out.println("Miao, I am: " + this.name);
	}
}