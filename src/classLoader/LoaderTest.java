package classLoader;

import java.net.URL;
import java.util.ArrayList;

/*
 * file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/resources.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/rt.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/sunrsasign.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/jsse.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/jce.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/charsets.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/lib/jfr.jar
file:/C:/Program%20Files/Java/jdk1.8.0_60/jre/classes
 */
public class LoaderTest {

	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
		    System.out.println(urls[i].toExternalForm());
		}
		System.out.println("**************");
		System.out.println(System.getProperty("sun.boot.class.path"));
		ClassLoader loader = LoaderTest.class.getClassLoader();    //获得加载ClassLoaderTest.class这个类的类加载器
		while(loader != null) {
		    System.out.println(loader);
		    loader = loader.getParent();    //获得父类加载器的引用
		}
		System.out.println(loader);
		
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		System.out.println(a.getClass() == b.getClass());
		
		System.out.println(a.getClass().isPrimitive());
		
		int ab = 5;
		Integer abc = new Integer(ab);
		System.out.println(abc);
		

	}

}
