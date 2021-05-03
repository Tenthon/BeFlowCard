package com.spe.beflowcardlib;

public class DataModel{
	private String cvv;
	private String month;
	private String name;
	private String noCard;
	private String years;

	public void setCvv(String cvv){
		this.cvv = cvv;
	}

	public String getCvv(){
		return cvv;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setNoCard(String noCard){
		this.noCard = noCard;
	}

	public String getNoCard(){
		return noCard;
	}

	public void setYears(String years){
		this.years = years;
	}

	public String getYears(){
		return years;
	}
}
