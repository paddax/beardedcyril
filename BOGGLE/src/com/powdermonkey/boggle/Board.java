package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Boggle board.
 * 
 * A boggle board can be created of any size the only restriction is that it
 * must be square and a dice file exists.
 * 
 * @author paddax@gmail.com
 * 
 */
public class Board {
	// purple-alert woz ere
	//FlirtyMomma woz ere 2
	/**
	 * Array of dice with each member having six sides (the standard board can
	 * be represented as dice[16][6] (16 dice - 6 sides / 96 faces)
	 */
	private char[][] dice;

	/**
	 * Total number of dice on the board (was cdice)
	 */
	private int totaldice;

	/**
	 * The randomised upper faces of the dice in the boggle board, the standard
	 * board would be roll[4][4]
	 */
	private char[][] roll;

	/**
	 * The length of one side of the boggle board totaldice = square * square
	 */
	private int square;

	public Board(int square) {
		totaldice = square * square;
		this.square = square;
		dice = new char[totaldice][6];
		roll = new char[square][square];
	}

	/**
	 * Loads the game dice from an input stream, the stream must contain 6
	 * characters per line with at least one line for each defined die. Lines
	 * with less than 6 characters are ignored.
	 * 
	 * @param is
	 *            Stream from which to read the definition
	 * @throws IOException
	 *             On any IO problem or the definition file does not contain
	 *             enough dice.
	 */
	public void load(InputStream is) throws IOException {
		BufferedReader sr = new BufferedReader(new InputStreamReader(is));
		try {
			for (int i = 0; i < dice.length; i++) {
				String line = sr.readLine();
				if (line != null) {
					String s = line.trim().toLowerCase();
					if (s.length() == 6) {
						for (int j = 0; j < 6; j++) {
							dice[i][j] = s.charAt(j);
						}
					}
				} else {
					throw new IOException("Unable to read dice");
				}
			}
		} finally {
			is.close();
		}
	}

	/**
	 * Simple getter method of the current roll, this has no meaning if shake
	 * has not been called first
	 * 
	 * @return The current randomised roll
	 */
	public char[][] getRoll() {
		return roll;
	}

	/**
	 * Shakes the dice and randomly places the dice into the "square" grid.
	 */
	public void shake() {
		// Create a list of dice
		LinkedList<Integer> di = new LinkedList<>();
		for (int i = 0; i < totaldice; i++) {
			di.add(i);
		}

		// foreach dice in the list randomly remove one of the them then
		// randomly place a face from the dice into the board (roll)
		for (int i = 0; i < totaldice; i++) {
			int side = (int) (6 * Math.random());
			int diceref = (int) (di.size() * Math.random());
			int diceind = di.get(diceref);
			di.remove(diceref);
			roll[i / square][i % square] = dice[diceind][side];
		}
	}

	/**
	 * Creates a console writable version of the current roll
	 * 
	 * @return Displayable string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < square; i++) {
			for (int j = 0; j < square; j++) {
				sb.append(roll[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Board b = new Board(4);
		try {
			b.load(new FileInputStream("dice2014.txt"));
			b.shake();
			System.out.println(b.toString());
		} catch (IOException e) {
		}
	}
}
