import javax.swing.SwingUtilities;

public class SorterRunnable {
	//time and execute the selected sort
	private String sortType;
	private int[] arr;
	
	public SorterRunnable(String sortType, int[] arr) {
		this.sortType = sortType;
		this.arr = arr;
	}
	
	public void sort(String sortType, int[] arr) {
		if (sortType.equals("merge")) {
			MergeSort.mergeSort(arr);
		} else if (sortType.equals("insertion")) {
			InsertionSort.sort(arr);
		} else if (sortType.equals("select")) {
			SelectionSort.sort(arr);
		}
	

	
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		//call Sorter's displayResult method here
		}
	});
	
	}
}
