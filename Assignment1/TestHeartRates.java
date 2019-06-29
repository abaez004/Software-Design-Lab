//Angel Baez Assignment #1 CSC221
import java.util.Scanner;

public class TestHeartRates{
	public static void main(String[] args){
		String firstName, lastName;
		int yearOfBirth;
		
		Scanner scan = new Scanner(System.in);
		System.out.printf("Person's First Name: ");
		firstName = scan.next();
		System.out.printf("Person's Last Name: ");
		lastName = scan.next();
		System.out.printf("Person's Year of Birth: ");
		yearOfBirth = scan.nextInt();
		
		HeartRates person = new HeartRates(firstName, lastName, yearOfBirth);
		System.out.printf("First Name: %s%nLast Name: %s%nAge: %d%nMaximum Heart Rate: %d%nTarget Heart Rate Range: %s", 
		person.getFirstName(), person.getLastName(), person.age(),
		person.maxHeartRate(), person.targetHeartRate());
		scan.close();
	}
}