// Name and ID: Connor Lamont, 40169486
// COMP 249
// Assignment #4 Part 2
// Due Date: April 24, 2021

package Part2;
/**
 * Date Apr 21-2021
 * This is a very simple interface which requires any class implementing it to define a method isOnSameTime
 * @author Connor Lamont
 * @version 1.0
 *
 */
public interface Watchable {
	/**
	 * This method declaration will be defined by any class implementing the Watchable interface
	 * @param S TVShow to be compared to the calling object
	 * @return String
	 */
	public String isOnSameTime(TVShow S);
}
