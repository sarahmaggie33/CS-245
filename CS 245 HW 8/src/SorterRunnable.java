import javax.swing.SwingUtilities;

public class SorterRunnable implements Runnable {
	//time and execute the selected sort
	private String sortType;
	private int[] arr;
	private int n;
	private long startTime;
	private long endTime;
	private long finalTime;
	private Sorter s;
	
	public SorterRunnable(String sortType, int[] arr, int n, Sorter s) {
		this.sortType = sortType;
		this.arr = arr;
		this.n = n;
		this.s = s;
	}
	
	@Override
	public void run() {
		if (sortType.equals("merge")) {
			startTime = System.currentTimeMillis();
			MergeSort.mergeSort(arr);
			endTime = System.currentTimeMillis();
		} else if (sortType.equals("insertion")) {
			startTime = System.currentTimeMillis();
			InsertionSort.sort(arr);
			endTime = System.currentTimeMillis();
		} else if (sortType.equals("select")) {
			startTime = System.currentTimeMillis();
			SelectionSort.sort(arr);
			endTime = System.currentTimeMillis();
		}
	
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			finalTime = endTime - startTime;
			//call Sorter's displayResult method here
			s.displayResult(n, finalTime);
			if (n == 256000) {
				s.refreshDisplay();
		    }
		}
	});
	}
	
}
