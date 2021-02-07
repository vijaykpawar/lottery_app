package com.rest.lottery;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class Line.
 */
public class Line {

	/** The result. */
	@JsonIgnore
	private int result;

	/** The number 1. */
	private int number1;

	/** The number 2. */
	private int number2;

	/** The number 3. */
	private int number3;

	public Line() {

	}

	public Line(int num1, int num2, int num3) {
		this.number1 = num1;
		this.number2 = num2;
		this.number3 = num3;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public int getResult() {
		result = 0;
		if (number1 == number2 && number2 == number3 && number3 < 3) {
			result = 5;
		} else if ((number1 + number2 + number3) == 2) {
			result = 10;
		} else if (number1 != number2 && number1 != number3) {
			result = 1;
		}
		return result;

	}

	/**
	 * Gets the number 1.
	 *
	 * @return the number 1
	 */
	public int getNumber1() {
		return number1;
	}

	/**
	 * Sets the number 1.
	 *
	 * @param number1 the new number 1
	 */
	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	/**
	 * Gets the number 2.
	 *
	 * @return the number 2
	 */
	public int getNumber2() {
		return number2;
	}

	/**
	 * Sets the number 2.
	 *
	 * @param number2 the new number 2
	 */
	public void setNumber2(int number2) {
		this.number2 = number2;
	}

	/**
	 * Gets the number 3.
	 *
	 * @return the number 3
	 */
	public int getNumber3() {
		return number3;
	}

	/**
	 * Sets the number 3.
	 *
	 * @param number3 the new number 3
	 */
	public void setNumber3(int number3) {
		this.number3 = number3;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Line [result=" + result + ", number1=" + number1 + ", number2=" + number2 + ", number3=" + number3
				+ "]";
	}

}
