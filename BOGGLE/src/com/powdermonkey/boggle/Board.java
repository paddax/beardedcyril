package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Board {
	//purple-alert woz ere
	private char[][] dice;
	private int cdice;
	private char[][] roll;
	private int square;

	public Board(int square) {
		cdice = square * square;
		this.square = square;
		dice = new char[cdice][6];
		roll = new char[square][square];
	}

	public void load(InputStream is) throws IOException {
		BufferedReader sr = new BufferedReader(new InputStreamReader(is));
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
	}
	
	public char[][] getRoll() {
		return roll;
	}

	public void shake() {
		LinkedList<Integer> di = new LinkedList<>();
		for(int i=0; i<cdice; i++) {
			di.add(i);
		}
		for (int i = 0; i < cdice; i++) {
			int side = (int) (6 * Math.random());
			int diceref = (int)(di.size() * Math.random());
			int diceind = di.get(diceref);
			di.remove(diceref);
			roll[i / square][i % square] = dice[diceind][side];
		}
	}

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
	
	public static void main(String [] args) {
		Board b = new Board(4);
		try {
			b.load(new FileInputStream("dice2014.txt"));
			b.shake();
			System.out.println(b.toString());
			
			System.out.println("shu".compareTo("shuft"));
			System.out.println("shun".compareTo("shuft"));
		} catch (IOException e) {
		}
	}
}
