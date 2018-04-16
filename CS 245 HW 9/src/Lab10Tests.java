import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class Lab10Tests {
	
	private BinarySearchTree<Integer> bst;
	private Field root;
	private Field numElements;
	private Field data;
	private Field left;
	private Field right;
	private Constructor<?> nodeConstructor;
	
	@BeforeEach
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
		root = BinarySearchTree.class.getDeclaredField("root");
		root.setAccessible(true);
		numElements = BinarySearchTree.class.getDeclaredField("numElements");
		numElements.setAccessible(true);
		
		Class<?> nodeClass = Class.forName("BinarySearchTree$BSTNode");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		left = nodeClass.getDeclaredField("left");
		left.setAccessible(true);
		right = nodeClass.getDeclaredField("right");
		right.setAccessible(true);
		nodeConstructor = nodeClass.getDeclaredConstructors()[1];
	}

	@Test
	public void testFields() {
		assertEquals("BinarySearchTree should only have \"root\" and \"numElements\" fields", BinarySearchTree.class.getDeclaredFields().length, 2);
	}
	
	@Test
	public void testConstructor() {
		try {
			assertNull("BinarySearchTree constructor is not working correctly", root.get(bst));
			assertEquals("BinarySearchTree constructor is not working correctly", numElements.get(bst), 0);
		} catch (Exception e) {
			fail("BinarySearchTree constructor is not working correctly");
		}
	}
	
	@Test
	public void testAdd() {
		try {
			BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				bst.add(randVal);
				red(myBst, randVal);
				assertTrue("add method is not working correctly", isBST(root.get(bst)));
				assertTrue("add method is not working correctly", equalBSTs(myBst, bst));
			}
		} catch (Exception e) {
			fail("add method is not working correctly");
		}
	}
	
	@Test
	public void testContains() {
		try {
			List<Integer> values = new ArrayList<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				red(bst, randVal);
				values.add(randVal);
			}
			for(Integer i : values) {
				assertTrue("contains method is not working correctly", bst.contains(i));
				assertFalse("contains method is not working correctly", bst.contains(i+100));
			}
		} catch (Exception e) {
			fail("contains method is not working correctly");
		}
	}
	
	@Test
	public void testRemove() {
		try {
			BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();
			ArrayList<Integer> values = new ArrayList<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<1000; ++i) {
				do {
					randVal = r.nextInt(1000);
				} while(values.contains(randVal));
				red(bst, randVal);
				red(myBst, randVal);
				values.add(randVal);
			}
			Iterator<Integer> itr = values.iterator();
			Integer value;
			int size;
			while(itr.hasNext()) {
				value = itr.next();
				size = (Integer)numElements.get(bst);
				bst.remove(value);
				orange(myBst, value);
				assertTrue("remove method is not working correctly", (Integer)numElements.get(bst) < size);
				assertTrue("remove method is not working correctly", isBST(root.get(bst)));
				assertTrue("remove method is not working correctly", equalBSTs(myBst, bst));
				size = (Integer)numElements.get(bst);
				bst.remove(value+1001);//not present
				assertTrue("remove method is not working correctly", ((Integer)numElements.get(bst)).equals(size));
				assertTrue("remove method is not working correctly", isBST(root.get(bst)));
				assertTrue("remove method is not working correctly", equalBSTs(myBst, bst));
			}
		} catch (Exception e) {
			fail("remove method is not working correctly");
		}
	}
	
	private boolean isBST(Object r)  {
		try {
			if(r == null || left.get(r) == null && right.get(r) == null) {
				return true;
			}
	        if(left.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(left.get(r))) > 0 && isBST(left.get(r));
	        }
	        if(right.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(right.get(r))) < 0 && isBST(right.get(r));
	        }
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
    }
	
	private boolean equalBSTs(BinarySearchTree<Integer> mb, BinarySearchTree<Integer> yb) {
		try {
			if (!numElements.get(mb).equals(numElements.get(yb))) {
				return false;
			}
			return equalBSTs(root.get(mb), root.get(yb)) && numElements.get(mb).equals(numElements.get(yb));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean equalBSTs(Object mn, Object yn) {
		try {
			if(mn == null && yn == null) {
				return true;
			} else if(mn != null && yn != null) {
				return data.get(mn).equals(data.get(yn)) && equalBSTs(left.get(mn), left.get(yn)) && equalBSTs(right.get(mn), right.get(yn));
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void red(BinarySearchTree<Integer> b, Integer v) {
        try {
			root.set(b, green(root.get(b), v, b));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object green(Object n, Integer v, Object b) {
    		try {
	        if (n == null) {
	            n = nodeConstructor.newInstance(v, null, null);
	            numElements.set(b, (Integer)(numElements.get(b))+1);
	        } else if (((Integer) data.get(n)).compareTo(v) > 0) {
	            left.set(n, green(left.get(n), v, b));
	        } else if (((Integer) data.get(n)).compareTo(v) < 0) {
	            right.set(n, green(right.get(n), v, b));
	        }
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
        return n;
    }
    
    private void orange(BinarySearchTree<Integer> b, Integer v) {
		try {
		root.set(b, yellow(root.get(b), v, b));
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

    private Object yellow(Object n, Integer v, Object b) {
		try {
	        if (n == null) {
	            return null;
	        } else if (((Integer)data.get(n)).compareTo(v) > 0) {
	            left.set(n, yellow(left.get(n), v, b));
	        } else if (((Integer)data.get(n)).compareTo(v) < 0) {
	            right.set(n, yellow(right.get(n), v, b));
	        } else {  
	            if (right.get(n) == null) {
	            		numElements.set(b, (Integer)(numElements.get(b))-1);
	                return left.get(n);    
	            } else if (left.get(n) == null) {
	            		numElements.set(b, (Integer)(numElements.get(b))-1);
	                return right.get(n);   
	            } else {
	                data.set(n, blue(left.get(n)));
	                left.set(n, yellow(left.get(n), (Integer)data.get(n), b));
	            }
	        }
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return n;
	}
    
   private Integer blue(Object n) {
    		try {
	        if (right.get(n) == null) {
	            return (Integer)data.get(n);
	        } else {
	            return blue(right.get(n));
	        }
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return -1;
    }
}
