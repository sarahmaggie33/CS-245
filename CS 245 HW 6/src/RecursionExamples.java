import java.util.Set;
import java.util.TreeSet;

public class RecursionExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printStars(5);
		System.out.println("3^4=" + pow(3,4));
		printBinary(9);
		
		System.out.println("\nmadam " + ((isPalindrome("madam"))?"is ":"is not ") + "a palindrome.");
		System.out.println("maddam " + ((isPalindrome("maddam"))?"is ":"is not ") + "a palindrome.");
		System.out.println("parrot " + ((isPalindrome("parrot"))?"is ":"is not ") + "a palindrome.");
		permute("BAR");
		combinations("GOOGLE", 3);
	}
	
	/*
	 * Parameter: numStars
	 * Base case: numStars is 0
	 * Recursive case: print a star and repeat with numStars-1
	 */
	public static void printStars(int numStars) {
		//base case
		if(numStars <= 0) {
			System.out.println();
		} 
		//recursive case
		else {
			System.out.print("*");
			printStars(numStars-1);
		}
	}
	
	/*
	 * Parameters: base, exponent
	 * Base case: exponent is 0
	 * Recursive case: base * pow(base, exponent-1)
	 */
	public static int pow(int base, int exponent) {
		//base case
		if(exponent == 0) {
			return 1;
		}
		//recursive case
		else {
			return base * pow(base, exponent-1);
		}
	}
	
	/*
	 * Parameter: n (decimal number)
	 * Base case: n < 2 (print 0 or 1)
	 * Recursive case: printBinary(n/2), printBinary(n%2)
	 */
	public static void printBinary(int n) {
		//base case
		if(n < 2) {
			System.out.print(n);
		}
		//recursive case
		else {
			printBinary(n/2);
			printBinary(n%2);
		}
	}
	
	/*
	 * Parameter: s (string)
	 * Base case: 0 or 1 characters left
	 * Recursive case: see if first char == last char and inside is palindrome
	 */
	public static boolean isPalindrome(String s) {
		//base case (0 or 1 character string)
		if(s.length() < 2) {
			return true;
		}
		else {
			char first = s.charAt(0);
			char last = s.charAt(s.length()-1);
			return first == last && isPalindrome(s.substring(1, s.length()-1));
		}
	}
	
	// Outputs all permutations of the given string.
	public static void permute(String word) {
	    permute(word, "");
	}

	//permute(BAR) = permute(BAR, '') = permute(AR, B), permute(BR, A), permute(BA, R)
	
	//permute(AR, B) = permute(R, BA), permute(A, BR)
	//permute(R, BA) = permute('', BAR)
	//permute('', BAR) = BAR
	//permute(A, BR) = permute('', BRA)
	//permute('', BRA) = BRA
	
	//permute(BR, A) = permute(R, AB), permute(B, AR)
	//permute(R, AB) = permute('', ABR)
	//permute('', ABR) = ABR
	//permute(B, AR) = permute('', ARB)
	//permute('', ARB) = ARB
	
	//permute(BA, R) = permute(A, RB), permute(B, RA)
	//permute(A, RB) = permute('', RBA)
	//permute('', RBA) = RBA
	//permute(B, RA) = permute('', RAB)
	//permute('', RAB) = RAB
	private static void permute(String word, String chosen) {
	    if (word.length() == 0) {
	        // base case: no choices left to be made
	        System.out.println(chosen);
	    } else {
	        // recursive case: choose each possible next letter
	        for (int i = 0; i < word.length(); i++) {
	            char nextChar = word.charAt(i);  													// choose			
	            String unchosen = word.substring(0, i) + word.substring(i + 1);	// remove
	            permute(unchosen, chosen + nextChar);         							// explore
	        }
	    }     
	}
	
	// Outputs all permutations of the given string.
	public static void combinations(String word, int length) {
		Set<String> uniqueWords = new TreeSet<String>();
		combinations(word, length, "", uniqueWords);
		for(String s : uniqueWords) {
			System.out.println(s);
		}
	}


	private static void combinations(String word, int length, String chosen, Set<String> uniqueWords) {
		if (chosen.length() == 3) {
			// base case: no choices left to be made
			uniqueWords.add(chosen);
		} else {
			// recursive case: choose each possible next letter
			for (int i = 0; i < word.length(); i++) {
				char nextChar = word.charAt(i);																// choose
				if(chosen.indexOf(nextChar) == -1) {
					String unchosen = word.substring(0, i) + word.substring(i + 1);			// remove
					combinations(unchosen, length, chosen + nextChar, uniqueWords);	// explore
				}
			}
		}
	}
}
