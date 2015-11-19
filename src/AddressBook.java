import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

public class AddressBook {

	private DefaultListModel<BuddyInfo> Book = new DefaultListModel<BuddyInfo>();

	public static void main(String[] args) {
		//GUI G = new GUI();
		
	}

	public void addBuddy(BuddyInfo Buddy) {
		this.Book.add(Book.getSize(), Buddy);
	}

	public void removeBuddy(BuddyInfo Buddy) {
		this.Book.removeElement(Buddy);
	}

	public DefaultListModel<BuddyInfo> getAddressBook() {
		return Book;
	}
	
	public int size() {
		return Book.size();
	}
	
	public void clear() {
		Book.removeAllElements();
	}
	
	public void export() throws IOException
	{
		String s = "";
		Object[] n = Book.toArray();
		BufferedWriter out = new BufferedWriter(new FileWriter("myFile.txt"));
		for (int i = 0; i < Book.size(); i++) {
			s = ((BuddyInfo) n[i]).toString() + "\n";
			out.write(s);
		}
		out.close();
	}


	public String listToString() {
		String s = "Friends: \n";
		Object[] n = Book.toArray();
		for (int i = 0; i < Book.size(); i++) {
			s += "Name: ";
			s += ((BuddyInfo) n[i]).getName();
			s += "\nAge: ";
			s += ((BuddyInfo) n[i]).getAge();
			s += "\nPhone Number: ";
			s += ((BuddyInfo) n[i]).getPhonenumber();
			s += "\n";
		}
		return s;
	}
}
