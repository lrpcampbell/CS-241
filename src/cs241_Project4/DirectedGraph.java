package cs241_Project4;

import java.util.Iterator;

public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T> {

	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	public DirectedGraph() {
		vertices = new SortedLinkedDictionary<>();
		edgeCount = 0;
	}
	
	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null;
	}

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

	public boolean addEdge(T begin, T end) {
		return addEdge(begin, end, 0);
	}

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

	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	public int getNumberOfVertices() {
		return vertices.getSize();
	}

	public int getNumberOfEdges() {
		return edgeCount;
	}

	public void clear() {
		vertices.clear();
		edgeCount = 0;
	}
	
	protected void resetVertices() {
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while(vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next();
			nextVertex.unvist();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	}

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

	@Override
	public StackInterface<T> getTopologicalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCheapestPath(T begin, T end, StackInterface<T> path) {
		// TODO Auto-generated method stub
		return 0;
	}

}
