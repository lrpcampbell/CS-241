import java.util.*;

/**
 * Holds all the methods that deal with vertices
 * 
 * @author lrpca
 *
 * @param <T>
 */
class Vertex<T extends Comparable<? super T>> {
	private T name;
	private int distanceFromSource = Integer.MAX_VALUE;
	private boolean visited;
	private ArrayList<Edge> edges = new ArrayList<>();

	/**
	 * Constructor that makes a vertex with a name
	 * 
	 * @param name
	 *            Name of vertex
	 */
	public Vertex(T name) {
		this.name = name;
	}

	/**
	 * Gets name of vertex
	 * 
	 * @return name Vertex name
	 */
	public T getName() {
		return name;
	}

	/**
	 * Set the name of a vertex
	 * 
	 * @param name
	 *            New vertex name
	 */
	public void setName(T name) {
		this.name = name;
	}

	/**
	 * Gets a distance a vertex from the source
	 * 
	 * @return distanceFromSource The distance
	 */
	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	/**
	 * Sets a new distance from a vertex to its source
	 * 
	 * @param distanceFromSource
	 *            New distance
	 */
	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	/**
	 * Checks to see if a vertex has been visited
	 * 
	 * @return true if visited, otherwise false
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets a vertex to visited or unvisited
	 * 
	 * @param visited
	 *            If vertex has been visited or not
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Gets a list of edges connected to that vertex
	 * 
	 * @return edges List of edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Sets edges connected to a vertex
	 * 
	 * @param edges
	 *            List of edges
	 */
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
}
