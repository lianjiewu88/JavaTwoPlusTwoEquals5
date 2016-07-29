package aop;

public class OrderCommand extends BaseCommand {

	public static void main(String[] args) {
		new OrderCommand().execute();
	}

	@Override
	protected void doBusiness() {
		  System.out.println("Do business here");
	}
}
