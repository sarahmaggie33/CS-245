import java.util.Arrays;
import java.util.Random;

public class Sack<E> {
	private E[] elementData;
	private int size;
	public static final int DEFAULT_CAPACITY = 10;

	public Sack() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public Sack(int capacity) {
		if (capacity > -1) {
			E[] elementData = (E[]) (new Object[capacity]);
			this.elementData = elementData;
			this.size = 0;
		} else if (capacity < 0) {
			throw new IllegalArgumentException("Parameter value is negative.");
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(E item) {
		ensureCapacity(size + 1);
		elementData[size] = item;
		size++;
	}
	
	public E grab() {
		E value;
		if (size == 0) {
			return null;
		} else {
			Random rand = new Random();
			int index = rand.nextInt(size);
			value = elementData[index];
			remove(index);
		}
		return value;
	}

	public E[] dump() {
		E[] newElementData = Arrays.copyOf(elementData, size);
		for (int i = 0; i < size; i++) {
			this.elementData[i] = null;
		}
		size = 0;
		return newElementData;
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
	
	private void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("This index is illegal: " + index);
		}
		//shift elements
		for(int i = index; i< size - 1; i++) {
			elementData[i] = elementData[i+1];
		}
		this.elementData[size - 1] = null;
		size--;
	}
	
}
