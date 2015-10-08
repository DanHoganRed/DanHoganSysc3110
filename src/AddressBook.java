import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.*;  

public class AddressBook {
	
	ArrayList<BuddyInfo> Book = new ArrayList<BuddyInfo>();
	
	public static void main(String[] args) {
		GUI G = new GUI();
	}
	
	public void addBuddy(BuddyInfo Buddy){
		this.Book.add(Buddy);
	}
	
	public void removeBuddy(BuddyInfo Buddy){
		this.Book.remove(Buddy);
	}
	
	public String listToString()
	{
		String s = "Friends: \n";
		for(BuddyInfo B:Book){
			s += "Name: ";
			s += B.getName();
			s += "\nAge: ";
			s += B.getAge();
			s += "\nPhone Number: ";
			s += B.getPhonenumber();
			s += "\n";
		}
		return s;	
	}
}
