package cs241_Project4;
/**
 * file: Vertex.java 
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

public class Vertex<T> implements VertexInterface<T> {
	private T label;
	private ListWithIteratorInterface<Edge> edgeList;
	private boolean visited;
	private VertexInterface<T> previousVertex;
	private double cost;
	
	/**
	 * method: constructor
	 * purpose: this constructor sets the vertex
	 * @param vertexLabel Vertex label
	 */
	public Vertex(T vertexLabel) {
		label = vertexLabel;
		edgeList = new LinkedListWithIterator<>();
		visited = false;
		previousVertex = null;
		cost = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getLabel()
	 */
	@Override
	public T getLabel() {
		return label;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#visit()
	 */
	@Override
	public void visit() {
		visited = true;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#unvist()
	 */
	@Override
	public void unvist() {
		visited = false;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#isVisited()
	 */
	@Override
	public boolean isVisited() {
		return visited;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#connect(cs241_Project4.VertexInterface, double)
	 */
	@Override
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
		boolean result = false;
		
		if(!this.equals(endVertex)) {
			Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
			boolean duplicateEdge = false;
			
			while(!duplicateEdge && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(endVertex.equals(nextNeighbor)) {
					duplicateEdge = true;
				}
			}
			
			if(!duplicateEdge) {
				edgeList.add(new Edge(endVertex, edgeWeight));
				result = true;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#connect(cs241_Project4.VertexInterface)
	 */
	@Override
	public boolean connect(VertexInterface<T> endVertex) {
		return connect(endVertex, 0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#disconnect(cs241_Project4.VertexInterface, cs241_Project4.VertexInterface)
	 */
	public boolean disconnect(VertexInterface<T> originVertex, VertexInterface<T> endVertex) {
		Iterator <VertexInterface<T>> neighbors = originVertex.getNeighborIterator();
		VertexInterface<T> nextNeighbor = neighbors.next();
		boolean found = false;
		
		return found;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getNeighborIterator()
	 */
	@Override
	public Iterator<VertexInterface<T>> getNeighborIterator() {
		return new NeighborIterator();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getWeightIterator()
	 */
	@Override
	public Iterator<Double> getWeightIterator() {
		return new WeightIterator();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#hasNeighbor()
	 */
	@Override
	public boolean hasNeighbor() {
		return !edgeList.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getUnvisitedNeighbor()
	 */
	@Override
	public VertexInterface<T> getUnvisitedNeighbor() {
		VertexInterface<T> result = null;
		Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
		while(neighbors.hasNext() && result == null) {
			VertexInterface<T> nextNeighbor = neighbors.next();
			if(!nextNeighbor.isVisited()) {
				result = nextNeighbor;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#setPredecessor(cs241_Project4.VertexInterface)
	 */
	@Override
	public void setPredecessor(VertexInterface<T> predecessor) {
		previousVertex = predecessor;
		
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getPredecessor()
	 */
	@Override
	public VertexInterface<T> getPredecessor() {
		return previousVertex;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#hasPredecessor()
	 */
	@Override
	public boolean hasPredecessor() {
		return previousVertex != null;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#setCost(double)
	 */
	@Override
	public void setCost(double newCost) {
		cost = newCost;
	}

	/*
	 * (non-Javadoc)
	 * @see cs241_Project4.VertexInterface#getCost()
	 */
	@Override
	public double getCost() {
		return cost;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		boolean result;
		if(other == null || getClass() != other.getClass()) {
			result = false;
		} else {
			@SuppressWarnings("unchecked")
			Vertex<T> otherVertex = (Vertex<T>) other;
			result = label.equals(otherVertex.label);
		}
		return result;
	}
	/**
	 * class: Edge
	 * purpose: this class controls the methods for edges
	 * @author lrpca
	 *
	 */
	protected class Edge {
		private VertexInterface<T> vertex;
		private double weight;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the edges
		 * @param endVertex End vertex
		 * @param edgeWeight Edge weight
		 */
		protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
			vertex = endVertex;
			weight = edgeWeight;
		}
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the edges
		 * @param endVertex End vertex
		 */
		protected Edge(VertexInterface<T> endVertex) {
			vertex = endVertex;
		}
		
		protected VertexInterface<T> getEndVertex() {
			return vertex;
		}
		
		/**
		 * method getWeight
		 * purpose: this method gets the weight of the edge
		 * @return Weight of edge
		 */
		protected double getWeight() {
			return weight;
		}
	}
	
	/**
	 * class: NeighborIterator
	 * purpose: this class controls the methods for the neighbor iterator
	 * @author lrpca
	 *
	 */
	private class NeighborIterator implements Iterator<VertexInterface<T>> {
		private Iterator<Edge> edges;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the neighbor iterator
		 */
		private NeighborIterator() {
			edges = edgeList.getIterator();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return edges.hasNext();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public VertexInterface<T> next() {
			VertexInterface<T> nextNeighbor = null;
			if(edges.hasNext()) {
				Edge edgeToNextNeighbor = edges.next();
				nextNeighbor = edgeToNextNeighbor.getEndVertex();
			} else {
				throw new NoSuchElementException();
			}
			
			return nextNeighbor;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * class: WeightIterator
	 * purpose: this class controls the methods for the weight iterator
	 * @author lrpca
	 *
	 */
	private class WeightIterator implements Iterator<Double> {
		private Iterator<Edge> edges;
		
		/**
		 * method: constructor
		 * purpose: this constructor sets the weight iterator
		 */
		private WeightIterator() {
			edges = edgeList.getIterator();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return edges.hasNext();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public Double next() {
			Double edgeWeight = new Double(0);
			if(edges.hasNext()) {
				Edge edgeToNextNeighbor = edges.next();
				edgeWeight = edgeToNextNeighbor.getWeight();
			} else {
				throw new NoSuchElementException();
			}
			
			return edgeWeight;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
