package odata;

public class TicketCreationTest {

	public static void main(String[] args) {
		Thread thread = new Thread(new TicketCreationRunner() );
		thread.start();

	}

}
