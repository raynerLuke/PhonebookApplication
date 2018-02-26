package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonList implements Serializable {

	private ArrayList<Person> allContacts;
	
	public PersonList()
	{
		allContacts = new ArrayList<Person>();
	}
    
    public void add(Person p)
    {
    	this.allContacts.add(p);
    }
    
    public ArrayList<Person> getAll()
    {
    	return this.allContacts;
    }
    
    public void remove(Person p)
    {
    	this.allContacts.remove(p);
    }
}
