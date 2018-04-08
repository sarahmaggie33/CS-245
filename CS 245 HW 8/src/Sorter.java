import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Sorter extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	private static JComboBox dropdown;
	private JTextArea center;
	private static JButton sort; 
	private static JLabel label;
	private String sortType = "select";
	private static Sorter s;

	
	public static final String SELECTION_SORT_TEXT = "Selection Sort";
	public static final String INSERTION_SORT_TEXT = "Insertion Sort";
	public static final String MERGE_SORT_TEXT = "Merge Sort";
	
	public Sorter() {
		setTitle("Sorter");
	    setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
		createContents();
	    setVisible(true);

	}
	
	private void createContents() {
		// NORTH section: left aligned flow layout
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel text = new JLabel("Sorting algorithm:  ");
		String[] sorters = {SELECTION_SORT_TEXT, INSERTION_SORT_TEXT, MERGE_SORT_TEXT};
		dropdown = new JComboBox(sorters);
		sort = new JButton("Sort");
		north.add(text);
		north.add(dropdown);
		north.add(sort);
		// JLabel loading gif; only when sorting
		ImageIcon gif = new ImageIcon(this.getClass().getResource("loading.gif"));
		label = new JLabel(gif, SwingConstants.LEFT);
		label.setSize(25, 25);
		north.add(label);
		label.setVisible(false);
		north.revalidate();
		
		// CENTER section: JTextArea w/EmptyBorder of 10px on all sides
		
		// JTextArea uses two tabs "\t" to separate columns
		center = new JTextArea();
		EmptyBorder border = new EmptyBorder(10, 10, 10, 10);
		center.setBorder(border);
		center.setEditable(false);
		
		// add Listener instance (inner class) to button
		SortButtonListener listener = new SortButtonListener();
		DropdownListener listener2 = new DropdownListener();
		sort.addActionListener(listener);
		dropdown.addActionListener(listener2);
		
		// add display and buttons to JFrame
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
	}
	
	private class DropdownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox combo = (JComboBox)e.getSource();
			String selection = (String)combo.getSelectedItem();
			if (selection.equals("Merge Sort")) {
				center.setText("");
//				System.out.println("merge");
				sortType = "merge"; 
			} else if (selection.equals("Insertion Sort")) {
				center.setText("");
//				System.out.println("insertion");
				sortType = "insertion";
			} else if (selection.equals("Selection Sort")) {
				center.setText("");
//				System.out.println("select");
				sortType = "select";
			}

		}
	}
	private class SortButtonListener implements ActionListener {
		private int[] arr;
		private SorterRunnable sr;
		private int num;
		
		
		public void actionPerformed(ActionEvent e) {
			ExecutorService es = Executors.newSingleThreadExecutor();
			int[] n = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000};
			for (int ii = 0; ii < n.length; ii++) {
				arr = new int[n[ii]];
				fillArr();
				num = n[ii];
			
//			num = 1000;
//			arr = new int[1000];
//			fillArr();
			
		
			sr = new SorterRunnable(sortType, arr, num, s);
				if (e.getActionCommand().contains("Sort")) {
					// disable JComboBox and sort button
					dropdown.setEnabled(false);
					sort.setEnabled(false);
					center.setText("N\t\tRuntime (ms)");
					// show the loading gif
					label.setVisible(true);	
					es.execute(sr);
				}
			}
			es.shutdown();
			
			}
	    
		
		private void fillArr() {
			Random r = new Random();
			for(int i=0; i<arr.length; ++i) {
				arr[i] = r.nextInt();
			}
		}
	}
	
	public synchronized void displayResult(int n, long runtime) {
		String newText = center.getText() + "\n" + n +"\t\t" + runtime;
		center.setText(newText);
		System.out.println("display result has been called");
	}
	
	public void refreshDisplay() {
		dropdown.setEnabled(true);
		sort.setEnabled(true);
		label.setVisible(false);	
	}
	
	public static void main(String[] args) {
		s = new Sorter();
	}
}