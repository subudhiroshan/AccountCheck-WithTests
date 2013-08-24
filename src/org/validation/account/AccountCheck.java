
package org.validation.account;

import java.text.SimpleDateFormat;

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
 * Class Name: AccountCheck
 * This class submits a 'Sort Code + Account Number' combination for verification. Contains the 'main' class. 
 */
public class AccountCheck {

	/* Constructor Name: AccountCheck()
	 * This constructor initiates data retrieval from files on the web and stores them locally on the machine. 	
	 */
	public AccountCheck(){
		ExternalFileData init = new ExternalFileData();
		init.retrieveModulusWeights();
		init.retrieveSubstitutionSortCodes();
		init.retrieveBankBranchDetails();
	}
	
	/* Method Name: main()
	 * This method starts the verification process.  	
	 */
	public static void main(String[] args) throws Exception {
		AccountCheck ac = new AccountCheck();
		ac.formatInput("089999", "66374958");//1.Y

//		ac.formatInput("107999", "88837491");//2.Y
//		ac.formatInput("202959", "63748472");//3.Y
//		ac.formatInput("871427", "46238510");//4.Y
//		ac.formatInput("872427", "46238510");//5.Y
//		ac.formatInput("871427", "09123496");//6.Y
//		ac.formatInput("871427", "99123496");//7.Y
//		ac.formatInput("820000", "73688637");//8.Y
//		ac.formatInput("827999", "73988638");//9.Y
//		ac.formatInput("827101", "28748352");//10.Y
//		ac.formatInput("134020", "63849203");//11.Y
//		ac.formatInput("118765", "64371389");//12.Y
//		ac.formatInput("200915", "41011166");//13.Y
//		ac.formatInput("938611", "07806039");//14.Y
//		ac.formatInput("938600", "42368003");//15.Y
//		ac.formatInput("938063", "55065200");//16.Y
//		ac.formatInput("772798", "99345694");//17.Y
//		ac.formatInput("086090", "06774744");//18.Y
//		ac.formatInput("309070", "02355688");//19.Y
//		ac.formatInput("309070", "12345668");//20.Y
//		ac.formatInput("309070", "12345677");//21.Y
//		ac.formatInput("309070", "99345694");//22.Y
//		ac.formatInput("938063", "15764273");//23.N
//		ac.formatInput("938063", "15764264");//24.N
//		ac.formatInput("938063", "15763217");//25.N
//		ac.formatInput("118765", "64371388");//26.N
//		ac.formatInput("203099", "66831036");//27.N
//		ac.formatInput("203099", "58716970");//28.N
//		ac.formatInput("089999", "66374959");//29.N
//		ac.formatInput("107999", "88837493");//30.N
//		ac.formatInput("074456", "12345112");//31.Y
//		ac.formatInput("070116", "34012583");//32.Y
//		ac.formatInput("074456", "11104102");//33.Y
//		ac.formatInput("180002", "00000190");//34.Y

	}

	/* Method Name: formatInput()
	 * This method takes a 'Sort Code + Account Number' combination, and transforms it to standard format.
	 * NOte:This is the starting point for the validation.
	 */
	boolean formatInput(String sortNumber, String accNumber){
		String accountNumber = "";
		String sortCode = "";
		if (accNumber.length() >= 10){
			if (accNumber.indexOf('-') > -1){
				accountNumber = accNumber.substring(2);
				sortCode = sortNumber;
			}else{
				accountNumber = accNumber.substring(0, 8);
				sortCode = sortNumber;
			}
		}else if (accNumber.length() == 9){
			accountNumber = accNumber.substring(1);
			sortCode = sortNumber.substring(0, 5) + accNumber.substring(0, 1);
		}else if (accNumber.length() == 7){
			accountNumber = accNumber + "0";
			sortCode = sortNumber;
		}else if (accNumber.length() == 6){
			accountNumber = accNumber + "00";
			sortCode = sortNumber;
		}else{
			accountNumber = accNumber;
			sortCode = sortNumber;
		}
		if (validateCode(sortCode, accountNumber)){
			System.out.println("The \"Sort Code + Account Number\" combination is VALID!");
		}else{
			System.out.println("The Sort Code + Account Number combination is NOT VALID!");
		}
		System.out.println("Branch details are \"" + bankDetails(sortCode) + "\"");
		return validateCode(sortCode, accountNumber);
	}

	/* Method Name: validateCode()
	 * This method subjects the 'Sort Code + Account Number' combination to the necessary checks.
	 */
	boolean validateCode(String sortNumber, String accNumber){
		boolean resultOR = false;
		boolean resultAND = true;
		int currentException = -1;
		for (int i=0; i<ExternalFileData.modulusWeights.size(); i++){
			if ((ExternalFileData.modulusWeights.get(i).getBegSortCode() <= Long.parseLong(sortNumber)) && (Long.parseLong(sortNumber) <= ExternalFileData.modulusWeights.get(i).getEndSortCode())){
				currentException = ExternalFileData.modulusWeights.get(i).getException();
				if (ExternalFileData.modulusWeights.get(i).getAlgorithm().equals("MOD10")){
					if (currentException == 5 || currentException == 6){
						resultAND = resultAND && ModulusChecks.standardMod10(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}else{
						resultOR = resultOR || ModulusChecks.standardMod10(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}
				}else if (ExternalFileData.modulusWeights.get(i).getAlgorithm().equals("MOD11")){
					if (currentException == 5 || currentException == 6){
						resultAND = resultAND && ModulusChecks.standardMod11(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}else{
						resultOR = resultOR || ModulusChecks.standardMod11(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}
				}else if (ExternalFileData.modulusWeights.get(i).getAlgorithm().equals("DBLAL")){
					if (currentException == 5 || currentException == 6){
						resultAND = resultAND && ModulusChecks.doubleAlternate(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}else{
						resultOR = resultOR || ModulusChecks.doubleAlternate(sortNumber, accNumber, ExternalFileData.modulusWeights.get(i).getWeights(), currentException);
					}
				}
			}
		}
		if (currentException == 5){
			return resultAND;
		}else if (currentException == 6){
			if ((Integer.parseInt(accNumber.substring(0, 1)) > 3) && (Integer.parseInt(accNumber.substring(0, 1)) < 9) && accNumber.substring(6, 7).equals(accNumber.substring(7, 8))){
				return true;
			}else{
				return resultAND;
			}
		}else{
			return resultOR;
		}
	}

	/* Method Name: bankDetails()
	 * This method takes a Bank Sort Code and returns the Bank Branch details. 
	 */
	String bankDetails(String sortNumber){
		if (ExternalFileData.bankBranchDetails.containsKey(sortNumber)){
			return ExternalFileData.bankBranchDetails.get(sortNumber);
		}else{
			return "Details currently unavailable";
		}
	}
	
	/* Method Name: log()
	 * This method allows logging of useful information. 
	 */
	static void log(String logEvent){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(System.currentTimeMillis()) + " -> " + logEvent);
	}

}
