import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame
{
  private static final int WIDTH = 200;
  private static final int HEIGHT = 220;

  // keeps track of whether it's X's turn or O's turn
  private boolean xTurn = true;

  //***************************************

  public TicTacToe()
  {
    setTitle("Tic-Tac-Toe");
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    createContents();
    setVisible(true);
  } // end TicTacToe constructor
  //**************************************

  // Create components and add to window.

  private void createContents()
  {
    JButton button; // re-instantiate this button and use
                    // to fill entire board
    setLayout(new GridLayout(3, 3));

    for (int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        button = new JButton();
        button.addActionListener(new Listener());
        add(button);
      } // end for j
    } // end for i
  } // end createContents
  //***************************************

  // If user clicks a button, change its label to "X" or "O".

  private class Listener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JButton btn = (JButton) e.getSource();
      if (btn.getText().isEmpty())
      {
        btn.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;
      }
    } // end actionPerformed
  } // end class Listener

  //**************************************

  public static void main(String[] args)
  {
    new TicTacToe();
  }
} // end class TicTacToe
