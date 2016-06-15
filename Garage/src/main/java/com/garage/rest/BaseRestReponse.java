package com.garage.rest;

public class BaseRestReponse {

	private String result;
	
	public BaseRestReponse (String result){
		this.result = result;
	}
	
	public BaseRestReponse (int result){
		this.result = String.valueOf(result);
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
