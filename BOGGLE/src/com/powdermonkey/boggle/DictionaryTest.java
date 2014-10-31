package com.powdermonkey.boggle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DictionaryTest {

	private AbstractDictionary[] dictionary = new AbstractDictionary[3];

	//@formatter:off
	private char[][] roll1 = {
		{'j', 'j', 'j', 'j'},
		{'j', 'j', 'j', 'j'},
		{'j', 'j', 'j', 'j'},
		{'j', 'j', 'j', 'j'},
	};
	private char[][] roll = {
			{'u', 'n', 't', 'i'},
			{'o', 't', 'e', 's'},
			{'y', 'a', 'w', 'r'},
			{'s', 'e', 'h', 'e'},
		};
	//@formatter:on

	private static final String dicfile = "TWL06.txt"; // "sowpods_eu.txt"

	public DictionaryTest() {
		try {
			dictionary[0] = new Dictionary();
			dictionary[0].loadWords(new FileInputStream(dicfile));

			dictionary[1] = new Dictionary2();
			dictionary[1].loadWords(new FileInputStream(dicfile));

			dictionary[2] = new Dictionary3();
			dictionary[2].loadWords(new FileInputStream(dicfile));
			Wordsearch search = new Wordsearch();

			Board b = new Board(4);
			b.load(new FileInputStream("dice2014.txt"));


			long[] time = new long[dictionary.length];
			int count = 0;
			List<String> sorted = null;
			while (true) {
				//b.shake();
				//System.out.println(b.toString());
				//roll = b.getRoll();
				for (int i = 0; i < 3; i++) {
					dictionary[i].resetStats();
					long t = System.currentTimeMillis();

					sorted = search.solve(roll, dictionary[i]);

					time[i] += System.currentTimeMillis() - t;
//					System.out.println("Total answers found: " + sorted.size()
//							+ " in " + t + "ms");
//					System.out.println(dictionary[i].toString());
				}
				count++;
				System.out.println((time[0]/count) + "," + (time[1]/count) + "," + (time[2]/count) + "," + sorted.size());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DictionaryTest();
	}

}
