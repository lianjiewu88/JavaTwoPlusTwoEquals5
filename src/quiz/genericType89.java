package quiz;

class LinkedList<E> { 
	  // private Node<E> head = null;
	  private Node head2 = null;
	  /*
	  private class Node<E> {
	      E value;
	      Node<E> next;
	    
	   Node(E value) {
	      this.value = value;
	      // 一个泛型类的内部类可以访问到它的外围类的类型参数，所以Node完全不需要有自己的类型参数。
	      this.next = head; // Type mismatch: cannot convert from LinkedList<E>.Node<E> to LinkedList<E>.Node<E>
	      head = this;
	      一个非静态的嵌套类的构造器，在编译时会将一个隐藏的参数作为它的第一个擦数，这个参数表示了它

的直接外围实例( immediately enclosing instance). 当在代码中任何可以让编译器找到合适的外围

实例的地方去调用构造器时，该参数就会被隐式传递进去。当你使用反射调用构造器时，这个隐藏的参数需要被显式地传递，
这对于Class.newInstance是不可能做到的，只有使用java.lang.reflect.Constructor. 
如果Inner实例并不需要一个外围的实例出的话，可以将inner类型声明为static。优先使用静态成员类

。
	    }*/
	  private class Node {
		   E value;
		   Node next;
		   Node(E value) {
		      this.value = value;
		      this.next = head2;
		      head2 = this;
		   }
		}
	  
	  public void add(E e){
	      new Node(e);
	}
	  
	  public void dump() {
		  for( Node n = head2; n != null; n = n.next)
		     System.out.println(n.value + " " );
		}
	}

public class genericType89 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> list = new LinkedList<String>();
		list.add("world");
		list.add("hello");
		list.dump();
	}
}


