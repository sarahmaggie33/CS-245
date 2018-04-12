import java.util.List;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root; // root of overall tree
    private int numElements;

    // post: constructs an empty search tree
    public BinarySearchTree() {
        root = null;
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        root = add(root, value);
    }

    // post: value added to tree so as to preserve binary search tree
    private BSTNode<E> add(BSTNode<E> node, E value) {
        if (node == null) {
            node = new BSTNode<E>(value);
        } else if (node.data.compareTo(value) > 0) {
            node.left = add(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = add(node.right, value);
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
    	
    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {
		return node;
    	
    }
    
    
    public void clear() {
    	
    }
    
    
    public boolean isEmpty() {
		return false;
    	
    }
    
    
    public int size() {
    	return -1;
    }
    
    
    public E[] toArray() {
		return null;
    	
    }
    
    private void toArray(BSTNode<E> node, List<E> aList) {
    	
    }
    
    public Iterator<E> iterator() {
		return null;
    	
    }
    
    
    private E getMax() {
		return null;
    	
    }
    
    
    
    
    
    private static class Iterator<E> {
    	private Stack<BSTNode<E>> stack;
    	public Iterator(BSTNode<E> node) {
    		
    	}
    	
    	public boolean hasNext() {
    		return false;
    	}
    	
    	public E next() {
    		return null;
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
