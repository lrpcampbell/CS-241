package cs241_Project4;
/**
 * file: LinkedStack.java 
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

import java.util.*;

public class LinkedStack<T> implements StackInterface<T> {
	private Node<T> topNode;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the linked stack
	 */
	public LinkedStack() {
		topNode = null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.StackInterface#push(java.lang.Object)
	 */
	@Override
	public void push(T newEntry) {
		Node<T> newNode = new Node<>(newEntry, topNode);
		topNode = newNode;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.StackInterface#pop()
	 */
	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNextNode();
		
		return top;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.StackInterface#peek()
	 */
	@Override
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		} else {
			return topNode.getData();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.StackInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.StackInterface#clear()
	 */
	@Override
	public void clear() {
		topNode = null;
	}
}
