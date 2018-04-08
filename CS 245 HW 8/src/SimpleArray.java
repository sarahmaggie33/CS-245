import java.security.SecureRandom;
import java.util.Arrays;

//caution: only thread safe if the "synchronized" modifier is present on the add and toString methods
public class SimpleArray {
	private static final SecureRandom generator = new SecureRandom();
	private final int[] array;	//the shared integer array
	private int writeIndex = 0;	//shared index of the next element to write
	
	//construct a SimpleArray of a given size
	public SimpleArray(int size) {
		array = new int[size];
	}
	
	//add a value to the shared array
	public synchronized void add(int value) {
		int position = writeIndex;	//store the write index
		try {
			//put thread to sleep for 0-499 millisecons
			Thread.sleep(generator.nextInt(500));
		} catch(InterruptedException ex) {
			//An InterruptedException removes the interrupted flag on the thread
			//Here we re-interrupt it to set the flag back to interrupted so that it finishes when no longer waiting
			Thread.currentThread().interrupt();	
		}
		
		//put value in the appropriate element
		array[position] = value;
		System.out.printf("%s wrote %2d to element %d.%n",  Thread.currentThread().getName(), value, position);
		++writeIndex;	//increment index of element to be written next
		System.out.printf("Next write index: %d%n", writeIndex);
	}
	
	//used for outputting the contents of the shared integer array
	@Override
	public synchronized String toString() {
		return Arrays.toString(array);
	}
}
