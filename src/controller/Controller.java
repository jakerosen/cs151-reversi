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
    
    // needed for first turn
    // find legal moves of turn player (game start -- Black)
    // tell View to display legal moves

    // forever loop (until game finished)
    // wait for input (i.e. wait for message to arrive in queue)
  }
}
