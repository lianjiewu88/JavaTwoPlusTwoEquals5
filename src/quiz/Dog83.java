package quiz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("serial")
public class Dog83 extends Exception {
	// Exception是可以序列化的，解序列会创建一个隐藏的构造器。如果序列化了Dog.INSTANCE, 然后对得到的字节序
    // 列byte sequence进行解序列，会得到另外一个Dog. 
	/* 实现了Serializable的单件类，必须要有一个readResolve方法，用意返回它的唯一的实例。
有可能由于对一个实现了Serializable的类进行了扩展，或者由于实现了一个扩展自Serializable的接口，使我们无

意中实现了Serializable。隐藏的构造器，例如序列化中产生的那个，会引起潜在错误。
*/
    public static final Dog83 INSTANCE = new Dog83();
    private Dog83() {}
    public String toString() {
       return "Woof";
    }
    
    private Object readResolve() {
        return INSTANCE;
   }
    

	public static void main(String[] args) {
		Dog83 newDog = (Dog83)deepCopy(Dog83.INSTANCE);
		System.out.println(newDog == Dog83.INSTANCE);
		System.out.println(newDog);
	}

	static public Object deepCopy(Object obj){
	   try{
	       ByteArrayOutputStream bos = new ByteArrayOutputStream();
	       new ObjectOutputStream(bos).writeObject(obj);
	       ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
	       return new ObjectInputStream(bin).readObject();
	    } catch(Exception e) {
	         throw new IllegalArgumentException(e);
	     }
	   }
	}

