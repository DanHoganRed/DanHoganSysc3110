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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class AddressBook{

	private DefaultListModel<BuddyInfo> Book = new DefaultListModel<BuddyInfo>();

	public static void main(String[] args) throws Exception {
		AddressBook A = new AddressBook();
		A.addBuddy(new BuddyInfo("Dan", 20, 613));
		A.exportToXmlFile();
		File f = new File("C:\\Users\\danhogan\\Downloads\\file.xml");
		A.readSAX(f);
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

	public String toXML() {
		String s = "<AddressBook>";
		Object[] n = Book.toArray();
		for (int i = 0; i < Book.size(); i++) {
			s += "   "+((BuddyInfo) n[i]).toXML();
		}
		s += "</AddressBook>";
		return s;
	}

	public void exportToXmlFile() throws IOException
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("AddressBook");
			doc.appendChild(rootElement);

			Object[] n = Book.toArray();
			for (int i = 0; i < Book.size(); i++) {
				// staff elements
				Element Buddy = doc.createElement("BudyInfo");
				rootElement.appendChild(Buddy);

				// set attribute to staff element
				Attr attr1 = doc.createAttribute("Name");
				attr1.setValue(((BuddyInfo) n[i]).getName());
				Buddy.setAttributeNode(attr1);

				// set attribute to staff element
				Attr attr2 = doc.createAttribute("Age");
				attr2.setValue(""+((BuddyInfo) n[i]).getAge());
				Buddy.setAttributeNode(attr2);

				// set attribute to staff element
				Attr attr3 = doc.createAttribute("ProneNumber");
				attr3.setValue(""+((BuddyInfo) n[i]).getPhonenumber());
				Buddy.setAttributeNode(attr3);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\Users\\danhogan\\Downloads\\file.xml"));

			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

		public void readSAX(File f) throws Exception{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser s = spf.newSAXParser();
			DefaultHandler dh = new DefaultHandler(){
				AddressBook A = new AddressBook();
				public void startElement(String u, String ln, String qName, Attributes a){
					String s = "START: "+ qName;
					
					for(int i=0;i<a.getLength();i++){
						s+=" "+a.getValue(i);
					}
					
					if(qName.equals("BuddyInfo"))
					{
						A.addBuddy(new BuddyInfo(a.getValue(1),Integer.parseInt(a.getValue(0)),Integer.parseInt(a.getValue(2))));
					}
					System.out.println(s);
				}
				public void endElement(String uri, String localName, String qName){
					System.out.println("END: "+ qName);
					if(qName.equals("AddressBook"))
					{
						try {
							A.export();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
					}
				}
				public void characters(char[] ch, int start, int length){
					System.out.println("CHARS: "+ new String(ch,start,length));
				}
				public AddressBook getAddressBook()
				{
					return A;
				}
			};
			s.parse(f, dh);
			Import();
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
