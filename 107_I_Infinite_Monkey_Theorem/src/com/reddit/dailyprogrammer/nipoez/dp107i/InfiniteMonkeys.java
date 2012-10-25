package com.reddit.dailyprogrammer.nipoez.dp107i;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <p>
 * Verify the Infinite Monkey Theorem.
 * <p>
 * Well that's a bit hard, so let's go with this. Using any method of your
 * choice, generate a random string of space-separated words. (The simplest
 * method would be to randomly choose, with equal probability, one of the 27
 * characters including letters and space.) Filter the words using a [2] word
 * list of your choice, so that only words in the word list are actually output.
 * 
 * <p>
 * That's all you need for the basic challenge. For extra points, run your
 * program for a few minutes and find the most interesting string of words you
 * can get. The longer the better. For style, see if you can "train your monkey"
 * by modifying either the random character generator or the word list to output
 * text that's more Shakespearean in less time.
 * 
 * <p>
 * Thanks to Pikmeir for posting this idea in /r/dailyprogrammer_ideas!
 * 
 * @see <a
 *      href="http://www.reddit.com/r/dailyprogrammer/comments/122c6d/10252012_challenge_107_intermediate_infinite/">Reddit
 *      page</a>
 * 
 * @author nipoez
 * 
 */
public class InfiniteMonkeys {
	/**
	 * Punctuation characters that are delimiters and also tokens
	 */
	private static final String PUNCTUATION_DELIMS = "\".,:;!?";
	private static final String RAND_CHARS = "''...,,,:;!??aaaaabbbccddeeeeeeeeffghhhhiiiiiijklllllmmmmmnnnnnoooooooppppqrrrrrssssssttttttuuuvwxyz                           ";
	private static final String BREAK_CHARS = ".,:;!?()[]{} ";

	public static void main(String[] args) {
		Set<String> validWords = getValidWords(System.getProperty("user.dir")
				+ "\\all_shakespeare.txt");
		int nonwords=0;
		Random random = new Random();
		int randMax=RAND_CHARS.length();
		int randIdx=0;
		StringBuilder word = new StringBuilder();
		StringBuilder monkeySpew = new StringBuilder();
		System.out.println(validWords);
		System.out.println(validWords.size());
		
		for (int i = 0; i < 100000000; ++i) {
			randIdx = random.nextInt(randMax);
			if(BREAK_CHARS.indexOf(RAND_CHARS.charAt(randIdx)) >= 0) {
				if (word.length() > 4){
				if (validWords.contains(word.toString())) {
					monkeySpew.append(word);
					monkeySpew.append(RAND_CHARS.charAt(randIdx));
					monkeySpew.append(" ");
				} else {
					++nonwords;
				}
				word.delete(0, word.length());}
			} else {
			word.append(RAND_CHARS.charAt(randIdx));
			}
		}
		System.out.println(nonwords);
		System.out.println(monkeySpew);
		
	}

	private static Set<String> getValidWords(String fullPath) {
		Set<String> validWords = new HashSet<String>();
		// These two two tokens exist, but we rip them out later
		validWords.add("'");
		validWords.add("--");
		StringTokenizer tokenizePuncuation = null;
		String token = null;

		try {

			// Use Scanner for a first pass of tokenization,
			// based on whitespace
			Scanner tokenize = new Scanner(new File(fullPath));
			while (tokenize.hasNext()) {
				// Use StringTokenizer for a second pass of tokenization,
				// treating certain punctuation characters as both
				// delimiters and tokens
				tokenizePuncuation = new StringTokenizer(tokenize.next().toLowerCase(),
						PUNCTUATION_DELIMS, true);
				while (tokenizePuncuation.hasMoreTokens()) {
					token = tokenizePuncuation.nextToken();
					// Trim starting and ending ' and --
					if (token.startsWith("'")) {
						token = token.substring(1, token.length());
					}
					if (token.endsWith("'")) {
						token = token.substring(0, token.length() - 1);
					}
					if (token.startsWith("--")) {
						token = token.substring(2, token.length());
					}
					if (token.endsWith("--")) {
						token = token.substring(0, token.length() - 2);
					}
					// Within the second pass tokenization,
					// only process non-empty tokens
					if (token.length() > 0) {
						if (!validWords.contains(token)) {
							validWords.add(token);
						}
					}
				}
			}
		} catch (Exception e) {// Poor practice exception handling
			System.err.println("Error: " + e.getMessage());
		}
		return validWords;
	}
}
