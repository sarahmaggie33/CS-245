public class DoublyLinkedList<E> {
	private Node first;
	private int size;
	
//	the private inner class
	private class Node {
		private E data;
		private Node next;
		private Node prev;
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
		dll.add("dog");
		dll.add("pig");
		dll.add("cat");
//		System.out.println(dll.get(0));
//		System.out.println(dll.get(1));
//		System.out.println(dll.get(2));
//		System.out.println(dll.indexOf("dog"));
//		System.out.println(dll.indexOf("pig"));
//		System.out.println(dll.indexOf("cat"));
//		dll.remove(1);
//		dll.remove(0);

		System.out.println(dll.size());
		System.out.println(dll.toString());
	}
	
	public void add(E value) {
//		if the list is empty, the new node is also the first node
		if (first == null) {
			first = new Node(value, first, first);

			size = 1;
//		The value should be placed after the last node in the list
		} else {
			Node f = first;
			Node current = first;
//			step through the list, making each non-null node the current node
			while(current.next != null) {
				current = current.next;
			}
//			end this when there is a null next node
//			the previous node is now the current node
//			the current node is after the previously current node
//			the current node has the value of value
//			current.next = new Node(value, first, current.prev);
			Node newNode = new Node(value, f, current.prev);
			current.next = newNode;
			current.prev = current;
			size++;
		}
		
	}
	
	public void add(int index, E value) {
//		The index parameter is invalid
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
//		if the list is empty, the new node is also the first node
		} else if (index == 0) {
			first = new Node(value, first, first);
			size++;
//		The value should be placed at the specific index in the list
		} else {
//			Start at the beginning of the list and step through until reaching the index
			Node current = first;
			for (int ii = 0; ii < index - 1; ii++) {
				current = current.next;
				size++;
			}
			Node previous = current.prev;
			previous.next = 
//			the current node is after the index before the specified index
//			the previously current node is now the previous node
//			the current node has the value of value
			current.next = new Node(value, current.next, current);
			current.prev = current;
		}
		
	}
	
	public void remove(int index) {
//		The index parameter is invalid
		if (first == null || index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
//		There is only one element in the list
		} else if (index == 0 && size == 1) {
			first = null;
			size--;
		} else {
//			the second node is now the first node... and so forth
			Node current = first;
			for (int ii = 0; ii < index - 1; ii++) {
				current = current.next;
			}
			current.next = current.next.next;
			current.prev = current.prev.prev;
			size--;
		}
		
	}
	
	public E get(int index) {
//		The index parameter is invalid
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
//		The current position is on the first node
		Node current = first;
//		Step through each element in the list, making each the current node temporarily
		for (int ii = 0; ii < index; ii++) {
			current = current.next;
		}
//		return the data of the element at the specified index
		return current.data;
		
	}
	
	public int indexOf(E value) {
		Node current = first;
		if (current.data == value) {
			return 0;
		}
		for (int ii = 1; ii < size; ii++) {
			current = current.next;
			if (current.data == value) {
				return ii;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
//		returns true if the list is empty
		return size == 0;
	}
	
	public int size() {
//		returns the number of elements in the list
		return this.size;
	}
	
	public String toString() {
		Node current = first;
		@SuppressWarnings("unchecked")
		E[] data = (E[]) (new Object[size]);
		if (size == 0) {
			return "[]";
		}
		data[0] = current.data;
		for (int ii = 1; ii < size; ii++) {
			current = current.next;
			data[ii] = current.data;
		}
		String output = "[";
		
		for (int jj = 0; jj < size - 1; jj++) {
			output += data[jj] + ", ";
		}
		output += data[size - 1];
		output += "]";
		return output;
	}
	
	
}
