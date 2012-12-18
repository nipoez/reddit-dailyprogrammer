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
		String sampleWord = "puma";
		List<String> sampleNeighbors = getNeighbors(sampleWord, allWords);
		for (String neighbor : sampleNeighbors) {
			System.out.println(neighbor);
		}
		System.out.println("------- Easy Challenge: best -------");
		String challengeWord = "best";
		List<String> challengeNeighbors = getNeighbors(challengeWord, allWords);
		System.out.println("\"best\" has " + challengeNeighbors.size()
				+ " neighbors");
		System.out.println("------- Bonus 1: 33 words -------");
		int bonusOneCount = 33;
		for (String bonusOneWord : allWords) {
			if (getNeighbors(bonusOneWord, allWords).size() == bonusOneCount) {
				System.out.println("\"" + bonusOneWord + "\" has "
						+ bonusOneCount + " neighbors.");
			}
		}
	}

	private static List<String> getNeighbors(final String word,
			final List<String> allWords) {
		List<String> neighbors = new ArrayList<String>();
		for (String potentialNeighbor : allWords) {
			if (StringUtils.getLevenshteinDistance(word, potentialNeighbor, 1) > 0) {
				neighbors.add(potentialNeighbor);
			}
		}
		return neighbors;
	}
}
