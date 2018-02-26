package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CallLogList implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private ArrayList<CallLog> allCallLogs; 
	
	public CallLogList()
	{
		this.allCallLogs = new ArrayList<CallLog>();
	}

	public ArrayList<CallLog> getAllCallLogs() {
		return this.allCallLogs;
	}

	public void setNewCallLog (CallLog entry) {
		this.allCallLogs.add(entry);
	}
	
	public void removeCallLog(CallLog entry)
	{
		this.allCallLogs.remove(entry);
	}
	
	public void updateEntry(Person p1, Person p2)
	{
		for(CallLog c : new ArrayList<CallLog>(allCallLogs))
		{
			if(c.getPerson().getFullName().equals(p1.getFullName()))
			{
			    c.setPerson(p2);
			}
		}
	   
	}
	
}
