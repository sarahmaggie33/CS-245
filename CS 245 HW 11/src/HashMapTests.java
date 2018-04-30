import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("rawtypes")
class HashMapTests {

	private HashMap<String, Integer> hm;
	private Field elementData;
	private Field REMOVED;
	private Field size;
	private Method hashFunction;
	private Constructor<?> hashEntryConstructor;
	
	@BeforeEach
	public void setUp() throws Exception {
		hm = new HashMap<String, Integer>();
		elementData = HashMap.class.getDeclaredField("elementData");
		elementData.setAccessible(true);
		REMOVED = HashMap.class.getDeclaredField("REMOVED");
		REMOVED.setAccessible(true);
		size = HashMap.class.getDeclaredField("size");
		size.setAccessible(true);
		hashFunction = HashMap.class.getDeclaredMethod("hashFunction", Object.class);
		hashFunction.setAccessible(true);
		Class<?> hashEntryClass = Class.forName("HashMap$HashEntry");
		hashEntryConstructor = hashEntryClass.getDeclaredConstructors()[0];
	}

	@Test
	public void testFields() {
		assertEquals("HashMap should only have \"MAX_LOAD_FACTOR\", \"elementData\", \"REMOVED\", and \"size\" fields", HashMap.class.getDeclaredFields().length, 4);
	}
	
	@Test
	public void testConstructor() {
		try {
			assertEquals("HashMap constructor is not working correctly", ((Object[])elementData.get(hm)).length, 10);
			assertNotNull("HashMap constructor is not working correctly", REMOVED.get(hm));
			assertEquals("HashMap constructor is not working correctly", size.get(hm), 0);
		} catch (Exception e) {
			fail("HashMap constructor is not working correctly");
		}
	}
	
	@Test
	public void testIsEmpty() {
		try {
			assertEquals("isEmpty is not working correctly", hm.isEmpty(), true);
			size.set(hm, 1);
			assertEquals("isEmpty is not working correctly", hm.isEmpty(), false);
		} catch (Exception e) {
			fail("isEmpty is not working correctly");
		}
	}
	
	@Test
	public void testSize() {
		try {
			assertEquals("size method is not working correctly", hm.size(), 0);
			size.set(hm, 1);
			assertEquals("size method is not working correctly", hm.size(), 1);
		} catch (Exception e) {
			fail("size method is not working correctly");
		}
	}
	
	@Test
	public void testClear() {
		try {
			size.set(hm, 3);
			HashMap.HashEntry[] data = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "one", 1), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "two", 2), (HashMap.HashEntry)REMOVED.get(hm), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "three", 3), null};
			elementData.set(hm, data);
			hm.clear();
			assertEquals("clear method is not working correctly", size.get(hm), 0);
			for(int i=0; i<data.length; ++i) {
				assertNull("clear method is not working correctly", ((Object[])elementData.get(hm))[i]);
			}
		} catch (Exception e) {
			fail("clear method is not working correctly");
		}
	}
	
	@Test
	public void testHashFunction() {
		try {
			Random r = new Random();
			int capacity = 10;
			int randVal;
			for(int c=1; c<5; ++c) {
				HashMap.HashEntry[] data = new HashMap.HashEntry[capacity];
				elementData.set(hm, data);
				for(int i=0; i<100; ++i) {
					randVal = r.nextInt();
					assertEquals("hashFunction method is not working correctly", hashFunction.invoke(hm, randVal), h(randVal, capacity));
				}
				capacity = capacity * 2;
			}
		} catch (Exception e) {
			fail("hashFunction method is not working correctly");
		}
	}
	
	@Test
	public void testPut() {
		try {
			//put without rehash
			HashMap.HashEntry[] data = new HashMap.HashEntry[10];
			hm.put("2", 2);				//hashes to 0 [2,n,n,n,n,n,n,n,n,n]
			data[0] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 1);
			
			hm.put("13", 13);			//hashes to 0 [2,13,n,n,n,n,n,n,n,n]
			data[1] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "13", 13);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 2);
			
			hm.put("1", 1);				//hashes to 9 [2,13,n,n,n,n,n,n,n,1]
			data[9] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "1", 1);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 3);
			
			hm.put("12", 12);			//hashes to 9 [2,13,12,n,n,n,n,n,n,1]
			data[2] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "12", 12);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 4);
			
			data[1] = (HashMap.HashEntry)REMOVED.get(hm);	//removes index 1 [2,r,12,n,n,n,n,n,n,1]
			elementData.set(hm, data);	
			size.set(hm, 3);
			hm.put("21", 21);		//hashes to 9 [2,21,12,n,n,n,n,n,n,1]
			data[1] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "21", 21);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 4);
			
			hm.put("22", 22);			//hashes to 0 [2,21,12,22,n,n,n,n,n,1]
			data[3] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "22", 22);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 5);
			
			hm.put("3", 3);			//hashes to 1 [2,21,12,22,3,n,n,n,n,1]
			data[4] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "3", 3);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 6);
			
			hm.put("4", 4);			//hashes to 2 [2,21,12,22,3,4,n,n,n,1]
			data[5] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "4", 4);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 7);
			
			hm.put("5", 55);			//hashes to 3 [2,21,12,22,3,4,55,n,n,1]
			data[6] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "5", 55);
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 8);
			
			//put duplicate without rehash
			HashMap.HashEntry he = ((HashMap.HashEntry[])(elementData.get(hm)))[6];
			hm.put("5", 5);			//hashes to 3 [2,21,12,22,3,4,5,n,n,1]
			assertTrue("put method is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), data));
			assertEquals("put method is not working correctly", size.get(hm), 8);
			assertTrue("put method is not working correctly", he == ((HashMap.HashEntry[])(elementData.get(hm)))[6]);
			
			//put with rehash and removal
			data[7] = (HashMap.HashEntry)REMOVED.get(hm);	//[2,21,12,22,3,4,5,r,n,1]
			elementData.set(hm, data);
			hm.put("6", 6);			//hashes to 14 [22,n,n,n,n,n,n,n,n,12,2,3,4,5,1,6,n,n,n,21]
			HashMap.HashEntry[] newData = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "22", 22), null, null, null, null, null, null, null, null, (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "12", 12), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "3", 3), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "4", 4), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "5", 5), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "1", 1), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "6", 6), null, null, null, (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "21", 21)};
			assertTrue("put method (and/or rehash) is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), newData));
			assertEquals("put method (and/or rehash) is not working correctly", size.get(hm), 9);
			
			//put with removals
			newData[7] = (HashMap.HashEntry)REMOVED.get(hm);	//[22,n,n,n,n,n,n,r,n,12,2,3,4,5,1,6,n,n,n,21]
			elementData.set(hm, newData);	
			hm.put("10", 10);		//hashes to 7 [22,n,n,n,n,n,n,10,n,12,2,3,4,5,1,6,n,n,n,21]
			newData[7] = (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "10", 10);
			assertTrue("put method (and/or rehash) is not working correctly", equalLists((HashMap.HashEntry[])elementData.get(hm), newData));
			assertEquals("put method (and/or rehash) is not working correctly", size.get(hm), 10);
		} catch (Exception e) {
			fail("put method (and/or rehash) is not working correctly");
		}
	}
	
	@Test
	public void testContainsKey() {
		try {
			assertFalse("containsKey method is not working correctly", hm.containsKey("2"));
			//[2,r,12,n,n,n,n,n,n,1]
			HashMap.HashEntry[] newData = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2), (HashMap.HashEntry)REMOVED.get(hm), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "12", 12), null, null, null, null, null, null, (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "1", 1)};
			elementData.set(hm, newData);
			size.set(hm, 3);
			assertTrue("containsKey method is not working correctly", hm.containsKey("2"));
			assertTrue("containsKey method is not working correctly", hm.containsKey("1"));
			assertTrue("containsKey method is not working correctly", hm.containsKey("12"));
			assertFalse("containsKey method is not working correctly", hm.containsKey("13"));
		} catch (Exception e) {
			fail("containsKey method is not working correctly");
		}
	}
	
	@Test
	public void testRemove() {
		try {
			assertFalse("remove method is not working correctly", hm.containsKey("2"));
			//[2,13,12,n,n,n,n,n,n,1]
			HashMap.HashEntry[] newData = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "13", 13), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "12", 12), null, null, null, null, null, null, (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "1", 1)};
			elementData.set(hm, newData);
			size.set(hm, 4);
			assertEquals("remove method is not working correctly", hm.remove("2"), new Integer(2));
			assertEquals("remove constructor is not working correctly", size.get(hm), 3);
			assertEquals("remove method is not working correctly", hm.remove("13"), new Integer(13));
			assertEquals("remove constructor is not working correctly", size.get(hm), 2);
			assertEquals("remove method is not working correctly", hm.remove("1"), new Integer(1));
			assertEquals("remove constructor is not working correctly", size.get(hm), 1);
			assertEquals("remove method is not working correctly", hm.remove("12"), new Integer(12));
			assertEquals("remove constructor is not working correctly", size.get(hm), 0);
			assertNull("remove method is not working correctly", hm.remove("12"));
			assertEquals("remove constructor is not working correctly", size.get(hm), 0);
		} catch (Exception e) {
			fail("remove method is not working correctly");
		}
	}
	
	@Test
	public void testToString() {
		try {
			assertEquals("toString method is not working correctly", hm.toString(), "[]");
			
			HashMap.HashEntry[] data = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2)};
			size.set(hm, 1);
			elementData.set(hm, data);
			assertEquals("toString method is not working correctly", hm.toString(), "[2]");
			
			HashMap.HashEntry[] newData = {(HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "2", 2), (HashMap.HashEntry)REMOVED.get(hm), (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "12", 12), null, null, null, null, null, null, (HashMap.HashEntry)hashEntryConstructor.newInstance(hm, "1", 1)};
			elementData.set(hm, newData);
			size.set(hm, 3);
			assertEquals("toString method is not working correctly", hm.toString(), "[2, 12, 1]");
		} catch (Exception e) {
			fail("toString method is not working correctly");
		}
	}
	
	private boolean equalLists(HashMap.HashEntry[] a, HashMap.HashEntry[] b) {
		if(a.length != b.length) {
			return false;
		}
		try {
			for(int i=0; i<a.length; ++i) {
				if(a[i] == null && b[i] == null) {
					continue;
				} else if(a[i] == REMOVED.get(hm) && b[i] == REMOVED.get(hm)) {
					continue;
				}
				if((a[i] == null && b[i] != null) || (a[i] != null && b[i] == null) || (a[i] == REMOVED.get(hm) && b[i] != REMOVED.get(hm)) || (a[i] != REMOVED.get(hm) && b[i] == REMOVED.get(hm))) {
					return false;
				}
				if(!a[i].getKey().equals(b[i].getKey()) || !a[i].getValue().equals(b[i].getValue())) {
					return false;
				}
			}
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return true;
	}
	
	private int h(Object k, int l) {
		return Math.abs(k.hashCode()) % l;
	}
}
