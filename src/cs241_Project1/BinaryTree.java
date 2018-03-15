package cs241_Project1;
/**
 * file: BinaryTree.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 1/21/2018
 *
 * purpose: This program handles all the functions/ methods of a binary tree
 * 
 * @author lrpca
 *
 * @param <T>
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
	private BinaryNode<T> root;
	private Integer[] inorder = new Integer[30];
	private int count = 0;

	/**
	 * method: BinaryTree constructor 
	 * purpose: this constructor starts the root at
	 * null
	 */
	public BinaryTree() {
		root = null;
	}

	/**
	 * method: BinaryTree constructor 
	 * purpose: this constructor adds a node with
	 * data as the root node
	 * 
	 * @param rootData
	 *            Data in the root
	 */
	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	/**
	 * method: BinaryTree constructor 
	 * purpose: this constructor gives the node data
	 * a left subtree and a right subtree
	 * 
	 * @param rootData
	 *            Root data
	 * @param leftTree
	 *            Left subtree of root
	 * @param rightTree
	 *            Right subtree of root
	 */
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}

	/**
	 * method: clear 
	 * purpose: this method clears the binary tree of all nodes
	 */
	@Override
	public void clear() {
		root = null;

	}

	/**
	 * method: getHeight 
	 * purpose: this method gets height of the binary tree
	 * 
	 * @return Tree height
	 */
	@Override
	public int getHeight() {
		return root.getHeight();
	}

	/**
	 * method: getNumberOfNode 
	 * purpose: this method gets the number of nodes in a
	 * binary tree
	 * 
	 * @return Number of nodes
	 */
	@Override
	public int getNumberOfNode() {
		return root.getNumberOfNodes();
	}

	/**
	 * method: getRootData 
	 * purpose: this method checks if the tree is empty, then
	 * returns the root data
	 * 
	 * @return Root data
	 */
	@Override
	public T getRootData() {
		if (isEmpty()) {
			return null;
		} else {
			return root.getData();
		}
	}

	/**
	 * method: getRootNode 
	 * purpose: this method gets the root node of a binary tree
	 * 
	 * @return Root node
	 */
	protected BinaryNode<T> getRootNode() {
		return root;
	}

	/**
	 * method: initialize 
	 * purpose: this method sets the global variables count and
	 * inorder to 0
	 */
	private void initialize() {
		count = 0;
		for (int i = 0; i < inorder.length; i++) {
			inorder[i] = null;
		}
	}

	/**
	 * method: inorderTraverse 
	 * purpose: this method prints out the inorder traversal
	 * of the binary tree
	 */
	public void inorderTraverse() {
		initialize();
		Integer[] inorder = inorderTraverse(root);
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] != null) {
				System.out.print(inorder[i] + " ");
			}
		}
	}

	/**
	 * method: inorderTraverse 
	 * purpose: this method puts the binary tree in the
	 * right inorder traversal
	 * 
	 * @param node
	 *            Root node
	 * @return An Integer array of the node traversed inorder
	 */
	private Integer[] inorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			inorderTraverse(node.getLeftChild());
			inorder[count++] = (Integer) node.getData();
			inorderTraverse(node.getRightChild());
		}
		return inorder;
	}

	/**
	 * method: inorderTraverseArray 
	 * purpose: this method sends data into the
	 * recursive inorderTraverse method
	 * 
	 * @return Integer array of binary tree nodes traversed inorder
	 */
	public Integer[] inorderTraverseArray() {
		initialize();
		return inorderTraverse(root);
	}

	/**
	 * method: isEmpty 
	 * purpose: this method checks if the binary tree is empty of
	 * not
	 * 
	 * @return true if binary tree is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		boolean found = false;
		if (root.getData() == null && root.getNumberOfNodes() == 1) {
			found = true;
		} else {
			found = false;
		}
		return found;
	}

	/**
	 * method: postorderTraverse 
	 * purpose: this method prints the postorder traversal
	 * of the binary tree
	 */
	public void postorderTraverse() {
		postorderTraverse(root);
	}

	/**
	 * method: postorderTraverse 
	 * purpose: this method traverses the binary tree
	 * nodes in postorder
	 * 
	 * @param node
	 *            Root node
	 */
	private void postorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			postorderTraverse(node.getLeftChild());
			postorderTraverse(node.getRightChild());
			System.out.print(node.getData() + " ");
		}

	}

	/**
	 * method: preorderTraverse 
	 * purpose: this method prints the preorder traversal
	 * of the binary tree
	 */
	public void preorderTraverse() {
		preorderTraverse(root);
	}

	/**
	 * method: preorderTraverse 
	 * purpose: this method traverses the binary tree nodes
	 * in preorder
	 * 
	 * @param node
	 */
	private void preorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preorderTraverse(node.getLeftChild());
			preorderTraverse(node.getRightChild());
		}

	}

	/**
	 * method: privateSetTree 
	 * purpose: this method sets binary tree nodes
	 * 
	 * @param rootData
	 *            Data in root
	 * @param leftTree
	 *            Left side of the root
	 * @param rightTree
	 *            Right side of the root
	 */
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<>(rootData);
		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}
		if (leftTree != null && leftTree != this) {
			leftTree.clear();
		}
		if (rightTree != null && rightTree != this) {
			rightTree.clear();
		}
	}

	/**
	 * method: setRootNode 
	 * purpose: this method sets a new root node
	 * 
	 * @param rootNode
	 *            Node replacing root
	 */
	protected void setRootNode(BinaryNode<T> rootNode) {
		root = rootNode;
	}

	/**
	 * method: setRootNode 
	 * purpose: this method sets the data in the root node
	 * 
	 * @param rootData
	 */
	protected void setRootNode(T rootData) {
		root.setData(rootData);
	}

	/**
	 * method: setTree 
	 * purpose: this method adds a node to the root with data
	 */
	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	/**
	 * method: setTree 
	 * purpose: this method sets the binary tree
	 * 
	 * @param rootData
	 *            Data in root
	 * @param leftTree
	 *            Left side of the root
	 * @param rightTree
	 *            Right side of the root
	 */
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	}

}
