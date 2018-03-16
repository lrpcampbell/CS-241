package cs241_Project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Program4 {
	private static File cityFile = new File("city.dat");
	private static File roadFile = new File("road.dat");
	private static Scanner scanCity;
	private static Scanner scanRoad;
	
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

	private static void RCommand(DirectedGraph<Integer> dg) {
		
	}

	private static void ICommand(DirectedGraph<Integer> dg) {
		// TODO Auto-generated method stub
		
	}

	private static void DCommand(DirectedGraph<Integer> dg) {
		// TODO Auto-generated method stub
		
	}

	private static void QCommand(DirectedGraph<Integer> dg) throws IOException {
		scanCity = new Scanner(cityFile);
		Scanner scan = new Scanner(System.in);
		String cityCode = "";
		boolean found = false;
		
		System.out.print("City Code: ");
		cityCode = scan.nextLine();
		
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
}
