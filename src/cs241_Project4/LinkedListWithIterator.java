package cs241_Project4;
/**
 * file: LinkedListWithIterator.java 
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

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
	private Node<T> firstNode;
	private Node<T> lastNode;
	private int numberOfEntries;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the linked list
	 */
	public LinkedListWithIterator() {
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#add(java.lang.Object)
	 */
	@Override
	public void add(T newEntry) {
		Node<T> newNode = new Node<>(newEntry);
		if(isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
		numberOfEntries++;
	}

	/**
	 * method: getNodeAt
	 * purpose: this method finds a node at a given position in the list
	 * @param givenPosition
	 * @return
	 */
	private Node<T> getNodeAt(int givenPosition) {
		assert firstNode != null && 1 <= givenPosition && givenPosition <= numberOfEntries;
		Node<T> currentNode = firstNode;
		for(int i = 1; i < givenPosition; i++) {
			currentNode = currentNode.getNextNode();
		}
		assert currentNode != null;
		return currentNode;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#add(int, java.lang.Object)
	 */
	@Override
	public void add(int newPosition, T newEntry) {
		if(newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			Node<T> newNode = new Node<T>(newEntry);
			if(isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			} else if(newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else if(newPosition == numberOfEntries + 1) {
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			} else {
				Node<T> nodeBefore = getNodeAt(newPosition + 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		} else {
			throw new IndexOutOfBoundsException("Illegal positiongiven to add operation.");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#remove(int)
	 */
	@Override
	public T remove(int givenPosition) {
		T result = null;
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			if(givenPosition == 1) {
				result = firstNode.getData();
				firstNode.getNextNode();
				if(numberOfEntries == 1) {
					lastNode = null;
				}
			} else {
				Node<T> nodeBefore = getNodeAt(givenPosition);
				Node<T> nodeToRemove = nodeBefore.getNextNode();
				Node<T> nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				result = nodeToRemove.getData();
				if(givenPosition == numberOfEntries) {
					lastNode = nodeBefore;
				}
			}
			numberOfEntries--;
			return result;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#remove(java.lang.Object)
	 */
	public void remove(T entry) {
		boolean removed = false;
		Node<T> node = new Node<>(entry);
		Node<T> currentNode = firstNode;
		if(!isEmpty()) {
			while(!removed) {
				if(currentNode.getNextNode().equals(node)) {
					Node<T> fnode = currentNode;
					currentNode = currentNode.getNextNode();
					fnode.setNextNode(currentNode.getNextNode());
					removed = true;
				} else {
					currentNode = currentNode.getNextNode();
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#clear()
	 */
	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#replace(int, java.lang.Object)
	 */
	@Override
	public T replace(int givenPosition, T newEntry) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			Node<T> desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#getEntry(int)
	 */
	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#toArray()
	 */
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		int index = 0;
		Node<T> currentNode = firstNode;
		while(index < numberOfEntries && currentNode != null) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node<T> currentNode = firstNode;
		while(!found && currentNode != null) {
			if(anEntry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#getLength()
	 */
	@Override
	public int getLength() {
		return numberOfEntries;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		boolean result;
		if(numberOfEntries == 0) {
			assert firstNode == null;
			result= true;
		} else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteratorForLinkedList();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.ListWithIteratorInterface#getIterator()
	 */
	@Override
	public Iterator<T> getIterator() {
		return iterator();
	}
	
	/**
	 * class: IteratorForLinkedList
	 * purpose: this class used for the iterators in the linked list
	 * @author lrpca
	 *
	 */
	private class IteratorForLinkedList implements Iterator<T> {
		private Node<T> nextNode;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the iterator for the list
		 */
		private IteratorForLinkedList() {
			nextNode = firstNode;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			if(hasNext()) {
				Node<T> returnNode = nextNode;
				nextNode = nextNode.getNextNode();
				return returnNode.getData();
			} else {
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported by this iterator.");
		}
	}
}
