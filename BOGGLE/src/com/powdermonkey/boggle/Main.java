package com.powdermonkey.boggle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Simple run test
 * @author paddax@gmail.com
 *
 */
public class Main {

	private static final String dicfile = "TWL06.txt"; // "sowpods_eu.txt"

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Construct and load a 2 letter index dictionary
		Dictionary2 dictionary = new Dictionary2();
		dictionary.loadWords(new FileInputStream(dicfile));

		// Construct a word search
		Wordsearch search = new Wordsearch();

		// Construct and load a board
		Board b = new Board(4);
		b.load(new FileInputStream("dice2014.txt"));

		b.shake();
		
		// t is used for collecting time information 
		long t = System.currentTimeMillis();
		// Solve the board and display result
		List<String> sorted = search.solve(b.getRoll(), dictionary);
		t = (System.currentTimeMillis() - t);
		
		// List the words found
		for(String s:  sorted) {
			System.out.println(s);
		}
		
		// Display the roll
		System.out.println();
		System.out.println(b);

		// Display the statistics
		System.out.println("Found " + sorted.size() + " words in " + t + "ms");
	}

}
