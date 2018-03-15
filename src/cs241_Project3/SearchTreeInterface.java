package cs241_Project3;
/**
 * file: SearchTreeInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 1/21/2018
 *
 * purpose: This program holds methods used to get, add or remove information
 * from a search tree
 * 
 * @author lrpca
 *
 * @param <T>
 */
public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T> {
	/**
	 * method: contains 
	 * purpose: this method determines whether data is in the
	 * search tree
	 * 
	 * @param entry
	 *            Data looked for in the search tree
	 * @return true if data is found, otherwise false
	 */
	public boolean contains(T entry);

	/**
	 * method: delete 
	 * purpose: this method removes data from a search tree
	 * 
	 * @param entry
	 *            Data being removed
	 * @return Data once it is removed
	 */
	public T delete(T entry);

	/**
	 * method: getEntry 
	 * purpose: this method gets data within a search tree
	 * 
	 * @param entry
	 *            Data looked for in search tree
	 * @return Data if found
	 */
	public T getEntry(T entry);

	/**
	 * method: insert 
	 * purpose: this method adds data in the search tree
	 * 
	 * @param newEntry
	 *            Data being added
	 * @return Data once it is added to the search tree
	 */
	public T insert(T newEntry);
}
