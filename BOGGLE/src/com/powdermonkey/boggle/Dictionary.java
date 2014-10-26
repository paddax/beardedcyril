package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> words;
	private int[] alphabet;

	public Dictionary() {
		words = new ArrayList<>();
		alphabet = new int[26];
	}

	public void loadWords(InputStream is) throws IOException {
		BufferedReader sr = new BufferedReader(new InputStreamReader(is));
		String line = null;
		char x = 'a' - 1;
		while ((line = sr.readLine()) != null) {
			String s = line.trim().toLowerCase();
			if (s.length() > 0) {
				char c = s.charAt(0);
				while (c != x) {
					x++;
					alphabet[x - 'a'] = words.size();
				}
				words.add(s);
			}
		}
		for (int i = 0; i < 26; i++) {
			System.out.println("" + (char) ('a' + i) + " " + alphabet[i]);
		}

	}

	public DictionaryIncrementalSearch lookup(DictionaryIncrementalSearch s,
			String l) {
		if (l.length() == 0)
			return s;

		if (s.index == 0) {
			int index = l.charAt(0) - 'a';
			s.index = alphabet[index];
		}

		for (int i = s.index; i < words.size(); i++) {
			String test = words.get(i);
			if(l.length() == test.length()) {
				// if the same length then we must be lexicographically earlier (result on same)
			}
			else if(l.length() < test.length()) {
				// if the length is shorter the substring must be lexicographically either the same or earlier
			}
			else {
				// if the length is longer the substring must be lexicographically either the same or earlier
			}
		}
		s.valid = false;
		return s;
	}

}
