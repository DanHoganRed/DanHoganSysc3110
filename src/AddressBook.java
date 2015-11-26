import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.swing.*;

public class AddressBook{

	private DefaultListModel<BuddyInfo> Book = new DefaultListModel<BuddyInfo>();

	public static void main(String[] args) {
		GUI G = new GUI();
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
	
	public boolean Import() throws IOException
	{
		String s = "";
		Object[] n = Book.toArray();
		BufferedReader in = new BufferedReader(new FileReader("myFile.txt"));
		if(in == null)
			return false;
		String line = in.readLine();
		while(line != null) {
			this.addBuddy(BuddyInfo.Import(line));
			line = in.readLine();
		}
		in.close();
		return true;
	}
	
	public boolean importObject() throws IOException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("myFile.txt")));
		boolean check=true;
		if(ois == null)
			return false;
		while (check) {
		   try{
			   Object b = ois.readObject();
			   if(b instanceof BuddyInfo)
				   this.addBuddy((BuddyInfo)b);
		   } catch(EOFException ex){
		       check=false;
		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
        ois.close();
        return true;
	}
	
	public void exportObject() throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("myFile.txt")));
		for (int i = 0; i < Book.size(); i++) {
			 oos.writeObject(Book.get(i));
		}
        oos.close();
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
