package quiz;

class LinkedList2<E> {
	private Node<E> head = null;

	// 当你在一个泛型类中嵌套另一个泛型类时，最好为类型参数设置不同名字。
	private static class Node<E> {
		E value;
		Node<E> next;

		Node(E value, Node<E> next) {
			this.value = value;

			this.next = next;
		}
	}

	public void add(E e) {
		head = new Node<E>(e, head);
	}

	public void dump() {
		for (Node<E> n = head; n != null; n = n.next)
			System.out.println(n.value + " ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList2<String> list = new LinkedList2<String>();
		list.add("world");
		list.add("hello");
		list.dump();
	}

}
