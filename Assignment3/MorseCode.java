//Angel Baez CSC221
public class MorseCode{
	private char ch;
	private String code;
	
	//creates a MorseCode object with a char ch associated with a String code
	//will throw an exception if the character is not within the range 31 < ch < 90
	public MorseCode(char ch, String code) throws Exception {
		if(ch < 31 || ch > 90) {
			throw new Exception("Out-of-range character: " + ch);
		}
		this.ch =  ch;
		this.code = code;
	}
	
	//returns the character ch
	public char getCh() {
		return this.ch;
	}

	//modifies ch to a new value
	public void setCh(char ch) {
		this.ch = ch;
	}

	//returns the code
	public String getCode() {
		return this.code;
	}
	
	//changes code to a new code
	public void setCode(String code) {
		this.code = code;
	}
}