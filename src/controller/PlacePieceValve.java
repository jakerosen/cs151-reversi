package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Color;
import model.Model;
import model.Position;
import model.Tile;
import view.Message;
import view.PlacePieceMessage;
import view.View;

/**
 * Interprets a PlacePieceMessage to place a new piece on the board.
 */
public class PlacePieceValve implements Valve {
  private Model model;
  private View view;

  /**
   * Constructs the valve with the given model and view
   *
   * @param model The model
   * @param view The view
   */
  public PlacePieceValve(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Interprets a PlacePieceMessage, placing the piece on the board.
   *
   * @param message The command to execute
   *
   * @return EXECUTED if the command is executed, or MISS if the command was
   * not, or ENDGAME if the game is finished.
   */
  @Override
  public ValveResponse execute(Message message) {
    if (message.getClass() != PlacePieceMessage.class) {
      return ValveResponse.MISS;
    }

    List<Position> legalPositions = new LinkedList<Position>(model.getCurrentMoves().keySet());
    for (Position p : legalPositions) {
      view.disableTile(p);
    }

    // get position from message (requires casting to PlacePieceMessage, which should be safe)
    Position pos = ((PlacePieceMessage) message).getPos();

    // call appropriate Model functions to place this piece on the board and flip pieces accordingly
    model.playPiece(pos);
    model.switchPlayers();
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(() -> view.switchPlayers());

    // get the list of flipped pieces from Model
    LinkedList<Tile> piecesToFlip = model.getFlippedPieces();//model.getCurrentMoves().get(pos);

    // give View the list of flipped pieces and placed piece so that View can appropriately use the flip animation
    // to update the graphics and place the new piece.
    es.execute(() -> view.updateBoard(model.getBoard(), pos, piecesToFlip));
    es.shutdown();
    try {
      es.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("error: thread interrupted");
    }

    // AND
    // Model needs to calculate available move locations for next turn
    model.getLegalMoves();
    Map<Position, LinkedList<Tile>> nextTurnMoves = model.getCurrentMoves();
    // View updates whose turn it is, if needed
    // if no moves are available for next player, switch back turns
    if (nextTurnMoves.isEmpty()) {
      view.getPrintStream().printf("There is no legal move for %s to make.\n", Color.toString(model.getTurnPlayer()));
      model.switchPlayers();
      view.switchPlayers();
      model.getLegalMoves();
      nextTurnMoves = model.getCurrentMoves();
      if (nextTurnMoves.isEmpty()) {
        // neither player can move, the game is over;
        int blackScore = model.getScoreOf(Color.BLACK);
        int whiteScore = model.getScoreOf(Color.WHITE);
        String winner = model.determineWinner();
        view.showFinalScore(winner, blackScore, whiteScore);
        return ValveResponse.ENDGAME;
      }
    }

    // View uses available move locations to activate new Buttons to be clickable
    view.displayLegalMoves(model);
    return ValveResponse.EXECUTED;
  }
}
