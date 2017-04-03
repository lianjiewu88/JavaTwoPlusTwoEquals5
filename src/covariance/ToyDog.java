package covariance;

public class ToyDog extends Dog{

	public ToyDog(String name) {
		super(name);
	}

	@Override
	protected void shout() {
		System.out.println("I am toyDog: " + this.name);
	}
}
