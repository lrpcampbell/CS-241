package cs241_Project4;
/**
 * file: LinkedQueue.java 
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

public class LinkedQueue<T> implements QueueInterface<T> {
	private Node<T> firstNode;
	private Node<T> lastNode;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the linked queue
	 */
	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.QueueInterface#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<>(newEntry, null);
		if(isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.QueueInterface#dequeue()
	 */
	@Override
	public T dequeue() {
		T front = getFront();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		if(firstNode == null) {
			lastNode = null;
		}
		return front;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.QueueInterface#getFront()
	 */
	@Override
	public T getFront() {
		if(isEmpty()) {
			return null;
		} else {
			return firstNode.getData();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.QueueInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.QueueInterface#clear()
	 */
	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
}
