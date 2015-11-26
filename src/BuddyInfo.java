import java.io.Serializable;

/*Hello Test*/
public class BuddyInfo implements Serializable{
	private String name;
	private int age;
	private long phonenumber;
	
	BuddyInfo()
	{
		name = null;
		age = 0;
		phonenumber= 0;
	}
	
	BuddyInfo(String name, int age, int phonenumber)
	{
		this.name = name;
		this.age = age;
		this.phonenumber = phonenumber;
	}
	
	BuddyInfo(BuddyInfo buddy)
	{
		this.name = buddy.getName();
		this.age = buddy.getAge();
		this.phonenumber = buddy.getPhonenumber();
	}
	
	public String getName() {
		return name;
	}
	
	public String getGreeting() {
		return "Hello " + name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean Over18() {
		return this.age>18;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public static BuddyInfo Import(String s)
	{
		String[] buddy = s.split("\\$");
		BuddyInfo b = new BuddyInfo(buddy[0],Integer.parseInt(buddy[1]),Integer.parseInt(buddy[2]));
		return b;
	}
	
	public String toString() {
		String s = "";
		s += this.name + "$";
		s += this.age + "$";
		s += this.phonenumber;
		return s;
	}
}
