package cs241_Project1;
/**
 * file: BinaryNode.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 1/21/2018
 *
 * purpose: This program creates nodes for a binary tree
 * 
 * @author lrpca
 *
 * @param <T>
 */
public class BinaryNode<T> {
	public T data;
	public BinaryNode<T> leftChild;
	public BinaryNode<T> rightChild;

	/**
	 * method: BinaryNode constructor 
	 * purpose: this constructor starts a binary node
	 * to null
	 */
	public BinaryNode() {
		this(null);
	}

	/**
	 * method: BinaryNode constructor 
	 * purpose: this constructor sets data to the
	 * node
	 * 
	 * @param dataPortion
	 *            Node data
	 */
	public BinaryNode(T dataPortion) {
		this(dataPortion, null, null);
	}

	/**
	 * method: BinaryNode constructor 
	 * purpose: this constructor sets data, the left
	 * child and the right child to the node
	 * 
	 * @param dataPortion
	 *            Node data
	 * @param newLeftChild
	 *            Node's left child
	 * @param newRightChild
	 *            Node's right child
	 */
	public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild) {
		data = dataPortion;
		leftChild = newLeftChild;
		rightChild = newRightChild;
	}

	/**
	 * method: copy 
	 * purpose: this method puts a copy of a Node inside the binary
	 * tree
	 * 
	 * @return Root node with copies of data as its left and right children
	 */
	public BinaryNode<T> copy() {
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		if (leftChild != null) {
			newRoot.setLeftChild(leftChild.copy());
		}
		if (rightChild != null) {
			newRoot.setRightChild(rightChild.copy());
		}
		return newRoot;
	}

	/**
	 * method: getData 
	 * purpose: this method gets data from a node
	 * 
	 * @return Node data
	 */
	public T getData() {
		return data;
	}

	/**
	 * method: getHeight 
	 * purpose: this method gets the height of the binary tree
	 * 
	 * @return Tree height
	 */
	public int getHeight() {
		return getHeight(this);
	}

	/**
	 * method: getHeight 
	 * purpose: this method gets height of the binary tree
	 * recursively
	 * 
	 * @param node
	 *            The node at the starting level
	 * @return Tree height
	 */
	private int getHeight(BinaryNode<T> node) {
		int height = 0;
		if (node != null) {
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		}
		return height;
	}

	/**
	 * method: getLeftChild 
	 * purpose: this method gets the left child of a particular
	 * node
	 * 
	 * @return left child
	 */
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * method: getNumerOfNodes 
	 * purpose: this method gets the total number of nodes
	 * in the binary tree
	 * 
	 * @return Number of nodes
	 */
	public int getNumberOfNodes() {
		int leftNumber = 0;
		int rightNumber = 0;

		if (leftChild != null) {
			leftNumber = leftChild.getNumberOfNodes();
		}
		if (rightChild != null) {
			rightNumber = rightChild.getNumberOfNodes();
		}
		return 1 + leftNumber + rightNumber;
	}

	/**
	 * method: getRightChild 
	 * purpose: this method gets the right child of a
	 * particular node
	 * 
	 * @return Right child
	 */
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * method: hasLeftChild 
	 * purpose: this method checks if a node has a left child
	 * 
	 * @return true if left child, otherwise false
	 */
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	/**
	 * method: hasRightChild 
	 * purpose: this method checks if a node has a right child
	 * 
	 * @return true if right child, otherwise false
	 */
	public boolean hasRightChild() {
		return rightChild != null;
	}

	/**
	 * method: isLeaf 
	 * purpose: this method checks if the node is a leaf node
	 * 
	 * @return true if leaf node, otherwise false
	 */
	public boolean isLeaf() {
		return leftChild == null || rightChild == null;
	}

	/**
	 * method: setData 
	 * purpose: this method sets new data to a node
	 * 
	 * @param newData
	 *            Data that will become the new data of a node
	 */
	public void setData(T newData) {
		data = newData;
	}

	/**
	 * method: setLeftChild 
	 * purpose: this method sets a node to be the new left
	 * child of a node
	 * 
	 * @param newLeftChild
	 *            The node that will be the new left child
	 */
	public void setLeftChild(BinaryNode<T> newLeftChild) {
		leftChild = newLeftChild;
	}

	/**
	 * method: setRight 
	 * purpose: this method sets a node to be the new right child
	 * of a node
	 * 
	 * @param newRightChild
	 *            The node that will be the new right child
	 */
	public void setRightChild(BinaryNode<T> newRightChild) {
		rightChild = newRightChild;
	}
}
