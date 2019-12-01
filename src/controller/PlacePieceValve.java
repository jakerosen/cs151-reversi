package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Model;
import model.Position;
import model.Tile;
import view.Message;
import view.PlacePieceMessage;
import view.View;

public class PlacePieceValve implements Valve {
  private Model model;
  private View view;

  public PlacePieceValve(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public ValveResponse execute(Message message) {
    if (message.getClass() != PlacePieceMessage.class) {
      return ValveResponse.MISS;
    }

    List<Position> legalPositions = new LinkedList<Position>(model.getCurrentMoves().keySet());
    for (Position p : legalPositions) {
      view.disableTile(p);
    }
    
    // TODO 
    // get position from message (requires casting to PlacePieceMessage, which should be safe)
    Position pos = ((PlacePieceMessage) message).getPos();
    // call appropriate Model functions to place this piece on the board and flip pieces accordingly
    model.playPiece(pos);
    model.switchPlayers();
    view.switchPlayers();
    // get the list of flipped pieces from Model
    LinkedList<Tile> piecesToFlip = model.getFlippedPieces();//model.getCurrentMoves().get(pos);
    //System.out.println(piecesToFlip);
    // give View the list of flipped pieces and placed piece so that View can appropriately use the flip animation
    view.updateBoard(model.getBoard(), pos, piecesToFlip);
    // to update the graphics and place the new piece.
    
    // AND
    // Model needs to calculate available move locations for next turn
    model.getLegalMoves();
    Map<Position, LinkedList<Tile>> nextTurnMoves = model.getCurrentMoves();
    // View updates whose turn it is, if needed
    // if no moves are available for next player, switch back turns
    if (nextTurnMoves.isEmpty()) {
      model.switchPlayers();
      view.switchPlayers();
      model.getLegalMoves();
      nextTurnMoves = model.getCurrentMoves();
      if (nextTurnMoves.isEmpty()) {
        // neither player can move, the game is over;
        // TODO calculate score here
        return ValveResponse.ENDGAME;
      }
    }

    // View uses available move locations to activate new Buttons to be clickable
    view.displayLegalMoves(model);
    
    System.out.println("Playing piece! pos: " + pos);
    System.out.println(model.getBoard());
    return ValveResponse.EXECUTED;
  }
}
