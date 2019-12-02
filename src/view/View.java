package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Board;
import model.Model;
import model.Position;
import model.Tile;

/**
 * The View for a Reversi game.
 */
public class View {//implements InputStrategy, OutputStrategy {
  private BlockingQueue<Message> messageQueue;
  private JFrame frame;
  private JPanel board;
  private TileShell[][] tiles;
  private PrintStream out;
  private TileShell turnPiece;
  private model.Color turnPlayer;
  private JLabel turnPlayerLabel;

  /**
   * Constructs the view with this given queue to put messages in.
   *
   * @param messageQueue The queue to place messages in.
   */
  public View(BlockingQueue<Message> messageQueue) {
    this.messageQueue = messageQueue;
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
    newGame.addActionListener(event -> {
      try {
        messageQueue.put(new NewGameMessage());
      } catch (InterruptedException e) {
        System.err.println("Thread interrupted when trying to add to message queue");
      }
    });
    newGamePanel.add(newGame);
    east.add(newGamePanel);

    // Turn Player Piece
    turnPlayer = model.Color.BLACK;
    JPanel turnPiecePanel = new JPanel(new GridLayout(1, 1));
    Tile tile = new Tile(0,0);
    tile.setState(turnPlayer);
    turnPiece = new TileShell(tile, 300, Color.WHITE, messageQueue);
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
   * Initializes the board display
   *
   * @param board The board.
   */
  public void initBoard(Board board) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        TileShell tile = new TileShell(board.getTile(i, j), 100, messageQueue);
        tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.board.add(tile);
        tiles[i][j] = tile;
      }
    }
    frame.setVisible(true);
  }

  /**
   * Resets the board display
   *
   * @param board The board.
   */
  public void newGame(Board board) {
    turnPiece.fastFlip();
    turnPlayerLabel.setText("Black's turn");
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        tiles[i][j].placePiece(board.getTile(i, j));
        tiles[i][j].disableTile();
      }
    }
    frame.setVisible(true);
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

  /**
   * Display the legal moves for the turn player.
   *
   * @param game The game state
   */
  public void displayLegalMoves(Model game) {
    HashMap<Position, LinkedList<Tile>> moves = game.getCurrentMoves();
    ArrayList<Position> legalPositions = new ArrayList<Position>(moves.keySet());
    for (Position pos : legalPositions) {
      int x = pos.getX();
      int y = pos.getY();
      tiles[x][y].enableTile();
    }
  }

  /**
   * Displays the final score.
   *
   * @param winner The winner
   * @param blackScore Black's score
   * @param whiteScore White's score
   */
  public void showFinalScore(String winner, int blackScore, int whiteScore) {
    String winnerMessage = String.format("Black score: %d\nWhite score: %d\n%s", blackScore, whiteScore, winner);
    JOptionPane.showMessageDialog(frame, winnerMessage);
  }

  /**
   * Switch the turn player
   */
  public void switchPlayers() {
    turnPiece.flip();
    turnPlayer = model.Color.flipColor(turnPlayer);
    turnPlayerLabel.setText(turnPlayer + "'s turn");
  }

  /**
   * @return The stream to print messages to.
   */
  public PrintStream getPrintStream() {
    return out;
  }

  /**
   * Disable the tile at this position.
   */
  public void disableTile(Position pos) {
    int x = pos.getX();
    int y = pos.getY();
    tiles[x][y].disableTile();
  }
}
