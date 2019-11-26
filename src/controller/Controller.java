package controller;

import java.util.concurrent.BlockingQueue;

import model.Model;
import view.Message;
import view.View;

public class Controller {
  private Model model;
  private View view;
  private BlockingQueue messageQueue;
  
  public Controller(Model model, View view, BlockingQueue<Message> messageQueue) {
    this.model = model;
    this.view = view;
    this.messageQueue = messageQueue;
  }
  
  public void playGame() {
    view.displayBoard(model.getBoard());
    
    // find legal moves of turn player
    // tell View to display legal moves
    // wait for input (i.e. wait for message to arrive in queue)
    
    // when processing PlacePieceMessage, inform model what piece to place and update it appropriately.
    // inform view of changes (i.e. it needs to know placed piece, flipped pieces).
  }
}
