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
	
	public void add(E value) {
//		if the list is empty, the new node is also the first node
		if (first == null) {
			first = new Node(value, null, null);
			first.next = first;
			first.prev = first;
//		The value should be placed after the last node in the list
		} else {
			Node newNode = new Node(value, first, first.prev);
			first.prev.next = newNode;
			first.prev = newNode;
		}
		size++;
	}
	
	public void add(int index, E value) {
//		The index parameter is invalid
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
//		if the list is empty, the new node is also the first node
		} else if (first == null && index == 0) {
			first = new Node(value, null, null);
			first.next = first;
			first.prev = first;
			size++;
		} else if (index == 0) {
			Node newNode = new Node(value, first, first.prev);
			first.prev.next = newNode;
			first.prev = newNode;
			first = newNode;
			size++;
//		The value should be placed at the specific index in the list
		} else {
			Node current = first;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node newNode = new Node(value, current.next, current);
			current.next.prev = newNode;
			current.next = newNode;
			size++;
		}
		
	}
	
	public void remove(int index) {
//		The index parameter is invalid
		if (first == null || index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
//		There is only one element in the list
		} else if (index == 0) {
			first.next.prev = first.prev;
			first.prev.next = first.next;	
			first = first.next;
			size--;
		} else {
			Node current = first;
			for (int ii = 0; ii < index - 1; ii++) {
				current = current.next;
			}
			current.next = current.next.next;
			current.next.prev = current;
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
