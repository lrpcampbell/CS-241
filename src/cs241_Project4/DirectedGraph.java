package cs241_Project4;
/**
 * file: DirectedGraph.java 
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

import java.util.*;
import java.io.*;


public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {

	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	/**
	 * method: constructor
	 * purpose: sets the new directed graph
	 */
	public DirectedGraph() {
		vertices = new SortedLinkedDictionary<>();
		edgeCount = 0;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#addVertex(java.lang.Object)
	 */
	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null;
	}

	/* 
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#addEdge(java.lang.Object, java.lang.Object, double)
	 */
	public boolean addEdge(T begin, T end, double edgeWeight) {
		boolean result = false;
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if(beginVertex != null && endVertex != null) {
			result = beginVertex.connect(endVertex, edgeWeight);
		}
		if(result) {
			edgeCount++;
		}
		return result;
	}

	/* 
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#addEdge(java.lang.Object, java.lang.Object)
	 */
	public boolean addEdge(T begin, T end) {
		return addEdge(begin, end, 0);
	}

	/* 
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#hasEdge(java.lang.Object, java.lang.Object)
	 */
	public boolean hasEdge(T begin, T end) {
		boolean found = false;
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if(beginVertex != null && endVertex != null) {
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			while(!found && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(endVertex.equals(nextNeighbor)) {
					found = true;
				}
			}
		}
		return found;
	}
	
	/**
	 * method: removeEdge
	 * purpose: this method removes an edge from the directed graph
	 * @param begin Starting point
	 * @param end Ending point
	 * @return True if edge is removed. otherwise false
	 */
	public boolean removeEdge(T begin, T end) {
		boolean result = false;
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if(beginVertex != null && endVertex != null) {
			result = beginVertex.disconnect(beginVertex, endVertex);
		}
		if(result) {
			edgeCount++;
		}
		return result;
	}

	/* 
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#isEmpty()
	 */
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#getNumberOfVertices()
	 */
	public int getNumberOfVertices() {
		return vertices.getSize();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#getNumberOfEdges()
	 */
	public int getNumberOfEdges() {
		return edgeCount;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.BasicGraphInterface#clear()
	 */
	public void clear() {
		vertices.clear();
		edgeCount = 0;
	}
	
	/**
	 * method: resetVertices
	 * purpose: this method resets the vertices iterator
	 */
	protected void resetVertices() {
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while(vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next();
			nextVertex.unvist();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.GraphAlgorithmsInterface#getBreathFirstTraversal(java.lang.Object)
	 */
	public QueueInterface<T> getBreathFirstTraversal(T origin) {
		resetVertices();
		
		QueueInterface<T> traversalOrder = new LinkedQueue<>();
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
		
		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexQueue.enqueue(originVertex);
		while(!vertexQueue.isEmpty()) {
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					traversalOrder.enqueue(nextNeighbor.getLabel());
					vertexQueue.enqueue(nextNeighbor);
				}
			}
		}
		return traversalOrder;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.GraphAlgorithmsInterface#getDepthFirstTravsersal(java.lang.Object)
	 */
	public QueueInterface<T> getDepthFirstTravsersal(T origin) {
		resetVertices();
		
		QueueInterface<T> traversalOrder = new LinkedQueue<>();
		StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
		
		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexStack.push(originVertex);
		
		while(!vertexStack.isEmpty()) {
			VertexInterface<T> topVertex = vertexStack.peek();
			Iterator<VertexInterface<T>> neighbors = topVertex.getNeighborIterator();
			while(neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					traversalOrder.enqueue(nextNeighbor.getLabel());
					vertexStack.push(nextNeighbor);
				} else {
					vertexStack.pop();
				}
			}
		}
		
		return traversalOrder;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.GraphAlgorithmsInterface#getShortestPath(java.lang.Object, java.lang.Object, cs241_Project4.StackInterface)
	 */
	public int getShortestPath(T begin, T end, StackInterface<T> path) {
		resetVertices();
		boolean done = false;
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		originVertex.visit();
		vertexQueue.enqueue(originVertex);
		while(!done && !vertexQueue.isEmpty()) {
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(!done && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					nextNeighbor.setCost(1 + frontVertex.getCost());
					nextNeighbor.setPredecessor(frontVertex);
					vertexQueue.enqueue(nextNeighbor);
				}
				
				if(nextNeighbor.equals(endVertex)) {
					done = true;
				}
			}
		}
		
		int pathLength = (int) endVertex.getCost();
		path.push(endVertex.getLabel());
		VertexInterface<T> vertex = endVertex;
		while(vertex.hasPredecessor()) {
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		}
		return pathLength;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.GraphAlgorithmsInterface#getTopologicalOrder()
	 */
	public StackInterface<T> getTopologicalOrder() {
		resetVertices();

		StackInterface<T> vertexStack = new LinkedStack<T>();
		int numberOfVertices = getNumberOfVertices();
		for (int counter = 1; counter <= numberOfVertices; counter++)
		{
			VertexInterface<T> nextVertex = findTerminal();
			nextVertex.visit();
			vertexStack.push(nextVertex.getLabel());
		}
		
		return vertexStack;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.GraphAlgorithmsInterface#getCheapestPath(java.lang.Object, java.lang.Object, cs241_Project4.StackInterface)
	 */
	public double getCheapestPath(T begin, T end, StackInterface<T> path) {
		resetVertices();
		boolean done = false;
		
		PriorityQueue<EntryPQ> priorityQueue = new PriorityQueue<EntryPQ>();
		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		priorityQueue.add(new EntryPQ(originVertex, 0, null));
	
		while (!done && !priorityQueue.isEmpty())
		{
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			
			if (!frontVertex.isVisited())
			{
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				
				if (frontVertex.equals(endVertex))
					done = true;
				else 
				{
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
					while (neighbors.hasNext())
					{
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();
						
						if (!nextNeighbor.isVisited())
						{
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						}
					}
				}
			}
		} 
		
		double pathCost = endVertex.getCost();
		path.push(endVertex.getLabel());
		
		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor())
		{
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} 

		return pathCost;
	}
	/**
	 * method: findTerminal
	 * purpose: this method finds the end vertex
	 * @return VertexInterface of last vertex
	 */
	protected VertexInterface<T> findTerminal() {
		boolean found = false;
		VertexInterface<T> result = null;
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		
		while (!found && vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next();
			if (!nextVertex.isVisited()) { 
				if (nextVertex.getUnvisitedNeighbor() == null ) { 
					found = true;
					result = nextVertex;
				} 
			} 
		} 

		return result;
	} 
	
	/**
	 * class: EntryPQ
	 * purpose: this class is used to hold the data in a priority queue
	 * @author lrpca
	 *
	 */
	private class EntryPQ implements Comparable<EntryPQ>, java.io.Serializable
	{
		private VertexInterface<T> vertex; 	
		private VertexInterface<T> previousVertex; 
		private double cost; 
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the entry in the priority queue
		 * @param vertex Vertex for entry
		 * @param cost Cost of entry
		 * @param previousVertex Vertex prior to other vertex
		 */
		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex) {
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		}
		
		/**
		 * method: getVertex
		 * purpose: this method gets the vertex
		 * @return VertexInterface of vertex
		 */
		public VertexInterface<T> getVertex() {
			return vertex;
		} 
		
		/**
		 * method: getPredecessor
		 * purpose: this method gets the predecessor vertex
		 * @return VertexInterface of predecessor vertex
		 */
		public VertexInterface<T> getPredecessor() {
			return previousVertex;
		}

		/**
		 * method: getCost
		 * purpose: this method gets the cost of a vertex
		 * @return Cost of vertex
		 */
		public double getCost() {
			return cost;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(EntryPQ otherEntry) {
			return (int)Math.signum(cost - otherEntry.cost);
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return vertex.toString() + " " + cost;
		}
	} 
}
