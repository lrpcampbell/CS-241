package cs241_Project2;

/**
 * file: Project2.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 2
 * date last modified: 1/30/2018
 *
 * purpose: This program holds all the methods used to manipulate data in a heap
 * 
 * @author lrpca
 *
 */
interface MaxHeapInterface<T extends Comparable<? super T>> {
	/**
	 * method: add
	 * purpose: adds a new piece of data to the heap
	 * 
	 * @param newEntry
	 *            Data being added
	 */
	public void add(T newEntry);

	/**
	 * method: clear
	 * purpose: clears all data from the heap
	 */
	public void clear();

	/**
	 * method: getMax
	 * purpose: gets the maximum's data
	 * 
	 * @return Maximum's data
	 */
	public T getMax();

	/**
	 * method: getSize
	 * purpose: gets the size of the heap
	 * 
	 * @return Heap size
	 */
	public int getSize();

	/**
	 * method: isEmpty
	 * purpose: checks to see if the heap is empty
	 * 
	 * @return True if empty, otherwise false
	 */
	public boolean isEmpty();

	/**
	 * method: removeMax
	 * purpose: removes the maximum from the heap
	 * 
	 * @return Data being removed
	 */
	public T removeMax();
}
