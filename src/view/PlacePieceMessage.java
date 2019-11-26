package view;

import model.Position;

public class PlacePieceMessage implements Message {
  Position pos;

  public PlacePieceMessage(int x, int y) {
    this(new Position(x, y));
  }

  public PlacePieceMessage(Position pos) {
    this.pos = pos;
  }
}
