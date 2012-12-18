package com.reddit.dailyprogrammer.nipoez.dp114e;

import java.io.File;
import java.util.HashSet;
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
		Set<String> allWords = new HashSet<String>();
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
		String ladderStart = "puma";
		for (String word : allWords){
			if(StringUtils.getLevenshteinDistance(ladderStart, word, 1) > 0){
				System.out.println(word);
			}
		}
	}

}
