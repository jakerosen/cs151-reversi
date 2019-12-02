package controller;

import model.Model;
import view.Message;
import view.NewGameMessage;
import view.View;

/**
 * Interprets a NewGameMessage, starting a new game of Reversi.
 */
public class NewGameValve implements Valve {
  private Model model;
  private View view;

  /**
   * Constructs the valve with the given model and view
   *
   * @param model The model
   * @param view The view
   */
  public NewGameValve(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Interprets a NewGameMessage, starting a new game of Reversi.
   *
   * @param message The command to execute
   *
   * @return EXECUTED if the command is executed, or MISS if the command was not.
   */
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
