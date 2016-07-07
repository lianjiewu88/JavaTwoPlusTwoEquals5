package basic;

interface ControllerConstants
{
	String name = "Jerry";
	interface view{
		String name = "View";
		interface controller {
			String name = "Controller";
		}
	}
}

public class InterfaceTest {

	public static void main(String[] args) {
		System.out.println("Name: " + ControllerConstants.view.controller.name);

	}

}
