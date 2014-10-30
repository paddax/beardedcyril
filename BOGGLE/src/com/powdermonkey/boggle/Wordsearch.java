package com.powdermonkey.boggle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * The solve method brings together a Dictionary and a roll and generates all
 * possible words
 * 
 * @author paddax@gmail.com
 * 
 */
public class Wordsearch {

	private IDictionary dictionary;

	private HashSet<String> answer;

	private char[][] roll;

	public Wordsearch() {
	}

	public List<String> solve(char[][] roll2, IDictionary dictionary) {
		answer = new HashSet<>();
		this.roll = roll2;
		this.dictionary = dictionary;
		boolean[][] path = new boolean[roll.length][roll.length];

		// for each dice in the grid generate a new start point to incrementally
		// search from
		for (int i = 0; i < roll.length; i++) {
			for (int j = 0; j < roll.length; j++) {
				DictionaryIncrementalSearch s = new DictionaryIncrementalSearch();
				search(s, duplo(path), i, j);
			}
		}

		ArrayList<String> sorted = new ArrayList<>(answer);
		Collections.sort(sorted);
		return sorted;
	}

	/**
	 * This works recursively, it gradually adds possible letters to the result
	 * until the dictionary tells it to stop.
	 * 
	 * @param dis
	 *            Incremental search object
	 * @param path
	 *            Dice already used
	 * @param i
	 *            The new dice to add x index
	 * @param j
	 *            The new dice to add y index
	 */
	private void search(DictionaryIncrementalSearch dis, boolean[][] path,
			int i, int j) {
		dis.text = dis.text + roll[i][j];

		// only perform a dictionary lookup when we have at least 3 letters (
		// >2)
		if (dis.text.length() > 2) {
			dictionary.lookup(dis);
			if (!dis.partial)
				return;
			if (dis.valid)
				answer.add(dis.text);
		}

		// At this point we have at least a partial word so we need to duplicate
		// the path before recursion
		path = duplo(path);

		// Mark this dice as searched
		path[i][j] = true;

		// Check each possible connection from this dice (check in range of
		// board and not previously searched)
		if (i > 0) { // left
			if (path[i - 1][j] != true) {
				search(dis.clone(), path, i - 1, j);
			}
		}
		if (i + 1 < path.length) { // right
			if (path[i + 1][j] != true) {
				search(dis.clone(), path, i + 1, j);
			}
		}
		if (j > 0) { // up
			if (path[i][j - 1] != true) {
				search(dis.clone(), path, i, j - 1);
			}
		}
		if (j + 1 < path.length) { // down
			if (path[i][j + 1] != true) {
				search(dis.clone(), path, i, j + 1);
			}
		}
		if (i > 0 && j > 0) { // left up
			if (path[i - 1][j - 1] != true) {
				search(dis.clone(), path, i - 1, j - 1);
			}
		}

		if (i > 0 && j + 1 < path.length) { // left down
			if (path[i - 1][j + 1] != true) {
				search(dis.clone(), path, i - 1, j + 1);
			}
		}

		if (i + 1 < path.length && j > 0) { // right up
			if (path[i + 1][j - 1] != true) {
				search(dis.clone(), path, i + 1, j - 1);
			}
		}
		if (i + 1 < path.length && j + 1 < path.length) { // right down
			if (path[i + 1][j + 1] != true) {
				search(dis.clone(), path, i + 1, j + 1);
			}
		}
	}

	/**
	 * Copies the current path array
	 * 
	 * @param path
	 *            Path to copy
	 * @return Copy of path
	 */
	private boolean[][] duplo(boolean[][] path) {
		boolean[][] p = new boolean[path.length][path.length];
		for (int i = 0; i < path.length; i++) {
			System.arraycopy(path[i], 0, p[i], 0, path[i].length);
		}
		return p;
	}
}
