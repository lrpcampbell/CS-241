/**
 * This holds all the methods that deal with edges
 * 
 * @author lrpca
 *
 * @param <T>
 */
class Edge<T extends Comparable<? super T>> {
	private T from;
	private T to;
	private int weight;

	/**
	 * Constructor to create an edge
	 * 
	 * @param from
	 *            The starting point
	 * @param to
	 *            The ending point
	 * @param weight
	 *            The distance between points
	 */
	public Edge(T from, T to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	/**
	 * Finds where the edge starts
	 * 
	 * @return from The starting point
	 */
	public T getFrom() {
		return from;
	}

	/**
	 * Finds where the edge ends
	 * 
	 * @return to The ending point
	 */
	public T getTo() {
		return to;
	}

	/**
	 * Finds the distance of the edge
	 * 
	 * @return weight The distance
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Finds the indexes of the neighbors
	 * 
	 * @param index
	 *            The starting point
	 * @return from If the index equals it, or to if the index equals that
	 */
	public int getNeighborIndex(int index) {
		if ((int) this.from == index) {
			return (int) this.to;
		} else {
			return (int) this.from;
		}
	}
}
