package com.powdermonkey.boggle;

import java.util.EnumSet;

public class DictionaryIncrementalSearch {

	public int index;
	public boolean valid;
	public boolean partial;
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
