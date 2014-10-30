package com.powdermonkey.boggle;

import java.io.IOException;
import java.io.InputStream;

/**
 * Common interface to the dictionary. This allows different dictionary
 * implementation to be tried without too much effort
 * 
 * @author paddax@gmail.com
 * 
 */
public interface IDictionary {

	/**
	 * Attempts to find the word dis.text in the list
	 * 
	 * @param dis
	 *            Search state thats updated by lookup for use in the next
	 *            search
	 * @return A reference to the dis parameter
	 */
	public abstract DictionaryIncrementalSearch lookup(
			DictionaryIncrementalSearch dis);

	/**
	 * Loads the dictionary from a stream, each word is expected to be in
	 * alphabetical order with a line seperator between each word.
	 * 
	 * <p>
	 * <b>NOTE:</b> The reader ensures all the words
	 * <ul>
	 * <li>Are a single case.
	 * <li>Greater than 2 characters long.
	 * </ul>
	 * 
	 * @param is
	 *            Stream containing words
	 * @throws IOException
	 */
	public abstract void loadWords(InputStream is) throws IOException;

}
