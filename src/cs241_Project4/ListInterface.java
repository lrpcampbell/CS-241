package cs241_Project4;

public interface ListInterface<T> {
	public void add(T newEntry);
	public void add(int newPosition, T newEntry);
	public T remove(int givenPosition);
	public void remove(T entry);
	public void clear();
	public T replace(int givenPosition, T newEntry);
	public T getEntry(int givenPosition);
	public T[] toArray();
	public boolean contains(T anEntry);
	public int getLength();
	public boolean isEmpty();
}
