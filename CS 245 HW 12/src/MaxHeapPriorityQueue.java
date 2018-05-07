import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Implements a priority queue of comparable objects using a
// max-heap represented as an array.
public class MaxHeapPriorityQueue<E extends Comparable<E>> {
	 private E[] elementData;
	 private int size;
	    
	 // Constructs an empty queue.
	 @SuppressWarnings("unchecked")
	 public MaxHeapPriorityQueue() {
	    elementData = (E[]) new Comparable[10];
	    size = 0;
	 }
	 
	// Adds the given element to this queue.
	 public void add(E value) {
		// resize if necessary
	        if (size + 1 >= elementData.length) {
	            elementData = Arrays.copyOf(elementData, elementData.length * 2);
	        }
	        
	        // insert as new rightmost leaf
	        elementData[size + 1] = value;
	        
	        // "bubble up" toward root as necessary to fix ordering
	        int index = size + 1;
	        boolean found = false;   // have we found the proper place yet?
	        while (!found && hasParent(index)) {
	            int parent = parent(index);
	            if (elementData[index].compareTo(elementData[parent]) > 0) {
	                swap(elementData, index, parent(index));
	                index = parent(index);
	            } else {
	                found = true;  // found proper location; stop the loop
	            }
	        }
	        
	        size++;
	 }
	 
	 public boolean isEmpty() {
		 return size == 0;
	 }
	 
	 // returns max value in the queue
	 public E peek() {
		 if (isEmpty()) {
	        throw new NoSuchElementException();
	     }
	     return elementData[1];
	 }
	 
	 public E remove() {
		 E result = peek();

	     // move rightmost leaf to become new root
	     elementData[1] = elementData[size];
	     size--;
	        
	     // "bubble down" root as necessary to fix ordering
	     int index = 1;
	     boolean found = false;   // have we found the proper place yet?
	     while (!found && hasLeftChild(index)) {
	         int left = leftChild(index);
	         int right = rightChild(index);
	         int child = left;
	         if (hasRightChild(index) &&
	             elementData[right].compareTo(elementData[left]) > 0) {
	        	 child = right;
	         } 
	          
	         if (elementData[index].compareTo(elementData[child]) < 0) {
	        	 swap(elementData, index, child);
	             index = child;
	         } else {
	        	 found = true;  // found proper location; stop the loop
	         }
	     }
	        
	     return result;
	 }	 
	 
	 public int size() {
		 return size;
	 }
	 
	 public void clear() {
		 for(int i = 1; i < size(); i++) {
			 elementData[i] = null;
		 }
		 size = 0;

	 }
	 
	 public boolean contains(Object value) {
		 if (size != 0) {
			 return contains(value, 1);
		 }
		 return false;
	 }

	private boolean contains(Object value, int index) {
		 if (index > size) {
			 return false;
		 }
		 if (elementData[index] != null) {
			 if (elementData[index].equals(value)) {
				 return true;
			 }
			 else {
				  contains(value, index + 1);
			 }
		 }
		 return contains(value, index + 1);
	 }
	 
	 public Object[] toArray() {
		 Object[] array = new Object[size];
		 int j = 0;
		 for(int i = 0; i < elementData.length; i++) {
			 if (elementData[i] != null) {
				 array[j] = elementData[i];
				 j++;
			 }
		 }
		 return array;
	 }
	 

	 
	 public String toString() {
		 String result = "[";
	     if (!isEmpty()) {
	    	 result += elementData[1];
	         for (int i = 2; i <= size; i++) {
	        	 result += ", " + elementData[i];
	         }
	     }
	     return result + "]";
	 }
	 
	 public static void heapSort(Comparable[] a, int size) {
		//bubble down non-leaf nodes until array is a max-heap
		 MaxHeapPriorityQueue q = new MaxHeapPriorityQueue();
		 int index = 0;
		 while (index < size) {
			 q.bubbleDown(index);
			 index++;
		 }
		 //remove elements repeatedly until there is a sorted array
		 q.sortRemove(); 
	 }
	 
	 public void bubbleDown(int index) {
		 //swap each node with its larger child as needed
	     boolean found = false;   // have we found the proper place yet?
	     while (!found && hasLeftChild(index)) {
	         int left = leftChild(index);
	         int right = rightChild(index);
	         int child = left;
	         if (hasRightChild(index) &&
	             elementData[right].compareTo(elementData[left]) > 0) {
	        	 child = right;
	         } 
	          
	         if (elementData[index].compareTo(elementData[child]) < 0) {
	        	 swap(elementData, index, child);
	             index = child;
	         } else {
	        	 found = true;  // found proper location; stop the loop
	         }
	     } 
	 }
	 
	 private E sortRemove() {
		 //move each removed element to the end
		 return null;
	 }
	 
	 private int parent(int index) {
	      return index / 2;
	 }
	 
	 private int leftChild(int index) {
		 return index * 2;
	 }
	 
	 private int rightChild(int index) {
		 return index * 2 + 1;
	 }
	 
	 private boolean hasParent(int index) {
		 return index > 1;
	 }
	 
	 private boolean hasLeftChild(int index) {
		 return leftChild(index) <= size;
	 }
	 
	 private boolean hasRightChild(int index) {
		 return rightChild(index) <= size;
	 }
	 
	 private void swap(E[] a, int index1, int index2) {
		 E temp = a[index1];
	     a[index1] = a[index2];
	     a[index2] = temp;
	 }
	
	 public Iterator<E> iterator(){
	 	 return new MHPQIterator();	 
	 }
	 
	 public class MHPQIterator implements Iterator<E> {
		 private int index = 0;
		 
		 public boolean hasNext() {
			 return next() != null && index < elementData.length;
		 }
		 
//		 public E next() {
//				if (currentNode == null) {
//	                throw new NoSuchElementException();
//	            }
//	            E toReturn = currentNode.data;
//	            currentNode = currentNode.next;
//	            return toReturn;
//			}
		 
		 public E next() {
			 if (size == 0) {
				 return null;
			 }
			 while (index < elementData.length && elementData[index] == null) {
				 index++;
			 }
			 E next = elementData[index];
			 return next;
		 }
	 }
	 	
}
