package model;

import java.util.ArrayList;

public interface InputStrategy {
  Position selectPosition(Color turnPlayer, ArrayList<Position> legalPositions);
}
