package dynamicproxy;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;



class HelloWorldImp implements IHelloWorld
{
    public void print()
    {
        System.out.println("Hello World");
    }
}


public class DynamicProxyDemo implements Serializable
{
    private static final long serialVersionUID = 1L;
 
    private static void test() throws Exception{
        IHelloWorld helloWorld = (IHelloWorld) newProxyInstance();
        helloWorld.print();
    }
    
    public static void main(String[] arg) throws Exception{
    	test();
    }
    public static Object newProxyInstance() throws Exception
    {
        String src = "package dynamicproxy;\n\n" + 
                     "public class DynamicProxy implements IHelloWorld\n" + 
                     "{\n" + 
                     "\tIHelloWorld helloWorld;\n\n" + 
                     "\tpublic DynamicProxy(IHelloWorld helloWorld)\n" + 
                     "\t{\n" + 
                     "\t\tthis.helloWorld = helloWorld;\n" + 
                     "\t}\n\n" + 
                     "\tpublic void print()\n" + 
                     "\t{\n" + 
                     "\t\tSystem.out.println(\"Before Hello World!\");\n" + 
                     "\t\thelloWorld.print();\n" + 
                     "\t\tSystem.out.println(\"After Hello World!\");\n" + 
                     "\t}\n" + 
                     "}";
 
        String fileDir = System.getProperty("user.dir");
        String fileName = "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\dynamicproxy\\DynamicProxy.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(src);
        writer.close();
 
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
 
        URL[] urls = new URL[] {(new URL("file:\\" + "C:\\Users\\i042416\\git\\JavaTwoPlusTwoEquals5\\src\\"))};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass("dynamicproxy.DynamicProxy");
        System.out.println("Class loaded successfully: " + c.getName());
 
        Constructor<?> constructor = c.getConstructor(IHelloWorld.class);
        IHelloWorld helloWorldImpl = new HelloWorldImp();
        IHelloWorld helloWorld = (IHelloWorld)constructor.newInstance(helloWorldImpl);
 
        return helloWorld;
    }
}