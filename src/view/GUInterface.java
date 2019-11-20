package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Board;
import model.Tile;

// will extend OutputStrategy eventually, and probably implement InputStrategy
public class GUInterface {
  private JFrame frame;
  private JPanel board;
  private PrintStream out;
  private TileShell turnPiece;
  private model.Color turnPlayer;
  private JLabel turnPlayerLabel;
  
  public GUInterface() {
    // init window
    frame = new JFrame();
    frame.setLayout(new BorderLayout());

    // init Board (Center)
    board = new JPanel(new GridLayout(8, 8));
    board.setPreferredSize(new Dimension(800, 800));
    
    // init East 
    JPanel east = new JPanel();
    east.setPreferredSize(new Dimension(300, 800));
    east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
    
    // New Game Button
    JPanel newGamePanel = new JPanel(new GridLayout(1, 1));
    newGamePanel.setPreferredSize(new Dimension(300, 100));
    JButton newGame = new JButton("New Game"); 
    newGame.setBackground(Color.RED);
    newGamePanel.add(newGame);
    east.add(newGamePanel);
    
    // Turn Player Piece
    turnPlayer = model.Color.BLACK;
    JPanel turnPiecePanel = new JPanel(new GridLayout(1, 1));
    Tile tile = new Tile(0,0);
    tile.setState(turnPlayer);
    turnPiece = new TileShell(tile, 300, Color.WHITE);
    turnPiecePanel.add(turnPiece);
    east.add(turnPiecePanel);
    
    // Turn Player Text
    JPanel turnPlayerPanel = new JPanel();
    turnPlayerLabel = new JLabel(turnPlayer + "'s turn");
    turnPlayerPanel.setBackground(Color.WHITE);
    turnPlayerPanel.add(turnPlayerLabel);
    east.add(turnPlayerPanel);
    
    // Message box
    JTextArea text = new JTextArea(40, 30);
    text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    text.setEditable(false);
    out = new PrintStream(new MessageStream(text));
    east.add(text);

    // Add Board and East components
    frame.add(board, BorderLayout.CENTER);
    frame.add(east, BorderLayout.EAST);

    // init Frame
    frame.setResizable(false);
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
        TileShell tile = new TileShell(board.getTile(i, j), 100);
        tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.board.add(tile);
      }
    }
    frame.setVisible(true);
  }
}
