package hashMap;

import java.util.HashMap;
import java.util.Map;

/* 
 * 可变对象是指创建后自身状态能改变的对象。换句话说，可变对象是该对象在创建后它的哈希值可能被改变。

在下面的代码中，对象MutableKey的键在创建时变量 i=10 j=20，哈希值是1291。

然后我们改变实例的变量值，该对象的键 i 和 j 从10和20分别改变成30和40。现在Key的哈希值已经变成1931。

显然，这个对象的键在创建后发生了改变。所以类MutableKey是可变的。
 */
class MutableKey {
	private int i;
	private int j;

	public MutableKey(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public final int getI() {
		return i;
	}

	public final void setI(int i) {
		this.i = i;
	}

	public final int getJ() {
		return j;
	}

	public final void setJ(int j) {
		this.j = j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MutableKey)) {
			return false;
		}
		MutableKey other = (MutableKey) obj;
		if (i != other.i) {
			return false;
		}
		if (j != other.j) {
			return false;
		}
		return true;
	}
}


public class objectAsKey {

	private static void simulateObjectLost() {
		// HashMap
		Map<MutableKey, String> map = new HashMap<>();
		
		// Object created
		MutableKey key = new MutableKey(10, 20);
		
		// Insert entry.
		map.put(key, "Robin");
		
		// This line will print 'Robin'
		System.out.println(map.get(key));
		
		// Object State is changed after object creation.
		// i.e. Object hash code will be changed.
		key.setI(30);
		
		// This line will print null as Map would be unable to retrieve the entry.
		System.out.println(map.get(key));
	}
	
	public static void main(String[] args) {
		// Object created
		MutableKey key = new MutableKey(10, 20);
		System.out.println("Hash code: " + key.hashCode());
		// Object State is changed after object creation.
		key.setI(30);
		key.setJ(40);
		System.out.println("Hash code: " + key.hashCode());
		
		simulateObjectLost();
	}
}


