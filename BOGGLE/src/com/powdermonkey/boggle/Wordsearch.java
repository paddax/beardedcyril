package com.powdermonkey.boggle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Wordsearch {

	private IDictionary dictionary;
	
	private char[][] roll = {
		{'u', 'n', 't', 'i'},
		{'o', 't', 'e', 's'},
		{'y', 'a', 'w', 'r'},
		{'s', 'e', 'h', 'e'},
	};
	
	private HashSet<String> answer;
	
	public Wordsearch() {
		try {
			//FileInputStream fis = new FileInputStream("sowpods_eu.txt");
			FileInputStream fis = new FileInputStream("TWL06.txt");
			dictionary = new Dictionary2();
			dictionary.loadWords(fis);
			answer = new HashSet<>();

			Board b = new Board(4);
			b.load(new FileInputStream("dice2014.txt"));
			b.shake();
			System.out.println(b.toString());

			//roll = b.getRoll();

			long t = System.currentTimeMillis();
			
			solve();
			
			t = System.currentTimeMillis() - t;
			ArrayList<String> sorted = new ArrayList<>(answer);
			Collections.sort(sorted);
			for(String s: sorted) {
				System.out.println(s);
			}
			
			System.out.println("Total answers found: " + sorted.size() + " in " + t + "ms");
			System.out.println("Solve count: " + solvecount);
			System.out.println("Dictionary lookup: " + dictionary.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Wordsearch();
	}

	private void solve() {
		boolean[][] path = new boolean[roll.length][roll.length];

		for (int i = 0; i < roll.length; i++) {
			for (int j = 0; j < roll.length; j++) {
				DictionaryIncrementalSearch s = new DictionaryIncrementalSearch();
				search(s, duplo(path), i, j);
			}
		}
	}

	private int solvecount = 0;
	
	private void search(DictionaryIncrementalSearch s, boolean[][] path, int i, int j) {
		path[i][j] = true;
		s.text = s.text + roll[i][j];
		if(s.text.length() > 2) {
			solvecount++;
			dictionary.lookup(s);
			if(!s.partial)
				return;
			if(s.valid) 
				answer.add(s.text);
		}
		if (i > 0) { // left
			if (path[i - 1][j] != true) {
				search(s.clone(), duplo(path), i - 1, j);
			}
		}
		if (i + 1 < path.length) { // right
			if (path[i + 1][j] != true) {
				search(s.clone(), duplo(path), i + 1, j);
			}
		}
		if (j > 0) { // up
			if (path[i][j - 1] != true) {
				search(s.clone(), duplo(path), i, j - 1);
			}
		}
		if (j + 1 < path.length) { // down
			if (path[i][j + 1] != true) {
				search(s.clone(), duplo(path), i, j + 1);
			}
		}
		if (i > 0 && j > 0) { // left up
			if (path[i - 1][j - 1] != true) {
				search(s.clone(), duplo(path), i - 1, j - 1);
			}
		}

		if (i > 0 && j + 1 < path.length) { // left down
			if (path[i - 1][j + 1] != true) {
				search(s.clone(), duplo(path), i - 1, j + 1);
			}
		}

		if (i + 1 < path.length && j > 0) { // right up
			if (path[i + 1][j - 1] != true) {
				search(s.clone(), duplo(path), i + 1, j - 1);
			}
		}
		if (i + 1 < path.length && j + 1 < path.length) { // right down
			if (path[i + 1][j + 1] != true) {
				search(s.clone(), duplo(path), i + 1, j + 1);
			}
		}
	}

	private boolean[][] duplo(boolean[][] path) {
		boolean[][] p = new boolean[path.length][path.length];
		for (int i = 0; i < path.length; i++) {
			System.arraycopy(path[i], 0, p[i], 0, path[i].length);
		}
		return p;
	}
}
