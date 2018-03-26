// non-cylic, ordered, singly-linked list data structure
// method implementations will use recursion rather than iteration

public class SortedLinkedList<E extends Comparable<E>> {
//	 points to the first node in the list
	private Node first;
//	 stores the number of elements in the list
	private int size;
	
	
	private class Node {
		private E data;
		private Node next;
		
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	
	public static void main(String[] args) {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		list.add(1);
		list.add(5);
		list.add(3);
		list.add(0);
		System.out.println(list.get(1));
		System.out.println(list.size());
		System.out.println(list.toString());
		list.remove(3);
		list.remove(0);
		System.out.println(list.get(0));
		System.out.println(list.toString());
	}
	
	
	public void add(E value) {
//		Special case: size == 0, create first node
		if (size == 0) {
			first = new Node(value, null);
			first.next = null;
		} else if (value.compareTo(first.data) < 1) {
			first = new Node(value, first);
//		Recursive case: add the elements in proper order
		} else {
			add(value, first);
		}
		size++;
	}
	
	private void add(E value, Node n) {
//		new node is the last in the list
		if (n.next == null) {
			n.next = new Node(value, null);
		}
//		if the next one in the list is bigger than the one you have
//		new value is after this node n and before n.next
		else if (n.next.data.compareTo(value) > 0) {
			n.next = new Node(value, n.next);
		
//		new value is after this node
		} else {
			n = n.next;
			add(value, n);
		}
	
	}
	
	
	public void remove(int index) {
		int currentIndex = 0;
//		invalid index parameter
		if (index < 0 || index >= size || first == null) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			first = first.next;
			size--;
		} else {
			remove(index, currentIndex, first); 
			size--;
		}	
	}
	
	private void remove(int index, int currentIndex, Node n) {
//		remove end of the list
		if (index - 1 == currentIndex && n.next.next == null) {
			n.next = null;
//		remove middle of the list
		} else if (index - 1 == currentIndex) {
			n.next = n.next.next;
		} else {
			currentIndex++;
			n = n.next;
			remove(index, currentIndex, n);	
		}
	}
	
	public E get(int index) {
		int currentIndex = 0;
		Node current = first;
//		The index parameter is invalid
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			return get(index, currentIndex, current);
		} 
	}
	
	private E get(int index, int currentIndex, Node n) {
		if(index == currentIndex) {
			return n.data;
		}
		currentIndex++;	
		n = n.next;
		return get(index, currentIndex, n);
	}
	
	
	public int indexOf(E value) {
		Node current = first;
		int currentIndex = 0;
//		base case: current value is the desired value
		if (current.data == value) {
			return currentIndex;
		} else {
			return indexOf(value, currentIndex, current);
		}
//		element is not found in the list
	}
	
	private int indexOf(E value, int currentIndex, Node n) {
		if (n.data.compareTo(value) > 0) {
			return -1;
		} else if (n.data == value) {
			return currentIndex;
		} 
		n = n.next;
		currentIndex++;
		
		return indexOf(value, currentIndex, n);
	}
	
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public int size() {
		return size;
	}
	
	
	public String toString() {
		return "[" + toString(first) + "]";
	}
	
	private String toString(Node n) {
		if (n == null) {
			return "";
		} else if (n.next == null) {
			return n.data + toString(n.next);
		} else {
			return n.data + ", " + toString(n.next);
		}
	}
	
}
