// Name and ID: Connor Lamont, 40169486
// COMP 249
// Assignment #4 Part 2
// Due Date: April 24, 2021

package Part2;
import java.util.Scanner;
/**
 * Date Apr 21-2021
 * This is a class file defining a TVShow. 
 * 
 * It contains a parameterized constructor, a simple copy constructor, a parameterized copy constructor, mutator & accessor methods, 
 * a clone method, and overridden equals and toString methods.
 * 
 * It implements the Watchable interface, and defines the logic for the isOnSameTime method to determine if two TVShow objects have overlapping timeslots
 * @author Connor Lamont
 * @version 1.0
 *
 */
public class TVShow implements Watchable {
	/**
	 * String variable for storing unique show ID
	 */
	private String showID;
	/**
	 * String variable for storing show name
	 */
	private String showName;
	/**
	 * double variable for storing show start time (24H)
	 */
	private double startTime;
	/**
	 * double variable for storing show end time (24H)
	 */
	private double endTime;

	/**
	 * Parameterized constructor. Initializes all of a new TVShow objects values to those of the parameters
	 * @param showID String containing the show ID
	 * @param showName String containing the show name
	 * @param startTime double containing the show start time
	 * @param endTime double containing the show end time
	 */
	public TVShow(String showID, String showName, double startTime, double endTime) {
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Copy constructor initializing all of a new TVShow objects values to those of the parameter TVShow
	 * @param original reference value to the TVShow object being copied
	 */
	public TVShow(TVShow original) {
		this.showID = original.showID;
		this.showName = original.showName;
		this.startTime = original.startTime;
		this.endTime = original.endTime;
	}
	
	/**
	 * Copy constructor that also creates a new show ID different than the copied TVShow object
	 * @param original reference value to the TVShow object being copied
	 * @param newID String containing the new show ID
	 */
	public TVShow(TVShow original, String newID) {
		this.showID = newID;
		this.showName = original.showName;
		this.startTime = original.startTime;
		this.endTime = original.endTime;
	}
	
	/**
	 * This clone method asks for a user to input a new show ID. It then returns a new TVShow object identical to the calling TVShow, but
	 * with the user input as its show ID
	 * @return returns the cloned TVShow object
	 */
	public TVShow clone() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a new showID:");
		String newID = scan.nextLine();
		scan.close();
		return new TVShow(this, newID);
	}
	
	/**
	 * Mutator method to change the calling TVShow's show name
	 * @param newID String containing the new show ID
	 */
	public void setShowID(String newID) {
		this.showID = newID;
	}
	/**
	 * Mutator method to change the calling TVShow's show name
	 * @param newName String containing the new show name
	 */
	public void setShowName(String newName) {
		this.showName = newName;
	}
	/**
	 * Mutator method to change the calling TVShow's start time
	 * @param newStartTime double containing the new start time
	 */
	public void setStartTime(double newStartTime) {
		this.startTime = newStartTime;
	}
	/**
	 * Mutator method to change the calling TVShow's end time
	 * @param newEndTime double containing the new end time
	 */
	public void setEndTime(double newEndTime) {
		this.endTime = newEndTime;
	}
	
	/**
	 * Accessor method to get the calling TVShow's show id
	 * @return return the calling objects show ID
	 */
	public String getShowID() {
		return this.showID;
	}
	/**
	 * Accessor method to get the calling TVShow's show name
	 * @return return the calling objects show name
	 */
	public String getShowName() {
		return this.showName;
	}
	/**
	 * Accessor method to get the calling TVShow's start time
	 * @return return the calling objects start time
	 */
	public double getStartTime() {
		return this.startTime;
	}
	/**
	 * Accessor method to get the calling TVShow's end time
	 * @return return the calling objects end time
	 */
	public double getEndTime() {
		return this.endTime;
	}
	
	/**
	 * This method compares the start and end times of the calling TVShow and the TVShow object passed as a parameter.
	 * If the calling show ends before the passed show begins, or the calling object begins after the passed show ends, they are on different timeslots
	 * If the calling show and passed show have the same start time, they air at the same time
	 * For all other cases, there is some overlap
	 *
	 */
	
	public String isOnSameTime(TVShow otherShow) {
		// Case 1: Different show times
		if (this.endTime <= otherShow.startTime || this.startTime >= otherShow.endTime)
			return "Different Time";
		// Case 2: Same start time
		else if (this.startTime == otherShow.startTime)
			return "Same Time";
		// Case 3: Not different show times and not same start time
		else
			return "Some Overlap";
	}
	
	
	// toString method
	@Override
	public String toString() {
		return (showName + " (ShowID " + showID + ") starts at " + startTime
				+ " and ends at " + endTime);
	}
	// equals method
	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		else {
			TVShow otherShow = (TVShow)obj;
			return (this.showName.equals(otherShow.showName) &&
					this.startTime == otherShow.startTime &&
					this.endTime == otherShow.endTime);
		}
	}
	
	
}
