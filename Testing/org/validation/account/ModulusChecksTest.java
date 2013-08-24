package org.validation.account;

import static org.junit.Assert.*;

import org.junit.Test;

/* Author:
 * Roshan C Subudhi
 * USC, Columbia
 * 
 * --- Elavon Interview---
 * 
 * Ph: 803-743-2899
 * 
 * =======================
 * 
 * Class Name: ModulusChecksTest
 * This class is a set of JUnit TestCases for the methods of 'ModulusChecks' class. 
 */
public class ModulusChecksTest {
	
	String SC = "089999";
	String AN = "66374958";
	int[] weights = new int[]{0, 0, 0, 0, 0, 0, 7, 1, 3, 7, 1, 3, 7, 1};
	int exception = 0;
	boolean isValid;

	/**
	 * TestCase Name: testDoubleAlternate()
	 * This TestCase performs the validation with double alternate modulus check, considering all exceptions.
	 */
	@Test
	public void testDoubleAlternate() {
		isValid = ModulusChecks.doubleAlternate(SC, AN, weights, exception);
		assertFalse(isValid);
	}

	/**
	 * TestCase Name: testStandardMod10()
	 * This TestCase performs the validation with standard modulus 10 check, considering all exceptions.
	 */
	@Test
	public void testStandardMod10() {
		isValid = ModulusChecks.standardMod10(SC, AN, weights, exception);
		assertTrue(isValid);
	}

	/**
	 * TestCase Name: testStandardMod11()
	 * This TestCase performs the validation with standard modulus 11 check, considering all exceptions.
	 */
	@Test
	public void testStandardMod11() {
		isValid = ModulusChecks.standardMod11(SC, AN, weights, exception);
		assertFalse(isValid);
	}

}
