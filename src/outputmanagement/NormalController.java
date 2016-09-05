package outputmanagement;

public class NormalController extends AbstractController {

	@Override
	protected String handle(String result) {
		return "Scala " + result;
	}

}
