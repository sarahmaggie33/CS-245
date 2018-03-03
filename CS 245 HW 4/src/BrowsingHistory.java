// imports for link opening
//import java.awt.Desktop;
//import java.net.URI;

// regular imports
import java.util.Scanner;
import java.util.EmptyStackException;

public class BrowsingHistory extends Stack<String> {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		Stack<String> backup = new Stack<String>();
		Scanner in = new Scanner(System.in);
		String input = "";
		
//		use to go open link
//		Desktop desktop = java.awt.Desktop.getDesktop();

		
		while (!input.equals("quit")) {
			System.out.println();
			if (stack.isEmpty()) {
				System.out.print("Enter a URL or \"quit\": ");
			} else {
				System.out.print("Enter a URL, \"back\", or \"quit\": ");
			}
			input = in.nextLine();
			String current = null;
			// Visit and display input (url)
			if (input.contains("http://www.") || input.contains("https://www.")) {
				try {
					stack.push(backup.pop());
				} catch (EmptyStackException e) {
					System.out.print("");
				}
				stack.push(input);
				current = stack.peek();
				
//				use to open link
//				try {
//					URI uri = new URI(input);
//					desktop.browse(uri);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//				
				System.out.println("Current URL: " + current);

			// Go back to and display the previous input (url)
			} else if (input.equals("back")) {
				try {
				backup.push(stack.pop());
				current = stack.peek();
				System.out.println("Current URL: " + current);
//					use to open link
//					try {
//						URI uri = new URI(current);
//						desktop.browse(uri);
//					} catch(Exception e) {
//						e.printStackTrace();
//					}
					
				// Inform the user there is no URL to go back to

				} catch (EmptyStackException e){
					System.out.println("No URL to go back to.");
				}

			}

			
		}
		in.close();

	}
}
