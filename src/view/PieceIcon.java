package view;

import java.awt.*;
import javax.swing.*;

/**
   An icon that contains a Reversi piece.
*/
public class PieceIcon implements Icon
{
   public PieceIcon(ReversiPiece piece,
      int width, int height)
   {
      this.piece = piece;
      this.width = width;
   }
   
   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return width;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      piece.draw(g2);
   }

   private int width;
   private ReversiPiece piece;
}


