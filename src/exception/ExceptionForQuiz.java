package exception;

import java.sql.SQLException;

public class ExceptionForQuiz<T extends Exception> {
	private void pleaseThrow(final Exception t) throws T {
		throw (T) t;
	}

	public static void main(final String[] args) {
		try {
			new ExceptionForQuiz<RuntimeException>()
					.pleaseThrow(new SQLException());
		} catch (final SQLException ex) {
			System.out.println("Jerry print, the exception class: " + ex.getClass().getSimpleName());			
			ex.printStackTrace();
		}
	}
}
