package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.CallLogList;
import model.PersonList;

public class FilePersistor {

	private PersonList model;
	private CallLogList history;
	
	public FilePersistor()
	{
		this.model = new PersonList();
		this.history = new CallLogList();
	}
	
	public void saveContacts(PersonList model)
	{
		try{
			//Write to disk with FileOutputStream
			FileOutputStream f_out = new 
				FileOutputStream("phonebook_lib/myobject1.data");

			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out = new
				ObjectOutputStream (f_out);

			// Write object out to disk
			obj_out.writeObject ( model );
			} catch (IOException e) {
			}
	}
    
	public PersonList loadContacts() throws ClassNotFoundException
	{
		try{ 
			// Read from disk using FileInputStream
			FileInputStream f_in = new 
				FileInputStream("phonebook_lib/myobject1.data");

			// Read object using ObjectInputStream
			ObjectInputStream obj_in = 
				new ObjectInputStream (f_in);

			// Read an object
			model = (PersonList) obj_in.readObject();

		}
		catch (IOException e){
			e.printStackTrace();
		}
		return this.model;
	}
    
	public void saveHistory(CallLogList callHistory)
	{
		try
		{
		    //Write to disk with FileOutputStream
			FileOutputStream f_out = new 
		    FileOutputStream("phonebook_lib/myobjectHistory.data");

			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out = new
			ObjectOutputStream (f_out);

			// Write object out to disk
			obj_out.writeObject ( callHistory );
		} 
		catch (IOException e) {
		}
	}
	
	public CallLogList loadHistory() throws ClassNotFoundException
	{
		try{ 
			// Read from disk using FileInputStream
			FileInputStream f_in = new 
				FileInputStream("phonebook_lib/myobjectHistory.data");

			// Read object using ObjectInputStream
			ObjectInputStream obj_in = 
				new ObjectInputStream (f_in);

			// Read an object
			history  = (CallLogList) obj_in.readObject();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return this.history;
	}

}
