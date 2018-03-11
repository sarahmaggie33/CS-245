import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class Lab6Tests {

	private DoublyLinkedList<Integer> dll;
	private Field first;
	private Field size;
	private Field data;
	private Field next;
	private Field prev;

	@Before
	public void setUp() throws Exception {
		dll = new DoublyLinkedList<Integer>();
		first = DoublyLinkedList.class.getDeclaredField("first");
		first.setAccessible(true);
		size = DoublyLinkedList.class.getDeclaredField("size");
		size.setAccessible(true);
		
		Class<?> nodeClass = Class.forName("DoublyLinkedList$Node");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		next = nodeClass.getDeclaredField("next");
		next.setAccessible(true);
		prev = nodeClass.getDeclaredField("prev");
		prev.setAccessible(true);
	}
	
	@Test
	public void testFields() {
		assertEquals("DoublyLinkedList should only have \"first\" and \"size\" fields", DoublyLinkedList.class.getDeclaredFields().length, 2);
	}

	@Test
	public void testConstructor() {
		try {
			assertNull("DoublyLinkedList constructor is not working correctly", first.get(dll));
			assertEquals("DoublyLinkedList constructor is not working correctly", size.get(dll), 0);
		} catch (Exception e) {
			fail("DoublyLinkedList constructor is not working correctly");
		}
	}
	
	@Test
	public void testAdd() {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
			Random rand = new Random();
			int r;
			for(int i=0; i<10; ++i) {
				r = rand.nextInt(10);
				dll.add(r);
				assertEquals("add(E) is not working correctly", size.get(dll), i+1);
				list.add(r);
				assertTrue("add(E) is not working correctly", equalLists(list));
			}
		} catch (Exception e) {
			fail("add(E) method is not working correctly");
		}
	}
	
	private boolean equalLists(ArrayList<Integer> a) {
		try {
			if((int)size.get(dll) != a.size()) {
				return false;
			}
			Object n = first.get(dll);
			Object previous;
			int i=0;
			while(i<a.size()) {
				if(!a.get(i).equals(data.get(n))) {
					return false;
				}
				previous = n;
				n = next.get(n);
				if(!prev.get(n).equals(previous)) {
					return false;
				}
				i++;
			}
			if(n != first.get(dll)) {
				return false;
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
