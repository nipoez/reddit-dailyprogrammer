package com.reddit.dailyprogrammer.nipoez.dp114e;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class WordLadder {
	private final static String WORDS_FILE_PATH = System
			.getProperty("user.dir") + "\\selected_four-letter_words.txt";
	private static Map<String, List<String>> neighborsCache = new HashMap<String, List<String>>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		// SET UP - Parse words
		//
		List<String> allWords = parseWords(WORDS_FILE_PATH);
		//
		// SAMPLE - validate "puma" results
		//
		System.out.println("------- Validation: puma -------");
		String sampleWord = "puma";
		List<String> sampleNeighbors = getNeighbors(sampleWord, allWords);
		for (String neighbor : sampleNeighbors) {
			System.out.println(neighbor);
		}
		//
		// EASY CHALLENGE
		//
		System.out.println("------- Easy Challenge: best -------");
		String challengeWord = "best";
		List<String> challengeNeighbors = getNeighbors(challengeWord, allWords);
		System.out.println("\"best\" has " + challengeNeighbors.size()
				+ " neighbors");
		//
		// BONUS 1
		//
		System.out.println("------- Bonus 1: 33 words -------");
		int bonusOneCount = 33;
		for (String bonusOneWord : allWords) {
			if (getNeighbors(bonusOneWord, allWords).size() == bonusOneCount) {
				System.out.println("\"" + bonusOneWord + "\" has "
						+ bonusOneCount + " neighbors.");
			}
		}
		//
		// BONUS 2
		//
		System.out
				.println("------- Bonus 2: Within 3 steps of \"best\" -------");
		String bonusTwoWord = "best";
		List<String> bonusTwoStepOne = getNeighbors(bonusTwoWord, allWords);
		List<String> bonusTwoStepTwo = new ArrayList<String>();
		List<String> bonusTwoStepThree = new ArrayList<String>();
		List<String> bonusTwoAll = new ArrayList<String>(bonusTwoStepOne);
		List<String> bonusTwoTmp = null;
		for (String step2Word : bonusTwoStepOne) {
			bonusTwoTmp = getNeighbors(step2Word, allWords, bonusTwoAll);
			bonusTwoStepTwo.addAll(bonusTwoTmp);
			bonusTwoAll.addAll(bonusTwoTmp);
		}
		for (String step3Word : bonusTwoStepTwo) {
			bonusTwoTmp = getNeighbors(step3Word, allWords, bonusTwoAll);
			bonusTwoStepThree.addAll(bonusTwoTmp);
			bonusTwoAll.addAll(bonusTwoTmp);
		}

		System.out.println("\"best\" has " + bonusTwoAll.size());

	}
	
	/**
	 * Parse all available words from a file.
	 * 
	 * @param fullPath Full path to the file
	 * @return all words from the file
	 */
	private static List<String> parseWords(final String fullPath) {
		List<String> allWords = new ArrayList<String>();
		try {
			// Use Scanner to tokenize words based on whitespace
			Scanner tokenize = new Scanner(new File(WORDS_FILE_PATH));
			while (tokenize.hasNext()) {
				// Retrieve the next whitespace-delimited word
				allWords.add(tokenize.next().toLowerCase());
			}
			// Close the resource
			tokenize.close();
		} catch (Exception e) {// Poor practice exception handling
			System.err.println("Error: " + e.getMessage());
		}
		return allWords;
	}

	/**
	 * <p>Get all possible words that are &quot;neighbors&quot; on the word ladder
	 * 
	 * <p>&quot;neighbor&quot; is defined as having a Levenshtein Distance of 1
	 * 
	 * <p>Performs lazy caching of all processed words
	 * 
	 * @param word Initial word
	 * @param allWords All valid words on the word ladder
	 * @return All neighbors of the initial word
	 */
	private static List<String> getNeighbors(final String word,
			final List<String> allWords) {
		List<String> neighbors = null;
		if (neighborsCache.containsKey(word)) {
			// Use cached neighbors if possible
			neighbors = neighborsCache.get(word);
		} else {
			// No cache
			// Find neighbors
			neighbors = new ArrayList<String>();
			// Check each word in the valid list
			for (String potentialNeighbor : allWords) {
				// Add the neighbor if the Levenshtein Distance is 1
				if (StringUtils.getLevenshteinDistance(word, potentialNeighbor,
						1) > 0) {
					neighbors.add(potentialNeighbor);
				}
			}
			// Cache the neighbors for future use
			neighborsCache.put(word, neighbors);
		}
		return neighbors;
	}

	/**
	 * <p>See {@link WordLadder#getNeighbors(String, List)}
	 * 
	 * <p>Removes excluded words from the complete list of words
	 * @param word Initial word
	 * @param allWords All valid words on the word ladder
	 * @param excludedWords All valid words that should be excluded
	 * @return All non-excluded neighbors of the initial word
	 */
	private static List<String> getNeighbors(final String word,
			final List<String> allWords, final List<String> excludedWords) {
		// Get the full neighbor list
		List<String> potentialNeighbors = getNeighbors(word, allWords);
		// Clone the full neighbor list 
		//  (Required to avoid Iterator failure after removing excluded words)
		List<String> neighbors = new ArrayList<String>(potentialNeighbors);
		// Check each valid neighbor
		for (String potentialNeighbor : potentialNeighbors) {
			// Remove any excluded words
			if (excludedWords.contains(potentialNeighbor)) {
				neighbors.remove(potentialNeighbor);
			}
		}
		return neighbors;
	}
}
