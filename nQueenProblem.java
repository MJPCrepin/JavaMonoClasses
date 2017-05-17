import java.util.Scanner;
import java.util.Stack;

//	The N-Queens problem: placing amount n of chess queens on an n*n chessboard so that no two queens threaten each other.
//	Value of n given to rows and columns, then placing a queen on every row and consecutively column to see if there is a conflict.
//	If there is, the code continues by placing queens on the next column, repeating until n rows have been tested.

public class nQueenProblem {

	public static Stack<Integer> s = new Stack<Integer>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");	// Ideally under 13!
		int n = in.nextInt(); 
		double startTime = System.currentTimeMillis();
		int total = solve(n);
		double endTime = System.currentTimeMillis();
		double time = (endTime-startTime)/1000;
		System.out.println("There are "+total+" solutions to the "+n+"-queens problem. That just took "+time+" seconds!");
		in.close();
	}
	
	public static int solve(int n) { 					// Iterate through chessboard and place queens
		int total = 0;
		int row = 0;
		int col = 0;
		while ( row < n ) { 
			while ( col < n ) { 
				if (isConflict(row, col) == false ) {
					s.push(col);
					break;
				} else col++;
			}
			if (s.isEmpty() == true) break; 			// no or all solutions have been found
			if (col >= n) { 							// finished all possible placements in a row
				col = s.pop() + 1;
				row--;
			} else {
				row++; col = 0;
			}
			if (s.size()==n){ 							// if stack size is n, a solution is found
				total++;
				System.out.println(total + ": \t" + s);
				col = s.pop() + 1; 						// continue to find next solution
				row--; }
		}
		return total;
	}
	
	public static boolean isConflict(int row, int col) {
		int diff = row-col;
		int sum = row+col;
		for (int i = 0; i < row; i++) {
			int t = s.get(i);
			if (t==col || i-t == diff || i+t == sum) return true;
		} return false;
	}
}
