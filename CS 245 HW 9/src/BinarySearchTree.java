import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root; // root of overall tree
    private int numElements;

    // post: constructs an empty search tree
    public BinarySearchTree() {
        root = null;
        numElements = 0;
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
    	if (!contains(value)) {
    		root = add(root, value);
    		numElements++;
    	}
    }

    // post: value added to tree so as to preserve binary search tree
    private BSTNode<E> add(BSTNode<E> node, E value) {
        if (node == null) {
            node = new BSTNode<E>(value);
        } else if (node.data.compareTo(value) > 0) {
            node.left = add(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = add(node.right, value);
        } else if (node.data.compareTo(value) == 0) {
        	return null;
        }
        return node;
    }

    
    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(root, value);
    }   

    // post: returns true if given tree contains value, returns false otherwise
    private boolean contains(BSTNode<E> node, E value) {
        if (node == null) {
            return false;
        } else {
            int compare = value.compareTo(node.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(node.left, value);
            } else {   // compare > 0
                return contains(node.right, value);
            }
        }
    }
    
    
    public void remove (E value) {
    	if (contains(value)) {
    		root = remove(root, value);
    		numElements--;
    	}
    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {
    	// a leaf
    	if (node == null) {
    		return null;
    	// current node is bigger than desired value
    	} else if (node.data.compareTo(value) > 0) {
    		node.left = remove(node.left, value);
        // current node is smaller than desired value
    	} else if (node.data.compareTo(value) < 0) {
        	node.right = remove(node.right, value);        
    	} else { // node.data == value; remove this node
    		if (node.right == null) {
    			return node.left; // no R child; replace w/ L
    		} else if (node.left == null) {
    			return node.right; // no L child; replace w/ R
    		} else {
    			//both children; replace w/ max from L
    			node.data = getMax(node.left);
    			node.left = remove(node.left, node.data);
    		}
    	}
         return node;    	
    }
    
    
    public void clear() {
    	root = null;
    	numElements = 0;
    }
    
    
    public boolean isEmpty() {
    	return numElements == 0;
    }
    
    
    public int size() {
    	return numElements;
    }
    
    
    @SuppressWarnings("unchecked")
	public E[] toArray() {
    	List<E> aList = new ArrayList<E>();
    	toArray(root, aList);
    	E[] array = (E[]) new Comparable[numElements];
    	array = aList.toArray(array);
		return array;
    }
    
    private void toArray(BSTNode<E> node, List<E> aList) {
    	if (node == null) {
    		return;
    	}
    	toArray(node.left, aList);
    	aList.add(node.data);
    	toArray(node.right, aList);
    	
    }
    
    public Iterator<E> iterator() {
    	Iterator<E> iterator = new Iterator<E>(root);
		return iterator;
    }
    
    
    private E getMax(BSTNode<E> node) {
    	if (node == null) {
    		return null;
    	} else if (node.right == null) {
    		return node.data;
    	} else {
    		return getMax(node.right);
    	}
    	
    }
    
    
    public static class Iterator<E> {
    	private Stack<BSTNode<E>> stack;
    	public Iterator(BSTNode<E> node) {
    		stack = new Stack<BSTNode<E>>();
    		//push all left children accessible from the parameter node
    		while(node != null) {
    			stack.push(node);
    			node = node.left;
    		}
    		//node with highest value will be on bottom of stack
    		//when popping the nodes they will go from lowest to highest
    	}
    	
    	public boolean hasNext() {
    		return !stack.isEmpty();
    	}
    	
    	public E next() {
    		// lowest value node is presented
    		BSTNode<E> node = stack.pop();
    		E result = node.data;
    		// the data of this node will be returned
    		if (node.right != null) { // if the current node has a right node, 
    			node = node.right; // the right node is now the current node
    			while (node != null) { // while there are more right nodes, 
    				stack.push(node); // push them to the stack
    				node = node.left; // the current node is then the leftmost node
    			}
    		}
    		return result;
    		
    	}
    }
    
    
    private static class BSTNode<E> {
        public E data;
        public BSTNode<E> left;
        public BSTNode<E> right;

        public BSTNode(E data) {
            this(data, null, null);
        }

        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
