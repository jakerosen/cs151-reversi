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

    // reset game board in Model
    model.newGame();

    // update View to reflect reset board
    view.newGame(model.getBoard());

    // start first turn
    model.getLegalMoves();
    view.displayLegalMoves(model);

    return ValveResponse.EXECUTED;
  }
}
