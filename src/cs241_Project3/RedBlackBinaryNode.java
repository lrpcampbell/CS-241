package cs241_Project3;
import cs241_Project1.BinaryNode;

/**
 * file: RedBlackBinaryNode.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 3 
 * date last modified: 2/19/2018
 *
 * purpose: This program holds all the methods for Red-black nodes
 * 
 * @author lrpca
 *
 */

public class RedBlackBinaryNode<T> extends BinaryNode<T> {
	// If color is true, then red
	// If color is false, then black
	public boolean color;

	/**
	 * method: constructor 
	 * purpose: this constructor set up the node
	 */
	public RedBlackBinaryNode() {
		this(null, null, null, false);
	}

	/**
	 * method: constructor 
	 * purpose: this constructor sets the data for the node
	 * 
	 * @param dataPortion
	 *            Node data
	 */
	public RedBlackBinaryNode(T dataPortion) {
		this(dataPortion, null, null, false);
	}

	/**
	 * method: constructor 
	 * purpose: this constructor sets the data, left child,
	 * right child and color of a node
	 * 
	 * @param dataPortion
	 *            Node data
	 * @param newLeftChild
	 *            Node left child
	 * @param newRightChild
	 *            Node right child
	 * @param newColor
	 *            Node color
	 */
	public RedBlackBinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild,
			boolean newColor) {
		data = dataPortion;
		leftChild = newLeftChild;
		rightChild = newRightChild;
		color = newColor;
	}

	/**
	 * method: getColor 
	 * purpose: this method gets the color of a node
	 * 
	 * @return Node color
	 */
	public boolean getColor() {
		return color;
	}

	/**
	 * method: setColor 
	 * purpose: this method sets the color of a node
	 * 
	 * @param newColor
	 *            Node color
	 */
	public void setColor(boolean newColor) {
		color = newColor;
	}
}
