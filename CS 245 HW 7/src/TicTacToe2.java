import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe2 extends JFrame {
	  private static final int WIDTH = 200;
	  private static final int HEIGHT = 220;
	  private JButton[][] board = new JButton[3][3];
	  //***************************************
	  public TicTacToe2() {
	    setTitle("Tic-Tac-Toe");
	    setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    createContents();
	    setVisible(true);
	  } // end TicTacToe constructor
	  //**************************************

	  // Create components and add to window.

	  private void createContents() {
	    setLayout(new GridLayout(3, 3));
	    Listener listener = new Listener();
	    
	    for (int i=0; i<3; i++) {
	    	for(int j=0; j<3; j++) {
		    	board[i][j] = new JButton();
		    	board[i][j].addActionListener(listener);
		        add(board[i][j]);
	    	}
	      }
	  } // end createContents
	  //***************************************

	  // If user clicks a button, change its label to "X" or "O".

	  private class Listener implements ActionListener {
		  private boolean isXTurn = true;
		  
	    public void actionPerformed(ActionEvent e) {
	      JButton btn = (JButton) e.getSource();
	      if (btn.getText().isEmpty()) {
	        btn.setText(isXTurn ? "X" : "O");
	        if(checkWinner()) {
		      JOptionPane.showMessageDialog(null, (isXTurn ? "X" : "O") + " won!");
		    }
	        isXTurn = !isXTurn;
	      }
	    }
	    
	    public boolean checkWinner() {
		    for(int i=0; i<3; i++) {
		    	if(!board[i][0].getText().isEmpty() && board[i][0].getText().equals(board[i][1].getText()) && board[i][1].getText().equals(board[i][2].getText())) {
		    		return true;
		    	}
		    	if(!board[0][i].getText().isEmpty() && board[0][i].getText().equals(board[1][i].getText()) && board[1][i].getText().equals(board[2][i].getText())) {
		    		return true;
		    	}
		    }
		    if(!board[0][0].getText().isEmpty() && board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][2].getText())) {
		    	return true;
		    }
		    if(!board[0][2].getText().isEmpty() && board[0][2].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][0].getText())) {
		    	return true;
		    }
		    return false;
	    }
	  }

	  //**************************************

	  public static void main(String[] args)
	  {
	    new TicTacToe2();
	  }
}
