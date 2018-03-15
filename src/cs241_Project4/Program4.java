package cs241_Project4;

import java.io.IOException;
import java.util.Scanner;

public class Program4 {
	public static void main(String[] args) throws IOException {
		String command = "";
		Scanner scan = new Scanner(System.in);
		DirectedGraph<> dg = new DirectedGraph<>();

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
				System.out.print("This is not a command. Type H for help.\n");
				break;
			}
		}
	}

	private static void RCommand(DirectedGraph<String> dg) {
		// TODO Auto-generated method stub
		
	}

	private static void ICommand(DirectedGraph<String> dg) {
		// TODO Auto-generated method stub
		
	}

	private static void DCommand(DirectedGraph<String> dg) {
		// TODO Auto-generated method stub
		
	}

	private static void QCommand(DirectedGraph<String> dg) {
		// TODO Auto-generated method stub
		
	}
}
