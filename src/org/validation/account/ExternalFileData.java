/**
 * 
 */
package org.validation.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
 * Class Name: ExternalFileData
 * This class retrieves data from external files present on the web. The data is retrieved, parsed and stored locally within the memory in appropriate data structures.
 */
public class ExternalFileData {

	String file1 = "http://www.vocalink.com/media/546135/valacdos.txt";
	String file2 = "http://www.vocalink.com/media/300584/scsubtab.txt";
	String file3 = "http://cse.sc.edu/~subudhi/third.txt";

	static HashMap<String, String> substitutionSortCodes;
	static HashMap<String, String> bankBranchDetails;
	static ArrayList<ModulusWeightInfo> modulusWeights;

	/* Method Name: retrieveModulusWeights()
	 * This method retrieves data from the 'valacdos.txt', on the web, and stores it in 'ModulusWeightInfo' class.  	
	 */
	void retrieveModulusWeights(){
		modulusWeights = new ArrayList<ModulusWeightInfo>();
		try {
			URL url = new URL(file1);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String currentLine;
			while ((currentLine = in.readLine()) != null){
				ModulusWeightInfo temp = new ModulusWeightInfo();
				temp.setBegSortCode(Long.parseLong(currentLine.substring(0, 6)));
				temp.setEndSortCode(Long.parseLong(currentLine.substring(7, 13)));
				temp.setAlgorithm(currentLine.substring(14, 19));

				int[] tempWeights = new int[14];
				for(int i=0; i<14; i++){
					tempWeights[i] = Integer.parseInt(currentLine.substring(20 + i*5, 24 + i*5).trim());
				}
				temp.setWeights(tempWeights);
				
				if (currentLine.substring(89).length()>0){
					temp.setException(Integer.parseInt(currentLine.substring(91).trim()));
				}else{
					temp.setException(0);
				}
				modulusWeights.add(temp);
			} 
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Method Name: retrieveSubstitutionSortCodes()
	 * This method retrieves data from the 'scsubtab.txt', on the web, and stores it in a Java Collection, namely, HashMap.  	
	 */
	void retrieveSubstitutionSortCodes(){
		substitutionSortCodes = new HashMap<>();
		try {
			URL url = new URL(file2);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String currentLine;
			while ((currentLine = in.readLine()) != null){
				substitutionSortCodes.put(currentLine.substring(0, currentLine.indexOf(" ")), currentLine.substring(currentLine.indexOf(" ")+1));
			} 
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Method Name: retrieveBankBranchDetails()
	 * This method retrieves data from the 'third.txt', custom-made on the web, and stores it in a Java Collection, namely, HashMap.  	
	 */
	void retrieveBankBranchDetails(){
		bankBranchDetails = new HashMap<>();
		try {
			URL url = new URL(file3);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String currentLine;
			while ((currentLine = in.readLine()) != null){
				bankBranchDetails.put(currentLine.substring(0, currentLine.indexOf("	")), currentLine.substring(currentLine.indexOf("	")+1));
			} 
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
