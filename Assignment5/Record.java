package application;

public class Record{
	private String name;
	private String state;
	private String phoneNumber;
	
	//default constructor of Record using the non default constructor 
	public Record() {
		this("","","");
	}
	
	//non default constructor of record that takes in three strings and assigns them to the appropriate fields
	public Record(String name, String state, String phoneNumber) {
		this.name = name;
		this.state = state;
		this.phoneNumber = phoneNumber;
	}
	
	//returns the name
	public String getName() {
		return this.name;
	}
	
	//returns the state
	public String getState() {
		return this.state;
	}
	
	//returns the phone numbers
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	//sets the name to the name passed in
	public void setName(String name) {
		this.name = name;
	}
	
	//sets the state to the state passed in
	public void setState(String state) {
		this.state = state;
	}
	
	//sets the phone number to the phone number passed in
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}