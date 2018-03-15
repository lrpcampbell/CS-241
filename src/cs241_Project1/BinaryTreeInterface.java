package cs241_Project1;
/**
 * file: BinaryTreeInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 1/21/2018
 *
 * purpose: This program holds methods used for binary trees
 * 
 * @author lrpca
 *
 * @param <T>
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T> {
	/**
	 * method: setTree 
	 * purpose: this method sets the binary tree's root data
	 * 
	 * @param rootData
	 *            Data being set to the root
	 */
	public void setTree(T rootData);

	/**
	 * method: setTree 
	 * purpose: this method sets that data in the binary tree
	 * 
	 * @param rootData
	 *            Data in the root
	 * @param leftTree
	 *            Data left of the root
	 * @param rightTree
	 *            Data right of the root
	 */
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);
}
