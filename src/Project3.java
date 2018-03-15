import java.io.*;
import java.util.*;

/**
 * Runs the main method
 * 
 * @author lrpca
 *
 */
public class Project3 {
	/**
	 * Main method that runs the program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String command = "";
		Scanner scan = new Scanner(System.in);
		Digraph<String> dg = new Digraph<>();

		// Checks if command is not E to exit
		while (!command.equalsIgnoreCase("E")) {
			System.out.print("Command? ");
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
				System.out.print("This is not a command.\n");
				break;
			}
		}
	}

	/**
	 * Runs the Q command, which gets all the information of a city
	 * 
	 * @param dg
	 *            Directed graph
	 * @throws FileNotFoundException
	 */
	private static void QCommand(Digraph<String> dg) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);

		System.out.print("City code: ");
		String city = scan.nextLine();

		String[] line = dg.findCityInfo(city.toUpperCase());

		// Checks if city exists
		while (line == null) {
			System.out.print("This is not a city. Please try again: ");
			city = scan.nextLine();
			line = dg.findCityInfo(city.toUpperCase());
		}

		dg.printCityInfo(line);
	}

	/**
	 * Runs the D command, which finds the shortest distance between two cities
	 * 
	 * @param dg
	 *            Directed graph
	 * @throws FileNotFoundException
	 */
	private static void DCommand(Digraph<String> dg) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);

		System.out.print("City codes: ");
		String userInput = scan.nextLine();

		String[] uiParts = userInput.split("\\s+");

		String[] line1 = dg.findCityInfo(uiParts[0].toUpperCase());
		String[] line2 = dg.findCityInfo(uiParts[1].toUpperCase());

		// Checks if either city exists
		while (line1 == null || line2 == null) {
			if (line1 == null && line2 == null) {
				System.out.print("Both of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
			} else {
				System.out.print("One of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
			}
		}

		String cityCode1 = dg.getCityCode(line1);
		String cityCode2 = dg.getCityCode(line2);

		String city1 = dg.getCityName(line1);
		String city2 = dg.getCityName(line2);
		System.out.print("The minimum distance between " + city1 + " and " + city2 + " is ");
		dg.getShortestPath(cityCode1, cityCode2);
	}

	/**
	 * Runs the I command, which inserts a road between two cities
	 * 
	 * @param dg
	 *            Directed graph
	 * @throws FileNotFoundException
	 */
	private static void ICommand(Digraph<String> dg) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		int weight = -1;

		System.out.print("City codes and distance: ");
		String userInput = scan.nextLine();

		String[] uiParts = userInput.split("\\s+");

		String[] line1 = dg.findCityInfo(uiParts[0].toUpperCase());
		String[] line2 = dg.findCityInfo(uiParts[1].toUpperCase());

		try {
			weight = Integer.parseInt(uiParts[2]);
		} catch (NumberFormatException e) {
			weight = -1;
		}

		// Checks if either city exists and the weight is appropriate for this graph
		while (line1 == null || line2 == null || weight <= 0) {
			if (line1 == null && line2 == null) {
				System.out.print("Both of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
				try {
					weight = Integer.parseInt(uiParts[2]);
				} catch (NumberFormatException e) {
					weight = -1;
				}
			} else if (line1 == null || line2 == null) {
				System.out.print("One of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
				try {
					weight = Integer.parseInt(uiParts[2]);
				} catch (NumberFormatException e) {
					weight = -1;
				}
			} else {
				System.out.print("This weight is not a vaild number. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
				try {
					weight = Integer.parseInt(uiParts[2]);
				} catch (NumberFormatException e) {
					weight = -1;
				}
			}
		}

		String cityNumber1 = dg.getCityNumber(line1);
		String cityNumber2 = dg.getCityNumber(line2);

		// Checks if road already exists
		if (dg.addEdge(cityNumber1, cityNumber2, weight)) {
			System.out.print("This road already exsits.\n");
		} else {
			String city1 = dg.getCityName(line1);
			String city2 = dg.getCityName(line2);
			System.out.print("You have inserted a road from " + city1 + " to " + city2 + " with a distance of " + weight
					+ ".\n");
		}
	}

	/**
	 * Runs the R command, which removes a road between two cities
	 * 
	 * @param dg
	 *            Directed graph
	 * @throws FileNotFoundException
	 */
	private static void RCommand(Digraph<String> dg) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);

		System.out.print("City codes: ");
		String userInput = scan.nextLine();

		String[] uiParts = userInput.split("\\s+");

		String[] line1 = dg.findCityInfo(uiParts[0].toUpperCase());
		String[] line2 = dg.findCityInfo(uiParts[1].toUpperCase());

		// Checks if either city exists
		while (line1 == null || line2 == null) {
			if (line1 == null && line2 == null) {
				System.out.print("Both of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
			} else {
				System.out.print("One of these cities doesn't exsit. Please try again: ");
				userInput = scan.nextLine();
				uiParts = userInput.split("\\s+");
				line1 = dg.findCityInfo(uiParts[0].toUpperCase());
				line2 = dg.findCityInfo(uiParts[1].toUpperCase());
			}
		}

		String cityCode1 = dg.getCityCode(line1);
		String cityCode2 = dg.getCityCode(line2);

		// Checks if road doesn't exist
		if (!dg.removeEdge(cityCode1, cityCode2)) {
			System.out.print("This road does not exsit.\n");
		} else {
			String city1 = dg.getCityName(line1);
			String city2 = dg.getCityName(line2);
			System.out.print("You have removed a road from " + city1 + " to " + city2 + ".\n");
		}
	}
}
