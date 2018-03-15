/**
 * Handles all the functions of each method in the MinHeap
 * 
 * @author lrpca
 *
 * @param <T>
 */
public class MinHeap<T extends Comparable<? super T>> implements MinHeapInterface<T> {
	private T[] heap;
	private int lastIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	
	/**
	 * Constructor that sets the default capacity
	 */
	public MinHeap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor that sets the heap to the proper capacity
	 * 
	 * @param initialCapacity
	 *            Given capacity
	 */
	public MinHeap(int initialCapacity) {
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
	 * Constructor that makes a heap out of an array
	 * 
	 * @param entries
	 *            Array of data to be put into a heap
	 */
	public MinHeap(T[] entries) {
		this(entries.length);
		assert initialized = true;
		for (int i = 0; i < entries.length; i++) {
			heap[i + 1] = entries[i];
		}
		lastIndex = entries.length;
		for (int i = lastIndex / 2; i > 0; i--) {
			reheap(i);
		}
	}
	
	/**
	 * Makes sure the capacity doesn't exceed the maximum capacity
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
	 * The optimal way to organize a heap
	 * 
	 * @param rootIndex
	 *            The starting point in the heap
	 */
	private void reheap(int rootIndex) {
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		while (!done && leftChildIndex <= lastIndex) {
			int smallerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			if (rightChildIndex <= lastIndex && heap[rightChildIndex].compareTo(heap[leftChildIndex]) < 0) {
				smallerChildIndex = rightChildIndex;
			}
			if (orphan.compareTo(heap[smallerChildIndex]) > 0) {
				heap[rootIndex] = heap[smallerChildIndex];
				rootIndex = smallerChildIndex;
				leftChildIndex = 2 * rootIndex;
			} else {
				done = true;
			}
		}
		heap[rootIndex] = orphan;
		
	}
	
	/**
	 * Gets the data in a certain place in the heap
	 * 
	 * @param number
	 *            Place where the data is in the heap
	 * @return Data in the place in the heap
	 */
	public T getData(int number) {
		return heap[number];
	}

	/**
	 * Adds a new entry into the heap
	 * 
	 * @param newEntry
	 *            Data being added
	 */
	@Override
	public void add(T newEntry) {
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		while(parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) < 0) {
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		}
		heap[newIndex] = newEntry;
		lastIndex++;
	}

	/**
	 * Clears all data in the heap
	 */
	@Override
	public void clear() {
		while (lastIndex > -1) {
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
		
	}

	/**
	 * Gets the minimum in the heap
	 * 
	 * @return root Heap minimum
	 */
	@Override
	public T getMin() {
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
		}
		return root;
	}

	/**
	 * Gets the size of the heap
	 * 
	 * @return lastIndex Heap size
	 */
	@Override
	public int getSize() {
		return lastIndex;
	}

	/**
	 * Checks if heap is empty
	 * 
	 * @return True if empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return lastIndex < 1;
	}

	/**
	 * Remove the minimum from the heap
	 * 
	 * @return root Removed data
	 */
	@Override
	public T removeMin() {
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		return root;
	}

}
