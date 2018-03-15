package cs241_Project4;
import java.util.*;

public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	private Node firstNode;
	private int numberOfEntries;
	
	public SortedLinkedDictionary() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
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

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

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

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	private class KeyIterator implements Iterator<K>{
		private Node nextNode;
		
		private KeyIterator() {
			nextNode = firstNode;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

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
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class ValueIterator implements Iterator<V> {
		private Node nextNode;
		
		private ValueIterator() {
			nextNode = firstNode;
		}
		
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

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
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class Node {
		private K key;
		private V value;
		private Node next;
		
		public Node(K newKey, V newValue) {
			key = newKey;
			value = newValue;
		}
		
		public K getKey() {
			return key;
		}
		
		public void setKey(K newKey) {
			key = newKey;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V newValue) {
			value = newValue;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public void setNextNode(Node newNext) {
			next = newNext;
		}
	}

}
