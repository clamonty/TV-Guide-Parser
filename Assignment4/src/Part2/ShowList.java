// Name and ID: Connor Lamont, 40169486
// COMP 249
// Assignment #4 Part 2
// Due Date: April 24, 2021

package Part2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Date Apr 21-2021
 * This is a class file defining a ShowList linked list class
 * 
 * It defines a private ShowNode class to represent data nodes.
 * 
 * It contains a default constructor, copy constructor, accessor methods, methods to add/delete ShowNodes from the head of the list,
 * methods to delete, add, replace, or insert ShowNodes at specific locations in the list, methods to search the list for specific
 * shows or show attributes, and a method to determine whether shows on the list have conflicting time slots.
 * 
 * @author Connor Lamont
 * @version 1.0
 *
 */
public class ShowList {
	// ShowNode inner class
	/**
	 * This is a private inner class to represent data nodes on the ShowList linked list data structure.
	 * It contains a default constructor, parameterized constructor, copy constructor, clone method, and accessor/mutator methods
	 * @author Connor Lamont
	 *
	 */
	private class ShowNode {
		/**
		 * TVShow variable to represent a ShowNodes TVShow object
		 */
		private TVShow show;
		/**
		 * ShowNode variable to represent the next node in the linked list
		 */
		private ShowNode link;
		
		/**
		 * Default constructor. 
		 * Sets both the show and link of the ShowNode to null
		 */
		public ShowNode() {
			show = null;
			link = null;
		}
		
		/**
		 * Parameterized constructor. Sets the show and link of the new ShowNode to the passed TVShow and ShowNode objects 
		 * @param show reference to TVShow ShowNode will represent
		 * @param linkValue reference to ShowNode this node will link to
		 */
		public ShowNode(TVShow show, ShowNode linkValue) {
			this.show = show;
			this.link = linkValue;
		}
		
		/**
		 * Copy constructor. Sets this nodes show and link to new TVShow and ShowNode objects made using copy constructors to make a deep copy
		 * @param otherNode reference to ShowNode node being copied
		 */
		public ShowNode(ShowNode otherNode) {
			this.show = new TVShow(otherNode.show);
			this.link = new ShowNode(otherNode.link);
		}
		
		/**
		 * This method initializes and returns a new ShowNode object made using the ShowNode copy constructor
		 * @return returns a newly initialized ShowNode object made using the ShowNode copy constructor
		 */
		public ShowNode clone() {
			return (new ShowNode(this));
		}
		
		// Mutator methods
		
		/**
		 * This mutator method changes the TVShow object reference value stored in the private show variable
		 * @param newShow reference value to the new TVShow object
		 */
		public void setShow(TVShow newShow) {
			this.show = newShow;
		}
		/**
		 * This mutator method changes the ShowNode object reference value stored in the private link variable
		 * @param newLink reference value to the new ShowNode object 
		 */
		public void setLink(ShowNode newLink) {
			this.link = newLink;
		}
		
		// Accessor methods
		/**
		 * This accessor method returns the reference value to the TVShow object stored in show
		 * @return returns the TVShow object stored in show
		 */
		public TVShow getShow() {
			return new TVShow(show);
		}
		/**
		 * This accessor method returns the reference value to the ShowNode object stored in link
		 * @return returns the ShowNode object stored in link
		 */
		public ShowNode getLink() {
			return link;
		}
	}
	/**
	 * ShowNode variable used to store the head node of the ShowList linked list class
	 */
	private ShowNode head;
	/**
	 * int variable used to track the number of nodes in the ShowList linked list class
	 */
	private int size;
	
	/**
	 * Default constructor for the ShowList class.
	 * Sets the head instance variable to a null reference value and the size of the list to 0
	 */
	public ShowList() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Copy constructor to create a deep copy of the ShowList object parameter
	 * If the parameter ShowList is null, sets the head node of the new ShowList to null
	 * Otherwise, iteratively goes through each ShowNode in the parameter ShowList, cloning a deep copy of each node and adding them to the newly constructed ShowList object
	 * @param originalList reference value to the ShowList object being copied
	 */
	public ShowList(ShowList originalList) {
		// if passed list is null, set this head list to null
		if (originalList == null)
			this.head = null;
		else {
			ShowNode currentNode = originalList.head;
			ShowNode tail = null;
			
			// As long as our current node isnt the final tail node of the original
			while (currentNode != null) {
				// Create a new node
				ShowNode newNode = new ShowNode(currentNode.show, null);
				// If tail is not yet a node
				if (tail == null) {
					// Set the head to the new node and the tail to the head
					this.head = newNode;
					tail = head;
				} 
				// If tail has been set to a node
				else {
					// Add the new node to the back of the new list
					tail.link = newNode;
					tail = newNode;
				}
				// Move the current Node to the next item on the list
				currentNode = currentNode.link;
			}
		}
		this.size = originalList.size;
	}
	
	/**
	 * This method deletes the ShowNode node from the calling ShowList object at the parameter index.
	 * A NoSuchElementException is thrown in the case of an invalid index
	 * Otherwise, the method uses a temporary pointer ShowNode to traverse the list, setting the link value at the node prior to the index node to point to the node following the index node. 
	 * This effectively removes the indexed node from the ShowList object, where it is deleted in Java's automatic garbage collection
	 * @param index int value of the ShowNode to be deleted from the ShowList
	 */
	public void deleteFromIndex(int index) {
		try {
			if (index < 0 || index > size)
				throw new NoSuchElementException();
			
			// Create traversal ShowNode object starting at head node
			ShowNode previousNode = head;
			// Move the position node to node immediately before the index
			for (int i = 0; i < index - 1; i++) {
				previousNode = previousNode.link;
			}
			// Create temporary ShowNode and set it equal to the index show node
			ShowNode indexNode = previousNode.link;
			// Change the previous nodes link to the index nodes link
			previousNode.link = indexNode.link;
			// Set the indexed node to null
			// Garbage collection will remove the node
			indexNode = null;
			size--;
		} catch (NoSuchElementException e) {
			System.out.println("Fatal error: Invalid list index.");
			System.out.println("Terminating program.");
			System.exit(0);
		}
	}
	
	/**
	 * This method inserts a new ShowNode containing a reference to the parameter TVShow object at the parameter index.
	 * If the index is invalid a NoSuchElementException is thrown.
	 * Otherwise, a new ShowNode object is instantiated, and it is inserted between the index node and the previous node
	 * @param showToAdd reference value to the TVShow object to be added to the ShowList
	 * @param index int value of the index the new ShowNode is to be inserted at 
	 */
	public void insertAtIndex(TVShow showToAdd, int index) {
		try {
			// Throw exception if index is invalid
			if (index < 0 || index > size)
				throw new NoSuchElementException();
			// Create traversal Node starting at head node
			ShowNode previousNode = head;
			// Move traversal node to node immediately preceding index node
			for (int i = 0; i < index - 1; i++) {
				previousNode = previousNode.link;
			}
			// Store node after index in nextNode variable
			ShowNode nextNode = previousNode.link;
			// Create new node using TVShow parameter and set its link to the previous nodes link
			ShowNode newNode = new ShowNode(showToAdd, nextNode);
			// Add the new node to the linked list, inserting it before the previous and next nodes
			previousNode.link = newNode;
			size++;
		} catch (NoSuchElementException e) {
			System.out.println("Fatal error: Index outside bounds of list");
			System.out.println("Terminating program");
			System.exit(0);
		}
	}
	
	/**
	 * This method replaces a ShowNode at the parameter index with a new ShowNode representing the parameter TVShow object
	 * If the index is invalid, the method simply returns.
	 * Otherwise, a new ShowNode object is created representing the parameter TVShow, and it is inserted to replace the ShowNode at the parameter index. The previous ShowNode is then removed via Java's automatic garbage collection
	 * @param replacementShow reference value to the new TVShow that will replace the TVShow at the parameter index of the ShowList
	 * @param index int value of the index ShowNode to be replaced
	 */
	public void replaceAtIndex(TVShow replacementShow, int index) {
		// Return if invalid index
		if (index < 0 || index > size)
			return;
		
		// Move traversal node to position before index node
		ShowNode previousNode = head;
		for (int i = 0; i < index - 1; i++) {
			previousNode = previousNode.link;
		}
		
		// Store old index node in variable
		ShowNode oldIndexNode = previousNode.link;
		// Make replacement index node with replacement show, set its link to old index link
		ShowNode newIndexNode = new ShowNode(replacementShow, oldIndexNode.link);
		
		// Set the previous nodes link to the new index node
		previousNode.link = newIndexNode;
		// Delete the old index node
		oldIndexNode = null;
	}
	// Creates a new ShowNode from the parameter tv show with a pointer to the previous node
	public void addToStart(TVShow showName) {
		head = new ShowNode(showName, head);
		this.size++;
	}
	
	/**
	 * This method deletes the current head node by setting the head instance variable to the next node in the linked ShowList
	 * @return returns true if a head node was successfully deleted. Returns false if the list was empty
	 */
	public boolean deleteFromStart() {
		if (head != null) {
			head = head.getLink();
			size--;
			return true;
		} else
			return false;
	}
	
	
	
	/**
	 * This method returns a reference to the ShowNode in the ShowList containing the target showID parameter.
	 * It iteratively searches the ShowList, tracking the number of nodes searched. 
	 * If a match is found, it prints to the console the number of iterations and returns a reference to that node.
	 * If no match is found, it returns null
	 * @param target String containing the showID to be searched for
	 * @return Returns either a reference to a ShowNode in the ShowList if a match is found, or null if no match is found
	 */
	private ShowNode find(String target) {
		ShowNode position = head;
		String showAtPosition;
		int iterations = 0;
		// While position node is not null
		while (position != null) {
			iterations++;
			// set showAtPosition to showID of position
			showAtPosition = position.getShow().getShowID();
			// If showAtPosition matches target string, return the position node
			if (showAtPosition.equals(target)) {
				System.out.println(target + " (" + position.getShow().getShowName() + ") was found after " + iterations + " iterations.");
				return position;
			}
			position = position.getLink();
		}
		//System.out.println(target + " was not found after " + iterations + " iterations.");
		// Return null if target string not found
		return null;
	}
	
	/**
	 * This helper method behaves identically to the .find() method but does not track the number of iterations it takes to find a matching ShowNode. It is used to avoid excess console messages when calling the .returnShow() method
	 * @param target String containing the target showID to be searched for
	 * @return Returns either a reference to the ShowNode containing the target showID, or null if no match is found
	 */
	private ShowNode findWithoutCount(String target) {
		ShowNode position = head;
		String showAtPosition;
		while (position != null) {
			// set showAtPosition to showID of position
			showAtPosition = position.getShow().getShowID();
			// If showAtPosition matches target string, return the position node
			if (showAtPosition.equals(target)) {
				return position;
			}
			position = position.getLink();
		}
		//System.out.println(target + " was not found after " + iterations + " iterations.");
		// Return null if target string not found
		return null;
	}
	
	/**
	 * This method returns a reference to the TVShow object stored in the ShowNode returned by the .findWithoutCount() method. 
	 * @param showID String containing the showID of the TVShow to be returned
	 * @return Returns a new TVShow object matching the showID parameter
	 */
	public TVShow returnShow(String showID) {
		ShowNode dataNode = this.findWithoutCount(showID);
		return new TVShow(dataNode.getShow());
		//return dataNode.getShow(); 
	}
	
	/**
	 * This method determines whether a given TVShow can be watched given a list of TVShows already being watched.
	 * The parameter Strings are used to create new TVShow objects which then call the isOnSameTime method to determine if there are any overlapping time slots.
	 * If any overlap is found, a message is displayed to the console displaying the nature of the conflict.
	 * If no overlaps are found, a message is displayed to the console informing the user they can watch the desired show
	 * @param wishID String containing the showID of the show the user wishes to watch
	 * @param watchList ArrayList containing the showID's of the shows the user is currently watching
	 * @return Returns a string detailing whether or not the user is able to watch their desired show
	 */
	public String canWatch(String wishID, ArrayList<String> watchList) {
		// Create new TVShow object by returning the ShowNode TVShow that matches the wish ID
		TVShow wantToWatch = returnShow(wishID);
		// For each show the user is watching
		for (int i = 0; i < watchList.size(); i++) {
			// Create new TVShow object by returning the ShowNode TVShow that matches the watch ID
			TVShow watching = returnShow(watchList.get(i));
			// Return string if there is overlap or same start time
			if (wantToWatch.isOnSameTime(watching).equals("Different Time"))
				continue;
			else if (wantToWatch.isOnSameTime(watching).equals("Same Time"))
				return "User can't watch show " + wishID + " as he/she will begin show " + watchList.get(i) + " at the same time.";
			else if (wantToWatch.isOnSameTime(watching).equals("Some Overlap"))
				return "User can't watch show " + wishID + " as he/she is not finished watching show " + watchList.get(i);
		}
		
		// If no watchList show overlaps or starts at same time as the wish ID, return confirmation string.
		return "User can watch show " + wishID + " as he/she is not watching anything else during that time.";
	}
	
	/**
	 * This method returns true if a call to the .find() method returns a ShowNode reference. 
	 * Otherwise it returns false
	 * @param showID String containing the showID of the TVShow .find() will look for
	 * @return Returns true of false depending on whether the ShowList contains a ShowNode containing the parameter showID
	 */
	public boolean contains(String showID) {
		return (find(showID) != null);
	}
	
	/**
	 * This method iteratively traverses the ShowList and prints the contents of each ShowNode to the console
	 */
	public void outputList() {
		ShowNode position = head;
		while (position != null) {
			System.out.println(position.show);
			position = position.link;
		}
	}
	
	/**
	 * This method compares two ShowList objects to see whether they are equal.
	 * If the size of one list does not match the size of the other, they are not equal.
	 * Two position showNodes are then created, and the TVShows at each of the ShowList's ShowNodes are compared.
	 * If any of the ShowNodes at a given position are not equal, the ShowLists are not equal and the method returns false.
	 * If both lists are completely traversed without a mismatch being found, the ShowLists are equal and the method returns true
	 * @return Returns true if the two lists contain identical nodes at each position and false otherwise.
	 */
	public boolean equals(Object otherObj) {
		if (otherObj == null || this.getClass() != otherObj.getClass())
			return false;
		ShowList otherList = (ShowList)otherObj;
		if (this.size != otherList.size)
			return false;
			
		ShowNode position = this.head;
		ShowNode otherPosition = otherList.head;
		while (position != null) {
			TVShow positionShow = position.getShow();
			TVShow otherPositionShow = otherPosition.getShow();
			if (!positionShow.equals(otherPositionShow))
				return false;
			position = position.link;
			otherPosition = otherPosition.link;
		}
		return true; // return true only if no mismatch found
	}
	

}
