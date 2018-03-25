package cs241_Project4;
/**
 * file: DictionaryInterface.java 
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

import java.util.Iterator;

public interface DictionaryInterface<K, V> {
	/**
	 * method: add
	 * purpose: this method adds data to the dictionary
	 * @param key Key of data being added
	 * @param value Value of data being added
	 * @return Value of data added
	 */
	public V add(K key, V value);
	
	/**
	 * method: remove
	 * purpose: this method removes data from the dictionary
	 * @param key Key of data being removed
	 * @return Value of data removed
	 */
	public V remove(K key);
	
	/**
	 * method: getValue
	 * purpose: this method gets the value of data in the dictionary
	 * @param key Key of data
	 * @return Value of data
	 */
	public V getValue(K key);
	
	/**
	 * method: contains
	 * purpose: this method finds data in the dictionary
	 * @param key Key of data being searched for
	 * @return True if key is found, otherwise false
	 */
	public boolean contains(K key);
	
	/**
	 * method: getKeyIterator
	 * purpose: this method gets the key iterator for the dictionary
	 * @return Iterator of keys
	 */
	public Iterator<K> getKeyIterator();
	
	/**
	 * method: getValueIterator
	 * purpose: this method gets the value iterator for the dictionary
	 * @return Iterator of values
	 */
	public Iterator<V> getValueIterator();
	
	/**
	 * method: isEmpty
	 * purpose: this method checks if the dictionary is empty
	 * @return True if dictionary is empty, otherwise false
	 */
	public boolean isEmpty();
	
	/**
	 * method: getSize
	 * purpose: this method gets the size of the dictionary
	 * @return Size of dictionary
	 */
	public int getSize();
	
	/**
	 * method: clear
	 * purpose: this method clears the dictionary
	 */
	public void clear();
}
