package cs241_Project4;
/**
 * file: VertexInterface.java 
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

import java.util.Iterator;

public interface VertexInterface<T> {
	/**
	 * method: getLabel
	 * purpose: this method gets the label of a vertex
	 * @return Vertex label
	 */
	public T getLabel();
	
	/**
	 * method: visit
	 * purpose: this method sets the vertex to visited
	 */
	public void visit();
	
	/**
	 * method: unvisit
	 * purpose: this method sets the vertex to unvisited
	 */
	public void unvist();
	
	/**
	 * method: isVisited
	 * purpose: this method checks if the vertex is visited
	 * @return True if the vertex is visited, otherwise false
	 */
	public boolean isVisited();
	
	/**
	 * method: connect
	 * purpose: this method connects two vertices
	 * @param endVertex Vertex being connected
	 * @param edgeWeight Weight of edge
	 * @return True if vertex was connected, otherwise false
	 */
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight);
	
	/**
	 * method: connect
	 * purpose: this method connects two vertices
	 * @param endVertex Vertex being connected
	 * @return True if vertex was connected, otherwise false
	 */
	public boolean connect(VertexInterface<T> endVertex);
	
	/**
	 * method: disconnect
	 * purpose: this method disconnects two vertices
	 * @param originVertex Starting vertex
	 * @param endVertex Ending vertex
	 * @return True if vertices were disconnected, otherwise false
	 */
	public boolean disconnect(VertexInterface<T> originVertex, VertexInterface<T> endVertex);
	
	/**
	 * method: getNeighborIterator
	 * purpose: this method gets the neighbor iterator
	 * @return Iterator of vertex's neighbors
	 */
	public Iterator<VertexInterface<T>> getNeighborIterator();
	
	/**
	 * method: getWeightIterator
	 * purpose: this method gets the weight iterator
	 * @return Iterator of edge weights
	 */
	public Iterator<Double> getWeightIterator();
	
	/**
	 * method: hasNeighbor
	 * purpose: this method checks if a vertex has a neighbor
	 * @return True if there is a neighbor, otherwise false
	 */
	public boolean hasNeighbor();
	
	/**
	 * method: getUnvisitedNeighbor
	 * purpose: this method gets the unvisited neighbor
	 * @return Unvisited neighbor
	 */
	public VertexInterface<T> getUnvisitedNeighbor();
	
	/**
	 * method: setPredecessor
	 * purpose: this method sets the predecessor
	 * @param predecessor New predecessor for vertex
	 */
	public void setPredecessor(VertexInterface<T> predecessor);
	
	/**
	 * method: getPredecessor
	 * purpose: this method gets the predecessor
	 * @return Vertex's predecessor
	 */
	public VertexInterface<T> getPredecessor();
	
	/**
	 * method: hasPredecessor
	 * purpose: this method checks if there is a predecessor
	 * @return True if there is a predecessor, otherwise false
	 */
	public boolean hasPredecessor();
	
	/**
	 * method: setCost
	 * purpose: this method sets the cost of an edge
	 * @param newCost New cost for edge
	 */
	public void setCost(double newCost);
	
	/**
	 * method: getCost
	 * purpose: this method gets the cost of an edge
	 * @return Edge cost
	 */
	public double getCost();
}
