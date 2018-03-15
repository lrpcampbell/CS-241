package cs241_Project4;
import java.util.Iterator;

public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T> {
	public Iterator<T> getIterator();
}
