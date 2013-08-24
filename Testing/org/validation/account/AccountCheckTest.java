package org.validation.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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
 * Class Name: AccountCheckTest
 * This class is a set of JUnit TestCases for the methods of 'AccountCheck' class. 
 */
public class AccountCheckTest {

	private AccountCheck accCheckTest;
	String SC = "089999";
	String AN = "66374958";
	
	@Before
	public void setUp() throws Exception {
		accCheckTest = new AccountCheck();
	}

	/**
	 * TestCase Name: testFormatInput()
	 * This TestCase performs the entire validation and provides appropriate results.
	 */
	@Test
	public void testFormatInput() {
		boolean result = accCheckTest.formatInput(SC, AN);
		assertTrue(result);
	}
	
	/**
	 * TestCase Name: testValidateCode()
	 * This TestCase performs validation, without standardizing the input codes. 
	 */
	@Test
	public void testValidateCode() {
		boolean result = accCheckTest.validateCode(SC, AN);
		assertTrue(result);
	}

	/**
	 * TestCase Name: testBankDetails()
	 * This TestCase verifies the bank branch details.
	 */
	@Test
	public void testBankDetails() {
		String result = accCheckTest.bankDetails(SC);
		assertEquals(result, "Details currently unavailable");
	}
}
