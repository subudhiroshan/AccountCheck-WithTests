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
 * Class Name: ModulusWeightInfo
 * This class is a data structure for the data in 'valacdos.txt' file. It contains 'getter'/'setter' methods.
 */
public class ModulusWeightInfo {

	long begSortCode;
	long endSortCode;
	String alg;
	int[] weights;
	int exception;
	
	public ModulusWeightInfo() {
		weights = new int[14];
	}
	
	void setBegSortCode(long sortCode){
		this.begSortCode = sortCode;
	}
	void setEndSortCode(long sortCode){
		this.endSortCode = sortCode;
	}
	void setAlgorithm(String algo){
		this.alg = algo;
	}
	void setWeights(int[] wts){
		System.arraycopy(wts, 0, this.weights, 0, wts.length);
	}
	void setException(int exc){
		this.exception = exc;
	}
	long getBegSortCode(){
		return begSortCode;
	}
	long getEndSortCode(){
		return endSortCode;
	}
	String getAlgorithm(){
		return alg;
	}
	int[] getWeights(){
		return weights;
	}
	int getException(){
		return exception;
	}
}
