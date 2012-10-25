/**
 * 
 */
package com.reddit.dailyprogrammer.nipoez.RandomTalker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	private static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String FILES_DIR = "files";
	private static final String FILE_NAME = "dailyprogrammer-desc.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parseFile( WORKING_DIR + "\\" + FILES_DIR + "\\"
				+ FILE_NAME);
	}

	private static void parseFile(String fullPath) {
		List<String> tokens = new ArrayList<String>();
		try {			
			Scanner tokenize = new Scanner ( new File (fullPath) );
			while (tokenize.hasNext()) {
			    tokens.add(tokenize.next());
			}

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println(tokens);

	}
}
