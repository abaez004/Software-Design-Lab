import java.util.GregorianCalendar;


public class HeartRates{

	public HeartRates(String firstName, String lastName, int yearOfBirth){
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public int getYearOfBirth(){
		return yearOfBirth;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setYearOfBirth(int yearOfBirth){
		this.yearOfBirth = yearOfBirth;
	}
	
	public int age(){
		int currentYear = new GregorianCalendar().get(GregorianCalendar.YEAR);
		return currentYear - yearOfBirth;
	}
	
	public int maxHeartRate(){
		return 220 - this.age();
	}
	
	public String targetHeartRate(){
		int minTarget = maxHeartRate() * 5 / 10;
		int maxTarget = maxHeartRate() * 17 / 20;
		return minTarget + " - " + maxTarget;
	}
	
	private String firstName, lastName; 
	private int yearOfBirth;
}