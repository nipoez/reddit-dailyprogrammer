package com.reddit.dailyprogrammer.nipoez.decodings;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Consider the translation from letters to numbers a -> 1 through z -> 26.
 * Every sequence of letters can be translated into a string of numbers this
 * way, with the numbers being mushed together. For instance hello -> 85121215.
 * Unfortunately the reverse translation is not unique. 85121215 could map to
 * hello, but also to heaubo. Write a program that, given a string of digits,
 * outputs every possible translation back to letters.
 * 
 * <p>
 * Sample input:
 * 
 * <p>
 * 123
 * 
 * <p>
 * Sample output:
 * 
 * <p>
 * abc <br>
 * aw <br>
 * lc
 * 
 * <p>
 * Thanks to ashashwat for posting this idea in [1] /r/dailyprogrammer_ideas!
 * 
 * @see <a
 *      href="http://www.reddit.com/r/dailyprogrammer/comments/122c4t/10252012_challenge_107_easy_all_possible_decodings/">Reddit
 *      page</a>
 * 
 * @author nipoez
 * 
 */
public class AllPossibleDecodings {

	public static void main(String[] args) {

		String input = "120310";
		StringBuilder possibleOutput = null;
		List<List<Integer>> possibleDecodings = getPossibleDecodings(input);

		for (List<Integer> aPossibleDecoding : possibleDecodings) {
			possibleOutput = new StringBuilder();
			for (Integer character : aPossibleDecoding) {
				// Subtract 1 from the output character to
				// convert to 0-based index
				// Add that index to the character 'a'
				//
				// Ex, output int "1" is index "0", added to "a" is "a"
				// Ex, output int "2" is index "1", added to "a" is "b"
				possibleOutput.append((char) (character.intValue() - 1 + 'a'));
			}
			System.out.println(aPossibleDecoding);
			System.out.println(possibleOutput);
		}
	}

	/**
	 * Recursively determine all possible character-integer decodings of a
	 * string of integers
	 * 
	 * @param input
	 *            Must be all integers; other content will fail
	 * @return All possible character-integer decodings
	 */
	public static List<List<Integer>> getPossibleDecodings(String input) {
		List<List<Integer>> possibleDecodings = new ArrayList<List<Integer>>();
		List<List<Integer>> recursedPossibleDecodings = null;
		List<Integer> aPossibleDecoding = null;
		Integer anInt = null;

		if (input == null || input.length() == 0 || input.equals("0")) {
			// No more content, nothing to do
			// NOTE: "0" is not a valid character-integer, skip it
			return possibleDecodings;
		} else if (input.length() == 1) {
			// Just 1 character, only 1 possibility.
			aPossibleDecoding = new ArrayList<Integer>();
			anInt = Integer.parseInt(input);
			aPossibleDecoding.add(anInt);
			possibleDecodings.add(aPossibleDecoding);
		} else {
			// Two or more characters allow for 2 possible decodings

			//
			// Take first character, recurse
			//
			anInt = Integer.parseInt(input.substring(0, 1));
			if (anInt.intValue() != 0) {
				// "0" is not a valid character-integer, skip it
				recursedPossibleDecodings = getPossibleDecodings(input
						.substring(1, input.length()));
				// For every recursive possibility, prepend this character
				for (List<Integer> tmpPossibleDecoding : recursedPossibleDecodings) {
					tmpPossibleDecoding.add(0, anInt);
					possibleDecodings.add(tmpPossibleDecoding);
				}
			}
			//
			// Take the first two characters, recurse
			//
			anInt = Integer.parseInt(input.substring(0, 2));
			// Only recurse if the integer represents a character (1-26)
			if (anInt.intValue() <= 26) {
				recursedPossibleDecodings = getPossibleDecodings(input
						.substring(2, input.length()));
				if (!recursedPossibleDecodings.isEmpty()) {
					// Recursion found possibilities
					// For every recursive possibility, prepend this character
					for (List<Integer> tmpPossibleDecoding : recursedPossibleDecodings) {
						tmpPossibleDecoding.add(0, anInt);
						possibleDecodings.add(tmpPossibleDecoding);
					}
				} else {
					// Recursion didn't find any new possibilities
					// Use just this character
					aPossibleDecoding = new ArrayList<Integer>();
					aPossibleDecoding.add(anInt);
					possibleDecodings.add(aPossibleDecoding);
				}
			}
		}

		return possibleDecodings;

	}
}