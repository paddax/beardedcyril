package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dictionary2 implements IDictionary {
	private ArrayList<String> words;
	private int[][] alphabet;

	public Dictionary2() {
		words = new ArrayList<>();
		alphabet = new int[26][26];
	}

	@Override
	public void loadWords(InputStream is) throws IOException {
		BufferedReader sr = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = sr.readLine()) != null) {
			String s = line.trim().toLowerCase();
			if (s.length() > 2) {
				words.add(s);
			}
		}
		int sofar = 0;
		for (int i = 0; i < 26; i++) {
			char one = (char) ('a' + i);
			for(int j=0; j<26; j++) {
				alphabet[i][j] = -1;
				char two = (char) ('a' + j);
				search: while(sofar < words.size()) {
					String s = words.get(sofar);
					char oned = s.charAt(0);
					char twod = s.charAt(1);
					if(oned == one && twod == two) {
						alphabet[i][j] = sofar;
						break search;
					}
					if(oned > one || twod > two) {
						break search;
					}
					sofar++;
				}
			}
		}

	}
	
	private int lookupcount;

	@Override
	public DictionaryIncrementalSearch lookup(DictionaryIncrementalSearch s) {
		if (s.text.length() < 2)
			return s;

		if (s.index == -1) {
			int one = s.text.charAt(0) - 'a';
			int two = s.text.charAt(1) - 'a';
			s.index = alphabet[one][two];
			if(s.index == -1) {
				s.valid = false;
				return s;
			}
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
