package aop;

public class OrderCommand implements Command {

	public static void main(String[] args) {
		Command cmd = new PerformanceTraceDecorator( new OrderCommand());
		cmd.execute();
	}

	@Override
	public void execute() {
		System.out.println("Do business here");
	}
}
