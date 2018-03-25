package cs241_Project4;
/**
 * file: Program4.java 
 * author: Lindsey Campbell 
 * class: CS 241 � Data Structures and Algorithms II
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

import java.io.*;
import java.util.*;

public class Program4 {
	private static File cityFile = new File("city.dat");
	private static File roadFile = new File("road.dat");
	private static Scanner scanCity;
	private static Scanner scanRoad;
	
	/**
	 * method: main
	 * purpose: this method takes in the data from city.dat and road.dat and then asks for a command 
	 * from the user and performs the right command
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String command = "";
		Scanner scan = new Scanner(System.in);
		DirectedGraph<Integer> dg = new DirectedGraph<>();
		scanCity = new Scanner(cityFile);
		scanRoad = new Scanner(roadFile);
		
		while (scanCity.hasNext()) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String cityNumStr = lineParts[0];
			if (!cityNumStr.equals("")) {
				int cityNum = Integer.parseInt(cityNumStr);
				dg.addVertex(cityNum);
			} else {
				int cityNum = Integer.parseInt(lineParts[1]);
				dg.addVertex(cityNum);
			}
		}
		
		while(scanRoad.hasNext()) {
			String line = scanRoad.nextLine();
			String[] lineParts = line.split("\\s+");
			String firstCity = lineParts[0];
			if (!firstCity.equals("")) {
				int city1Num = Integer.parseInt(firstCity);
				int city2Num = Integer.parseInt(lineParts[1]);
				int distance = Integer.parseInt(lineParts[2]);
				dg.addEdge(city1Num, city2Num, distance);
			} else {
				int city1Num = Integer.parseInt(lineParts[1]);
				int city2Num = Integer.parseInt(lineParts[2]);
				int distance = Integer.parseInt(lineParts[3]);
				dg.addEdge(city1Num, city2Num, distance);
			}
		}

		// Checks if command is not E to exit
		while (!command.equalsIgnoreCase("E")) {
			System.out.print("\nCommand? ");
			command = scan.nextLine();

			switch (command.toUpperCase()) {
			case "Q":
				QCommand(dg);
				break;
			case "D":
				DCommand(dg);
				break;
			case "I":
				ICommand(dg);
				break;
			case "R":
				RCommand(dg);
				break;
			// Runs H command, which displays all the command to choose from
			case "H":
				System.out.print("Q  Query the city information by entering the city code.");
				System.out.print("\nD  Find the minimum distance between two cities.");
				System.out.print("\nI  Insert a road by entering two city codes and distance.");
				System.out.print("\nR  Remove an existing road by entering two city codes.");
				System.out.print("\nH  Display this message.");
				System.out.print("\nE  Exit.\n");
				break;
			// Runs E command, which exits the program
			case "E":
				System.out.print("Thank you for using my program.");
				break;
			// Catches if the command given is an option
			default:
				System.out.print("This is not a command. Type H for help.\n");
				break;
			}
		}
		scanCity.close();
		scanRoad.close();
	}

	/**
	 * method: RCommand
	 * purpose: this method removes a road between two cities
	 * @param dg Directed graph with all the information
	 * @throws IOException
	 */
	private static void RCommand(DirectedGraph<Integer> dg) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("City Codes: ");
		String city1Code = scan.next();
		String city2Code = scan.next();
		int city1 = findCityNumber(city1Code);
		int city2 = findCityNumber(city2Code);
		
		if(city1 == 0 && city2 == 0) {
			System.out.print("Both of these cities does not exist.");
		} else if(city1 == 0 || city2 == 0) {
			System.out.print("One of these cities does not exist.");
		} else {
			if(dg.hasEdge(city1, city2)) {
				dg.removeEdge(city1, city2);
				System.out.print("You have removed a road between "+findCityName(city1Code)+" and "+findCityName(city2Code));
			} else {
				System.out.print("The road between "+findCityName(city1Code)+" and "+findCityName(city2Code)+" does not exist.");
			}
		}
	}

	/**
	 * method: ICommand
	 * purpose: this method inserts a road between two cities
	 * @param dg Directed graph with all the information
	 * @throws IOException
	 */
	private static void ICommand(DirectedGraph<Integer> dg) throws IOException {
		Scanner scan = new Scanner(System.in);
		int distance = 0;
		System.out.print("City Codes and Distance: ");
		String city1Code = scan.next();
		String city2Code = scan.next();
		int city1 = findCityNumber(city1Code);
		int city2 = findCityNumber(city2Code);
		try {
			distance = Integer.parseInt(scan.next());
			if(city1 == 0 && city2 == 0) {
				System.out.print("Both of these cities does not exist.");
			} else if(city1 == 0 || city2 == 0) {
				System.out.print("One of these cities does not exist.");
			} else {
				if(dg.hasEdge(city1, city2)) {
					System.out.print("There is already a road between "+findCityName(city1Code)+" and "+findCityName(city2Code));
				} else {
					dg.addEdge(city1, city2, distance);
					System.out.print("You have inserted a road between "+findCityName(city1Code)+" and "+findCityName(city2Code)+" with a distance of "+distance);
				}
			}
		} catch(NumberFormatException e) {
			System.out.print("This is not an appropriate distance.");
		}
	}

	/**
	 * method: DCommand
	 * purpose: this method finds the distance between two cities
	 * @param dg Directed graph with all the information
	 * @throws IOException
	 */
	private static void DCommand(DirectedGraph<Integer> dg) throws IOException {
		Scanner scan = new Scanner(System.in);
		StackInterface<Integer> path = new LinkedStack<>();
		System.out.print("City Codes: ");
		String city1Code = scan.next();
		String city2Code = scan.next();
		int city1 = findCityNumber(city1Code);
		int city2 = findCityNumber(city2Code);
		
		double length = dg.getCheapestPath(city1, city2, path);
		System.out.print("The minimum distance between "+findCityName(city1Code)+" and "+findCityName(city2Code)+" is "+length+" through the route: ");
		while(!path.isEmpty()) {
			System.out.print(findCityCode(path.pop())+" ");
		}
	}

	/**
	 * method: QCommand
	 * purpose: this method prints out all the information given for a city
	 * @param dg Directed graph with all the information
	 * @throws IOException
	 */
	private static void QCommand(DirectedGraph<Integer> dg) throws IOException {
		scanCity = new Scanner(cityFile);
		Scanner scan = new Scanner(System.in);
		boolean found = false;
		
		System.out.print("City Code: ");
		String cityCode = scan.nextLine();
		
		while (scanCity.hasNext() || !found) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String cityNumStr = lineParts[0];
			if (cityNumStr.equals("")) {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[2])) {
						found = true;
						for(int i = 0; i < lineParts.length; i++) {
							System.out.print(lineParts[i]+" ");
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
					System.out.print("This city does not exist.");
				}
			} else {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[1])) {
						found = true;
						for(int i = 0; i < lineParts.length; i++) {
							System.out.print(lineParts[i]+" ");
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
					System.out.print("This city does not exist.");
				}
			}
		}
		scanCity.close();
	}
	
	/**
	 * method: findCityName
	 * purpose: this method finds the full name of the city
	 * @param cityCode City code
	 * @return City name
	 * @throws IOException
	 */
	public static String findCityName(String cityCode) throws IOException {
		scanCity = new Scanner(cityFile);
		Scanner scan = new Scanner(System.in);
		boolean found = false;
		String city = "";
		
		while (scanCity.hasNext() && !found) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String cityNumStr = lineParts[0];
			if (cityNumStr.equals("")) {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[2])) {
						found = true;
						city = lineParts[3];
						try {
							int elevation = Integer.parseInt(lineParts[4]);
						} catch (NumberFormatException e) {
							city += " "+lineParts[4];
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			} else {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[1])) {
						found = true;
						city = lineParts[2];
						try {
							int elevation = Integer.parseInt(lineParts[3]);
						} catch (NumberFormatException e) {
							city += " "+lineParts[3];
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			}
		}
		scanCity.close();
		return city;
	}
	
	/**
	 * method: findCityNumber
	 * purpose: this method finds the city number
	 * @param cityCode City code
	 * @return City number
	 * @throws IOException
	 */
	public static int findCityNumber(String cityCode) throws IOException {
		scanCity = new Scanner(cityFile);
		Scanner scan = new Scanner(System.in);
		boolean found = false;
		int city = 0;
		
		while (scanCity.hasNext() && !found) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String cityNumStr = lineParts[0];
			if (cityNumStr.equals("")) {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[2])) {
						found = true;
						city = Integer.parseInt(lineParts[1]);
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			} else {
				try {
					if(cityCode.equalsIgnoreCase(lineParts[1])) {
						found = true;
						city = Integer.parseInt(cityNumStr);
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			}
		}
		scanCity.close();
		return city;
	}
	
	/**
	 * method: findCityCode
	 * purpose: this method finds the city code
	 * @param cityNum City number
	 * @return City code
	 * @throws IOException
	 */
	public static String findCityCode(int cityNum) throws IOException {
		scanCity = new Scanner(cityFile);
		Scanner scan = new Scanner(System.in);
		boolean found = false;
		String city = "";
		
		while (scanCity.hasNext() && !found) {
			String line = scanCity.nextLine();
			String[] lineParts = line.split("\\s+");
			String cityNumStr = lineParts[0];
			if (cityNumStr.equals("")) {
				try {
					if(cityNum == Integer.parseInt(lineParts[1])) {
						found = true;
						city = lineParts[2];
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			} else {
				try {
					if(cityNum == Integer.parseInt(lineParts[0])) {
						found = true;
						city = lineParts[1];
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			}
		}
		scanCity.close();
		return city;
	}
}
