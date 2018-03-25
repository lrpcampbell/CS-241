package cs241_Project4;
/**
 * file: SortedLinkedDictionary.java 
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

public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	private Node firstNode;
	private int numberOfEntries;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the sorted linked dictionary
	 */
	public SortedLinkedDictionary() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#add(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V add(K key, V value) {
		V result = null;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while(currentNode != null && key.compareTo(currentNode.getKey()) > 0) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		if(currentNode != null && key.equals(currentNode.getKey())) {
			result = currentNode.getValue();
			currentNode.setValue(value);
		} else {
			Node newNode = new Node(key, value);
			if(nodeBefore == null) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				newNode.setNextNode(currentNode);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#remove(java.lang.Object)
	 */
	@Override
	public V remove(K key) {
		V result = null;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		boolean found = false;
		while(currentNode != null && !found) {
			nodeBefore = currentNode;
			if(currentNode != null && key.equals(currentNode.getKey())) {
				result = currentNode.getValue();
				found = true;
				nodeBefore.setNextNode(currentNode.getNextNode());
				currentNode = null;
				numberOfEntries--;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#getValue(java.lang.Object)
	 */
	@Override
	public V getValue(K key) {
		V result = null;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while(currentNode != null && key.compareTo(currentNode.getKey()) > 0) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		if(currentNode != null && key.equals(currentNode.getKey())) {
			result = currentNode.getValue();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(K key) {
		boolean found = false;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while(currentNode != null && !found) {
			nodeBefore = currentNode;
			if(currentNode != null && key.equals(currentNode.getKey())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#getKeyIterator()
	 */
	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#getValueIterator()
	 */
	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#isEmpty()
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
	 * @see cs241_Project4.DictionaryInterface#getSize()
	 */
	@Override
	public int getSize() {
		return numberOfEntries;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.DictionaryInterface#clear()
	 */
	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	/**
	 * class: KeyIterator
	 * purpose: this class controls the methods for the key iterator
	 * @author lrpca
	 *
	 */
	private class KeyIterator implements Iterator<K>{
		private Node nextNode;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the key iterator
		 */
		private KeyIterator() {
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
		public K next() {
			K result;
			if(hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * class: ValueIterator
	 * purpose: this class controls the methods for the value iterator
	 * @author lrpca
	 *
	 */
	private class ValueIterator implements Iterator<V> {
		private Node nextNode;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the value iterator
		 */
		private ValueIterator() {
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
		public V next() {
			V result;
			if(hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * class: Node
	 * purpose: this class controls the methods for the nodes
	 * @author lrpca
	 *
	 */
	private class Node {
		private K key;
		private V value;
		private Node next;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the node
		 * @param newKey Key for node
		 * @param newValue Value for node
		 */
		public Node(K newKey, V newValue) {
			key = newKey;
			value = newValue;
		}
		
		/**
		 * method: getKey
		 * purpose: this method gets the key of a node
		 * @return Key of node
		 */
		public K getKey() {
			return key;
		}
		
		/**
		 * method: setKey
		 * purpose: this method sets the key of a node
		 * @param newKey New key for node
		 */
		public void setKey(K newKey) {
			key = newKey;
		}
		
		/**
		 * method: getValue
		 * purpose: this method gets the value of a node
		 * @return Value of node
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * method: setValue
		 * purpose: this method sets the value of a node
		 * @param newValue New value for node
		 */
		public void setValue(V newValue) {
			value = newValue;
		}
		
		/**
		 * method: getNextNode
		 * purpose: this method gets the next node of a node
		 * @return Next node of node
		 */
		public Node getNextNode() {
			return next;
		}
		
		/**
		 * method: setNextNode
		 * purpose: this method sets the next node of a node
		 * @param newNext New next node for node
		 */
		public void setNextNode(Node newNext) {
			next = newNext;
		}
	}

}
