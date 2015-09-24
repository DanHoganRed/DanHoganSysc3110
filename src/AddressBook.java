import java.util.*;


public class AddressBook {
	
	ArrayList<BuddyInfo> Book = new ArrayList<BuddyInfo>();
	public static void main(String[] args) {
		System.out.println("Address Book!\n");
		
		AddressBook AB = new AddressBook();
		BuddyInfo BI = new BuddyInfo();
		
		BI.setName("Sam");
		BI.setAge(20);
		BI.setPhonenumber(5555555);
		System.out.println("Hello " + BI.getName()+"\n");
		
		AB.addBuddy(BI);
		AB.removeBuddy(BI);

	}
	
	public void addBuddy(BuddyInfo Buddy){
		this.Book.add(Buddy);
	}
	
	public void removeBuddy(BuddyInfo Buddy){
		this.Book.remove(Buddy);
	}

}
