/**
 * Holds all the methods used to manipulate data in a heap
 * 
 * @author lrpca
 *
 * @param <T>
 */
public interface MinHeapInterface<T extends Comparable<? super T>> {
	/**
	 * Adds a new piece of data to the heap
	 * 
	 * @param newEntry
	 *            Data being added
	 */
	public void add(T newEntry);

	/**
	 * Clears all data from the heap
	 */
	public void clear();

	/**
	 * Gets the minimum's data
	 * 
	 * @return Minimum's data
	 */
	public T getMin();

	/**
	 * Gets the size of the heap
	 * 
	 * @return Heap size
	 */
	public int getSize();

	/**
	 * Checks to see if the heap is empty
	 * 
	 * @return True if empty, otherwise false
	 */
	public boolean isEmpty();

	/**
	 * Removes the minimum from the heap
	 * 
	 * @return Data being removed
	 */
	public T removeMin();
}
