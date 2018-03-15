package cs241_Project4;

public class LinkedQueue<T> implements QueueInterface<T> {
	private Node firstNode;
	private Node lastNode;
	
	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	}
	
	@Override
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		if(isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
	}

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

	@Override
	public T getFront() {
		if(isEmpty()) {
			return null;
		} else {
			return firstNode.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T newData, Node newNext) {
			data = newData;
			next = newNext;
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
