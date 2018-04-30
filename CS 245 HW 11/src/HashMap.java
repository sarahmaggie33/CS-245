
public class HashMap<K, V> {
	private final double MAX_LOAD_FACTOR = .75;
	private HashEntry[] elementData;
	private final HashEntry REMOVED; // references the HashEntry that replaces a removed element
	private int size;

	@SuppressWarnings("unchecked")
	public HashMap() {
		elementData = new HashMap.HashEntry[10];
		REMOVED = new HashEntry(null, null);
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean containsKey(K key) {
		if (!isEmpty()) {
			for (int i = 0; i < elementData.length; i++) {
				if (elementData[i] == null) {
					continue;
				} else if (elementData[i].getKey() == null) {
					continue;
				} else {
					if (elementData[i].getKey().equals(key)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String toString() {
		String result = "[";
		boolean first = true;
		if (!isEmpty()) {
			for (int i = 0; i < elementData.length; i++) {
				HashEntry current = elementData[i];
				if (current != null) {
					if (current.getKey() != null) {
						if (!first) {
							result += ", ";
						}
						result += current.getKey();
						first = false;
					}
				} else {

					first = false;
				}
			}

		}
		return result + "]";
	}

	private int hashFunction(Object key) {
		return Math.abs(key.hashCode()) % elementData.length;
	}

	public class HashEntry {
		private K key;
		private V value;

		public HashEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return this.key;
		}

		public V getValue() {
			return this.value;
		}

	}

	// --------------------------------------------------------------

	public void put(K key, V value) {
		int bucket = hashFunction(key);
		while (elementData[bucket] != null && !elementData[bucket].key.equals(key) && elementData[bucket] != REMOVED) { // linear probing																										
			bucket = (bucket + 1) % elementData.length;
			System.out.println("bucket");
		}
		// System.out.println("bucket1");

		if (elementData[bucket] == null || elementData[bucket] == REMOVED) {
			if (loadFactor() >= MAX_LOAD_FACTOR) {
				rehash();
				bucket = hashFunction(key);
				while (elementData[bucket] != null && !elementData[bucket].key.equals(key) && elementData[bucket] != REMOVED) { // linear probing
					bucket = (bucket + 1) % elementData.length;
					System.out.println("bucket2");
				}
			}
			elementData[bucket] = new HashEntry(key, value);
			size++;
		} else { // old value should be replaced with the new value
			elementData[bucket].value = value;
		}

	}

	public void clear() {
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = null;
		}
		size = 0;
	}

	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public V remove(Object key) {
		if (containsKey((K) key)) {
			for (int i = 0; i < elementData.length; i++) {
				if (elementData[i] == null) {
					continue;
				} else if (elementData[i].getKey() == null) {
					continue;
				} else {
					if (elementData[i].getKey().equals(key)) {
						V value = elementData[i].getValue();
						elementData[i] = REMOVED;
						size--;
						return value;
					}
				}
			}
		}
		return null;
	}

	private double loadFactor() {
		return (double) size / MAX_LOAD_FACTOR;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		// replace element data array with a larger empty version
		HashEntry[] oldElementData = elementData;
		elementData = new HashMap.HashEntry[2 * oldElementData.length];
		size = 0;

		// re-add all of the old data into the new array
		for (int i = 0; i < oldElementData.length; i++) {
			HashEntry current = oldElementData[i];
			while (current != null) {
				put(current.getKey(), current.getValue());
			}
		}
	}

}
