package outputmanagement;

public class ControllerTest {

	public static void main(String[] args) {
		NormalController n = new NormalController();
		SmartController s = new SmartController();
		System.out.println("Normal: " + n.postOutput());
		System.out.println("Smart: " + s.postOutput());
	}
}
