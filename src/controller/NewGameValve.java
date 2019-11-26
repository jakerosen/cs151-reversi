package controller;

import view.Message;

public class NewGameValve implements Valve {

  @Override
  public ValveResponse execute(Message message) {
    // TODO
    // reset game board in Model
    // update View to reflect reset board

    return ValveResponse.MISS;
  }
}
