package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Board;
import model.Game;
import model.Position;
import model.Tile;

public class GUInterface {//implements InputStrategy, OutputStrategy {
  private JFrame frame;
  private JPanel board;
  private TileShell[][] tiles;
  private PrintStream out;
  private TileShell turnPiece;
  private model.Color turnPlayer;
  private JLabel turnPlayerLabel;
  private boolean wakeUp; // This is to wait for the button to be pressed... it's the best I've got right now.
  
  public GUInterface() {
    wakeUp = false;
    tiles = new TileShell[8][8];
    for (int i = 0; i < 8; i++) {
      tiles[i] = new TileShell[8];
    }

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
        tiles[i][j] = tile;
      }
    }
    frame.setVisible(true);
  }

  /**
   * This method is not implemented.
   */
  public Position selectPosition(Game game) {
    return null;
  }
  
  /**
   * Updates the board according to the piece played. This method does not change the state of the board, it merely
   * displays changes that were made.
   * 
   * @param board The board
   * @param placedPiece The position of the piece that was just played
   * @param flippedPieces The pieces that were flipped during this turn.
   */
  public void updateBoard(Board board, Position selectedPosition, LinkedList<Tile> flippedPieces) {
    int x = selectedPosition.getX();
    int y = selectedPosition.getY();
    tiles[x][y].placePiece(board.getTile(selectedPosition));
    ExecutorService es = Executors.newCachedThreadPool();
    for (Tile tile : flippedPieces) {
      es.execute(() -> tiles[tile.getX()][tile.getY()].flip());
    }
    es.shutdown();
    try {
      es.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("error: thread interrupted");
    }
  }
  
  public void displayLegalMoves(Game game) {
    HashMap<Position, LinkedList<Tile>> moves = game.getCurrentMoves();
    ArrayList<Position> legalPositions = new ArrayList<Position>(moves.keySet());
    //LinkedList<Tile> flippedPieces = game.getFlippedPieces();
    Board board = game.getBoard();
    
    if (legalPositions.size() < 0) {
      game.switchPlayers();
      switchPlayers();
      wakeUp = true;
      return;
    }

    for (Position pos : legalPositions) {
      int x = pos.getX();
      int y = pos.getY();
      tiles[x][y].addActionListener(e -> {
        for (Position p : legalPositions) {
          tiles[p.getX()][p.getY()].disableTile();
        }
        game.playPiece(pos);
        game.switchPlayers();
        switchPlayers();
        updateBoard(board, pos, moves.get(pos));
        wakeUp = true;
        System.out.println(wakeUp);
      });
        
      tiles[x][y].enableTile();
    }
  }
  
  public void switchPlayers() {
    turnPiece.flip();
    turnPlayer = model.Color.flipColor(turnPlayer);
    turnPlayerLabel.setText(turnPlayer + "'s turn");
  }
  
  public boolean isTimeToWakeUp() {
    return wakeUp;
  }
  
  public void setWakeUp(boolean w) {
    wakeUp = w;
  }

  public PrintStream getStream() {
    return out;
  }
}
