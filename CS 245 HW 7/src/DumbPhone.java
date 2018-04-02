
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DumbPhone extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 500;
	private JTextArea display;
	private JButton topMiddleButton;
	private JButton topLeftButton;
	private JButton topRightButton;
	private JButton[] numberButtons = new JButton[10];
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
		createContents();
		getContentPane().add(display, BorderLayout.NORTH);
		setVisible(true);

	}

	private void createContents() {
		// create JPanel, and JTextArea display
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(5, 3, 1, 1);
		panel.setLayout(grid);
		display = new JTextArea();
		display.setPreferredSize(new Dimension(280, 80));
		display.setEditable(false);
		display.setLineWrap(true);
		display.setFont(new Font("Helvetica", Font.PLAIN, 32));

		// create JButtons
		topLeftButton = new JButton(DELETE_BUTTON_TEXT);
		topLeftButton.setEnabled(false);
		topMiddleButton = new JButton(CALL_BUTTON_TEXT);
		topRightButton = new JButton(TEXT_BUTTON_TEXT);
		numberButtons[0] = new JButton("1");
		numberButtons[1] = new JButton("<html><center>2<br>ABC</center></html>");
		numberButtons[2] = new JButton("<html><center>3<br>DEF</center></html>");
		numberButtons[3] = new JButton("<html><center>4<br>GHI</center></html>");
		numberButtons[4] = new JButton("<html><center>5<br>JKL</center></html>");
		numberButtons[5] = new JButton("<html><center>6<br>MNO</center></html>");
		numberButtons[6] = new JButton("<html><center>7<br>PQRS</center></html>");
		numberButtons[7] = new JButton("<html><center>8<br>TUV</center></html>");
		numberButtons[8] = new JButton("<html><center>9<br>WXYZ</center></html>");
		starButton = new JButton("*");
		numberButtons[9] = new JButton("<html><center>0<br>space</center></html>");
		poundButton = new JButton("#");

		// add JButtons to buttons JPanel
		panel.add(topLeftButton, 0);
		panel.add(topMiddleButton, 1);
		panel.add(topRightButton, 2);
		for (int i = 0; i < numberButtons.length; i++) {
			panel.add(numberButtons[i], i + 3);
		}
		panel.add(starButton, 12);
		panel.add(poundButton, 14);

		// add Listener instance (inner class) to buttons
		Listener listener = new Listener();
		topLeftButton.addActionListener(listener);
		topMiddleButton.addActionListener(listener);
		topRightButton.addActionListener(listener);
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].addActionListener(listener);
		}
		starButton.addActionListener(listener);
		poundButton.addActionListener(listener);

		// add display and buttons to JFrame
		getContentPane().add(panel, BorderLayout.CENTER);

	}

	private class Listener implements ActionListener {
		private boolean isNumberMode = true;
		private String lastPressed = "";
		private String displayText = "";
		private boolean isCallingMode = false;
		private boolean isCanceled = false;
		private Date lastPressTime;

		public void actionPerformed(ActionEvent e) {
			Date dNow = new Date();
			
			////////////////////
			// number mode
			////////////////////
			
			// is delete visible?
			if (isNumberMode) {
				if (!display.getText().equals(ENTER_NUMBER_TEXT) && !isCallingMode && !displayText.equals("")) {
					topLeftButton.setEnabled(true);
				} else {
					topLeftButton.setEnabled(false);
				} if (e.getActionCommand().contains("Delete")) {
					if (displayText.length() >= 1 ) {
						String newString = displayText.substring(0, displayText.length() - 1);
						displayText = newString;
					}
				} else if (e.getActionCommand().contains("1")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += e.getActionCommand().toString();
					lastPressed = e.getActionCommand().toString();
				} else if (e.getActionCommand().contains("2")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "2";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("3")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "3";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("4")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "4";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("5")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "5";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("6")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "6";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("7")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "7";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("8")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "8";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("9")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "9";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().equals("*")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "*";
					lastPressed = e.getActionCommand().toString();

				} else if (e.getActionCommand().contains("0")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "0";
					lastPressed = e.getActionCommand().toString();
				} else if (e.getActionCommand().contains("#")) {
					if (displayText.equals(ENTER_NUMBER_TEXT) || displayText.equals(CALLING_DISPLAY_TEXT)) {
						displayText = "";
					}
					displayText += "#";
					lastPressed = e.getActionCommand().toString();


				} else if (e.getActionCommand().contains("Call")) {
					if (!displayText.equals("") && !displayText.equals(ENTER_NUMBER_TEXT)) {
						displayText = CALLING_DISPLAY_TEXT;
						topLeftButton.setText(null);
						topLeftButton.setEnabled(false);
						topRightButton.setText(null);
						topRightButton.setEnabled(false);
						topMiddleButton.setText(END_BUTTON_TEXT);
						isCallingMode = true;
					} else {
						topLeftButton.setEnabled(false);

						displayText = ENTER_NUMBER_TEXT;
					}
				} else if (e.getActionCommand().equals("Text")) {
					if (!displayText.equals("") && !displayText.equals(ENTER_NUMBER_TEXT)) {
						displayText = TEXT_DISPLAY_TEXT;
						topLeftButton.setEnabled(false);
						topMiddleButton.setText(CANCEL_BUTTON_TEXT);
						topRightButton.setText(SEND_BUTTON_TEXT);
						topRightButton.setEnabled(false);
						isNumberMode = false;
						numberButtons[0].setEnabled(false);
						starButton.setEnabled(false);
						poundButton.setEnabled(false);

					} else {
						displayText = ENTER_NUMBER_TEXT;
					}

				} else if (e.getActionCommand().equals("End")) {
					display.setText("");
					lastPressed = "";
					displayText = "";
					topLeftButton.setEnabled(false);
					topRightButton.setText(TEXT_BUTTON_TEXT);
					topRightButton.setEnabled(true);
					topMiddleButton.setText(CALL_BUTTON_TEXT);
					topLeftButton.setText(DELETE_BUTTON_TEXT);
					isCallingMode = false;
				}
				if (displayText.equals("")) {
					topLeftButton.setEnabled(false);
				}
			} // end number mode
			
			////////////////////////
			// letter mode
			////////////////////////
			else {
				if (!display.getText().equals(ENTER_NUMBER_TEXT) && !display.getText().equals(TEXT_DISPLAY_TEXT) && !displayText.equals("")) {
					topLeftButton.setEnabled(true);
					topRightButton.setEnabled(true);
				} else {
					topRightButton.setEnabled(false);
					topLeftButton.setEnabled(false);
				}
				if (e.getActionCommand().contains("Delete")) {
					if (displayText.length() >= 1 ) {
						String newString = displayText.substring(0, displayText.length() - 1);
						displayText = newString;
					}
				} else if (e.getActionCommand().contains("2")) {
					if (displayText.equals(TEXT_DISPLAY_TEXT)) {
						displayText = "";
					}
					if (lastPressTime == null) {
						lastPressTime = new Date();
					}
					long diff = dNow.getTime() - lastPressTime.getTime();
					long diffSeconds = diff / 1000 % 60;
					lastPressTime = new Date();
					if (diffSeconds <= 1) {
						if (lastPressed.equals("A")) {
							lastPressed = "B";
							String newDisplay = displayText.substring(0, displayText.length() - 1);
							newDisplay += "B";
							displayText = newDisplay;
						} else if (lastPressed.equals("B")) {
							lastPressed = "C";
							String newDisplay = displayText.substring(0, displayText.length() - 1);
							newDisplay += "C";
							displayText = newDisplay;

						} else if (lastPressed.equals("C")) {
							lastPressed = "A";
							String newDisplay = displayText.substring(0, displayText.length() - 1);
							newDisplay += "A";
							displayText = newDisplay;

						} else {
							lastPressed = "A";
							displayText += "A";
						}
					} else {
						displayText += "A";
					}
				}
			
					// 3 - DEF button
					else if (e.getActionCommand().contains("3")) {
						if (displayText.equals(TEXT_DISPLAY_TEXT)) {
							displayText = "";
						}
						if (lastPressTime == null) {
							lastPressTime = new Date();
						}
						long diff1 = dNow.getTime() - lastPressTime.getTime();
						long diffSeconds1 = diff1 / 1000 % 60;
						lastPressTime = new Date();
						if (diffSeconds1 <= 1) {
							if (lastPressed.equals("D")) {
								lastPressed = "E";
								String newDisplay = displayText.substring(0, displayText.length() - 1);
								newDisplay += "E";
								displayText = newDisplay;
							} else if (lastPressed.equals("E")) {
								lastPressed = "F";
								String newDisplay = displayText.substring(0, displayText.length() - 1);
								newDisplay += "F";
								displayText = newDisplay;

							} else if (lastPressed.equals("F")) {
								lastPressed = "D";
								String newDisplay = displayText.substring(0, displayText.length() - 1);
								newDisplay += "D";
								displayText = newDisplay;

							} else {
								lastPressed = "D";
								displayText += "D";
							}
						} else {
							displayText += "D";
						}
					
					//end 3 button
					// 4 - GHI button
					} else if (e.getActionCommand().contains("4")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("G")) {
									lastPressed = "H";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "H";
									displayText = newDisplay;
								} else if (lastPressed.equals("H")) {
									lastPressed = "I";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "I";
									displayText = newDisplay;

								} else if (lastPressed.equals("I")) {
									lastPressed = "G";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "G";
									displayText = newDisplay;

								} else {
									lastPressed = "G";
									displayText += "G";
								}
							} else {
								displayText += "G";
							}
						
					//end 4 button
					// 5 - JKL button
					} else if (e.getActionCommand().contains("5")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("J")) {
									lastPressed = "K";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "K";
									displayText = newDisplay;
								} else if (lastPressed.equals("K")) {
									lastPressed = "L";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "L";
									displayText = newDisplay;

								} else if (lastPressed.equals("L")) {
									lastPressed = "J";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "J";
									displayText = newDisplay;

								} else {
									lastPressed = "J";
									displayText += "J";
								}
							} else {
								displayText += "J";
							}
						
					//end 5 button
					// 6 - MNO button
					} else if (e.getActionCommand().contains("6")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("M")) {
									lastPressed = "N";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "N";
									displayText = newDisplay;
								} else if (lastPressed.equals("N")) {
									lastPressed = "O";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "O";
									displayText = newDisplay;

								} else if (lastPressed.equals("O")) {
									lastPressed = "M";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "M";
									displayText = newDisplay;

								} else {
									lastPressed = "M";
									displayText += "M";
								}
							} else {
								displayText += "M";
							}
						
					//end 6 button
					// 7 - PQRS button
					} else if (e.getActionCommand().contains("7")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("P")) {
									lastPressed = "Q";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "Q";
									displayText = newDisplay;
								} else if (lastPressed.equals("Q")) {
									lastPressed = "R";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "R";
									displayText = newDisplay;

								} else if (lastPressed.equals("R")) {
									lastPressed = "S";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "S";
									displayText = newDisplay;
								} else if (lastPressed.equals("S")) {
									lastPressed = "P";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "P";
									displayText = newDisplay;
								} else {
									lastPressed = "P";
									displayText += "P";
								}
							} else {
								displayText += "P";
							}
						
					//end 7 button
					// 8 - TUV button
					} else if (e.getActionCommand().contains("8")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("T")) {
									lastPressed = "U";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "U";
									displayText = newDisplay;
								} else if (lastPressed.equals("U")) {
									lastPressed = "V";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "V";
									displayText = newDisplay;

								} else if (lastPressed.equals("V")) {
									lastPressed = "T";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "T";
									displayText = newDisplay;

								} else {
									lastPressed = "T";
									displayText += "T";
								}
							} else {
								displayText += "T";
							}
						
					//end 8 button
					// 9 - WXYZ button
					} else if (e.getActionCommand().contains("9")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							if (lastPressTime == null) {
								lastPressTime = new Date();
							}
							long diff1 = dNow.getTime() - lastPressTime.getTime();
							long diffSeconds1 = diff1 / 1000 % 60;
							lastPressTime = new Date();
							if (diffSeconds1 <= 1) {
								if (lastPressed.equals("W")) {
									lastPressed = "X";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "X";
									displayText = newDisplay;
								} else if (lastPressed.equals("X")) {
									lastPressed = "Y";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "Y";
									displayText = newDisplay;

								} else if (lastPressed.equals("Y")) {
									lastPressed = "Z";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "Z";
									displayText = newDisplay;
								} else if (lastPressed.equals("Z")) {
									lastPressed = "W";
									String newDisplay = displayText.substring(0, displayText.length() - 1);
									newDisplay += "W";
									displayText = newDisplay;
								} else {
									lastPressed = "W";
									displayText += "W";
								}
							} else {
								displayText += "W";
							}
						
					//end 9 button
					// 0 - space button
					} else if (e.getActionCommand().contains("0")) {
							if (displayText.equals(TEXT_DISPLAY_TEXT)) {
								displayText = "";
							}
							displayText += " ";
				} else if (e.getActionCommand().equals("Send") || e.getActionCommand().equals("Cancel")) {
					display.setText("");
					lastPressed = "";
					displayText = "";
					topLeftButton.setEnabled(false);
					topRightButton.setText(TEXT_BUTTON_TEXT);
					topMiddleButton.setText(CALL_BUTTON_TEXT);
					topLeftButton.setText(DELETE_BUTTON_TEXT);
					isNumberMode = true;
					topRightButton.setEnabled(true);
					numberButtons[0].setEnabled(true);
					starButton.setEnabled(true);
					poundButton.setEnabled(true);
					isCanceled = true;
				}
				if (isCanceled) {
					isCanceled = false;
				} else if (!displayText.equals(ENTER_NUMBER_TEXT) && !displayText.equals(TEXT_DISPLAY_TEXT) && !displayText.equals("")) {
					topLeftButton.setEnabled(true);
					System.out.println("on delete button");
					topRightButton.setEnabled(true);
				} else {
					System.out.println("off delete button");
					topRightButton.setEnabled(false);
					topLeftButton.setEnabled(false);
				}
			} // end else

			// repaint the display
			display.setText(displayText);
			display.revalidate();
		}

	}

	public static void main(String[] args) {
		new DumbPhone();
	}
}
