import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;
import java.io.IOException;
import java.nio.file.Paths;

import java.nio.file.Files;

public class Assignment6 {

    /*
     * Converts from a stack to a Queue. The queue will be represented as a LinkedList
     */
    public static Queue<Character> toQueue(Stack<Character> stackOriginal) {
    	LinkedList<Character> linkedChars = new LinkedList<Character>();
    	stackOriginal.stream().forEach((Character c) -> linkedChars.add(c));
    	return (Queue<Character>)linkedChars;
    }

    /*
     * Returns a stack with items reversed from the original stack
     */
    public static Stack<Character> reverseStack(Stack<Character> stackOriginal) {
    	Stack<Character> stackCopy = new Stack<Character>();
    	stackOriginal.stream().forEach((Character c) -> stackCopy.push(c));
    	Stack<Character> stackNew = new Stack<Character>();
    	stackOriginal.stream().forEach((Character c) -> stackNew.push(stackCopy.pop()));
        return stackNew;
    }

    /*
     * Returns the sum of integers between the start and end positions
     */
    public static int sumBetween(Stack<Integer> stack, int startPosition, int endPosition) {
    	if(stack == null || endPosition >= stack.size() || startPosition >= stack.size() || startPosition > endPosition) 
    		return -1;
    	return IntStream.range(startPosition, endPosition).map(i -> stack.get(i)).sum();
    }

    /*
     * Reads from an input file and returns the characters that are integers from the file.
     * The function will read the lines from the file, extract the characters, filter non-numeric characters (i.e. not 0,1,...9),
     * and place these character numbers on a stack
     */
    public static Stack<Character> readNumericFromFile(final String filePath) {
    	Stack<Character> numberStack = new Stack<Character>();
    	try{
    		List<String> lines = Files.readAllLines(Paths.get(filePath));
    		lines.forEach(s-> {
    			s.chars()
    			.filter(c -> (c < 58 && c > 47))
    			.mapToObj(i -> (char) i)
    			.forEach(c -> numberStack.push(c));
    		});
    	}
    	catch(IOException e){
    		System.out.println("You have entered an invalid path!");
    	}
        return numberStack;
    }
}