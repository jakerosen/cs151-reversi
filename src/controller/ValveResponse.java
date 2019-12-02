package controller;

/**
 * Responses that a valve may return
 *
 * MISS - Command not executed
 * EXECUTED - Command executed
 * ENDGAME - The game is over
 */
public enum ValveResponse {
  MISS, EXECUTED, ENDGAME;
}
