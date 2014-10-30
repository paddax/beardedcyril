package com.powdermonkey.boggle;

public class DictionaryIncrementalSearch {

	/**
	 * Internal index set by the IDictionary, used for optimisation
	 */
	public int index;

	/**
	 * After a call to lookup valid is true if the presented text is in the
	 * dictionary
	 */
	public boolean valid;

	/**
	 * After a call the lookup partial is set to true if the presented word
	 * could be valid if more characters were added.
	 * 
	 * Its also set to true at the same time as valid (bug / side effect /
	 * feature)
	 */
	public boolean partial;

	/**
	 * The text to search
	 */
	public String text;

	public DictionaryIncrementalSearch() {
		index = -1;
		text = "";
	}

	public DictionaryIncrementalSearch(String text, int index, boolean valid,
			boolean partial) {
		this.text = text;
		this.index = index;
		this.valid = valid;
		this.partial = partial;
	}

	public DictionaryIncrementalSearch clone() {
		return new DictionaryIncrementalSearch(text, index, valid, partial);
	}
}
