package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import model.Tile;

/**
   An icon that contains a Reversi piece.
*/
public class TileIcon implements Icon {
   private int width;
   private ReversiPiece piece;

   public TileIcon(ReversiPiece piece, int width, int height) {
      this.piece = piece;
      this.width = width;
   }
   
   public int getIconWidth() {
      return width;
   }

   public int getIconHeight() {
      return width;
   }

   public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g;

      g2.setColor(new Color(34, 139, 34));
      Rectangle2D.Double tile = new Rectangle2D.Double(0, 0, width, width);
      g2.fill(tile);

      piece.draw(g2);
   }
}


