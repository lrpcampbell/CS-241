package cs241_Project3;
/**
 * file: Program3.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 3
 * date last modified: 2/26/2018
 *
 * purpose: This program runs main method and executes the commands given by the user
 * 
 * @author lrpca
 *
 */
import java.util.*;

import cs241_Project1.BinarySearchTree;

public class Program3 {
	/**
	 * method: main
	 * purpose: this method gathers user commands
	 * @param arg
	 */
	public static void main(String[] arg) {
		Random rand = new Random();
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(null);
		RedBlackTree<Integer> rbt = new RedBlackTree<>();
		int option = 0;
		Scanner scan = new Scanner(System.in);
		boolean worked = false;
		
		for(int i = 0; i < 100; i++) {
			int value = rand.nextInt(1000) + 1;
			bst.insert(value);
			rbt.add(value);
		}
		
		do {
			System.out.print("\nOptions Menu:\n"
					+"1) Insert a new value\n"
					+"2) Delete a value\n"
					+"3) Number of leaves in both trees\n"
					+"4) List of all values in the tree between a and b\n"
					+"5) Delete the first 20 entries in the trees\n"
					+"6) Exit the program\n"
					+"What would you like to do? ");
			do {
				try {
					option = scan.nextInt();
					scan.nextLine();
					worked = true;
				} catch (NumberFormatException e) {
					System.out.print("\nInvalid command. Try again: ");
					worked = false;
				}
			} while (worked == false);
			
			switch(option) {
			case 1:
				option1(bst, rbt);
				break;
			case 2:
				option2(bst, rbt);
				break;
			case 3:
				option3(bst, rbt);
				break;
			case 4:
				option4(bst, rbt);
				break;
			case 5:
				option5(bst, rbt);
				break;
			case 6:
				System.out.println("\nThank you for using my program!");
				System.exit(0);
				break;
			default:
				System.out.println("\nThis is not a command.");
				break;
			}
		}while(option != 6);
	}

	/**
	 * method: option5
	 * purpose: this method removes the first 20 integers in preorder traversal
	 * @param bst Binary search tree
	 * @param rbt Red-black tree
	 */
	private static void option5(BinarySearchTree<Integer> bst, RedBlackTree<Integer> rbt) {
		ArrayList<Integer> preorderBST = new ArrayList<>(100);
		ArrayList<Integer> preorderRBT = new ArrayList<>(100);
		
		preorderBST = bst.preorder(preorderBST);
		preorderRBT = rbt.preorder(preorderRBT);
		
		preorderBST.trimToSize();
		preorderRBT.trimToSize();
		
		for(int i = 0; i < 20; i++) {
			bst.delete(preorderBST.get(i));
			rbt.remove(preorderRBT.get(i));
		}
		
		System.out.print("\nThe height of the binary search tree is "+bst.getHeight()+" and the number of leaves is "+bst.getNumberOfLeaves());
		System.out.print("\nThe height of the red-black tree is "+rbt.getHeight()+" and the number of leaves is "+rbt.getNumberOfLeaves());
	}

	/**
	 * method: option4
	 * purpose: this method gets the list of data between a and b
	 * @param bst Binary search tree
	 * @param rbt Red-black tree
	 */
	private static void option4(BinarySearchTree<Integer> bst, RedBlackTree<Integer> rbt) {
		Scanner scan = new Scanner(System.in);
		int a = 0, b = 0;
		boolean worked = false;
		ArrayList<Integer> listBST = new ArrayList<>(100);
		ArrayList<Integer> listRBT = new ArrayList<>(100);
		
		System.out.print("\nWhat is the minimum 'a' integer? ");
		do {
			try {
				a = scan.nextInt();
				scan.nextLine();
				worked = true;
			} catch (NumberFormatException e) {
				System.out.print("\nNot an integer value. Try again: ");
				worked = false;
			}
		} while (worked == false);
		worked = false;
		
		System.out.print("\nWhat is the maximum 'b' integer? ");
		do {
			try {
				b = scan.nextInt();
				scan.nextLine();
				worked = true;
			} catch (NumberFormatException e) {
				System.out.print("\nNot an integer value. Try again: ");
				worked = false;
			}
		} while (worked == false);
		
		listRBT = rbt.listData(a, b);
		listBST = bst.listData(a, b);
		
		listBST.trimToSize();
		listRBT.trimToSize();
		
		System.out.print("\nThe values between a and b in the binary search tree are: ");
		
		for(int i = 0; i < listBST.size(); i++) {
			System.out.print(listBST.get(i)+" ");
		}
		
		System.out.print("\nThe values between a and b in the red-black tree are: ");
		
		for(int i = 0; i < listRBT.size(); i++) {
			System.out.print(listRBT.get(i)+" ");
		}
	}

	/**
	 * method: option3
	 * purpose: this method gets the number of leaf nodes in the trees
	 * @param bst Binary search tree
	 * @param rbt Red-black tree
	 */
	private static void option3(BinarySearchTree<Integer> bst, RedBlackTree<Integer> rbt) {
		System.out.print("\nThere are "+bst.getNumberOfLeaves()+" leaf nodes in the binary search tree.");
		System.out.print("\nThere are "+rbt.getNumberOfLeaves()+" leaf nodes in the red-black tree.");
	}

	/**
	 * method: option2
	 * purpose: this method removes a integer of the user's choice
	 * @param bst Binary search tree 
	 * @param rbt Red-black tree
	 */
	private static void option2(BinarySearchTree<Integer> bst, RedBlackTree<Integer> rbt) {
		Scanner scan = new Scanner(System.in);
		int deleteNum = 0;
		boolean worked = false;
		
		System.out.print("\nWhat intger would you like to delete? ");
		do {
			try {
				deleteNum = scan.nextInt();
				scan.nextLine();
				worked = true;
			} catch (NumberFormatException e) {
				System.out.print("\nNot an integer value. Try again: ");
				worked = false;
			}
		} while (worked == false);
		
		if(!bst.contains(deleteNum)) {
			System.out.print("\n"+deleteNum+" does not exist.");
		} else {
			bst.delete(deleteNum);
			rbt.remove(deleteNum);
			System.out.print("\n"+deleteNum+" was deleted to the binary search tree and the red-black tree.");
		}
	}

	/**
	 * method: option1
	 * purpose: this method adds and integer of the user's choice
	 * @param bst Binary search tree
	 * @param rbt Red-black tree
	 */
	private static void option1(BinarySearchTree<Integer> bst, RedBlackTree<Integer> rbt) {
		Scanner scan = new Scanner(System.in);
		int insertNum = 0;
		boolean worked = false;
		
		System.out.print("\nWhat intger would you like to insert? ");
		do {
			try {
				insertNum = scan.nextInt();
				scan.nextLine();
				worked = true;
			} catch (NumberFormatException e) {
				System.out.print("\nNot an integer value. Try again: ");
				worked = false;
			}
		} while (worked == false);
		
		if(bst.contains(insertNum)) {
			System.out.print("\n"+insertNum+" already exists, ignore.");
		} else {
			bst.insert(insertNum);
			rbt.add(insertNum);
			System.out.print("\n"+insertNum+" was added to the binary search tree and the red-black tree.");
		}
	}
}
