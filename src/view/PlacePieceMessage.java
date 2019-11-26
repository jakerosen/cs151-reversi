package view;

import model.Position;

public class PlacePieceMessage implements Message {
  private Position pos;

  public PlacePieceMessage(int x, int y) {
    this(new Position(x, y));
  }

  public PlacePieceMessage(Position pos) {
    this.pos = pos;
  }
  
  public Position getPos() {
    return pos;
  }
}
