package cs241_Project4;

import java.util.Iterator;
public interface VertexInterface<T> {
	public T getLabel();
	public void visit();
	public void unvist();
	public boolean isVisited();
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight);
	public boolean connect(VertexInterface<T> endVertex);
	public boolean disconnect(VertexInterface<T> endVertex);
	public Iterator<VertexInterface<T>> getNeighborIterator();
	public Iterator<Double> getWeightIterator();
	public boolean hasNeighbor();
	public VertexInterface<T> getUnvisitedNeighbor();
	public void setPredecessor(VertexInterface<T> predecessor);
	public VertexInterface<T> getPredecessor();
	public boolean hasPredecessor();
	public void setCost(double newCost);
	public double getCost();
}
