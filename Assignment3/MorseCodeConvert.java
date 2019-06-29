//Angel Baez CSC221
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConvert{
	private ArrayList<MorseCode> listCodes;
	
	//MorseCodeConvert constructor that creates an array list of type MorseCode by reading in a file
	//of tab delimited entries. Checks for entry errors using exception handling.
	public MorseCodeConvert(String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			listCodes = new ArrayList<MorseCode>();
			while(scan.hasNext()) {
				String s = scan.nextLine();
				if(s.contains("\t")) {
					String first = s.substring(0, s.indexOf("\t"));
					String last = s.substring(s.indexOf("\t") + 1);
					if(first.length() == 1 && !last.isEmpty()) {
						char c = first.charAt(0);
						try {
							listCodes.add(new MorseCode(c, last));
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}
					else
						System.out.println("Skipping line: " + s);
				}
				else
					System.out.println("Skipping line: " + s);
			}
			scan.close();
		}
		catch(Exception e) {
			e = new Exception("Invalid file name: " + fileName);
			System.out.println(e.getMessage());
		}
	}
	
	//Prints the entries of listCodes
	public void printMorseCodeList(){
		for(MorseCode m:listCodes) {
			System.out.printf("('%c', %s)%n", m.getCh(), m.getCode());
		}
	}
	
	//Takes a string s and converts it to morse code using the entries in listCodes
	public void convertString(String s) {
		if(s == null || s == "" || s == "\n") {
			System.out.println();
			return;
		}
		
		s = s.toUpperCase();
		
		for(int i = 0; i < s.length(); ++i) {
			boolean found = false;
			char currentChar = s.charAt(i);
			if(currentChar != ' ') {
				for(MorseCode m:listCodes) {
					if(m.getCh() == currentChar) {
						System.out.print(m.getCode() + " ");
						found = true;
						break;
					}
				}
				if(!found)
					System.out.print("? ");
			}
		}
		System.out.println();
	}
	
	//Reads a file and converts the text to morse code in the console
	//checks to see if the file is valid and throws an exception if not
	public void convertFile(String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNext()) {
				String line = scan.nextLine();
				convertString(line);
			}
			scan.close();
		}
		catch(Exception e) {
			e = new Exception("Invalid file name: " + fileName);
			System.out.println(e.getMessage());
		}
	}
}