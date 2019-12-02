package controller;

import view.Message;

/**
 * Interprets a given Message.
 */
public interface Valve {
  /**
   * Interprets the given Message.
   *
   * @param message The command to execute
   *
   * @return EXECUTED if the command is executed, or MISS if the command was not.
   */
  ValveResponse execute(Message message);
}
