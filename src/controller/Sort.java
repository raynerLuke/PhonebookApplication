package controller;

import java.util.Comparator;

import model.CallLog;
import model.Person;

public class Sort{

    private static int value = 0;
    
	public static final Comparator<Person> compareFirstName = new Comparator<Person>() 
	{
	    public int compare(Person p1, Person p2)
	    {
	    	value = p1.getFirstName().compareTo(p2.getFirstName());
	        return value;
	    }
	};
    
	public static final Comparator<Person> compareLastName = new Comparator<Person>() 
	{
	    public int compare(Person p1, Person p2)
	    {
	    	value = p1.getLastName().compareTo(p2.getLastName());
	        return value;
	    }
	};
	
	public static final Comparator<CallLog> compareByNewest = new Comparator<CallLog>()
	{
	    public int compare(CallLog c1, CallLog c2)
	    {
	    	value = c1.getDate().compareTo(c2.getDate());
	    	return value;
	    }
    };
    
    public static final Comparator<CallLog> compareByOldest = new Comparator<CallLog>()
	{
	    public int compare(CallLog c1, CallLog c2)
	    {
	    	value = c2.getDate().compareTo(c1.getDate());
	    	return value;
	    }
    };
}
