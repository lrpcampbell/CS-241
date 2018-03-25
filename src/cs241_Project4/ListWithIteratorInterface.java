package cs241_Project4;
/**
 * file: ListWithIteratorInterface.java 
 * author: Lindsey Campbell 
 * class: CS 241 – Data Structures and Algorithms II
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

import java.util.Iterator;

public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T> {
	public Iterator<T> getIterator();
}
