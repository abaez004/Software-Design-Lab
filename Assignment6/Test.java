import java.util.Queue;
import java.util.Stack;

public class Test{
	public static void main(String[] args) {
		Stack<Character> s = new Stack<Character>();
		s.push('a');
		s.push('b');
		s.push('c');
		Queue<Character> q = Assignment6.toQueue(s);
		System.out.println(q);
		System.out.println(Assignment6.reverseStack(s));
		System.out.println(s);
		Stack<Integer> si = new Stack<Integer>();
		si.push(1);
		si.push(2);
		si.push(3);
		si.push(4);
		si.push(5);
		System.out.println(Assignment6.sumBetween(si, 2, 4));
		System.out.println(Assignment6.readNumericFromFile("testFile.txt"));
	}
}