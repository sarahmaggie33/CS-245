import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
	public static final int DEFAULT_CAPACITY = 10;
	private E[] elementData;
	private int size;
	
	public Stack() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public Stack(int capacity) {
		if (capacity > -1) {
			E[] elementData = (E[]) (new Object[capacity]);
			this.elementData = elementData;
			this.size = 0;
		} else if (capacity < 0) {
			throw new IllegalArgumentException("Parameter value is negative.");
		}
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void push(E item) {
		ensureCapacity(size + 1);
		elementData[size] = item;
		size++;
	}
	
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E[] newElementData = Arrays.copyOf(elementData, size);
		E item = newElementData[size - 1];
		elementData[size - 1] = null;
		size--; 
		return item;
	}
	
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E item = elementData[size - 1];
		return item;
	}
	
	private void ensureCapacity(int capacity) {
		if (capacity > elementData.length) {
			int newCapacity = 2 * elementData.length + 1;
			if (newCapacity < capacity) {
				newCapacity = capacity;
			}
			this.elementData = Arrays.copyOf(elementData, newCapacity);
		
		} 
	}
	
}
