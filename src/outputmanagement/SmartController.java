package outputmanagement;

public class SmartController extends AbstractController {

	@Override
	protected String handle(String result) {
		return "Java " + result;
	}
}