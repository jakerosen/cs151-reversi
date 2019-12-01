package model;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import view.ConsoleInterface;
import view.InputStrategy;
import view.OutputStrategy;

/**
 * Runs the logic for Reversi,
 * Takes input,
 * Keeps track of turns and turn order,
 * Reports final score
 */
public class Model {
  private Board board;
  private Color turnPlayer;
  private HashMap<Position, LinkedList<Tile>> currentMoves;
  private LinkedList<Tile> flippedPieces;
  private boolean lastPlayerPassed;

  /**
   * Initializes the Game, runs the main loop
   * @param args Unused
   */
  public static void main(String[] args) {
    consoleInterface();
  }
  
  /**
   * Initializes the game model.
   */
  public Model() {
    board = Board.makeBoard();
    turnPlayer = Color.BLACK;
    currentMoves = null;
    lastPlayerPassed = false;
  }
  
  public void getLegalMoves() {
    currentMoves = board.getLegalMoves(turnPlayer);
    lastPlayerPassed = currentMoves.isEmpty();
  }
  
  public void playPiece(Position selectedPosition) {
    flippedPieces = currentMoves.get(selectedPosition);
    board.playPiece(selectedPosition, turnPlayer, currentMoves);
  }
  
  public void switchPlayers() {
    turnPlayer = Color.flipColor(turnPlayer);
  }

  /**
   * Plays a game of Reversi
   *
   * @param input The strategy to get user input for making a move
   * @param output The strategy of displaying output
   */
  public static void playGame(InputStrategy input, OutputStrategy output) {
    Model game = new Model();
    PrintStream outStream = output.getStream();

    //turnPlayer = Color.BLACK;
    //Board board = Board.getBoard();
    //boolean lastPlayerPassed = false;

    while (true) {
      output.displayBoard(game.board);
      game.getLegalMoves();

      if (game.currentMoves.size() > 0) {
        game.lastPlayerPassed = false;
        output.displayLegalMoves(game);
        Position selectedPosition = input.selectPosition(game);
        game.playPiece(selectedPosition);
        game.switchPlayers();
        output.updateBoard(game.getBoard(), selectedPosition, game.getCurrentMoves().get(selectedPosition));

      } else {
        if (game.lastPlayerPassed) {
          // both players cannot make a legal move, game over
          outStream.println("Neither player has any legal moves.  Game over.");

          // score
          int blackScore = game.board.getScoreOf(Color.BLACK);
          int whiteScore = game.board.getScoreOf(Color.WHITE);
          String winner = null;
          if (blackScore == whiteScore) {
            // tied score
            winner = "Tie game";
          } else {
            winner = (blackScore > whiteScore) ? "Black" : "White";
          }

          outStream.printf("Black score: %d\nWhite score: %d\nWinner: %s\n", blackScore, whiteScore, winner);

          return;
        }

        game.lastPlayerPassed = true;
        outStream.printf("%s has no legal moves.\n", Color.toString(game.turnPlayer));
        game.turnPlayer = Color.flipColor(game.turnPlayer);
      }
    }
  }

  /**
   * Plays a game of Reversi through the console.
   */
  public static void consoleInterface() {
    ConsoleInterface console = new ConsoleInterface(System.out);
    playGame(console, console);
  }

  public Board getBoard() {
    return board;
  }

  public Color getTurnPlayer() {
    return turnPlayer;
  }

  public HashMap<Position, LinkedList<Tile>> getCurrentMoves() {
    return currentMoves;
  }
  
  public LinkedList<Tile> getFlippedPieces() {
    return flippedPieces;
  }

  public boolean didLastPlayerPass() {
    return lastPlayerPassed;
  }
  
  public void newGame() {
    board.reset();
    turnPlayer = Color.BLACK;
    currentMoves = null;
    lastPlayerPassed = false;
  }
  
  public int getScoreOf(Color player) {
    return board.getScoreOf(player);
  }
  
  public String determineWinner() {
    int blackScore = getScoreOf(Color.BLACK);
    int whiteScore = getScoreOf(Color.WHITE);
    if (blackScore == whiteScore) {
      // tied score
      return "Tie game!";
    } else {
      return (blackScore > whiteScore) ? "Black wins!" : "White wins!";
    }
  }
}
