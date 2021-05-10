// Name and ID: Connor Lamont, 40169486
// COMP 249
// Assignment #4 Part 1
// Due Date: April 24, 2021

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Date Apr 21-2021
 * This is the main class file outlining the logic for a program to parse text files based on a specific set of rules
 * 
 * It contains methods for opening .txt files, removing any non-ASCII characters, and outputting three new files.
 * One file will contain all the words from the input file containing more than three vowels
 * One file will contain all the words from the input file starting with the letter 'o'
 * One file will contain all the distinct words from the input file
 * 
 * 
 * 
 * @author Connor Lamont
 * @version 1.0
 *
 */

public class Part1 {
	/**
	 * The main method for initiating and running the program to parse the input text file.
	 * 
	 * The user is asked to enter the file input name. 
	 * This filename is then used to open a new file input stream.
	 * 
	 * Each line from the input file is then added to a String ArrayList, which is then trimmed to remove all non-ASCII characters
	 * 
	 * The three parsing methods are then called on this String ArrayList before closing the input stream
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Get name of input file from user
		System.out.println("Please enter file name (spacing and capitalization matters!)");
		String fileName = scan.nextLine();
		
		// Try and open file input stream
		Scanner input = null;
		try {
			input = new Scanner(new FileInputStream(fileName));
		} 
		catch (FileNotFoundException e) {
			System.out.println("File " + fileName + " was not found");
			System.out.println("or could not be opened. Ending program");
			System.exit(0);
		} 
		
		// Read every word from input file and store in a string
		ArrayList<String> words = new ArrayList<String>();
		while (input.hasNext()) {
			words.add(input.next());
		}
		
		for (int i = 0; i < words.size(); i++) {
			words.set(i, words.get(i).replaceAll("[^a-zA-Z0-9]", "").trim());
		}
		
		outputVerbs(words);
		outputOs(words);
		outputDistinct(words);
		
		scan.close();
		
	}
	
	/**
	 * This method takes in a String ArrayList and parses it for all words containing more than three vowels.
	 * 
	 * An output stream is then opened, and the parsed words and their word count are printed to a new text file vowel_verbiage2.txt
	 * @param wordList String ArrayList<> containing all words from the original input file
	 */
	
	public static void outputVerbs(ArrayList<String> wordList) {
		// Declare an output stream object and initialize it to null
		PrintWriter output = null;
		// Try and open an output stream
		try {
			output = new PrintWriter(new FileOutputStream("vowel_verbiage2.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening/writing to vowel_verbage.txt");
			System.out.println("Exiting program");
			System.exit(0);
		}
		
		// Create a new ArrayList to store verbs
		ArrayList<String> vowelVerbs = new ArrayList<String>();
		// For each word in the word list
		for (String word: wordList) {
			int vowelCount = 0;
			// Get each letter in the word into an array
			String[] chars = word.split("");
			for (String character : chars) {
				// If the letter is a vowel, increment the vowel count
				if (character.equalsIgnoreCase("a") || character.equalsIgnoreCase("e")
					|| character.equalsIgnoreCase("i") || character.equalsIgnoreCase("o")
					|| character.equalsIgnoreCase("u"))
					vowelCount++;
			}
			// If the vowel count is greater than 3, add it to the verbs arrayList
			if (vowelCount > 3) {
				vowelVerbs.add(word);
			}
		}
		
		// Print the word count and each word to the output file
		output.println("Word Count: " + vowelVerbs.size());
		for (String word: vowelVerbs) {
			output.println(word);
		}
		
		// Close the output stream
		output.close();
	}
	
	/**
	 * This method takes in a String ArrayList and parses it for all words beginning with the letter 'o'
	 * 
	 * An output stream is then opened, and the parsed words and their word count are printed to a new text file obsessive_o2.txt
	 * @param wordList String ArrayList<> containing all words from the original input file
	 */
	public static void outputOs(ArrayList<String> wordList) {
		// Declare and try to initialize a new output stream
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream("obsessive_o2.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening/writing to obsessive_o.txt");
			System.out.println("Exiting program");
			System.exit(0);
		}
		
		// Declare and initialize a new string array list
		ArrayList<String> obsessives = new ArrayList<String>();
		
		// For each word in parameter array list
		// If it starts with 'o', add it to the obsessives array list
		for (String word: wordList) {
			if (word.isEmpty())
				continue;
			
			String firstChar = word.substring(0, 1);
			if (firstChar.equalsIgnoreCase("o"))
				obsessives.add(word);
		}
		
		// Print the word count and each word from the obsessives array list to the output stream
		output.println("Word Count: " + obsessives.size());
		for (String word: obsessives) {
			output.println(word);
		}
		// Close the output stream
		output.close();
	}

	/**
	 * This method takes in a String ArrayList and parses it for distinct words
	 * 
	 * An output stream is then opened, and the parsed words and their word count are printed to a new text file distinct_data2.txt
	 * @param wordList String ArrayList<> containing all words from the original input file
	 */
	public static void outputDistinct(ArrayList<String> wordList) {
		// Open a new output stream
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream("distinct_data2.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening/writing to distinct_data.txt");
			System.out.println("Exiting program");
			System.exit(0);
		}
		
		// Declare and initialize new String ArrayList<> to hold distinct words
		ArrayList<String> distinctWords = new ArrayList<String>();
		
		// For each word in passed ArrayList, if our distinct word ArrayList contains it or it is blank, skip it. Otherwise add it to the 
		// Distinct word array list
		for (String word: wordList) {
			if (word.isBlank())
				continue;
			else if (distinctWords.contains(word))
				continue;
			else
				distinctWords.add(word);
		}
		
		// Print the number of distinct words followed by each distinct word to the output file
		output.println("Word Count: " + distinctWords.size());
		for (String word: distinctWords) {
			output.println(word);
		}
		// Close the output stream
		output.close();
	}
}
