package cs241_Project3;
/**
 * file: TreeInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 1/21/2018
 *
 * purpose: This program holds methods that are for gather information about a
 * tree
 * 
 * @author lrpca
 *
 * @param <T>
 */
public interface TreeInterface<T> {
	/**
	 * method: clear 
	 * purpose: this method clears nodes from tree
	 */
	public void clear();

	/**
	 * method: getHeight 
	 * purpose: this method gets the height of the tree
	 * 
	 * @return tree height
	 */
	public int getHeight();

	/**
	 * method: getNumberOfNode 
	 * purpose: this method gets the number of nodes in the tree
	 * 
	 * @return number of nodes
	 */
	public int getNumberOfNode();

	/**
	 * method: getRootData 
	 * purpose: this method gets the root data
	 * 
	 * @return root data
	 */
	public T getRootData();

	/**
	 * method: isEmpty 
	 * purpose: this method determines whether the tree is empty or not
	 * 
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty();
}
