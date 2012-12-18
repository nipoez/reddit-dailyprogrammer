package com.reddit.dailyprogrammer.nipoez.dp114e;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class WordLadder {
	private static String wordsFilePath = System.getProperty("user.dir")
			+ "\\selected_four-letter_words.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> allWords = new ArrayList<String>();
		try {

			// Use Scanner for a first pass of tokenization,
			// based on whitespace
			Scanner tokenize = new Scanner(new File(wordsFilePath));
			while (tokenize.hasNext()) {
				// Use StringTokenizer for a second pass of tokenization,
				// treating certain punctuation characters as both
				// delimiters and tokens
				allWords.add(tokenize.next().toLowerCase());
			}
			tokenize.close();
		} catch (Exception e) {// Poor practice exception handling
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println("------- Validation: puma -------");
		String ladderStart = "puma";
		for (String word : allWords) {
			if (StringUtils.getLevenshteinDistance(ladderStart, word, 1) > 0) {
				System.out.println(word);
			}
		}
		System.out.println("------- Easy Challenge: best -------");
		ladderStart = "best";
		int bestCount = 0;
		for (String word : allWords) {
			if (StringUtils.getLevenshteinDistance(ladderStart, word, 1) > 0) {
				++bestCount;
			}
		}
		System.out.println("\"best\" has " + bestCount + " neighbors");
		System.out.println("------- Bonus 1: 33 words -------");
		int neighborCount = 0;
		for (String levenshteinLeft : allWords) {
			neighborCount = 0;
			for (String levenshteinRight : allWords) {
				if (StringUtils.getLevenshteinDistance(levenshteinLeft,
						levenshteinRight, 1) > 0) {
					++neighborCount;
				}

			}
			if (neighborCount == 33) {
				System.out.println("\"" + levenshteinLeft + "\" has " + neighborCount
						+ " neighbors.");
			}
		}
	}
}
