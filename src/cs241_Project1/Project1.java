package cs241_Project1;
/**
 * file: Project1.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1
 * date last modified: 1/21/2018
 *
 * purpose: This program runs main method and executes the commands given by the user
 * 
 * @author lrpca
 *
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project1 {
	/**
	 * method: formBinarySearchTree 
	 * purpose: this method recursively puts the array
	 * of user input into a binary search tree
	 * 
	 * @param bst
	 *            Binary search tree storing user input
	 * @param numbers
	 *            Array the binary search tree is getting input from
	 * @param counter
	 *            Keeps track of where the array should pull from next
	 * @return Binary search tree of user input
	 */
	private static BinarySearchTree<Integer> formBinarySearchTree(BinarySearchTree<Integer> bst, Integer[] numbers,
			int counter) {
		if (counter != numbers.length) {
			bst.insert(numbers[counter]);
			bst = formBinarySearchTree(bst, numbers, counter + 1);
		}
		return bst;
	}

	/**
	 * method: main 
	 * purpose: this method gets the initial sequence of integers from
	 * the user and commands from the user
	 * 
	 * @param arg
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static <T> void main(String[] arg) throws IOException {
		String sequence, command;
		String[] numberStr;
		int commandNumber = 0;
		boolean notInt = false;
		Integer[] numbers = new Integer[1];
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(null);
		Scanner scan = new Scanner(System.in);

		// Gets initial sequence from user
		System.out.println("Please enter the initial sequence of values: ");
		sequence = scan.nextLine();

		// Puts sequence into an array, then into a binary search tree
		numberStr = sequence.split("\\s+");
		numbers = makeIntArray(numberStr);
		bst = formBinarySearchTree(bst, numbers, 0);

		if (numbers[0] != null) {
			// Traverses sequence into preorder, inorder and postorder
			System.out.print("Pre-order: ");
			bst.preorderTraverse();

			System.out.print("\nIn-order: ");
			bst.inorderTraverse();

			System.out.print("\nPost-order: ");
			bst.postorderTraverse();
		}

		// Handles commands given by the user to manipulate the original sequence
		System.out.print("\nCommand? ");
		command = scan.next();
		command = command.toUpperCase();
		if (command.equals("I") || command.equals("D") || command.equals("P") || command.equals("S")) {
			try {
				commandNumber = scan.nextInt();
			} catch(InputMismatchException e) {
				System.out.print("This is not an integer value.");
				scan.nextLine();
				notInt = true;
			}
		}

		while (command != "E") {
			switch (command) {
			case "H":
				System.out.print("I Insert a value");
				System.out.print("\nD Delete a value");
				System.out.print("\nP Find predecessor");
				System.out.print("\nS Find successor");
				System.out.print("\nE Exit the program");
				System.out.print("\nH Display this message");
				break;
			case "I":
				if(!notInt) {
					if (bst.contains(commandNumber)) {
						System.out.print(commandNumber + " already exists, ignore.");
					} else {
						bst.insert(commandNumber);
						System.out.print("In-order: ");
						bst.inorderTraverse();
					}
				}
				notInt = false;
				break;
			case "D":
				if(!notInt) {
					if (!bst.contains(commandNumber)) {
						System.out.print(commandNumber + " doesn't exist!");
					} else {
						bst.delete(commandNumber);
						System.out.print("In-order: ");
						bst.inorderTraverse();
					}
				}
				notInt = false;
				break;
			case "P":
				if(!notInt) {
					if (!bst.contains(commandNumber)) {
						System.out.print(commandNumber + " doesn't exist!");
					} else {
						 int predecessor = bst.findPredecessor(commandNumber);
						 if (predecessor != commandNumber) {
							 System.out.print(predecessor);
						 } else {
							 System.out.print("There is no predecessor for this number.");
						 }
					}
				}
				notInt = false;
				break;
			case "S":
				if(!notInt) {
					if (!bst.contains(commandNumber)) {
						System.out.print(commandNumber + " doesn't exist!");
					} else {
						int successor = bst.findSuccessor(commandNumber);
						 if (successor != commandNumber) {
							 System.out.print(successor);
						 } else {
							 System.out.print("There is no successor for this number.");
						 }
					}
				}
				notInt = false;
				break;
			case "E":
				System.out.print("Thank you for using my program!");
				System.exit(0);
				break;
			default:
				System.out.print("Invalid command!");
				break;
			}
			System.out.print("\nCommand? ");
			command = scan.next();
			command = command.toUpperCase();
			if (command.equals("I") || command.equals("D") || command.equals("P") || command.equals("S")) {
				try {
					commandNumber = scan.nextInt();
				} catch(InputMismatchException e) {
					System.out.print("This is not an integer value.");
					scan.nextLine();
					notInt = true;
				}
			}
		}
	}

	/**
	 * method: makeIntArray 
	 * purpose: this method converts the array of strings to an
	 * array of integers
	 * 
	 * @param numberStr
	 *            Array of strings
	 * @return numbers Array of integers
	 */
	private static Integer[] makeIntArray(String[] numberStr) {
		Integer[] numbers = new Integer[numberStr.length];
		String newSequence;
		Scanner scan = new Scanner(System.in);
		try {
			numbers[0] = Integer.parseInt(numberStr[0]);
			numbers = makeIntArray2(numbers, numberStr, 1);
		} catch (NumberFormatException e) {
			System.out.print("The sequence of numbers you inputted is not in the right format. Make sure to "
					+ "\nonly input integer values and separate your numbers with one space.");

			System.out.println("\nPlease enter the initial sequence of values: ");
			newSequence = scan.nextLine();
			numberStr = newSequence.split("\\s+");
			numbers = makeIntArray(numberStr);
		}
		return numbers;
	}

	/**
	 * method: makeIntArray2 
	 * purpose: this method converts the array of strings to
	 * an array of integers recursively
	 * 
	 * @param numbers
	 *            Array of integers
	 * @param numberStr
	 *            Array of strings
	 * @param counter
	 *            Keeps track of where we are in each array
	 * @return numbers Array of integers returned
	 */
	private static Integer[] makeIntArray2(Integer[] numbers, String[] numberStr, int counter) {
		String newSequence;
		Scanner scan = new Scanner(System.in);
		try {
			if (counter != numberStr.length) {
				numbers[counter] = Integer.parseInt(numberStr[counter]);
				numbers = makeIntArray2(numbers, numberStr, counter + 1);
			}
		} catch (NumberFormatException e) {
			System.out.print("The sequence of numbers you inputted is not in the right format. Make sure to"
					+ "\nonly input integer values and separate your numbers with one space.");

			System.out.println("\nPlease enter the initial sequence of values: ");
			newSequence = scan.nextLine();
			numberStr = newSequence.split("\\s+");
			numbers = makeIntArray(numberStr);
		}
		return numbers;
	}
}
