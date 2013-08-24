package org.validation.account;

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
 * Class Name: ModulusChecks
 * This class performs all the standard Modulus Checks on 'Sort Code + Account Number' combinations, considering all the exceptions.
 */
public class ModulusChecks {

	/* Method Name: doubleAlternate()
	 * This method verifies a 'Sort Code + Account Number' combination with an exception, if any, using Double Alternate Modulus Check.
	 */
	static boolean doubleAlternate(String sortNumber, String accNumber, int[] weight, int exception){
		String actualSC;
		int sum;
		switch (exception){

		case 0:
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			if ((sum % 10) == 0){
				return true;
			}else{
				return false;
			}

		case 1:
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			sum = sum + 27;
			if ((sum % 10) == 0){
				return true;
			}else{
				return false;
			}

		case 3:
			if (accNumber.substring(2, 3).equals("6") || accNumber.substring(2, 3).equals("9")){
				return true;
			}
			break;

		case 5:
			if (ExternalFileData.substitutionSortCodes.containsKey(sortNumber)){
				actualSC = ExternalFileData.substitutionSortCodes.get(sortNumber);
			}else{
				actualSC = sortNumber;
			}
			sum = 0;
			for (int i = 0; i<actualSC.length(); i++){
				int tempSum = Integer.parseInt(actualSC.substring(i, i+1)) * weight[i];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				while(tempSum>0){
					sum += tempSum % 10;
					tempSum = tempSum/10;
				}
			}
			if (((sum % 10) == 0) && (accNumber.substring(7, 8).equals("0"))){
				return true;
			}else{
				if ((10 - sum % 10) == Integer.parseInt(accNumber.substring(7, 8))){
					return true;
				}else{
					return false;
				}
			}

		case 6:
				sum = 0;
				for (int i = 0; i<sortNumber.length(); i++){
					int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
					while(tempSum>0){
						sum += tempSum % 10;
						tempSum = tempSum/10;
					}
				}
				for (int j = 0; j<accNumber.length(); j++){
					int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
					while(tempSum>0){
						sum += tempSum % 10;
						tempSum = tempSum/10;
					}
				}
				if ((sum % 10) == 0){
					return true;
				}else{
					return false;
				}

		default:
			AccountCheck.log("DBLAL exception not found!");
		}
		return false;
	}

	/* Method Name: standardMod10()
	 * This method verifies a 'Sort Code + Account Number' combination with an exception, if any, using Standard Modulus 10 Check.
	 */
	static boolean standardMod10(String sortNumber, String accNumber, int[] weight, int exception){
		int sum;

		switch (exception){

		case 0:
		case 13:
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 10) == 0){
				return true;
			}else{
				return false;
			}

		case 8: 
			String actualSC = "090126";
			sum = 0;
			for (int i = 0; i<actualSC.length(); i++){
				int tempSum = Integer.parseInt(actualSC.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 10) == 0){
				return true;
			}else{
				return false;
			}

		default:
			AccountCheck.log("MOD10 exception not found!");
		}
		return false;
	}

	/* Method Name: standardMod11()
	 * This method verifies a 'Sort Code + Account Number' combination with an exception, if any, using Standard Modulus 11 Check.
	 */
	static boolean standardMod11(String sortNumber, String accNumber, int[] weight, int exception){
		int sum;
		int[] newWeight = new int[14];
		String actualSC = "";

		switch (exception){

		case 0:
		case 11:
		case 12:
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				return false;
			}

		case 2:
			if (!accNumber.substring(0, 1).equals("0") && !accNumber.substring(6, 7).equals("9")){
				newWeight = new int[]{0, 0, 1, 2, 5, 3, 6, 4, 8, 7, 10, 9, 3, 1};
			}else if (!accNumber.substring(0, 1).equals("0") && accNumber.substring(6, 7).equals("9")){
				newWeight = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 10, 9, 3, 1};
			}else{
				System.arraycopy(weight, 0, newWeight, 0, weight.length);
			}
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * newWeight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * newWeight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				return false;
			}
		case 9:
			actualSC = "309634";
			if (!accNumber.substring(0, 1).equals("0") && !accNumber.substring(6, 7).equals("9")){
				newWeight = new int[]{0, 0, 1, 2, 5, 3, 6, 4, 8, 7, 10, 9, 3, 1};
			}else if (!accNumber.substring(0, 1).equals("0") && accNumber.substring(6, 7).equals("9")){
				newWeight = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 10, 9, 3, 1};
			}else{
				System.arraycopy(weight, 0, newWeight, 0, weight.length);
			}
			sum = 0;
			for (int i = 0; i<actualSC.length(); i++){
				int tempSum = Integer.parseInt(actualSC.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				return false;
			}

		case 4: 
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == Integer.parseInt(accNumber.substring(6, 8))){
				return true;
			}else{
				return false;
			}

		case 5:
			if (ExternalFileData.substitutionSortCodes.containsKey(sortNumber)){
				actualSC = ExternalFileData.substitutionSortCodes.get(sortNumber);
			}else{
				actualSC = sortNumber;
			}
			sum = 0;
			for (int i = 0; i<actualSC.length(); i++){
				int tempSum = Integer.parseInt(actualSC.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if (((sum % 11) == 0) && (accNumber.substring(6, 7).equals("0"))){
				return true;
			}else if ((sum % 11) == 1){
				return false;
			}else{					
				if ((11 - sum % 11) == Integer.parseInt(accNumber.substring(6, 7))){
					return true;
				}else{
					return false;
				}
			}	

		case 6:
				sum = 0;
				for (int i = 0; i<sortNumber.length(); i++){
					int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
					sum += tempSum;
				}
				for (int j = 0; j<accNumber.length(); j++){
					int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
					sum += tempSum;
				}
				if ((sum % 11) == 0){
					return true;
				}else{
					return false;
				}

		case 7:
			if (accNumber.substring(6, 7).equals("9")){
				for(int i = 0; i<14; i++){
					if (i < 8){
						newWeight[i] = 0;
					}else{
						newWeight[i] = weight[i];
					}
				}
			}else{
				System.arraycopy(weight, 0, newWeight, 0, weight.length);
			}
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * newWeight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * newWeight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				return false;
			}

		case 10:
			if (accNumber.substring(6, 7).equals("9") && accNumber.substring(0, 2).equals("09") || accNumber.substring(0, 2).equals("99")){
				for(int i = 0; i<14; i++){
					if (i < 8){
						newWeight[i] = 0;
					}else{
						newWeight[i] = weight[i];
					}
				}
			}else{
				System.arraycopy(weight, 0, newWeight, 0, weight.length);
			}
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * newWeight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * newWeight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				return false;
			}

		case 14:
			sum = 0;
			for (int i = 0; i<sortNumber.length(); i++){
				int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
				sum += tempSum;
			}
			for (int j = 0; j<accNumber.length(); j++){
				int tempSum = Integer.parseInt(accNumber.substring(j, j+1)) * weight[j+sortNumber.length()];
				sum += tempSum;
			}
			if ((sum % 11) == 0){
				return true;
			}else{
				if (!(accNumber.substring(7, 8).equals("0") || accNumber.substring(7, 8).equals("1") || accNumber.substring(7, 8).equals("9"))){
					return false;
				}else{
					sum = 0;
					for (int i = 0; i<sortNumber.length(); i++){
						int tempSum = Integer.parseInt(sortNumber.substring(i, i+1)) * weight[i];
						sum += tempSum;
					}
					String newAC = "0" + accNumber.substring(0, 7);
					for (int j = 0; j<newAC.length(); j++){
						int tempSum = Integer.parseInt(newAC.substring(j, j+1)) * weight[j+sortNumber.length()];
						sum += tempSum;
					}
					if ((sum % 11) == 0){
						return true;
					}else{
						return false;
					}
				}
			}

		default:
			AccountCheck.log("MOD11 exception not found!");
		}
		return false;
	}

}
