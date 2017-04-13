package exception;

import java.sql.SQLException;

public class ExceptionForQuiz<T extends Exception> {
	public void pleaseThrow(final T exception ) throws T {
		throw exception;
	}

	public static void main(final String[] args) {
		try {
			new ExceptionForQuiz<SQLException>()
					.pleaseThrow(new SQLException());
		} catch (final SQLException ex) {
			System.out.println("Jerry print, the exception class: " + ex.getClass().getSimpleName());			
			ex.printStackTrace();
		}
	}
}
