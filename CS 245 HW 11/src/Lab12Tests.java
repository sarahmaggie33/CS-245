import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("rawtypes")
class Lab12Tests {

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
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			System.out.println(sStackTrace);
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
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			System.out.println(sStackTrace);
			fail("toString method is not working correctly");
			
		}
	}
	
	private int h(Object k, int l) {
		return Math.abs(k.hashCode()) % l;
	}
}
