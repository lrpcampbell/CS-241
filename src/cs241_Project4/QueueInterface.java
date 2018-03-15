package cs241_Project4;

public interface QueueInterface<T> {
	public void enqueue(T newEntry);
	public T dequeue();
	public T getFront();
	public boolean isEmpty();
	public void clear();
}
