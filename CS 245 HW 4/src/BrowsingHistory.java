// imports for link opening
import java.awt.Desktop;
import java.net.URI;
// regular imports
import java.util.Scanner;

public class BrowsingHistory extends Stack<String> {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		Scanner in = new Scanner(System.in);
		String input = "";
		int size = 0;
		
//		use to go open link
		Desktop desktop = java.awt.Desktop.getDesktop();

		while (size < 2 && !input.equals("quit")) {
			System.out.println();
			System.out.print("Enter a URL or \"quit\": ");
			input = in.nextLine();
			String current = null;
			// Visit and display input (url)
			if (input.contains("http://www.") || input.contains("https://www.")) {
				stack.push(input);
				size++;
				current = stack.peek();
				
//				use to open link
				try {
					URI uri = new URI(input);
					desktop.browse(uri);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("Current URL: " + current);

			// Go back to and display the previous input (url)
			} else if (input.equals("back")) {
				if (size > 2) {
					stack.pop();
					size--;
					current = stack.peek();
					System.out.println("Current URL: " + current);
					
//					use to open link
					try {
						URI uri = new URI(current);
						desktop.browse(uri);
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				// Inform the user there is no URL to go back to

				} else {
					System.out.println("No URL to go back to.");
				}

			}
			 while (size > 1 && !input.equals("quit")) {
				 System.out.println();
				 System.out.print("Enter a URL, \"back\", or \"quit\": ");
				 input = in.nextLine();
				 if (input.contains("back")) {
					 if (size > 1){
						stack.pop();
						current = stack.peek();	
						size--;
						System.out.println("Current URL: " + current);
							
//						use to open link
						try {
							URI uri = new URI(current);
							desktop.browse(uri);
						} catch(Exception e) {
							e.printStackTrace();
						}	
							
						// Inform the user there is no URL to go back to
	
						} else {
							System.out.println("No URL to go back to.");
						}
				} else if (input.contains("http://www.")) {
					System.out.println("Current URL: " + input);
					stack.push(input);
					size++;
					
//					use to open link
					try {
						URI uri = new URI(current);
						desktop.browse(uri);
					} catch(Exception e) {
						e.printStackTrace();
					}
				} 
			 }
		}
		in.close();

	}
}
