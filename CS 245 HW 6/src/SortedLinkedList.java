import DoublyLinkedList.Node;

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
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("a");
		list.add("ab");
		System.out.println(list.toString());
	}
	
	
	public void add(E value) {
		
//		Base case: size == 0, create first node
		if (size == 0 && first == null) {
			first = new Node(value, null);
			first.next = first;
//		Recursive case: add the elements in proper order
		} else {
			Node current = first;
			for(int ii = 0; ii < size; ii++) {
				add(value, current);
				current = current.next;
			}
		}
		size++;
	}
	
	private void add(E value, Node n) {
		Node newNode = new Node(value, null);
//		new value is before this node n
		if (value.compareTo(n.data) == -1) {
			newNode.next = n;
			if (n.data.compareTo(first.data) == 0) {
				first = newNode;
				first.next = n;
			}	
			
//		value is = this node	
		} else if (value.compareTo(n.data) == 0) {			
			newNode.next = n;
			
//		value is after this node		
		} else if (value.compareTo(n.data) == 1) {
			n.next = newNode;
		}
	}
	
	
	public void remove(int index) {
//		invalid index parameter
		if (index > 0 || index >= size || first == null) {
			throw new IndexOutOfBoundsException();
//		Base case: index == 0
		} else if (index == 0) {
			first.next = null;
			size--;
		} else {
//			remove(index, currentIndex, node); 
			size--;
		}	
	}
	
	private void remove(int index, int currentIndex, Node n) {

	
	}
	
	
	public E get(int index) {
//		The index parameter is invalid
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
		return null;
		
	}
	
	private E get(int index, int currentIndex, Node n) {
		return null;
		
	}
	
	
	public int indexOf(E value) {
		Node current = first;
		int currentIndex = 0;
//		base case: current value is the desired value
		if (current.data == value) {
			return currentIndex;
		} else {
			for (int ii = 0; ii < size; ii++) {
				indexOf(value, currentIndex, current);
			}
		}
//		element is not found in the list
		return -1;
	}
	
	private int indexOf(E value, int currentIndex, Node n) {
		n = n.next;
		if (n.data == value) {
			return currentIndex;
		}
		return currentIndex;

		
	}
	
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public int size() {
		return size;
	}
	
	
	public String toString() {
		Node current = first;
		String s = "[";
//		base case: nothing in list: print just brackets
		if (current == null) {
			return s += "]";
//		recursive case: 	
		} else {
			for (int ii = 0; ii < size - 1; ii++) {
				s += toString(current);
				current = current.next;
			}
			return s + current.data + "]";
		}
		
	}
	
	private String toString(Node n) {
		String s = "";
		if (n != null) {
			s += n.data + ", ";
		}
		return s;
	}
	
}
