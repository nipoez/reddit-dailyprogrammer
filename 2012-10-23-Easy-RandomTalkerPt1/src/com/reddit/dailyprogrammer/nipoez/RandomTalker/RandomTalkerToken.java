package com.reddit.dailyprogrammer.nipoez.RandomTalker;

/**
 * Utility class for {@link RandomTalker}, to facilitate tracking occurrences of
 * specific string tokens. Also supports equality and sorting to play nicely
 * with Java collections.
 * 
 * @author nipoez
 * 
 */
public class RandomTalkerToken implements Comparable<RandomTalkerToken> {

	/**
	 * Token whose occurrences we want to track
	 */
	private String token;
	/**
	 * Count of token occurrences
	 */
	private int count;

	/**
	 * RandomTalkerToken constructor
	 * 
	 * @param token
	 * @param initialCount
	 */
	public RandomTalkerToken(String token, int initialCount) {
		this.setToken(token);
		this.setCount(initialCount);
	}

	/**
	 * <p>
	 * Comparisons based on {@link RandomTalkerToken#count}, with order
	 * reversed.
	 * 
	 * <p>
	 * Note: @link {@link Integer#compare(int, int)} requires Java 7
	 */
	@Override
	public int compareTo(RandomTalkerToken randomTalkerToken) {
		return -1
				* Integer
						.compare(this.getCount(), randomTalkerToken.getCount());
	}

	/**
	 * <p>
	 * Equality based on {@link RandomTalkerToken#token} value.
	 * <p>
	 * {@link RandomTalkerToken#count} is irrelevant.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj instanceof RandomTalkerToken) {
			RandomTalkerToken randomTalkerToken = (RandomTalkerToken) obj;
			isEqual = this.getToken().equals(randomTalkerToken.getToken());
		}
		return isEqual;
	};

	/**
	 * Use {@link RandomTalkerToken#token}'s hasCode()
	 */
	@Override
	public int hashCode() {
		return this.getToken().hashCode();
	};

	/**
	 * Basic string representation in format "{token: count}"
	 */
	@Override
	public String toString() {
		return "{" + this.getToken() + ": " + this.getCount() + "}";
	};

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * token setter
	 * 
	 * @param token
	 *            the token to set. null values set as empty string.
	 */
	public void setToken(String token) {
		if (token == null) {
			this.token = "";
		} else {
			this.token = token;
		}
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	private void setCount(int count) {
		this.count = count;
	}

	/**
	 * Increment the occurrence count of this token
	 * 
	 * @return the count
	 */
	public void increment() {
		++this.count;
	}

}
