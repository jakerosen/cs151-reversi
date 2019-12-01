package controller;

import model.Model;
import view.Message;
import view.NewGameMessage;
import view.View;

public class NewGameValve implements Valve {
  private Model model;
  private View view;

  public NewGameValve(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public ValveResponse execute(Message message) {
    if (message.getClass() != NewGameMessage.class) {
      return ValveResponse.MISS;
    }

    // TODO
    // reset game board in Model
    // update View to reflect reset board

    System.out.println("New game!");
    return ValveResponse.EXECUTED;
  }
}
