package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

public class NewGameIcon implements Icon {
  private int width;
  private int height;
  
  public NewGameIcon(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getIconWidth() {
    return width;
  }

  public int getIconHeight() {
    return height;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) { 
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    Rectangle2D.Double tile = new Rectangle2D.Double(0, 0, width, height);
    g2.fill(tile);
  }
}
