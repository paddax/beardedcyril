package com.powdermonkey.boggle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Wordsearch {

	public Wordsearch() {
	}

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("sowpods_eu.txt");
			Wordsearch x = new Wordsearch();
			Dictionary dictionary = new Dictionary();
			dictionary.loadWords(fis);
			
			Board b = new Board(4);
			b.load(new FileInputStream("dice2014.txt"));
			b.shake();
			System.out.println(b.toString());
			
			char[][] r = b.getRoll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
