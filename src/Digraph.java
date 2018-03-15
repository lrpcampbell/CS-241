import java.io.*;
import java.util.*;

/**
 * Holds all the methods that deal with a directed graph
 * 
 * @author lrpca
 *
 * @param <T>
 */
class Digraph<T extends Comparable<? super T>> {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private File cityFile = new File("city(2).dat");
	private File roadFile = new File("road(2).dat");

	/**
	 * Constructor that makes the directed graph out of two files
	 * 
	 * @throws FileNotFoundException
	 */
	public Digraph() throws FileNotFoundException {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();

		Scanner scanCity = new Scanner(cityFile);
		Scanner scanRoad = new Scanner(roadFile);

		while (scanCity.hasNext()) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String code = getCityCode(lineParts);
			if (!code.equals("")) {
				Vertex<String> v = new Vertex<String>(code);
				vertices.add(v);
			}
		}

		while (scanRoad.hasNext()) {
			Edge<String> e = null;
			String line = scanRoad.nextLine();
			String[] lineParts = line.split("\\s+");
			if (lineParts[0].equals("") && lineParts.length > 1) {
				String[] cityInfo1 = findCityInfo(lineParts[1]);
				String cityCode1 = getCityCode(cityInfo1);
				String[] cityInfo2 = findCityInfo(lineParts[2]);
				String cityCode2 = getCityCode(cityInfo2);
				e = new Edge<>(cityCode1, cityCode2, Integer.parseInt(lineParts[3]));
			} else if (!lineParts[0].equals("") && lineParts.length > 1) {
				String[] cityInfo1 = findCityInfo(lineParts[0]);
				String cityCode1 = getCityCode(cityInfo1);
				String[] cityInfo2 = findCityInfo(lineParts[1]);
				String cityCode2 = getCityCode(cityInfo2);
				e = new Edge<>(cityCode1, cityCode2, Integer.parseInt(lineParts[2]));
			}
			edges.add(e);
		}
	}

	/**
	 * Adds an edge between two vertices on the graph
	 * 
	 * @param from
	 *            The starting vertex
	 * @param to
	 *            The ending vertex
	 * @param weight
	 *            THe distance
	 * @return true if edge was added, otherwise false
	 * @throws FileNotFoundException
	 */
	public boolean addEdge(T from, T to, int weight) throws FileNotFoundException {
		boolean found = false;
		int counter = 0;
		int length = getLength();

		while (!found && counter < length) {
			@SuppressWarnings("unchecked")
			Edge<T> edge = edges.get(counter);
			if (edge.getFrom().equals(from) && edge.getTo().equals(to)) {
				found = true;
			} else {
				counter++;
			}
		}
		if (!found) {
			Edge<T> e = new Edge<>(from, to, weight);
			edges.add(e);
		}

		return found;
	}

	/**
	 * Removes an edge from the graph
	 * 
	 * @param from
	 *            The starting vertex
	 * @param to
	 *            The ending vertex
	 * @return true if removed, otherwise false
	 * @throws FileNotFoundException
	 */
	public boolean removeEdge(T from, T to) throws FileNotFoundException {
		boolean found = false;
		int counter = 0;
		int length = getLength();

		while (!found && counter < length) {
			@SuppressWarnings("unchecked")
			Edge<T> edge = edges.get(counter);
			
			if (edge.getFrom().equals(from) && edge.getTo().equals(to)) {
				found = true;
				edges.remove(edge);
			} else {
				counter++;
			}
		}

		return found;
	}

	/**
	 * Gets the how many edges there is in the graph
	 * 
	 * @return length Number of edges
	 * @throws FileNotFoundException
	 */
	public int getLength() throws FileNotFoundException {
		Scanner scanRoad = new Scanner(roadFile);
		int length = 0;

		while (scanRoad.hasNext()) {
			String line = scanRoad.nextLine();
			length++;
		}

		return length;
	}

	/**
	 * Gets the shortest path from one vertex to another
	 * 
	 * @param from
	 *            The starting vertex
	 * @param to
	 *            The ending vertex
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void getShortestPath(T from, T to) throws FileNotFoundException {
		Vertex<T> vFrom = new Vertex<>(from);
		vFrom.setDistanceFromSource(0);
		int nextVertex = 0;

		ArrayList<Edge> neighbors = getNeighbors(from);
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(vFrom)) {
				vertices.get(i).setEdges(neighbors);
			}
		}

		for (int i = 0; i < vertices.size(); i++) {
			ArrayList<Edge> currentVertex = vertices.get(nextVertex++).getEdges();

			for (int joinedEdge = 0; joinedEdge < currentVertex.size(); joinedEdge++) {
				int neighborIndex = currentVertex.get(joinedEdge).getNeighborIndex(nextVertex);

				if (!vertices.get(neighborIndex).isVisited()) {
					int tentative = vertices.get(nextVertex).getDistanceFromSource()
							+ currentVertex.get(joinedEdge).getWeight();

					if (tentative < vertices.get(neighborIndex).getDistanceFromSource()) {
						vertices.get(neighborIndex).setDistanceFromSource(tentative);
					}
				}
				
				vertices.get(nextVertex).setVisited(true);

				nextVertex = getVertexShortestDistanced();
			}
		}

		System.out.print(nextVertex + " through the route: " + from + ", xxx, ..., xxx, " + to + ".\n");

	}

	/**
	 * Makes an ArrayList of edges connected to a vertex
	 * 
	 * @param from
	 *            The starting vertex
	 * @return neighbors An ArrayList of edges
	 */
	private ArrayList<Edge> getNeighbors(T from) {
		ArrayList<Edge> neighbors = new ArrayList<>();

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getFrom().equals(from)) {
				neighbors.add(edges.get(i));
			}
		}

		return neighbors;
	}

	/**
	 * Gets the shortest distance of a vertex
	 * 
	 * @return storedNodeIndex The distance
	 */
	private int getVertexShortestDistanced() {
		int storedNodeIndex = 0;
		int storedDist = Integer.MAX_VALUE;

		for (int i = 0; i < vertices.size(); i++) {
			int currentDist = vertices.get(i).getDistanceFromSource();

			if (!vertices.get(i).isVisited() && currentDist < storedDist) {
				storedDist = currentDist;
				storedNodeIndex = i;
			}
		}

		return storedNodeIndex;
	}

	/**
	 * Gets the number of the city based on the information in the file
	 * 
	 * @param info
	 *            The line being searched through
	 * @return The part of the array that is the number of the city
	 */
	public T getCityNumber(T[] info) {
		if (info[0].equals("")) {
			return info[1];
		} else {
			return info[0];
		}
	}

	/**
	 * Gets the code of the city based on the information in the file
	 * 
	 * @param info
	 *            The line being searched through
	 * @return code City code
	 */
	public String getCityCode(String[] info) {
		String code = "";
		if (info[0].equals("") && info.length > 1) {
			code = info[2];
		} else if (info.length > 1) {
			code = info[1];
		}
		return code;
	}

	/**
	 * Gets the name of the city based on the information in the file
	 * 
	 * @param info
	 *            The line being searched through
	 * @return The city name
	 */
	public String getCityName(String[] info) {
		if (info[0].equals("") && info.length == 7) {
			return info[3] + " " + info[4];
		} else if (info[0].equals("") && info.length == 6) {
			return info[3];
		} else if (!info[0].equals("") && info.length == 6) {
			return info[2] + " " + info[3];
		} else {
			return info[2];
		}
	}

	/**
	 * Prints out the information of a city
	 * 
	 * @param info
	 *            City Info that needs to be printed
	 */
	public void printCityInfo(String[] info) {
		for (int i = 0; i < info.length; i++) {
			System.out.print(info[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Finds the city information in the city(2).dat file
	 * 
	 * @param city
	 *            City being searched for
	 * @return A string array with all the city info
	 * @throws FileNotFoundException
	 */
	public String[] findCityInfo(String city) throws FileNotFoundException {
		Scanner scanCity = new Scanner(cityFile);

		while (scanCity.hasNext()) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");

			for (int i = 0; i < lineParts.length; i++) {
				if (lineParts[i].equals(city)) {
					return lineParts;
				}
			}
		}
		return null;
	}
}
