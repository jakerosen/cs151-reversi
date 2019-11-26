package controller;

import view.Message;

public class PlacePieceValve implements Valve {

  @Override
  public ValveResponse execute(Message message) {
    // TODO 
    // get position from message (requires casting to PlacePieceMessage, which should be safe)
    // call appropriate Model functions to place this piece on the board and flip pieces accordingly
    // get the list of flipped pieces from Model
    // give View the list of flipped pieces and placed piece so that View can appropriately use the flip animation
    // to update the graphics and place the new piece.
    
    // AND
    // Model needs to calculate available move locations for next turn
    // View updates whose turn it is, if needed
    // View uses available move locations to activate new Buttons to be clickable
    
    // AND
    // If neither player can take a turn, send EndGame Response
    
    return ValveResponse.MISS;
  }
}
