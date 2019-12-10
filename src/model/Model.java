package model;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import view.ConsoleInterface;
import view.InputStrategy;
import view.UIStrategy;

/**
 * The Model of a Reversi game.
 */
public class Model {
  private Board board;
  private Color turnPlayer;
  private HashMap<Position, LinkedList<Tile>> currentMoves;
  private LinkedList<Tile> flippedPieces;
  private boolean lastPlayerPassed;

  /**
   * Initializes the game model.
   */
  public Model() {
    board = Board.makeBoard();
    turnPlayer = Color.BLACK;
    currentMoves = null;
    lastPlayerPassed = false;
  }
  
  /**
   * Generates the legal moves that the current player can take.
   *
   * @return The moves.
   */
  public HashMap<Position, LinkedList<Tile>> getLegalMoves() {
    currentMoves = board.getLegalMoves(turnPlayer);
    lastPlayerPassed = currentMoves.isEmpty();
    return currentMoves;
  }

  /**
   * Plays a piece on the given position.
   * Precondition: the position is a legal move.
   *
   * @param selectedPosition The position
   */
  public void playPiece(Position selectedPosition) {
    flippedPieces = currentMoves.get(selectedPosition);
    board.playPiece(selectedPosition, turnPlayer, currentMoves);
  }

  /**
   * Switch turn player
   */
  public void switchPlayers() {
    turnPlayer = Color.flipColor(turnPlayer);
  }

  /**
   * Plays a game of Reversi
   *
   * @param input The strategy to get user input for making a move
   * @param output The strategy of displaying output
   */
  public static void playGame(InputStrategy input, UIStrategy output) {
    Model game = new Model();
    PrintStream outStream = output.getPrintStream();

    //turnPlayer = Color.BLACK;
    //Board board = Board.getBoard();
    //boolean lastPlayerPassed = false;

    while (true) {
      output.initBoard(game.board);
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
          
          // score for non-full boards
          if (blackScore + whiteScore < 64) {
            if (winner.charAt(0) == 'T') {
              blackScore = 32; whiteScore = 32;
            } else  if (winner.charAt(0) == 'B') {
               blackScore = 64 - whiteScore;
            } else {
              whiteScore = 64 - blackScore;
            }
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

  /**
   * @return The board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * @return The turn player's color
   */
  public Color getTurnPlayer() {
    return turnPlayer;
  }

  /**
   * Precondition: call getLegalMoves before this method
   *
   * @return The current legal moves
   */
  public HashMap<Position, LinkedList<Tile>> getCurrentMoves() {
    return currentMoves;
  }

  /**
   * Precondition: call playPiece before this method
   *
   * @return The pieces that were flipped after a piece was played
   */
  public LinkedList<Tile> getFlippedPieces() {
    return flippedPieces;
  }

  /**
   * @return Whether or not the last played passed.
   */
  public boolean didLastPlayerPass() {
    return lastPlayerPassed;
  }

  /**
   * Restarts the game of Reversi.
   */
  public void newGame() {
    board.reset();
    turnPlayer = Color.BLACK;
    currentMoves = null;
    lastPlayerPassed = false;
  }

  /**
   * Gets the score of this player.
   *
   * @param player The player
   * @return Their score.
   */
  public int getScoreOf(Color player) {
    return board.getScoreOf(player);
  }

  /**
   * Determines the winner of the game.
   *
   * @return The winner
   */
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
