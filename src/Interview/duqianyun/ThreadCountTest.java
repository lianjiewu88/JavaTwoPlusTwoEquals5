package Interview.duqianyun;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Test;

@RunWith(Parameterized.class)

public class ThreadCountTest {
	private String expectResult;
	private String input;
	private ConcurrentHashMap<String, AtomicLong> wc;
	private ThreadCount tc;
	
	
	public ThreadCountTest(String inputBuf, String expectResult){
		this.expectResult = expectResult;
		this.input = inputBuf;
		wc = new ConcurrentHashMap<String, AtomicLong>();
		tc = new ThreadCount(inputBuf,wc);		
	}	
	
	/* 
	 * @Parameters修饰我们提供参数的静态方法，它需要返回List<Object[]>，List包含的是参数组，
	 * Object[]即按顺序提供的一组参数。
	 */
	@Parameters
	public static Collection data(){
	    for( int i = 0; i < 10; i++){
	    	
	    }
		Object[][] data = new Object[][]{
				{"hello world, world hello,","hello:2;world:2;"},
				{"免费 托管  Google, #@!#Google@!","Google:2;免费:1;托管:1;"}		
			};
		return Arrays.asList(data);
	}
	
	@Test
	public void testThreadCount(){
		tc.run();//use current thread to test, no need to start another thread
		System.out.println("Parameter input  :"+input);
		System.out.println("Parameter output :"+expectResult);
		System.out.println("Program output   :"+tc.toString());
		System.out.println();
		assertEquals(expectResult,tc.toString());
	}
}
