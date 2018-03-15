package cs241_Project1;

/**
 * file: BinarySearchTree.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 1 
 * date last modified: 2/26/2018
 *
 * purpose: This program handles binary search tree methods
 * 
 * @author lrpca
 *
 * @param <T>
 */
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {
	/**
	 * method: BinarySearchTree constructor 
	 * purpose: this constructor is the default
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * method: BinarySearchTree constructor 
	 * purpose: this constructor sets the root
	 * a node with data
	 * 
	 * @param rootEntry
	 *            Root data
	 */
	public BinarySearchTree(T rootEntry) {
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	/**
	 * method: contains 
	 * purpose: this method checks is the binary search tree has a
	 * certain piece of data
	 * 
	 * @param entry
	 *            Data being searched for in tree
	 * @return true if data is found, otherwise false
	 */
	@Override
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}

	/**
	 * method: delete 
	 * purpose: this method deletes data from the binary search tree
	 * 
	 * @param entry
	 *            Data being deleted
	 * @return Data being deleted
	 */
	@Override
	public T delete(T entry) {
		BinaryNode<T> oldEntry = new BinaryNode<>(null);
		BinaryNode<T> newRoot = deleteEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.getData();
	}

	/**
	 * method: deleteEntry 
	 * purpose: this method deletes data from binary search tree
	 * recursively
	 * 
	 * @param rootNode
	 *            Root node
	 * @param entry
	 *            Data being deleted
	 * @param oldEntry
	 *            Node that will take the data of the node being deleted
	 * @return Node being deleted
	 */
	private BinaryNode<T> deleteEntry(BinaryNode<T> rootNode, T entry, BinaryNode<T> oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if (comparison == 0) {
				oldEntry.setData(rootData);
				rootNode = deleteFromNode(rootNode);
			} else if (comparison < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = deleteEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			} else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(deleteEntry(rightChild, entry, oldEntry));
			}
		}
		return rootNode;
	}

	/**
	 * method: deleteFromNode 
	 * purpose: this method deletes the node from the binary
	 * search tree
	 * 
	 * @param rootNode
	 *            Node being deleted
	 * @return Node being deleted
	 */
	private BinaryNode<T> deleteFromNode(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(deleteLargest(leftSubtreeRoot));
		} else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();
		} else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}

	/**
	 * method: deleteLargest 
	 * purpose: this method deletes the largest node
	 * 
	 * @param rootNode
	 *            Node being deleted
	 * @return Node being deleted
	 */
	private BinaryNode<T> deleteLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = deleteLargest(rightChild);
			rootNode.setRightChild(rightChild);
		} else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}

	/**
	 * method: findEntry 
	 * purpose: this method finds data in a binary search tree
	 * 
	 * @param rootNode
	 *            Node that methods starts searching from
	 * @param entry
	 *            Data that method is finding
	 * @return Data that method is finding
	 */
	private T findEntry(BinaryNode<T> rootNode, T entry) {
		T data = null;
		if (rootNode != null) {
			T rootData = rootNode.getData();
			if (entry.equals(rootData)) {
				data = rootData;
			} else if (entry.compareTo(rootData) < 0) {
				data = findEntry(rootNode.getLeftChild(), entry);
			} else {
				data = findEntry(rootNode.getRightChild(), entry);
			}
		}
		return data;
	}

	/**
	 * method: findLargest 
	 * purpose: this method finds the largest node in binary
	 * search tree
	 * 
	 * @param rootNode
	 *            Node that method starts searching from
	 * @return Largest node
	 */
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			rootNode = findLargest(rootNode.getRightChild());
		}
		return rootNode;
	}

	/**
	 * method: findPredecessor 
	 * purpose: this method finds the predecessor of a
	 * number in the binary search tree
	 * 
	 * @param data
	 *            Number that you are finding the predecessor to
	 * @return predecessor Returns predecessor of the number
	 */
	public int findPredecessor(int data) {
		int predecessor = 0;
		Integer[] inorderArray = this.inorderTraverseArray();
		predecessor = findInorderPredecessor(inorderArray, data, 0);
		return predecessor;
	}

	/**
	 * method: findInorderPredecessor 
	 * purpose: this method finds the predecessor of
	 * a number in the binary search tree recursively
	 * 
	 * @param data
	 *            Number that you are finding the predecessor to
	 * @param inorderArray
	 *            Array with data in the in-order traversal
	 * @param counter
	 *            Keep track of place in array
	 * @return predecessor Returns predecessor of the number
	 */
	private int findInorderPredecessor(Integer[] inorderArray, int data, int counter) {
		int predecessor = data;
		if (data != inorderArray[0]) {
			if (data == inorderArray[counter]) {
				predecessor = inorderArray[counter - 1];
			} else if (counter < inorderArray.length - 1) {
				predecessor = findInorderPredecessor(inorderArray, data, counter + 1);
			}
		}
		return predecessor;
	}

	/**
	 * method: findSuccessor 
	 * purpose: this method finds the successor of a number in
	 * the binary search tree
	 * 
	 * @param data
	 *            Number that you are finding the successor to
	 * @return predecessor Returns successor of the number
	 */
	public int findSuccessor(int data) {
		int successor = 0;
		Integer[] inorderArray = this.inorderTraverseArray();
		successor = findInorderSuccessor(inorderArray, data, 0);
		return successor;
	}

	/**
	 * method: findInorderSuccessor 
	 * purpose: this method finds the successor of a
	 * number in the binary search tree recursively
	 * 
	 * @param data
	 *            Number that you are finding the successor to
	 * @param inorderArray
	 *            Array with data in the in-order traversal
	 * @param counter
	 *            Keep track of place in array
	 * @return predecessor Returns successor of the number
	 */
	private int findInorderSuccessor(Integer[] inorderArray, int data, int counter) {
		int successor = data;
		if (inorderArray[counter + 1] != null) {
			if (data == inorderArray[counter]) {
				successor = inorderArray[counter + 1];
			} else if (counter < inorderArray.length - 1) {
				successor = findInorderSuccessor(inorderArray, data, counter + 1);
			}
		}
		return successor;
	}

	/**
	 * method: getEntry 
	 * purpose: this method gets data from binary search tree
	 * 
	 * @param entry
	 *            Data trying to be found
	 * @return Data method is getting
	 */
	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}

	/**
	 * method: insert 
	 * purpose: this method inserts data into the binary search tree
	 * 
	 * @param newEntry
	 *            Data being inserted
	 * @return Data being inserted
	 */
	@Override
	public T insert(T newEntry) {
		T data = null;
		if (isEmpty()) {
			setRootNode(new BinaryNode<>(newEntry));
		} else {
			data = insertEntry(getRootNode(), newEntry);
		}
		return data;
	}

	/**
	 * method: insertEntry 
	 * purpose: this method inserts data into the binary search
	 * tree recursively
	 * 
	 * @param rootNode
	 *            Starting node
	 * @param newEntry
	 *            Data being inserted
	 * @return Data being inserted
	 */
	private T insertEntry(BinaryNode<T> rootNode, T newEntry) {
		T data = null;

		int comparison = newEntry.compareTo(rootNode.getData());
		if (comparison == 0) {
			data = rootNode.getData();
			rootNode.setData(newEntry);
		} else if (comparison < 0) {
			if (rootNode.hasLeftChild()) {
				data = insertEntry(rootNode.getLeftChild(), newEntry);
			} else {
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
			}
		} else {
			if (rootNode.hasRightChild()) {
				data = insertEntry(rootNode.getRightChild(), newEntry);
			} else {
				rootNode.setRightChild(new BinaryNode<>(newEntry));
			}
		}
		return data;
	}

	/**
	 * method: listData 
	 * purpose: this method lists all the data between two points
	 */
	public ArrayList<T> listData(T a, T b) {
		ArrayList<T> list = new ArrayList<>(100);
		BinaryNode<T> rootNode = getRootNode();
		int comparisonA = a.compareTo(rootNode.getData());
		int comparisonB = b.compareTo(rootNode.getData());

		if (comparisonA < 0 && comparisonB > 0) {
			list.add(rootNode.getData());
			list = listDataHelper(rootNode.getLeftChild(), a, b, list);
			list = listDataHelper(rootNode.getRightChild(), a, b, list);
		} else if (comparisonB < 0) {
			list = listDataHelper(rootNode.getLeftChild(), a, b, list);
		} else {
			list = listDataHelper(rootNode.getRightChild(), a, b, list);
		}

		return list;
	}

	/**
	 * method: listDataHelper 
	 * purpose: this method helps list all the data between
	 * two points
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @param a
	 *            Minimum point
	 * @param b
	 *            Maximum point
	 * @param list
	 *            List of data
	 * @return List of data
	 */
	private ArrayList<T> listDataHelper(BinaryNode<T> rootNode, T a, T b, ArrayList<T> list) {
		if (rootNode != null) {
			int comparisonA = a.compareTo(rootNode.getData());
			int comparisonB = b.compareTo(rootNode.getData());

			if (comparisonA < 0 && comparisonB > 0) {
				list.add(rootNode.getData());
				list = listDataHelper(rootNode.getLeftChild(), a, b, list);
				list = listDataHelper(rootNode.getRightChild(), a, b, list);
			} else if (comparisonB < 0) {
				list = listDataHelper(rootNode.getLeftChild(), a, b, list);
			} else {
				list = listDataHelper(rootNode.getRightChild(), a, b, list);
			}
		}
		return list;
	}

	/**
	 * method: getNumberOfLeaves 
	 * purpose: this method finds how leaves are in the
	 * tree
	 */
	public int getNumberOfLeaves() {
		return getLeafCount(getRootNode());
	}

	/**
	 * method: getLeafCount 
	 * purpose: this method finds the number of leaves in the
	 * tree
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Height of tree
	 */
	private int getLeafCount(BinaryNode<T> rootNode) {
		if (rootNode == null) {
			return 0;
		}
		if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
			return 1;
		} else {
			return getLeafCount(rootNode.getLeftChild()) + getLeafCount(rootNode.getRightChild());
		}
	}

	/**
	 * method: getHeight 
	 * purpose: this method finds the height of the tree
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Height of tree
	 */
	public int getHeight(BinaryNode<T> rootNode) {
		int height = 0;

		if (rootNode != null) {
			height = 1 + Math.max(getHeight(rootNode.getLeftChild()), getHeight(rootNode.getRightChild()));
		}
		return height;
	}

	/**
	 * method: preorder 
	 * purpose: this method gets the preorder traversal of the tree
	 */
	public ArrayList<T> preorder(ArrayList<T> order) {
		return preorderTraverse(getRootNode(), order);
	}

	/**
	 * method: preorderTraverse 
	 * purpose: this method gets the preorder traversal of
	 * the tree
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @param order
	 *            Preorder array list
	 * @return Preorder array list
	 */
	private ArrayList<T> preorderTraverse(BinaryNode<T> rootNode, ArrayList<T> order) {
		if (rootNode != null) {
			order.add(rootNode.getData());
			order = preorderTraverse(rootNode.getLeftChild(), order);
			order = preorderTraverse(rootNode.getRightChild(), order);
		}
		return order;
	}

	/**
	 * method: setTree 
	 * purpose: this method sets binary search tree
	 * 
	 * @param rootData
	 *            Data of root
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}

	/**
	 * method: setTree 
	 * purpose: this method sets binary search tree
	 * 
	 * @param rootData
	 *            Data of root
	 * @param leftTree
	 *            Data left of the root
	 * @param rightTree
	 *            Data right of the root
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	}
}
