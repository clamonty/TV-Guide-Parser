// Name and ID: Connor Lamont, 40169486
// COMP 249
// Assignment #4 Part 2
// Due Date: April 24, 2021

package Part2;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Date Apr 21-2021
 * This file acts as the main Driver for part 2 of this assignment. It contains the main method and logic for processing a user's TVShow wish list.
 * 
 * The method asks for a user to enter the file paths of the TVGuide and TVInterests input files. Input streams are then opened for each input file, and the data from each is read into the program. 
 * 
 * The wishlist is then processed, and any time conflicts between shows on the wishlist and shows currently on the watch list are displayed to the console. This informs the user of which of their desired shows can be watched. 
 * 
 * Several TVShow objects are then created and used to demonstrate the methods of the TVShow and ShowList classes. The results of each demonstration are printed to the console.
 * 
 * @author Connor Lamont
 * @version 1.0
 *
 */
public class ProcessWishlist {

	/**
	 * The main method for the ProcessWishlist driver class.
	 * @param args Array of String arguments
	 */
	public static void main(String[] args) {
		// Declare and initialize scanner object for recording user input
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter TV guide file name (capitalization and spelling matters!)");
		String guideFile = scan.nextLine();
		
		System.out.println("Please enter TV wish and watch list file name (capitalization and spelling matters!)");
		String interestFile = scan.nextLine();
		
		// Create two empty lists
		ShowList list1 = new ShowList();
		ShowList list2 = new ShowList();
		Scanner tvGuide = null;
		Scanner interestInput = null;
		try {
			tvGuide = new Scanner(new FileInputStream(guideFile));
			interestInput = new Scanner(new FileInputStream(interestFile));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening files.");
			System.out.println("Exiting program.");
			System.exit(0);
		}
		
		
		// Add each unique show to the first list
		while (tvGuide.hasNextLine()) {
			// Get the show ID and name in a string array
			String[] idAndName = tvGuide.nextLine().split(" ");
			// Get the show start time
			String[] startTime = tvGuide.nextLine().split(" ");
			// Get the show end time
			String[] endTime = tvGuide.nextLine().split(" ");
			// Skip over \n in file in between shows
			tvGuide.nextLine();
			
			// Parse the string arrays into variables
			String showID = idAndName[0];
			String showName = idAndName[1];
			double start = Double.parseDouble(startTime[1]);
			double end = Double.parseDouble(endTime[1]);
			
			// Initialize a new TVShow object using the parsed variables
			TVShow thisShow = new TVShow(showID, showName, start, end);
			
			// If list 1 contains any entries with the new shows showID, do not add it to the list
			if (!(list1.contains(thisShow.getShowID())))
				list1.addToStart(thisShow);
		}
		
		// Generate Array Lists of tv show ID's on watch list and wish list
		ArrayList<String> watchlist = new ArrayList<String>();
		ArrayList<String> wishlist = new ArrayList<String>();
		getWatchAndWish(watchlist, wishlist, interestInput);
		
		// For each show on wishlist, check if there is a conflict with shows being watched
		for (int i = 0; i < wishlist.size(); i++) {
			System.out.println(list1.canWatch(wishlist.get(i), watchlist));
		}
		System.out.println();
		
		// Ask user to input show ID's and look for them in the list
		while (true) {
			System.out.println("Please enter showID (q to exit):");
			String showID = scan.nextLine();
			if (showID.equalsIgnoreCase("q"))
				break;
			else
				list1.contains(showID);
		}
		System.out.println();
		System.out.println("Now demonstrating TVShow constructors and methods...");
		System.out.println();
		// TVShow objects with parameterized constructors
		TVShow jetsons = new TVShow("ABC123", "The_Jetsons", 17.00, 17.30);
		TVShow flintstones = new TVShow("ABC125", "The_Flintstones", 17.30, 18.00);
		TVShow scoobyDoo = new TVShow("ABC127", "Scooby_Doo", 17.30, 18.00);
		TVShow johnnyBravo = new TVShow("ABC129", "Johnny_Bravo", 17.45, 18.15);
		
		// TVShow object with copy constructor and same show ID
		TVShow jetsonsCopy = new TVShow(jetsons);
		// TVShow object with copy constructor and new show ID
		TVShow jetsonsCopyNewID = new TVShow(jetsons, "CBA321");
		
		// Demonstrate the toString() method
		System.out.println(jetsons);
		System.out.println(flintstones);
		System.out.println(scoobyDoo);
		System.out.println(johnnyBravo);
		System.out.println();
		// Demonstrate the TVShow equals() method
		System.out.println("Is the jetsons equal to the jetsons copy?");
		if (jetsons.equals(jetsonsCopy))
			System.out.println("Yes!");
		else
			System.out.println("No!");
		System.out.println();
		System.out.println("Is the jetsons equal to the jetsons copy with a new ID?");
		if (jetsons.equals(jetsonsCopyNewID))
			System.out.println("Yes!");
		else
			System.out.println("No!");
		System.out.println();
		// Demonstrate the isOnSameTime() method
		System.out.println(jetsons.isOnSameTime(flintstones));
		System.out.println();
		System.out.println(flintstones.isOnSameTime(scoobyDoo));
		System.out.println();
		System.out.println(scoobyDoo.isOnSameTime(johnnyBravo));
		System.out.println();
		
		// Demonstrate the clone method
		TVShow scooby2 = scoobyDoo.clone();
		System.out.println(scooby2);
		System.out.println();
		
		System.out.println("Now demonstrating ShowList constructors and methods...");
		System.out.println();
		
		// New list with deep copy constructor
		ShowList list3 = new ShowList(list1);
		// Outputting both lists
		System.out.println("Outputting list 1:");
		list1.outputList();
		System.out.println();
		System.out.println("Outputting list3:");
		list3.outputList();
		System.out.println();
		// Demonstrating equals method and deep copy
		System.out.println("Is list 3 a deep copy of list 1?");
		System.out.println(list3.equals(list1));
		System.out.println();
		
		// Demonstrating the add/delete/replace ShowList methods
		
		list2.addToStart(jetsons);
		list2.addToStart(flintstones);
		list2.addToStart(scoobyDoo);
		list2.addToStart(johnnyBravo);
		System.out.println("Outputting list 2:");
		list2.outputList();
		System.out.println();
		
		// Remove Johnny Bravo
		System.out.println("Removing Johnny Bravo...");
		list2.deleteFromStart();
		list2.outputList();
		System.out.println();
		
		// Replace scoobyDoo with Johnny Bravo
		System.out.println("Replacing flintstones with Johnny Bravo...");
		list2.replaceAtIndex(johnnyBravo, 1);
		list2.outputList();
		System.out.println();
		
		// Insert the jetsons copy with new ID show between flintstones and jonny bravo
		System.out.println("Inserting Flintstones between Jetsons & Johnny Bravo...");
		list2.insertAtIndex(flintstones, 2);
		list2.outputList();
		System.out.println();
		
		System.out.println("This concludes the demonstration. Closing all streams and exiting program. Have a nice day :)");
		scan.close();
		tvGuide.close();
		interestInput.close();
		
		
	}
	
	/**
	 * This helper method parses the TVShow interests file into two ArrayLists, one for the current watch list and one for the current wishlist
	 * @param watchlist ArrayList the watchlist TVShows are read into
	 * @param wishlist ArrayList the wishlist TVShows are read into
	 * @param inputStream Scanner input stream connected to the TV Interests input file
	 */
	public static void getWatchAndWish(ArrayList<String> watchlist, ArrayList<String> wishlist, Scanner inputStream) {
		String interestLine = inputStream.nextLine();
		while (true) {
			interestLine = inputStream.nextLine();
			if (interestLine.equalsIgnoreCase("wishlist"))
				break;
			else
				watchlist.add(interestLine);
		}
		while (inputStream.hasNextLine()) {
			interestLine = inputStream.nextLine();
			wishlist.add(interestLine);
		}
		
	}
	
	

}
