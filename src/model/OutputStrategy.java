package model;

import java.io.PrintStream;

public abstract class OutputStrategy {
  private PrintStream out;
  
  public OutputStrategy(PrintStream out) {
    this.out = out;
  }

  public abstract void displayBoard(Board board);
  
  public PrintStream getStream() {
    return out;
  }
}
