package cs241_Project4;
/**
 * file: GraphAlgorithmsInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 4
 * date last modified: 3/22/2018
 *
 * purpose: This program takes in data of cities and roads between cities. Then it follows certain commands given
 * by the user the manipulate the data.
 * 
 * @author lrpca
 *
 * @param <T>
 */

public interface GraphAlgorithmsInterface<T> {
	/**
	 * method: getBreadthFirstTraversal
	 * purpose: this method finds the traversal using breadth first search
	 * @param origin Starting point
	 * @return Queue of traversal
	 */
	public QueueInterface<T> getBreathFirstTraversal(T origin);
	
	/**
	 * method: getDepthFirstSearch
	 * purpose: this method finds the traversal using depth first search
	 * @param origin Starting point
	 * @return Queue of traversal
	 */
	public QueueInterface<T> getDepthFirstTravsersal(T origin);
	
	/**
	 * method: getTopologicalOrder
	 * purpose: this method finds the topological order
	 * @return Stack of order
	 */
	public StackInterface<T> getTopologicalOrder();
	
	/**
	 * method: getShortestPath
	 * purpose: this method finds the shortest path in the graph from one vertex to another
	 * @param begin Starting point
	 * @param end Ending point
	 * @param path Stack of path
	 * @return Length of shortest path
	 */
	public int getShortestPath(T begin, T end, StackInterface<T> path);
	
	/**
	 * method: getCheapestPath
	 * purpose: this method finds the cheapest path in the graph from one vertex to another
	 * @param begin Starting point
	 * @param end Ending point
	 * @param path Stack of path
	 * @return Cost of cheapest path
	 */
	public double getCheapestPath(T begin, T end, StackInterface<T> path);
}
