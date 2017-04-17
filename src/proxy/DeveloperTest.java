package proxy;



public class DeveloperTest {

	public static void main(String[] args) {
		ProductOwner ross = new ProductOwner("Ross");
		ProductOwner rossProxy = (ProductOwner) new EnginnerCGLibProxy().bind(ross);
		rossProxy.defineBackLog();
	}
}
