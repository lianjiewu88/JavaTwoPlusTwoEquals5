package exception;

import java.sql.SQLException;

public class ExceptionQuiz2<T extends Exception> {
	private void pleaseThrow(final Exception t) throws T {
		/*
		 * 
		 * 我们天真地希望第七行能产生一个编译错误，因为我们不能将SQLException转换成RuntimeException，
		 * 但是这并不会发生。发生的是将T替换成了Exception，所以我们有：
		 * 
		 * throw (Exception) t; // t is also an Exception
		 */
		throw (T) t;
	}
	
	/*
	 *   private void pleaseThrow(Exception t) throws Exception {
    		throw t;
  		}
	 */

	public static void main(final String[] args) {
		
		new ExceptionQuiz2<RuntimeException>().pleaseThrow(new SQLException());
	}
}
