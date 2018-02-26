package controller;

import java.util.ArrayList;

import model.CallLog;
import model.CallLogList;
import model.Person;
import model.PersonList;

public class PhonebookController {
      
	private static PhonebookController instance;
	private PersonList personList;
	private CallLogList callLog;
	private FilePersistor fp = new FilePersistor();
	
	private PhonebookController()
	{
		this.personList = new PersonList();
		this.callLog = new CallLogList();
	}

    public static PhonebookController getInstance()
    {
    	if(instance == null)
    	{
    		instance = new PhonebookController();
    	}
    	return instance;
    }
    
    public void addContact(Person p)
    {
    	this.personList.add(p);
    }
    
    public ArrayList<Person> getAllContacts()
    {
    	return this.personList.getAll();
    }
    
    public void removeContact(Person p)
    {
    	this.personList.remove(p);
    }
    
    public String toString()
    {
    	return this.personList.toString();
    }

    public void addCallLog(CallLog entry)
    {
    	this.callLog.setNewCallLog(entry);
    }
    
    public ArrayList<CallLog> getAllCallLogs()
    {
    	return this.callLog.getAllCallLogs();
    }
    
    public void removeCallLogEntry(CallLog entry)
    {
    	this.callLog.removeCallLog(entry);
    }
    
    public void updateEntry(Person p1, Person p2)
    {
    	this.callLog.updateEntry(p1, p2);
    
    }
    
    public void saveContacts()
    {
    	fp.saveContacts(this.personList);
    }
   
    public void loadContacts() throws ClassNotFoundException
    {
    	this.personList = fp.loadContacts();
    }
    
    public void saveCallHistory()
    {
    	fp.saveHistory(this.callLog);
    }
    
    public void loadHistory() throws ClassNotFoundException
    {
    	this.callLog = fp.loadHistory();
    }
    
   
}
