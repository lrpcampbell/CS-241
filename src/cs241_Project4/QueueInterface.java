package cs241_Project4;
/**
 * file: QueueInterface.java 
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

public interface QueueInterface<T> {
	/**
	 * method: enqueue
	 * purpose: this method adds data to the queue
	 * @param newEntry New data for queue
	 */
	public void enqueue(T newEntry);
	
	/**
	 * method: dequeue
	 * purpose: this method removes data from the queue
	 * @return Data being removed
	 */
	public T dequeue();
	
	/**
	 * method: getFront
	 * purpose: this method gets the data at the front of the queue
	 * @return Front data
	 */
	public T getFront();
	
	/**
	 * method: isEmpty
	 * purpose: this method checks if the queue is empty
	 * @return True if the queue is empty, otherwise false
	 */
	public boolean isEmpty();
	
	/**
	 * method: clear
	 * purpose: this method clears the queue
	 */
	public void clear();
}
