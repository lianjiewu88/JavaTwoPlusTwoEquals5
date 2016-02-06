package memoryTest;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

class Person implements Serializable {
	static final long serialVersionUID = 1L;
	String name; // 姓名
	Person friend;    //朋友
	public Person() {}
	public Person(String name) {
		super();
		this.name = name;
	}
}

/*
 * 2016-02-05 16:53PM: 
 * 软引用 ：通过SoftReference类实现，eg : SoftReference<Person> p = new 
 * SoftReference<Person>(new Person(“Rain”));,内存非常紧张的时候会被回收，其他时候不会被回收，
 * 所以在使用之前要判断是否为null从而判断他是否已经被回收了。
 * 
 * 弱引用 ：通过WeakReference类实现，eg : 
 * WeakReference<Person> p = new WeakReference<Person>(new Person(“Rain”));
 * 不管内存是否足够，系统垃圾回收时必定会回收。
 * 
 */
public class manageTest {

	private static void test(){
		Person p1 = new Person("Kevin");
		Person p2 = new Person("Rain");
		Person p3 = new Person("Sunny");
		p1.friend = p2;
		p3 = p2;
		p2 = null;
	}
	
	private static void test2(){
		
		Person person = new Person("Sunny");
		
		//创建一个引用队列
		ReferenceQueue<Person> rq = new ReferenceQueue<Person>();
		
		//创建一个虚引用，让此虚引用引用到person对象
		PhantomReference<Person> pr = new PhantomReference<Person>(person, rq);
		
		//切断person引用变量和对象的引用
		person = null;
		
		//试图取出虚引用所引用的对象
		//发现程序并不能通过虚引用访问被引用对象，所以此处输出为null
		System.out.println(pr.get());
		
		//强制垃圾回收
		System.gc();
		System.runFinalization();
		
		//因为一旦虚引用中的对象被回收后，该虚引用就会进入引用队列中
		//所以用队列中最先进入队列中引用与pr进行比较，输出true
		System.out.println(rq.poll() == pr);
	}
	
	public static void main(String[] args) {
		test2();
	}
}
