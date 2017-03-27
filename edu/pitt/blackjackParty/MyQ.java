
package edu.pitt.blackjackParty;

/**
 *
 * @author adamobryan
 */

// CS 0445 Spring 2016
// MyQ<T> interface
// The overall logic of the MyQ<T> is the following:
// Data is logically "added" in the same order that it is "removed".

public interface MyQ<T>
{
// Add a new Object to the MyQ in the next available location. If
// all goes well, return true. If there is no room in the MyQ for
// the new item, return false (you do NOT have to resize it)
public boolean addItem(T item);
// Remove and return the "oldest" item in the MyQ. If the MyQ
// is empty, return null
public T removeItem();
// Return true if the MyQ is full, and false otherwise
public boolean full();
// Return true if the MyQ is empty, and false otherwise
public boolean empty();
// Return the number of items currently in the MyQ
public int size();
// Reset the MyQ to empty status by reinitializing the variables
// appropriately
public void clear();
}