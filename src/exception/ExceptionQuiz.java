package exception;

import java.sql.SQLException;

/*
 * 1. RuntimeException和SQLException都继承自Exception，
 * 但是在这个代码中RuntimeException是未检查的异常，而SQLException是受检异常。
 *  java里面异常分为两大类:checkedexception(检查异常)和unchecked exception(未检 查异常)，
 *  对于未检查异常也叫RuntimeException(运行时异常),对于运行时异常，
 *  java编译器不要求你一定要把它捕获或者一定要继续抛出，
 *  但是对checkedexception(检查异常)要求你必须要在方法里面或者捕获或者继续抛出
 *  
 */
public class ExceptionQuiz<T extends Exception> {
	  private void pleaseThrow(final Exception t) throws T {
	      throw (T) t;
	  }
	  public static void main(final String[] args){
	      try{
	            new ExceptionQuiz<RuntimeException>().pleaseThrow(new SQLException());
	         }
	         catch( final SQLException ex){
	            ex.printStackTrace();
	         }
	       }
	}
	  