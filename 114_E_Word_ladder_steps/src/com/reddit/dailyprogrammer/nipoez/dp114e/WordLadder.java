package com.reddit.dailyprogrammer.nipoez.dp114e;

import java.io.File;
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
		List<String> allWords = new ArrayList<String>();
		try {

			// Use Scanner for a first pass of tokenization,
			// based on whitespace
			Scanner tokenize = new Scanner(new File(WORDS_FILE_PATH));
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

	private static List<String> getNeighbors(final String word,
			final List<String> allWords) {
		List<String> neighbors = null;
		if (neighborsCache.containsKey(word)) {
			neighbors = neighborsCache.get(word);
		} else {
			neighbors = new ArrayList<String>();
			for (String potentialNeighbor : allWords) {
				if (StringUtils.getLevenshteinDistance(word, potentialNeighbor,
						1) > 0) {
					neighbors.add(potentialNeighbor);
				}
			}
			neighborsCache.put(word, neighbors);
		}
		return neighbors;
	}

	private static List<String> getNeighbors(final String word,
			final List<String> allWords, final List<String> excludedWords) {
		List<String> potentialNeighbors = getNeighbors(word, allWords);
		List<String> neighbors = new ArrayList<String>(potentialNeighbors);
		for (String potentialNeighbor : potentialNeighbors) {
			if (excludedWords.contains(potentialNeighbor)) {
				neighbors.remove(potentialNeighbor);
			}
		}
		return neighbors;
	}
}
