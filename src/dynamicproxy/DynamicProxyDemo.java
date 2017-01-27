package dynamicproxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

class HelloWorldImp implements IHelloWorld {
	public void print() {
		System.out.println("Hello World");
	}
}

public class DynamicProxyDemo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static void main(String[] arg) throws Exception {
		Class<?> c = getProxyClass();
		Constructor<?> constructor = c.getConstructor(IHelloWorld.class);
		IHelloWorld helloWorldImpl = new HelloWorldImp();
		IHelloWorld helloWorld = (IHelloWorld) constructor.newInstance(helloWorldImpl);

		helloWorld.print();
	}

	private static String getSourceCode() {
		String src = "package dynamicproxy;\n\n"
				+ "public class DynamicProxy implements IHelloWorld\n" + "{\n"
				+ "\tIHelloWorld helloWorld;\n\n"
				+ "\tpublic DynamicProxy(IHelloWorld helloWorld)\n" + "\t{\n"
				+ "\t\tthis.helloWorld = helloWorld;\n" + "\t}\n\n"
				+ "\tpublic void print()\n" + "\t{\n"
				+ "\t\tSystem.out.println(\"Before Hello World!\");\n"
				+ "\t\thelloWorld.print();\n"
				+ "\t\tSystem.out.println(\"After Hello World!\");\n" + "\t}\n"
				+ "}";
		return src;
	}

	private static String createJavaFile(String sourceCode) {
		// TODO: avoid using absolute file path in productive code
		String fileName = "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\dynamicproxy\\DynamicProxy.java";
		File javaFile = new File(fileName);
		Writer writer;
		try {
			writer = new FileWriter(javaFile);
			writer.write(sourceCode);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	private static void compile(String fileName) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
			CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
			ct.call();
			sjfm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Class<?> loadClass() {
		URL[] urls;
		Class<?> c = null;
		try {
			urls = new URL[] { (new URL("file:\\"
					+ "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\")) };
			URLClassLoader ul = new URLClassLoader(urls);
			c = ul.loadClass("dynamicproxy.DynamicProxy");
			System.out.println("Class loaded successfully: " + c.getName());
		} catch (MalformedURLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static Class<?> getProxyClass() {
		String sourceCode = getSourceCode();
		String javaFile = createJavaFile(sourceCode);
		compile(javaFile);
		return loadClass();
	}
}