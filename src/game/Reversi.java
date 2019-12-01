package game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import controller.Controller;
import model.Model;
import view.Message;
import view.View;

public class Reversi {

  public static void main(String[] args) {
    BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<Message>();
    Model model = new Model();
    View view = new View(messageQueue);
    Controller controller = new Controller(model, view, messageQueue);
    controller.playGame();
    
    // game is over
  }
}
