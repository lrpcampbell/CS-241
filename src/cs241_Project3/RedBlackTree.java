package cs241_Project3;

/**
 * file: RedBlackTree.java
 * author: Lindsey Campbell
 * class: CS 241 – Data Structures and Algorithms II
 *
 * assignment: program 3
 * date last modified: 2/26/2018
 *
 * purpose: This program handles all the methods for the red-black trees
 * 
 * @author lrpca
 *
 */
import java.util.ArrayList;

import cs241_Project1.BinarySearchTree;

public class RedBlackTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {
	public RedBlackBinaryNode<T> parent;
	public RedBlackBinaryNode<T> grand;
	public RedBlackBinaryNode<T> predecessor;

	/**
	 * method: constructor 
	 * purpose: this constructor is the default
	 */
	public RedBlackTree() {
		super();
	}

	/**
	 * method: constructor 
	 * purpose: this constructor set the root node
	 * 
	 * @param rootEntry
	 *            Data for root node
	 */
	public RedBlackTree(T rootEntry) {
		super();
		setRootNode(new RedBlackBinaryNode<T>(rootEntry, null, null, false));
	}

	/**
	 * method: add 
	 * purpose: this method adds data into the tree
	 * 
	 * @param newEntry
	 *            Data being added
	 * @return Data being added
	 */
	public T add(T newEntry) {
		T data = null;

		if ((RedBlackBinaryNode<T>) getRootNode() == null) {
			setRootNode(new RedBlackBinaryNode<>(newEntry, null, null, false));
		} else {
			data = addEntry((RedBlackBinaryNode<T>) getRootNode(), newEntry);
		}

		return data;
	}

	/**
	 * method: addEntry 
	 * purpose: this method adds data into the tree recursively
	 * 
	 * @param rootNode
	 *            Root of tree/subtree
	 * @param newEntry
	 *            Data being added
	 * @return Data being added
	 */
	private T addEntry(RedBlackBinaryNode<T> rootNode, T newEntry) {
		T data = null;
		RedBlackBinaryNode<T> node = new RedBlackBinaryNode<>(newEntry, null, null, true);
		int comparison = newEntry.compareTo(rootNode.getData());

		if (comparison == 0) {
			data = rootNode.getData();
			rootNode = node;
			rootNode.setColor(false);
		} else if (comparison < 0) {
			if (rootNode.hasLeftChild()) {
				grand = rootNode;
				RedBlackBinaryNode<T> left = (RedBlackBinaryNode<T>) rootNode.getLeftChild();
				parent = left;
				data = addEntry(left, newEntry);
			} else {
				parent = rootNode;
				grand = parent;
				rootNode.setLeftChild(node);
				adjustAfterAdd(node);
			}
		} else {
			if (rootNode.hasRightChild()) {
				grand = rootNode;
				RedBlackBinaryNode<T> right = (RedBlackBinaryNode<T>) rootNode.getRightChild();
				parent = right;
				data = addEntry(right, newEntry);
			} else {
				parent = rootNode;
				grand = parent;
				rootNode.setRightChild(node);
				adjustAfterAdd(node);
			}
		}

		return data;
	}

	/**
	 * method: adjustAfterAdd 
	 * purpose: this method rebalances the tree and recolors
	 * some of the nodes
	 * 
	 * @param node
	 *            Node being looked at
	 */
	private void adjustAfterAdd(RedBlackBinaryNode<T> node) {
		RedBlackBinaryNode<T> grandLeft = (RedBlackBinaryNode<T>) grand.getLeftChild();
		RedBlackBinaryNode<T> grandRight = (RedBlackBinaryNode<T>) grand.getRightChild();

		if (parent.getColor() && node.getColor()) {
			if (parent == grandLeft && grandRight.getColor()) {
				parent.setColor(false);
				if (grand != getRootNode()) {
					grand.setColor(true);
				} else {
					grand.setColor(false);
				}
			} else if (parent == grandRight && grandLeft.getColor()) {
				parent.setColor(false);
				if (grand != getRootNode()) {
					grand.setColor(true);
				} else {
					grand.setColor(false);
				}
			} else if (node == parent.getRightChild() && parent == grandRight) {
				grand.setColor(true);
				parent.setLeftChild(grand);
				parent.setColor(false);
				grand = parent;
			} else if (node == parent.getLeftChild() && parent == grandRight) {
				grand.setRightChild(node);
				parent = node;
				grand.setColor(true);
				parent.setLeftChild(grand);
				parent.setColor(false);
				grand = parent;
			} else if (node == parent.getRightChild() && parent == grandLeft) {
				grand.setLeftChild(node);
				parent = node;
				grand.setColor(true);
				parent.setRightChild(grand);
				parent.setColor(false);
				grand = parent;
			} else if (node == parent.getLeftChild() && parent == grandLeft) {
				grand.setColor(true);
				parent.setRightChild(grand);
				parent.setColor(false);
				grand = parent;
			}
		}
	}

	/**
	 * method: remove 
	 * purpose: this method removes data from the tree
	 * 
	 * @param entry
	 *            Data being removed
	 * @return Data being removed
	 */
	public T remove(T entry) {
		RedBlackBinaryNode<T> oldEntry = new RedBlackBinaryNode<>(null, null, null, false);
		RedBlackBinaryNode<T> newRoot = removeEntry((RedBlackBinaryNode<T>) getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.getData();
	}

	/**
	 * method: removeEntry 
	 * purpose: this method removes data from the tree
	 * recursively
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @param entry
	 *            Data being removed
	 * @param oldEntry
	 *            Empty node
	 * @return Data being removed
	 */
	private RedBlackBinaryNode<T> removeEntry(RedBlackBinaryNode<T> rootNode, T entry, RedBlackBinaryNode<T> oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if (comparison == 0) {
				oldEntry.setData(rootData);
				rootNode = removeFromNode(rootNode);
			} else if (comparison < 0) {
				RedBlackBinaryNode<T> leftChild = (RedBlackBinaryNode<T>) rootNode.getLeftChild();
				rootNode.setLeftChild(removeEntry(leftChild, entry, oldEntry));
			} else {
				RedBlackBinaryNode<T> rightChild = (RedBlackBinaryNode<T>) rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}

		return rootNode;
	}

	/**
	 * method: removeFromNode 
	 * purpose: this method finds the predecessor to a node
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Root node
	 */
	private RedBlackBinaryNode<T> removeFromNode(RedBlackBinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			RedBlackBinaryNode<T> leftSubtreeRoot = (RedBlackBinaryNode<T>) rootNode.getLeftChild();
			grand = parent;
			parent = leftSubtreeRoot;
			predecessor = findLargest(leftSubtreeRoot);
			adjustForRemoval(rootNode, leftSubtreeRoot);
		} else if (rootNode.hasRightChild()) {
			rootNode = (RedBlackBinaryNode<T>) rootNode.getRightChild();
		} else {
			rootNode = (RedBlackBinaryNode<T>) rootNode.getLeftChild();
		}

		return rootNode;
	}

	/**
	 * method: adjustForRemoval 
	 * purpose: this method rebalances the tree after
	 * removal
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @param leftSubtreeRoot
	 *            Left child of Root node
	 */
	private void adjustForRemoval(RedBlackBinaryNode<T> rootNode, RedBlackBinaryNode<T> leftSubtreeRoot) {
		if (!predecessor.getColor()) {
			if (predecessor.hasLeftChild() || predecessor.hasRightChild()) {
				RedBlackBinaryNode<T> childPredecessor = (RedBlackBinaryNode<T>) predecessor.getLeftChild();
				if (childPredecessor.getColor()) {
					rootNode.setData(predecessor.getData());
					rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
					childPredecessor.setColor(false);
				}
			} else if (!predecessor.hasLeftChild() && !predecessor.hasRightChild()) {
				rootNode.setData(predecessor.getData());
				rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
				RedBlackBinaryNode<T> nullNode = new RedBlackBinaryNode<>(null, null, null, false);
				if (parent.hasLeftChild()) {
					parent.setRightChild(nullNode);
				} else {
					parent.setLeftChild(nullNode);
				}

				caseCheck(nullNode, rootNode);
			}
		} else {
			rootNode.setData(predecessor.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		}
	}

	/**
	 * method: caseCheck 
	 * purpose: this method checks all the cases and recolors the
	 * nodes
	 * 
	 * @param nullNode
	 *            Black null node used to show double black node
	 * @param rootNode
	 *            Root node/starting point
	 */
	private void caseCheck(RedBlackBinaryNode<T> nullNode, RedBlackBinaryNode<T> rootNode) {
		RedBlackBinaryNode<T> left = (RedBlackBinaryNode<T>) parent.getLeftChild();
		RedBlackBinaryNode<T> right = (RedBlackBinaryNode<T>) parent.getRightChild();

		if (parent == getRootNode()) {
			parent.setColor(false);
		} else if (left.getColor() || right.getColor()) {
			if (left == nullNode) {
				parent.setLeftChild(null);
				right.setColor(false);
				parent.setColor(true);

				grand.setRightChild(left);
				parent.setLeftChild(grand);
				grand = parent;
			} else {
				parent.setRightChild(null);
				left.setColor(false);
				parent.setColor(true);

				grand.setLeftChild(right);
				parent.setRightChild(grand);
				grand = parent;
			}
		} else if (!left.getColor() && !right.getColor() && !parent.getColor()) {
			if (left == nullNode) {
				right.setColor(true);
			} else {
				left.setColor(true);
			}
			caseCheck(nullNode, parent);
		} else if (!left.getColor() && !right.getColor() && parent.getColor()) {
			parent.setColor(false);
			if (left == nullNode) {
				right.setColor(true);
			} else {
				left.setColor(true);
			}
		} else if (left.getColor() || right.getColor()) {
			RedBlackBinaryNode<T> leftL = (RedBlackBinaryNode<T>) left.getLeftChild();
			RedBlackBinaryNode<T> rightL = (RedBlackBinaryNode<T>) right.getLeftChild();
			RedBlackBinaryNode<T> leftR = (RedBlackBinaryNode<T>) left.getRightChild();
			RedBlackBinaryNode<T> rightR = (RedBlackBinaryNode<T>) right.getRightChild();

			if (left == nullNode) {
				if (rightL.getColor() || rightR.getColor()) {
					if (rightL.getColor()) {
						rightL.setColor(parent.getColor());
					} else {
						rightR.setColor(parent.getColor());
					}
					parent.setColor(false);
					right.setColor(false);

					grand.setRightChild(left);
					parent.setLeftChild(grand);
					grand = parent;
				}
			} else {
				if (leftL.getColor() || leftR.getColor()) {
					left.setColor(parent.getColor());
					if (leftL.getColor()) {
						leftL.setColor(false);
					} else {
						leftR.setColor(false);
					}
					parent.setColor(false);

					grand.setLeftChild(right);
					parent.setRightChild(grand);
					grand = parent;
				}
			}

		}
	}

	/**
	 * method: removeLargest 
	 * purpose: this method removes the largest node
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Root node
	 */
	private RedBlackBinaryNode<T> removeLargest(RedBlackBinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			RedBlackBinaryNode<T> rightChild = (RedBlackBinaryNode<T>) rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		} else {
			rootNode = (RedBlackBinaryNode<T>) rootNode.getLeftChild();
		}

		return rootNode;
	}

	/**
	 * method: findLargest 
	 * purpose: this method finds the largest node
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Root node
	 */
	private RedBlackBinaryNode<T> findLargest(RedBlackBinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			grand = parent;
			parent = rootNode;
			rootNode = findLargest((RedBlackBinaryNode<T>) rootNode.getRightChild());
		}

		return rootNode;
	}

	/**
	 * method: getHeight 
	 * purpose: this method finds the height of the tree
	 * 
	 * @param rootNode
	 *            Root node/starting point
	 * @return Height of tree
	 */
	public int getHeight(RedBlackBinaryNode<T> rootNode) {
		int height = 0;

		if (rootNode != null) {
			height = 1 + Math.max(getHeight((RedBlackBinaryNode<T>) rootNode.getLeftChild()),
					getHeight((RedBlackBinaryNode<T>) rootNode.getRightChild()));
		}
		return height;
	}

	/**
	 * method: getNumberOfLeaves 
	 * purpose: this method finds how leaves are in the
	 * tree
	 */
	public int getNumberOfLeaves() {
		return getLeafCount((RedBlackBinaryNode<T>) getRootNode());
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
	private int getLeafCount(RedBlackBinaryNode<T> rootNode) {
		if (rootNode == null) {
			return 0;
		}
		if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
			return 1;
		} else {
			return getLeafCount((RedBlackBinaryNode<T>) rootNode.getLeftChild())
					+ getLeafCount((RedBlackBinaryNode<T>) rootNode.getRightChild());
		}
	}

	/**
	 * method: listData 
	 * purpose: this method lists all the data between two points
	 */
	public ArrayList<T> listData(T a, T b) {
		ArrayList<T> list = new ArrayList<>(100);
		RedBlackBinaryNode<T> rootNode = (RedBlackBinaryNode<T>) getRootNode();
		int comparisonA = a.compareTo(rootNode.getData());
		int comparisonB = b.compareTo(rootNode.getData());

		if (comparisonA < 0 && comparisonB > 0) {
			list.add(rootNode.getData());
			list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getLeftChild(), a, b, list);
			list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getRightChild(), a, b, list);
		} else if (comparisonB < 0) {
			list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getLeftChild(), a, b, list);
		} else {
			list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getRightChild(), a, b, list);
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
	private ArrayList<T> listDataHelper(RedBlackBinaryNode<T> rootNode, T a, T b, ArrayList<T> list) {
		if (rootNode != null) {
			int comparisonA = a.compareTo(rootNode.getData());
			int comparisonB = b.compareTo(rootNode.getData());

			if (comparisonA < 0 && comparisonB > 0) {
				list.add(rootNode.getData());
				list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getLeftChild(), a, b, list);
				list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getRightChild(), a, b, list);
			} else if (comparisonB < 0) {
				list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getLeftChild(), a, b, list);
			} else {
				list = listDataHelper((RedBlackBinaryNode<T>) rootNode.getRightChild(), a, b, list);
			}
		}

		return list;
	}

	/**
	 * method: preorder 
	 * purpose: this method gets the preorder traversal of the tree
	 */
	public ArrayList<T> preorder(ArrayList<T> order) {
		return preorderTraverse((RedBlackBinaryNode<T>) getRootNode(), order);
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
	private ArrayList<T> preorderTraverse(RedBlackBinaryNode<T> rootNode, ArrayList<T> order) {
		if (rootNode != null) {
			order.add(rootNode.getData());
			order = preorderTraverse((RedBlackBinaryNode<T>) rootNode.getLeftChild(), order);
			order = preorderTraverse((RedBlackBinaryNode<T>) rootNode.getRightChild(), order);
		}
		return order;
	}
}
