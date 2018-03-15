package cs241_Project2;

/**
 * file: Project2.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 2
 * date last modified: 1/30/2018
 *
 * purpose: This program handles all the functions of each method in the MaxHeap
 * 
 * @author lrpca
 *
 */
import java.util.Arrays;

class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {
	private T[] heap;
	private int lastIndex;
	private boolean initialized = false;
	private int swap;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	/**
	 * method: constructor
	 * purpose: sets the default capacity
	 */
	public MaxHeap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * method: constructor
	 * purpose: sets the heap to the proper capacity
	 * 
	 * @param initialCapacity
	 *            Given capacity
	 */
	public MaxHeap(int initialCapacity) {
		if (initialCapacity < DEFAULT_CAPACITY) {
			initialCapacity = DEFAULT_CAPACITY;
		} else {
			checkCapacity(initialCapacity);
		}
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}

	/**
	 * method: constructor
	 * purpose: makes a heap out of an array
	 * 
	 * @param entries
	 *            Array of data to be put into a heap
	 */
	public MaxHeap(T[] entries) {
		this(entries.length);
		assert initialized = true;
		swap = 0;
		for (int i = 0; i < entries.length; i++) {
			heap[i + 1] = entries[i];
		}
		lastIndex = entries.length;
		for (int i = lastIndex / 2; i > 0; i--) {
			reheap(i);
		}
	}

	/**
	 * method: checkCapacity
	 * purpose: makes sure the capacity doesn't exceed the maximum capacity
	 * 
	 * @param initialCapacity
	 *            Given capacity
	 */
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > DEFAULT_CAPACITY && initialCapacity < MAX_CAPACITY) {
			initialCapacity = MAX_CAPACITY;
		}
		if (initialCapacity > MAX_CAPACITY) {
			System.out.print("Initial capacity is too big.");
		}

	}

	/**
	 * method: getData
	 * purpose: gets the data in a certain place in the heap
	 * 
	 * @param number
	 *            Place where the data is in the heap
	 * @return Data in the place in the heap
	 */
	public T getData(int number) {
		return heap[number];
	}

	/**
	 * method: add
	 * purpose: adds a new entry into the heap
	 * 
	 * @param newEntry
	 *            Data being added
	 */
	@Override
	public void add(T newEntry) {
		swap = 0;
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		while (parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) > 0) {
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			swap++;
		}
		heap[newIndex] = newEntry;
		lastIndex++;
	}

	/**
	 * method: getSwap
	 * purpose: gets the number of swaps
	 * 
	 * @return Swap number
	 */
	public int getSwap() {
		return swap;
	}

	/**
	 * method: removeMax
	 * purpose: remove the maximum from the heap
	 * 
	 * @return root Removed data
	 */
	@Override
	public T removeMax() {
		T root = null;
		swap = 0;
		if (!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		return root;
	}

	/**
	 * method: reheap
	 * purpose: the optimal way to organize a heap
	 * 
	 * @param rootIndex
	 *            The starting point in the heap
	 */
	private void reheap(int rootIndex) {
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		while (!done && leftChildIndex <= lastIndex) {
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			if (rightChildIndex <= lastIndex && heap[rightChildIndex].compareTo(heap[leftChildIndex]) > 0) {
				largerChildIndex = rightChildIndex;
			}
			if (orphan.compareTo(heap[largerChildIndex]) < 0) {
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
				swap++;
			} else {
				done = true;
			}
		}
		heap[rootIndex] = orphan;
	}

	/**
	 * method: getMax
	 * purpose: gets the maximum in the heap
	 * 
	 * @return root Heap maximum
	 */
	@Override
	public T getMax() {
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
		}
		return root;
	}

	/**
	 * method: isEmpty
	 * purpose: checks if heap is empty
	 * 
	 * @return True if empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return lastIndex < 1;
	}

	/**
	 * method: getSize
	 * purpose: gets the size of the heap
	 * 
	 * @return lastIndex Heap size
	 */
	@Override
	public int getSize() {
		return lastIndex;
	}

	/**
	 * method: clear
	 * purpose: clears all data in the heap
	 */
	@Override
	public void clear() {
		while (lastIndex > -1) {
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
	}

}
