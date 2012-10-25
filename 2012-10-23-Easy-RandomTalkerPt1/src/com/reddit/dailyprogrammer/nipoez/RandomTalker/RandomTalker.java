/**
 * 
 */
package com.reddit.dailyprogrammer.nipoez.RandomTalker;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <p>
 * [10/23/2012] Challenge #106 [Easy] (Random Talker, Part 1)
 * 
 * <p>
 * Your program will be responsible for analyzing a small chunk of text, the
 * text of this entire dailyprogrammer description. Your task is to output the
 * distinct words in this description, from highest to lowest count with the
 * number of occurrences for each. Punctuation will be considered as separate
 * words where they are not a part of the word.
 * 
 * <p>
 * The following will be considered their own words: " . , : ; ! ? ( ) [ ] { }
 * 
 * <p>
 * For anything else, consider it as part of a word.
 * 
 * <p>
 * Extra Credit:
 * 
 * <p>
 * Process the text of the ebook <a
 * href="http://www.gutenberg.org/cache/epub/5200/pg5200.txt">Metamorphosis, by
 * Franz Kafka</a> and determine the top 10 most frequently used words and their
 * counts. (This will help for part 2)
 * 
 * @see <a
 *      href="http://www.reddit.com/r/dailyprogrammer/comments/11xje0/10232012_challenge_106_easy_random_talker_part_1/">Reddit
 *      page</a>
 * 
 * @author nipoez
 * 
 */
public class RandomTalker {

	/**
	 * Executable's current working directory
	 */
	private static final String WORKING_DIR = System.getProperty("user.dir");
	/**
	 * Directory containing parseable text files
	 */
	private static final String FILES_DIR = "files";
	/**
	 * File to process
	 */
	// private static final String FILE_NAME = "dailyprogrammer-desc.txt";
	private static final String FILE_NAME = "Metamorphosis.txt";
	/**
	 * Punctuation characters that are delimiters and also tokens
	 */
	private static final String PUNCTUATION_DELIMS = "\".,:;!?()[]{}";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Get all tokens with their occurrence count
		List<RandomTalkerToken> tokens = parseFile(WORKING_DIR + "\\"
				+ FILES_DIR + "\\" + FILE_NAME);
		// Sort the tokens, highest occurring items first
		Collections.sort(tokens, Collections.reverseOrder());
		// Dump the tokens
		printTokens(tokens, 10);
	}

	private static void printTokens(List<RandomTalkerToken> tokens,
			int maxTokens) {
		int stop = maxTokens;
		int maxTokenLen = 1;
		String tokenFormat = null;
		// If the requested max is greater than the available tokens,
		// print all tokens
		if (tokens.size() < stop) {
			stop = tokens.size();
		}

		// Determine maximum token length, for padding purposes
		for (int i = 0; i < stop; ++i) {
			if (tokens.get(i).getToken().length() > maxTokenLen) {
				maxTokenLen = tokens.get(i).getToken().length();
			}
		}
		// Set the token format string for padding
		tokenFormat = "%1$-" + maxTokenLen + "s";

		// Print the requested number of tokens, 1 per line
		//  Right-pad the token using String.format
		for (int i = 0; i < stop; ++i) {
			System.out.println(String.format(tokenFormat, tokens.get(i)
					.getToken())
					+ " : " + tokens.get(i).getCount());
		}
	}

	private static List<RandomTalkerToken> parseFile(String fullPath) {
		List<RandomTalkerToken> tokens = new ArrayList<RandomTalkerToken>();
		StringTokenizer tokenizePuncuation = null;
		String token = null;
		RandomTalkerToken rttoken = null;
		try {
			// Use Scanner for a first pass of tokenization,
			// based on whitespace
			Scanner tokenize = new Scanner(new File(fullPath));
			while (tokenize.hasNext()) {
				// Use StringTokenizer for a second pass of tokenization,
				// treating certain punctuation characters as both
				// delimiters and tokens
				tokenizePuncuation = new StringTokenizer(tokenize.next(),
						PUNCTUATION_DELIMS, true);
				while (tokenizePuncuation.hasMoreTokens()) {
					token = tokenizePuncuation.nextToken();
					// Within the second pass tokenization,
					// only process non-empty tokens
					if (token.length() > 0) {
						rttoken = new RandomTalkerToken(token, 1);
						if (tokens.contains(rttoken)) {
							// This token has been seen before,
							// increment the occurrence count
							tokens.get(tokens.indexOf(rttoken)).increment();
						} else {
							// This is a new token,
							// start tracking it
							tokens.add(rttoken);
						}
					}
				}
			}
			tokenize.close();

		} catch (Exception e) {// Poor practice exception handling
			System.err.println("Error: " + e.getMessage());
		}

		return tokens;
	}
}
