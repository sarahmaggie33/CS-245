
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DumbPhone extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 500;
	private JTextArea display;
	private JButton topMiddleButton;
	private JButton topLeftButton;
	private JButton topRightButton;
	private JButton[] numberButtons;
	private JButton starButton;
	private JButton poundButton;
	private static final String CALL_BUTTON_TEXT = "Call";
	private static final String TEXT_BUTTON_TEXT = "Text";
	private static final String DELETE_BUTTON_TEXT = "Delete";
	private static final String CANCEL_BUTTON_TEXT = "Cancel";
	private static final String SEND_BUTTON_TEXT = "Send";
	private static final String END_BUTTON_TEXT = "End";
	private static final String CALLING_DISPLAY_TEXT = "Calling...";
	private static final String TEXT_DISPLAY_TEXT = "Enter text...";
	private static final String ENTER_NUMBER_TEXT = "Enter a number...";

	
	public DumbPhone() {
		setTitle("Dumb Phone");
	    setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    setBackground(Color.blue);
	    createContents();
//	    add(new JButton("text"), BorderLayout.NORTH);
	    setVisible(true);
	    
	}
	
	private void createContents() {
		//create JPanel, and JTextArea display
		JPanel panel =  new JPanel();
		GridLayout grid = new GridLayout(5, 3, 1, 1);
		panel.setLayout(grid);
		JTextArea display = new JTextArea(280, 80);
		display.setEditable(false);
		display.setLineWrap(true);
		display.setFont(new Font("Helvetica", Font.PLAIN, 32));
        
        //create JButtons
        topLeftButton = new JButton(DELETE_BUTTON_TEXT);
        topLeftButton.setEnabled(false);
        topMiddleButton = new JButton(CALL_BUTTON_TEXT);
        topRightButton = new JButton(TEXT_BUTTON_TEXT);
        JButton one = new JButton("1");
//      numberButtons[1] = new JButton("1");
        JButton two = new JButton("<html><center>2<br>ABC</center></html>");
        JButton three = new JButton("<html><center>3<br>DEF</center></html>");
        JButton four = new JButton("<html><center>4<br>GHI</center></html>");
        JButton five = new JButton("<html><center>5<br>JKL</center></html>");
        JButton six = new JButton("<html><center>6<br>MNO</center></html>");
        JButton seven = new JButton("<html><center>7<br>PQRS</center></html>");
        JButton eight = new JButton("<html><center>8<br>TUV</center></html>");
        JButton nine = new JButton("<html><center>9<br>WXYZ</center></html>");
        JButton dot = new JButton(".");
        JButton zero =  new JButton("<html><center>0<br>space</center></html>");
        JButton pound = new JButton("#");
        
        //add JButtons to buttons JPanel
        panel.add(topLeftButton);
        panel.add(topMiddleButton);
        panel.add(topRightButton);
        panel.add(one);
        panel.add(two);
        panel.add(three);
        panel.add(four);
        panel.add(five);
        panel.add(six);
        panel.add(seven);
        panel.add(eight);
        panel.add(nine);
        panel.add(dot);
        panel.add(zero);
        panel.add(pound);

        
        //add Listener instance (inner class) to buttons
        Listener listener = new Listener();
        topLeftButton.addActionListener(listener);
        topMiddleButton.addActionListener(listener);
        topRightButton.addActionListener(listener);
        one.addActionListener(listener);
        two.addActionListener(listener);
        three.addActionListener(listener);
        four.addActionListener(listener);
        five.addActionListener(listener);
        six.addActionListener(listener);
        seven.addActionListener(listener);
        eight.addActionListener(listener);
        nine.addActionListener(listener);
        dot.addActionListener(listener);
        zero.addActionListener(listener);
        pound.addActionListener(listener);
       
        //add display and buttons to JFrame
	    add(display, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);

	}
	
	private class Listener implements ActionListener {
		private boolean isNumberMode = true;
		private String lastPressed = "";
		private int lastCharacterIndex = 0;
		private Date lastPressTime;
		public void actionPerformed(ActionEvent e) {
			if (!display.getText().equals(ENTER_NUMBER_TEXT) || !lastPressed.equals("")) {
				topLeftButton.setEnabled(true);
			}
			if(e.getSource().equals("1")) {
				lastPressed += "1";
			} else if (e.getActionCommand().contains("2")) {
				lastPressed += "2";
			} else if (e.getActionCommand().contains("3")) {
				lastPressed += "3";
			} else if (e.getActionCommand().contains("4")) {
				lastPressed += "4";
			} else if (e.getActionCommand().contains("5")) {
				lastPressed += "5";
			} else if (e.getActionCommand().contains("6")) {
				lastPressed += "6";
			} else if (e.getActionCommand().contains("7")) {
				lastPressed += "7";
			} else if (e.getActionCommand().contains("8")) {
				lastPressed += "8";
			} else if (e.getActionCommand().contains("9")) {
				lastPressed += "9";
			} else if (e.getActionCommand().contains("*")) {
				lastPressed += "*";
			} else if (e.getActionCommand().contains("#")) {
				lastPressed += "#";
			} else if (e.getActionCommand().equals("Call")) {
				if (!lastPressed.equals("")) {
					display.setText(CALLING_DISPLAY_TEXT);
					topLeftButton.setText(null);
					topLeftButton.setEnabled(false);
					topRightButton.setText(null);
					topRightButton.setEnabled(false);
					topMiddleButton.setText(END_BUTTON_TEXT);
				} else {
					display.setText(ENTER_NUMBER_TEXT);
				}
			} else if (e.getActionCommand().equals("Text")) {
				if (!lastPressed.equals("")) {
				
				} else {
					display.setText(ENTER_NUMBER_TEXT);
				}
			}
	    }
	}
	
	public static void main(String[] args) {
		new DumbPhone();
	}
}