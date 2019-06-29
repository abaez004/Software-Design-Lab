//Angel Baez Assignment #1 CSC221
import java.util.Random;


public class TortoiseHareRace{
	
	//Calculates the amount of spaces the tortoise moves based on the random number move passed in.
	//If the tortoise goes below the 1st position or above the 70th position, the number is adjusted.
	public static int tortoiseCalculate(int tortoise, int move) {
		if(move <= 4)
			tortoise += 3;
		else if(move <= 6)
			tortoise -= 6;
		else
			tortoise += 1;
		
		if(tortoise < 1)
			tortoise = 1;
		else if(tortoise > 70)
			tortoise = 70;
		return tortoise;
	}
	
	
	//Calculates the amount of spaces the hare moves based on the random number move passed in.
	//If the hare goes below the 1st position or above the 70th position, the number is adjusted.
	public static int hareCalculate(int hare, int move) {
		if(move <= 1)
			hare += 0;
		else if(move <= 3)
			hare += 9;
		else if(move <= 4)
			hare -= 12;
		else if(move <= 7)
			hare += 1;
		else 
			hare -= 2;
		
		if(hare < 1)
			hare = 1;
		else if(hare > 70)
			hare = 70;
		
		return hare;
	}
	
	//Prints the current time of the race and the positions of the hare and the tortoise on the race track.
	public static void printRace(int tortoise, int hare, int round) {
		System.out.printf("Time: %d%n", round);
		String tortSpace = "";
		String hareSpace = "";
		String diffSpace = "";
		int difference = tortoise - hare;
		
		if(difference < 0)
			difference *= -1;
		
		for(int i = 1; i < tortoise; ++i)
			tortSpace += " ";
		
		for(int i = 1; i < hare; ++i)
			hareSpace += " ";
		
		for(int i = 1; i < difference; ++i)
			diffSpace += " ";
		
		if(tortoise == hare)
			System.out.printf("%s%s%n",tortSpace, "B");
		else if (tortoise > hare)
			System.out.printf("%s%s%s%s%n", hareSpace, "H", diffSpace, "T");
		else 
			System.out.printf("%s%s%s%s%n", tortSpace, "T", diffSpace, "H");
		
		String raceLine = "";
		for(int i = 0; i < 70; ++i)
			raceLine += "-";
		System.out.printf("%s%n", raceLine);
	}
	
	public static void main(String args[]) {
		int tortoise = 1;
		int hare = 1;
		int move;
		int round = 0;
		
		Random rando = new Random();
		System.out.println("ON YOUR MARK, GET SET\nBANG !!!!!\nAND THEY'RE OFF !!!!!\n");
		printRace(tortoise, hare, round);
		
		while(tortoise < 70 && hare < 70){
			round++;
			move = rando.nextInt(10);
			tortoise = tortoiseCalculate(tortoise, move);
			move = rando.nextInt(10);
			hare = hareCalculate(hare, move);
			printRace(tortoise, hare, round);
		}
		
		if(tortoise > hare)
			System.out.println("TORTOISE WINS!!! YAY!!!");
		else if(hare > tortoise)
			System.out.println("Hare wins. Yuch.");
		else
			System.out.println("It's a tie.");
		System.out.printf("Time Elapsed = %d seconds",round);
	}
	
	
}