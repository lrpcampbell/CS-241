package cs241_Project4;
/**
 * file: StackInterface.java 
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

public interface StackInterface<T> {
	/**
	 * method: push
	 * purpose: this method pushes data onto the stack
	 * @param newEntry Data being pushed onto the stack
	 */
	public void push(T newEntry);
	
	/**
	 * method: pop
	 * purpose: this method pops data off of the stack
	 * @return Data being popped off the stack
	 */
	public T pop();
	
	/**
	 * method: peek
	 * purpose: this method sees the next piece of data in the stack
	 * @return Next piece of data on stack
	 */
	public T peek();
	
	/**
	 * method: isEmpty
	 * purpose: this method checks if the stack is empty
	 * @return True if the stack is empty, otherwise false
	 */
	public boolean isEmpty();
	
	/**
	 * method: clear
	 * purpose: this method clears the stack
	 */
	public void clear();
}
