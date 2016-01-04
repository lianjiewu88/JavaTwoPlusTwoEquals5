package quiz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class Super implements Serializable {
	final Set<Super> set = new HashSet<Super>();
}

final class Sub extends Super {
	private int id;

	public Sub(int id) {
		this.id = id;
		set.add(this);
	}

	public void checkInvariant() {
		if (!set.contains(this))
			throw new AssertionError("invariant violated");
	}

	public int hashCode() {
		System.out.println("hashCode is called: " + id);
		return id;
	}

	public boolean equals(Object o) {
		return (o instanceof Sub) && (id == ((Sub) o).id);
	}
}

public class SerialKiller91 {

	public static void main(String[] args) {
		Sub sub = new Sub(666);
		sub.checkInvariant();

		Sub copy = (Sub) deepCopy(sub);
		/*
		 * 序列化系统会反序列化Sub实例中Super的域，即set。包含了一个队HashSet的引用。在内部，
		 * 每个HashSet实例包含一个队HashMap的引用，HashMap的键是该散列集合的元素。
		 * HashSet有一个readObject方法，创建一个空的HashMap,并使用HashMap的put方法，
		 * 针对集合中的每个元素在HashMap中插入一个键值对。put方法会调用键的hashCode方法以确定它所在的bucket。
		 * 在我们的代码中，散列映射表中唯一的键就是sub的实例，而它的set域正在被反序列化。这个实例的子类域，即id，尚未被初始化，
		 *  因此值为0.而Sub的hashCode将返回这个值，而不是最后保存在这个域中的值666.因为hashCode返回了错误的值，
		 *  相应的键值对将会放入错误的单元格中，当随后id被初始化成666时，一切都太迟了。
		 *  
		 *  Super.readObject
  -> HashSet.readObject
      -> HashMap.put
         ->Sub.hashCode, 而sub实例正处于创建过程中，被覆写的方法在sub域被初始化之前就被调

用了，而该方法需要依赖于Sub的域。
如果一个HashSet，HashTable或者HashMap被序列化，必须确认其北荣没有直接或间接地引用到它们自

身。


		 */
		copy.checkInvariant();
	}

	static public Object deepCopy(Object obj) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			new ObjectOutputStream(bos).writeObject(obj);
			ByteArrayInputStream bin = new ByteArrayInputStream(
					bos.toByteArray());
			return new ObjectInputStream(bin).readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
