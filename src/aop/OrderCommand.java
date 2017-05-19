package aop;

// Jerry 2017-05-19 9:19AM: this is a good design - OrderCommand only focus on business logic, the 
// performance trace logic is put to a Decorator
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
