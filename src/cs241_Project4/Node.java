package cs241_Project4;
/**
 * file: Node.java 
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

public class Node<T> {
	private T data;
	private Node<T> next;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the node with data
	 * @param newData Data for node
	 * @param newNext Next node for node
	 */
	public Node(T newData, Node<T> newNext) {
		data = newData;
		next = newNext;
	}
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the node with data
	 * @param newData Data for node
	 */
	public Node(T newData) {
		data = newData;
	}
	
	/**
	 * method: getData
	 * purpose: this method gets the data of the node
	 * @return Data of node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * method: setData
	 * purpose: this method sets the data of a node
	 * @param newData New data for the node
	 */
	public void setData(T newData) {
		data = newData;
	}
	
	/**
	 * method: getNextNode
	 * purpose: this method gets the next node
	 * @return Next node of a node
	 */
	public Node<T> getNextNode() {
		return next;
	}
	
	/**
	 * method: setNextNode
	 * purpose: this method sets the next node of a node
	 * @param newNext New next node for node
	 */
	public void setNextNode(Node<T> newNext) {
		next = newNext;
	}
}
