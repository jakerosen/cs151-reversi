package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;

// will extend OutputStrategy eventually, and probably implement InputStrategy
public class GUInterface {
  private JFrame frame;
  private JPanel board;
  
  public GUInterface() {
    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setSize(new Dimension(880, 880));
    board = new JPanel(new GridLayout(8, 8));
    board.setPreferredSize(new Dimension(880, 880));
    frame.add(board, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Displays the board.
   *
   * @param board The board.
   */
  public void displayBoard(Board board) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        this.board.add(new TileLabel(board.getTile(i, j), 100));
      }
    }
  }
}
