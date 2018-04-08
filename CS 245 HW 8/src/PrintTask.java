import java.security.SecureRandom;

public class PrintTask implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private final int sleepTime;	//random sleep time for thread
	private final String taskName;
	
	public PrintTask(String taskName) {
		this.taskName = taskName;
		
		//pick random sleep time between 0 and 5 seconds
		sleepTime = generator.nextInt(5000);	//milliseconds
	}
	
	//method run contains the code that a thread will execute
	@Override
	public void run() {
		try {
			System.out.printf("%s going to sleep for %d milliseconds.%n",  taskName, sleepTime);
			Thread.sleep(sleepTime);	//put thread to sleep for sleepTime amount of time
		} catch(InterruptedException exception) {
			exception.printStackTrace();
			//An InterruptedException removes the interrupted flag on the thread
			//Here we re-interrupt it to set the flag back to interrupted so that it finishes when no longer waiting
			Thread.currentThread().interrupt();
		}
		//print task name
		System.out.printf("%s done sleeping%n", taskName);
	}
}
