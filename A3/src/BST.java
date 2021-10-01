import java.util.*;
/**
 * This class allows the user to create a binary search tree.
 * @author Maryam Elahi, Sahir, Don and Darylle
 *
 * @param <T>
 */

public class BST<T extends Comparable<T>> implements Iterable<T>
{
	class BSTNode implements Comparable<BSTNode>
	{
		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}

		public T getData() {
			return data;
		}

		public void setData(T d) {
			data = d;
		}

		public void setLeft(BSTNode bst) {
			left = bst;
		}

		public void setRight(BSTNode r) {
			right = r;
		}

		public BSTNode getLeft() {
			return left;
		}

		public BSTNode getRight() {
			return right;
		}

		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null);
		}

		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}

	}

	// The different traversal types.
	public static final int INORDER = 0;
	public static final int PREORDER = 1;
	public static final int POSTORDER = 2;
	public static final int LEVELORDER = 3;

	private BSTNode root;
	private int size;
	private Comparator<T> sortList;

	/**
	 * Constructor for objects of class BST
	 */
	public BST() {
		root = null;
		size = 0;
		sortList = null;
	}

	/**
	 *  Constructor for objects of class BST
	 */
	public BST(Comparator<T> sortList)
	{
		this.sortList = sortList;
	}


	/**
	 * Return true if element d is present in the tree.
	 */
	public T find(T d) {
		return find(d, root);
	}
	
	/**
	 * Return true if the node exists and false if it doesn't.
	 * @param key
	 * @return true or false
	 */
	public boolean exists(T key)
	{
		BSTNode r = root;
		T find = exists(key, r);
		
		if (find == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Add element d to the tree.
	 * @param d
	 */
	public void add(T d) {
		BSTNode n = new BSTNode(d);
		if (root == null) {
			size++;
			root = n;
		} else {
			add(root, n);
		}
	}

	/**
	 * Delete element d from the tree.
	 * @param d
	 * 
	 */
	public void delete(T d)
	{
		BSTNode r = root;
		BSTNode key = new BSTNode(d);
		delete(r, key);

	}

	/**
	 * Return the height of the tree.
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Traverse tree in order.
	 * 
	 */
	public void inOrder() {
		traverse(root, INORDER);
	}

	/**
	 * Traverse tree post order.
	 * 
	 */
	public void postOrder() {
		traverse(root, POSTORDER);
	}

	/**
	 * Traverse tree pre order.
	 * 
	 */
	public void preOrder() {
		traverse(root, PREORDER);
	}

	/**
	 * Traverse tree level order.
	 * 
	 */
	public void levelOrder() {
		traverse(root, LEVELORDER);
	}
	
	/**
	 * Get the optimal height of the tree.
	 * 
	 */
	public int optimalHeight()
	{
		return optimalHeight(size);
	}

	// Private methods.

	private T find(T d, BSTNode r) {
		if (r == null)
			return null;
		int c = d.compareTo(r.getData());
		if (c == 0)
			return r.getData();
		else if (c < 0)
			return find(d, r.getLeft());
		else
			return find(d, r.getRight());
	}
	
	private T exists(T d, BSTNode r) {
		if (r == null)
			return null;
		int c = d.compareTo(r.getData());
		if (c == 0)
			return r.getData();
		else if (c < 0)
			return find(d, r.getLeft());
		else
			return find(d, r.getRight());
	}

	/* Do the actual add of node n to tree rooted at r */
	private void add(BSTNode r, BSTNode n) {
		int c = compare(n,r);
		if (c < 0) {
			// the element to be added is less than the root
			if (r.getLeft() == null) {
				// there is no left node so
				// we can add it here
				r.setLeft(n);
				size++;
			} else {
				// add it to the left sub-tree
				add(r.getLeft(), n);
			}
		} else if (c > 0) {
			// the element to be added is greater than the root
			if (r.getRight() == null) {
				// there is no right node so
				// we can add it here
				r.setRight(n);
				size++;
			} else {
				// add it to the right sub-tree
				add(r.getRight(), n);
			}
		}
	}

	/* Implement a height method. */
	private int height(BSTNode r) {
		int h = -1;
		if (r != null) {
			int rh = height(r.getRight());
			int lh = height(r.getLeft());
			h = (rh > lh ? 1 + rh : 1 + lh);
		}
		return h;
	}

	/**
	 * Traverse tree with corresponding traversal type
	 * @param type
	 *
	 */
	public void traverse(int type)
	{
		traverse(root, type);
	}

	/*
	 * Traverse the tree. travtype determines the type of traversal to perform.
	 */
	private void traverse(BSTNode r, int travType) {
		if (r != null) {
			switch (travType) {
			case INORDER:
				traverse(r.getLeft(), travType);
				visit(r);
				traverse(r.getRight(), travType);
				break;
			case PREORDER:
				visit(r);
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				break;
			case POSTORDER:
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				visit(r);
				break;
			}
		}
	}
	
	/**
	 * Return an Iterator over this list.
	 * 
	 * @returns an iterator
	 */
	private class BSTIteratorInOrder implements Iterator
	{
		public BSTIteratorInOrder()
		{
			queue.clear();
			traverse(root, INORDER);
		}
		@Override
		public boolean hasNext() 
		{
			return !queue.isEmpty();
		}

		@Override
		public Object next() 
		{

			return queue.remove();
		}


	}

	private Queue<T> queue = new LinkedList<T>();

	/**
	* Visit then add the root node to queue
	* @param r
	*/
	private void visit(BSTNode r) {
		if (r != null)
			queue.add(r.getData());
	}

	/**
	* Compare base on chosen comparator and else compares alphabectically.
	* Returns an int for sorting.
	* @param n1
	* @param n2
	*/
	private int compare(BSTNode n1, BSTNode n2)
	{
		if(sortList != null)
		{
			return sortList.compare(n1.getData(), n2.getData());
		}
		else
		{
			return n1.getData().compareTo(n2.getData());
		}
	}

	/**
	*	Returns a BSTNode value root by comparing
	*	it with BSTNode d. If the root value matches
	*	d value, it will return a null value root
	*	meaning the node has been deleted
	*   @param root
	*   @param d
	*
	*/
	private BSTNode delete(BSTNode root, BSTNode d)
	{
		if(root == null)
		{
			return null;
		}

		int c = d.compareTo(root);
		if(c < 0)
		{
			root.setLeft(delete(root.getLeft(), d));
		}
		else if (c > 0)
		{
			root.setRight(delete(root.getRight(), d));
		}
		else 
		{
			if(root.getLeft() == null && root.getRight() == null)
			{
				size--;
				return null;
			}
			else if(root.getLeft() != null && root.getRight() != null)
			{
				BSTNode pred = maximum(root.getLeft());
				root.setData(pred.getData());
				root.setLeft(delete(root.getLeft(), pred));
				size--;
			}
			else
			{
				BSTNode next; 
				if (root.getLeft() != null) 
				{
					next = root.getLeft();
				}
				else
				{
					next = root.getRight();
				}
				root = next;
				size--;
			}
		}
		return root;
	}

	/**
	*	Returns the maximum value of a
	*	subtree rooted at rootMax parameter
	*	@param  rootMax
	*/
	private BSTNode maximum(BSTNode rootMax)
	{
		if(rootMax.getRight() == null)
		{
			return rootMax;
		}
		else
		{
			return maximum(rootMax.getRight());
		}

	}
	
	/**
	*	Recursive paramater method
	*	which returns the optimal
	*	height
	*   @param  size
	*
	*/
	private int optimalHeight(int size)
	{
		double height = Math.log(size) / Math.log(2);
		if(size == 0)
		{
			return -1;
		}
		return (int) Math.round(height);
	}

	@Override
	public Iterator<T> iterator()
	{
		return new BSTIteratorInOrder();
	}

	


}