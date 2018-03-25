package cs241_Project4;
/**
 * file: ListInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 4
 * date last modified: 3/22/2018
 *
 * purpose: This program takes in data of cities and roads between cities. Then it follows certain commands given
 * by the user the manipulate the data.
 * 
 * @author lrpca
 *
 * @param <T>
 */

public interface ListInterface<T> {
	/**
	 * method: add
	 * purpose: this method adds data to the list
	 * @param newEntry Data being added
	 */
	public void add(T newEntry);
	
	/**
	 * method: add
	 * purpose: this method adds data to the list
	 * @param newPosition Position where the data should be added
	 * @param newEntry Data being added
	 */
	public void add(int newPosition, T newEntry);
	
	/**
	 * method: remove
	 * purpose: this method removes data from the list
	 * @param givenPosition Position where the removed data is
	 * @return Data being removed
	 */
	public T remove(int givenPosition);
	
	/**
	 * method: remove
	 * purpose: this method removes data from a list
	 * @param entry Data being removed
	 */
	public void remove(T entry);
	
	/**
	 * method: clear
	 * purpose: this method clears the list
	 */
	public void clear();
	
	/**
	 * method: replace
	 * purpose: this method replaces data in the list with new data
	 * @param givenPosition Position where the data is that is being replaced
	 * @param newEntry New data that will replace the old data
	 * @return Data being replaced
	 */
	public T replace(int givenPosition, T newEntry);
	
	/**
	 * method: getEntry
	 * purpose: this method gets data in the list
	 * @param givenPosition Position of data
	 * @return Data in the list
	 */
	public T getEntry(int givenPosition);
	
	/**
	 * method: toArray
	 * purpose: this method puts the data in the list into an array
	 * @return Array of data in the list
	 */
	public T[] toArray();
	
	/**
	 * method: contains
	 * purpose: this method checks to see if data is in the list
	 * @param anEntry Data being searched for
	 * @return True if data is in the list, otherwise false
	 */
	public boolean contains(T anEntry);
	
	/**
	 * method: getLength
	 * purpose: this method gets the length of the list
	 * @return Length of list
	 */
	public int getLength();
	
	/**
	 * method: isEmpty
	 * purpose: this method checks if the list is empty
	 * @return True if the list is empty, otherwise false
	 */
	public boolean isEmpty();
}
