package cs241_Project4;
/**
 * file: BasicGraphInterface.java 
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

public interface BasicGraphInterface<T> {
	/**
	 * method: addVertex
	 * purpose: this method adds a vertex to this graph
	 * @param vertexLabel Object that labels the new vertex and distance from the labels of the current vertices
	 * @return True if vertex is added, otherwise false
	 */
	public boolean addVertex(T vertexLabel);
	
	/**
	 * method: addEdge
	 * purpose: this method adds an edge to the graph with a weight from one vertex to another
	 * @param begin Starting vertex
	 * @param end Ending vertex
	 * @param edgeWeight Weight of edge
	 * @return True if edge is added, otherwise false
	 */
	public boolean addEdge(T begin, T end, double edgeWeight);
	
	/**
	 * method: addEdge
	 * purpose: this method adds an edge to the graph from one vertex to another
	 * @param begin Starting vertex
	 * @param end Ending vertex
	 * @return True if edge is added, otherwise false
	 */
	public boolean addEdge(T begin, T end);
	
	/**
	 * method: hasEdge
	 * purpose: this method checks is two vertices are connected with an edge
	 * @param begin Starting vertex
	 * @param end Ending vertex
	 * @return True if there is an edge, otherwise false
	 */
	public boolean hasEdge(T begin, T end);
	
	/**
	 * method: isEmpty
	 * purpose: this method determines whether the graph is empty
	 * @return True if graph is empty, otherwise false
	 */
	public boolean isEmpty();
	
	/**
	 * method: getNumberofVertices
	 * purpose: this method finds the number of vertices in the graph
	 * @return The number of vertices in the graph
	 */
	public int getNumberOfVertices();
	
	/**
	 * method: getNumberOfEdges
	 * purpose: this method finds the number of edges in the graph
	 * @return The number of edges in the graph
	 */
	public int getNumberOfEdges();
	
	/**
	 * method: clear
	 * purpose: this method clears the graph
	 */
	public void clear();
}
