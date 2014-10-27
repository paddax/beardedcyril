package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dictionary implements IDictionary {
	private ArrayList<String> words;
	private int[] alphabet;

	public Dictionary() {
		words = new ArrayList<>();
		alphabet = new int[26];
	}

	@Override
	public void loadWords(InputStream is) throws IOException {
		BufferedReader sr = new BufferedReader(new InputStreamReader(is));
		String line = null;
		char x = 'a' - 1;
		while ((line = sr.readLine()) != null) {
			String s = line.trim().toLowerCase();
			if (s.length() > 2) {
				char c = s.charAt(0);
				while (c != x) {
					x++;
					alphabet[x - 'a'] = words.size();
				}
				words.add(s);
			}
		}
		for (int i = 0; i < 26; i++) {
			//System.out.println("" + (char) ('a' + i) + " " + alphabet[i]);
		}

	}
	
	private int lookupcount;

	@Override
	public DictionaryIncrementalSearch lookup(DictionaryIncrementalSearch s) {
		if (s.text.length() == 0)
			return s;

		if (s.index == -1) {
			int index = s.text.charAt(0) - 'a';
			s.index = alphabet[index];
		}

		for (int i = s.index; i < words.size(); i++) {
			String test = words.get(i);
			lookupcount++;
			int comp = s.text.compareTo(test);
			if (comp == 0) {
				s.index = i + 1;
				s.valid = true;
				s.partial = true;
				return s;
			} else if (comp > 0) {
			} else if (comp < 0) {
				if(s.text.length() < test.length()) {
					if(test.substring(0, s.text.length()).compareTo(s.text) == 0) {
						s.partial = true;
						s.index = i;
						s.valid = false;
						return s;
					} else {
						s.valid = false;
						return s;
					}
				}
				else {
					s.valid = false;
					return s;
				}
			}

		}
		s.valid = false;
		return s;
	}
	
	
	
	public String toString() {
		return "" + lookupcount;
	}
}
