
public class BuddyInfo {
	private String name;
	private int age;
	private long phonenumber;

	public static void main(String[] args) {
		BuddyInfo BI = new BuddyInfo();
		BI.setName("Sam");
		BI.setAge(20);
		BI.setPhonenumber(5555555);
		System.out.println("Hello " + BI.getName()+"\n");

	}

	public String getName() {
		return name;
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

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

}
