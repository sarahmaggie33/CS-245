import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
	private BSTNode<E> root; // root of overall tree
	private BSTNode<E> first; // first node in linked list; smallest value in the tree
    private int numElements;

    // post: constructs an empty search tree
    public BinarySearchTree() {
        this.root = null;
        this.numElements = 0;
        this.first = null;
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
//    	assignFirst();
        this.root = add(root, value, null, null);
    }

    // post: assign each node's parent and next when a new node is created
    private BSTNode<E> add(BSTNode<E> node, E value, BSTNode<E> parent, BSTNode<E> prev) {
    	if (node == null) {
            node = new BSTNode<E>(value);
            node.parent = parent;
    		if (prev != null) {
    			node.next = prev.next;
    			prev.next = node;
    		} else {
    			first = node;
    			node.next = parent; 
    		}
           this.numElements++;
        } else if (node.data.compareTo(value) > 0) { // is to be a left child
            node.left = add(node.left, value, node, getPrevNode(node));

        } else if (node.data.compareTo(value) < 0) { // is to be a right child
            node.right = add(node.right, value, node, node); 
        }
        return node;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(this.root, value);
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
    
    public void remove(E value) {
        this.root = remove(root, value);
    	assignFirst();
    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {
        if (node == null) {
            return null;
        } else if (node.data.compareTo(value) > 0) {
            node.left = remove(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = remove(node.right, value);
        } else {  // node.data == value; remove this node
            if (node.right == null && node.left != null) {
            	numElements--;
            	node.left.parent = node.parent;
            	if (getPrevNode(node) != null) {
            		getPrevNode(node).next = node.next;
            	}
                return node.left;    // no R child; replace w/ L
            } else if (node.left == null && node.right != null){
            	numElements--;
            	node.right.parent = node.parent;
            	if (getPrevNode(node) != null) {
                	getPrevNode(node).next = node.next;
                }
            	return node.right;   // no L child; replace w/ R
            } else if (node.left != null){
                // both children; replace w/ max from L
            	numElements--;
                node.data = getMax(node.left);
                node.left = remove(node.left, node.data);
            }
        }
        return node;
    }
    
    private E getMax(BSTNode<E> node) {
        if(node == null) {
        	return null;
        } else if (node.right == null) {
            return node.data;
        } else {
            return getMax(node.right);
        }
    }

    public void clear() {
    	this.root = null;
    	this.first = null;
    	this.numElements = 0;
    }
    
    public boolean isEmpty() {
    	return this.numElements == 0;
    }
    
    public int size() {
    	return this.numElements;
    }
    
    @SuppressWarnings("unchecked")
	public E[] toArray() {
		ArrayList<E> aList = new ArrayList<E>();
		E[] arr = (E[]) new Comparable[this.numElements];
		toArray(this.root, aList);
		return aList.toArray(arr);
    }
    
    private void toArray(BSTNode<E> node, List<E> aList) {
		if (node != null) {
            toArray(node.left, aList);
            aList.add(node.data);
            toArray(node.right, aList);
        }
    }
    
    private BSTNode<E> getPrevNode(BSTNode<E> node) {
    	// use to update the prev node's next pointer
    	// If the node has a left child, move down the left subtree to get the max node
    	BSTNode<E> returnNode = null;
    	if (node.left != null) {
    		node = node.left;
    		while(node.right != null) {
    			node = node.right;
    		}
    		returnNode = node;
    	} else if (node.parent != null) {
    		// node is right child
    		if (node.parent.right == node) {
    			return node.parent;
    		// node is left child
    		} else {
    			while (node.parent != null && node.parent.left == node) {
    				node = node.parent;
    			}
    			if (node.parent != null) {
    				returnNode = node.parent;
    			} else {
    			returnNode = null;
    			}
    		}
   
    	}
		return returnNode;
    }
    
    private void assignFirst() {
    	BSTNode<E> node = root;
    	 if (node == null) {
    		 first = node;
    	 } else if (node.left == null) {
             first = node;
         } else {
        	 while (node.left != null) {
        		 node = node.left;
        	 }
        	 first = node;
         }
    
    }
    
    private BSTNode<E> buildTreeUtil(E[] values, int start, int end, BSTNode<E> parent) {
    	// base case; stops recursion
    	if (start > end) {
    		return null;
    	} 
    	// create a new node storing the middle element of the list
    	int mid = (start + end) /2;
    	BSTNode<E> node = new BSTNode<E>(values[mid], null, null, null, null);
    	// assign new node's left ref to a recursive call using left half of the list
    	node.left = buildTreeUtil(values, start, mid - 1, node);
    	// assign new node's right ref to a recursive call using right half of the list
    	node.right = buildTreeUtil(values, mid + 1, end, node);
    	// during recursion:
    		// update the node's parent using the parameter
    		node.parent = getPrevNode(node);
    		// assign the next references:
    			// if you are left child and have no right children, your next is your parent
    			if (node.data.compareTo(node.parent.data) < 0 && node.right == null) {
    				node.next = node.parent;
    			}
    			// if you are a left child and you have right children, your right most child's next node is your parent
    			else if (node.data.compareTo(node.parent.data) < 0 && node.right != null) {
    				BSTNode<E> current = node;
    				while(current.right.right != null) {
    					current = current.right;
    				}
    				node.next = node.right;
    				node.parent = current.next;
    			}
    			// if you are a right child and you have no left children, your parent's next is you
    			else if (node.data.compareTo(node.parent.data) > 0 && node.right == null) {
    				node.parent.next = node;
    			}

    			// if you are a right child and you have left children, assign your parent's next to your left most child (min in your left subtree)
    			else if (node.data.compareTo(node.parent.data) > 0 && node.right != null) {
    				BSTNode<E> current = node.left;
    				while(current.left != null) {
    					current = current.left;
    				}
    				node.parent.next = current;
    			}
    			// if you are the root with a right child, assign your next to your right child's left most child (min in your right subtree)
    			else if(node.data.compareTo(root.data) == 0 && node.right != null) {
    				BSTNode<E> current = node.right;
    				while(current.left != null) {
    					current = current.left;
    				}
    				node.next = current;
    			}
    			// else you're the last node and don't have a next ref
    			else {
    				node.next = null;
    			}
    	return node;
    }
    
    
	public Iterator iterator() {
		return new Iterator();
	}
    
	public void balance() {
		// Get an array of sorted values in the tree 
		E[] values = toArray();
		// Assign the tree's root to the result of the buildTreeUtil helper method 
		root = buildTreeUtil(values, 0, values.length, root);
		// Call assignFirst to update the tree's "first" attribute
		assignFirst();
	}
	
    public class Iterator {
		private BSTNode<E> currentNode;
	 
		public Iterator() {
			currentNode = first;
		}
 
		public boolean hasNext() {
			return currentNode != null;
		}
		
		public E next() {
			if (currentNode == null) {
                throw new NoSuchElementException();
            }
            E toReturn = currentNode.data;
            currentNode = currentNode.next;
            return toReturn;
		}
    }

    private static class BSTNode<E> {
		public E data;
        public BSTNode<E> left;
        public BSTNode<E> right;
        public BSTNode<E> parent;
        public BSTNode<E> next;
        

        public BSTNode(E data) {
            this(data, null, null, null, null);
        }

        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right, BSTNode<E> parent, BSTNode<E> next) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.next = next;
        }
    }
}
