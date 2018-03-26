import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lab7Tests {
	
	private SortedLinkedList<Integer> sll;
	private Field first;
	private Field size;
	private Field data;
	private Field next;

	@BeforeEach
	public void setUp() throws Exception {
		sll = new SortedLinkedList<Integer>();
		first = SortedLinkedList.class.getDeclaredField("first");
		first.setAccessible(true);
		size = SortedLinkedList.class.getDeclaredField("size");
		size.setAccessible(true);
		
		Class<?> nodeClass = Class.forName("SortedLinkedList$Node");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		next = nodeClass.getDeclaredField("next");
		next.setAccessible(true);
	}
	
	@Test
	public void testFields() {
		assertEquals("SortedLinkedList should only have \"first\" and \"size\" fields", SortedLinkedList.class.getDeclaredFields().length, 2);
	}

	@Test
	public void testConstructor() {
		try {
			assertNull("SortedLinkedList constructor is not working correctly", first.get(sll));
			assertEquals("SortedLinkedList constructor is not working correctly", size.get(sll), 0);
		} catch (Exception e) {
			fail("SortedLinkedList constructor is not working correctly");
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
				sll.add(r);
				assertEquals("add(E) is not working correctly", size.get(sll), i+1);
				list.add(r);
				Collections.sort(list);
				assertTrue("add(E) is not working correctly", equalLists(list));
			}
		} catch (Exception e) {
			fail("add(E) method is not working correctly");
		}
	}
	
	private boolean equalLists(ArrayList<Integer> a) {
		try {
			if((int)size.get(sll) != a.size()) {
				return false;
			}
			Object n = first.get(sll);
			int i=0;
			while(i<a.size()) {
				if(!a.get(i).equals(data.get(n))) {
					return false;
				}
				n = next.get(n);
				i++;
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
