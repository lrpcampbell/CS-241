package cs241_Project4;
import java.util.*;

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
	private Node firstNode;
	private Node lastNode;
	private int numberOfEntries;
	
	public LinkedListWithIterator() {
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}

	@Override
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if(isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
		numberOfEntries++;
	}

	private Node getNodeAt(int givenPosition) {
		assert firstNode != null && 1 <= givenPosition && givenPosition <= numberOfEntries;
		Node currentNode = firstNode;
		for(int i = 1; i < givenPosition; i++) {
			currentNode = currentNode.getNextNode();
		}
		assert currentNode != null;
		return currentNode;
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if(newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			Node newNode = new Node(newEntry);
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
				Node nodeBefore = getNodeAt(newPosition + 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		} else {
			throw new IndexOutOfBoundsException("Illegal positiongiven to add operation.");
		}
	}

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
				Node nodeBefore = getNodeAt(givenPosition);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
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

	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
		}
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while(index < numberOfEntries && currentNode != null) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while(!found && currentNode != null) {
			if(anEntry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
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
	public Iterator<T> iterator() {
		return new IteratorForLinkedList();
	}

	@Override
	public Iterator<T> getIterator() {
		return iterator();
	}
	
	private class IteratorForLinkedList implements Iterator<T> {
		private Node nextNode;
		
		private IteratorForLinkedList() {
			nextNode = firstNode;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				Node returnNode = nextNode;
				nextNode = nextNode.getNextNode();
				return returnNode.getData();
			} else {
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			}
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported by this iterator.");
		}
	}
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T newData) {
			data = newData;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T newData) {
			data = newData;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public void setNextNode(Node newNext) {
			next = newNext;
		}
	}

}
