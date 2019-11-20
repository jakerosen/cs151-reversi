package view;

import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;

public abstract class AbstractPiece {
  public abstract void draw(Graphics2D g2);
  public abstract void flip(JComponent comp);
}
