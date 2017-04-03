package covariance;

public class Dog extends Animal{

	public Dog(String name) {
		super(name);
	}

	@Override
	protected void shout() {
		System.out.println("Wang, I am: " + this.name);
	}
}
