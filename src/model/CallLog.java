package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CallLog implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Person person;
	private Date date;
	private Date endCallTime;
	private String formattedDate;
	private long diff;
	private long durationSec;
	private long durationMin;
	private SimpleDateFormat sdf;

    
	
	public CallLog(Person person)
	{
		this.person = person;
		this.date = new Date();
		this.sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
		this.formattedDate = sdf.format(date);
	
	}
    
	public void endCallTimer()
	{
		endCallTime = new Date();
		this.diff = endCallTime.getTime() - date.getTime(); 
		this.durationSec = this.diff / 1000 % 60;
		this.durationMin = this.diff / (60 * 1000) % 60;
	}
	
	public String getCallDuration()
	{
		return "Call Duration  " + this.durationMin + ":" + this.durationSec;
	}
	
	public Person getPerson() 
	{
		return person;
	}
	
	public void setPerson(Person person) 
	{
		this.person = person;
	}
	
	public String getDate() 
	{
		return this.formattedDate;
	}
    
    public String toString()
    {
    	return this.person.getFirstName() + " " + this.person.getLastName()+ " " +this.formattedDate;
    }
}
