package cs241_Project2;

/**
 * file: Project2.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 2
 * date last modified: 1/30/2018
 *
 * purpose: This program runs main method and executes the commands given by the user
 * 
 * @author lrpca
 *
 */
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Project2 {
	/**
	 * method: main 
	 * purpose: asks the user to choose one of the two tests displayed
	 * 
	 * @param arg
	 * @throws IOException
	 */
	public static <T> void main(String[] arg) throws IOException {
		Scanner scan = new Scanner(System.in);
		String choiceStr;
		int choice = 0;
		boolean worked = false;

		// Prints message with commands
		System.out.println("=====================================================================");
		System.out.println("Please select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100");
		System.out.print("Enter choice: ");

		// Catches NumberFormatException, prints a user friendly message, and asks for
		// another command
		do {
			try {
				choiceStr = scan.nextLine();
				choice = Integer.parseInt(choiceStr);
				worked = true;
			} catch (NumberFormatException e) {
				System.out.print("\nInvalid command. Try again: ");
				worked = false;
			}
		} while (worked == false);

		// Determines which test to use for each command
		worked = false;
		while(worked == false) {
			if(choice == 1) {
				System.out.println();
				option1();
				System.out.println("\n=====================================================================");
				worked = true;
			} else if(choice == 2) {
				System.out.println();
				option2();
				System.out.println("\n=====================================================================");
				worked = true;
			} else {
				System.out.print("\nThis is not an option. Please try again: ");
				do {
					try {
						choiceStr = scan.nextLine();
						choice = Integer.parseInt(choiceStr);
						worked = true;
					} catch (NumberFormatException e) {
						System.out.print("\nInvalid command. Try again: ");
						worked = false;
					}
				} while (worked == false);
				worked = false;
			}
		}
	}

	/**
	 * method: option1 
	 * purpose: runs test one which takes 20 sets of a random 100
	 * numbers and finds the average amount of swaps
	 * 
	 * @param pw
	 *            PrintWriter to print in the output.txt file
	 */
	private static void option1() {
		Random rand = new Random();
		int numberOfSets = 1;
		int averageSeries = 0;
		int averageOptimal = 0;

		// Generates a new set of 100 random numbers
		while (numberOfSets <= 20) {
			MaxHeap<Integer> mhSeries = new MaxHeap<>(100);
			Integer[] set = new Integer[100];
			int swaps = 0;

			// Adds set into a max-heap and into an array
			for (int i = 0; i < 100; i++) {
				// Generates random number from 1-200
				int value = rand.nextInt(200) + 1;

				// Adds number to max-heap
				mhSeries.add(value);

				// Adds the number of swaps it took to add that number to the max-heap to a
				// total for the entire max-heap
				swaps += mhSeries.getSwap();

				// Adds the number to array
				set[i] = value;
			}
			// Adds total number of swaps to the average
			averageSeries += swaps;

			// Creates a max-heap out of the array
			MaxHeap<Integer> mhOptimal = new MaxHeap<>(set);

			// Adds total number of swaps to average
			averageOptimal += mhOptimal.getSwap();

			numberOfSets++;
		}

		// Prints averages
		System.out.println("Average swaps for series of insertions: " + averageSeries / 20);

		System.out.println("Average swaps for optimal method: " + averageOptimal / 20);

	}

	/**
	 * method: option2 
	 * purpose: runs test two which puts a the numbers 1-100 into a
	 * heap
	 * 
	 * @param pw
	 *            PrintWriter to print in the output.txt file
	 */
	private static void option2() {
		MaxHeap<Integer> mhSeries = new MaxHeap<>(100);
		Integer[] set = new Integer[100];
		int counter = 1;
		int swaps = 0;

		// Adds numbers 1-100 into a max-heap and an array, and keeps track of the
		// number of swaps for the max-heap
		for (int i = 0; i < 100; i++) {
			mhSeries.add(counter);
			swaps += mhSeries.getSwap();
			set[i] = counter;
			counter++;
		}

		// Prints max-heap of numbers 1-100
		System.out.print("Heap built using series of insertions: ");
		printHeap(mhSeries);

		// Prints number of swaps
		System.out.println("Number of swaps: " + swaps);

		// Prints max-heap after 10 maximum removals
		System.out.print("Heap after 10 removals: ");
		for (int i = 0; i < 10; i++) {
			mhSeries.removeMax();
		}
		printHeap(mhSeries);

		// Prints max-heap made from the array
		System.out.print("\nHeap built using optimal of insertions: ");
		MaxHeap<Integer> mhOptimal = new MaxHeap<>(set);
		printHeap(mhOptimal);

		// Prints number of swaps
		System.out.println("Number of swaps: " + mhOptimal.getSwap());

		// Prints max-heap after 10 removals
		System.out.print("Heap after 10 removals: ");
		for (int i = 0; i < 10; i++) {
			mhOptimal.removeMax();
		}
		printHeap(mhOptimal);
	}

	/**
	 * method: printHeap 
	 * purpose: prints out all the data in the MaxHeap
	 * 
	 * @param mh
	 *            MaxHeap that has the data being printed
	 * @param pw
	 *            PrintWriter to print in the output.txt file
	 */
	private static void printHeap(MaxHeap<Integer> mh) {
		for (int i = 1; i <= 10; i++) {
			System.out.print(mh.getData(i) + ",");
		}
		System.out.println("...");
	}
}
