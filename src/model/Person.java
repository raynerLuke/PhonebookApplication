package model;

import java.io.Serializable;
import java.util.Comparator;

public class Person implements Serializable{
    
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String emailAddress = null;
	protected Object getFirstName;
	
	public Person(String firstName, String lastName, int phoneNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmailAddress() 
	{
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}
	
	public String getFirstName() 
	{
		return this.firstName;
	}
	
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	public String getLastName() 
	{
		return this.lastName;
	}
	
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() 
	{
		return "" + this.phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String toString()
	{
		return this.firstName + " " + this.lastName + " " + "0"+this.phoneNumber;
	}
	
	public String getFullName()
	{
		return this.firstName + " " + this.lastName;
	}
	
}
