package cs241_Project4;
import java.util.*;

public class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;
	
	public LinkedStack() {
		topNode = null;
	}
	
	@Override
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNextNode();
		
		return top;
	}

	@Override
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		} else {
			return topNode.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public void clear() {
		topNode = null;
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
