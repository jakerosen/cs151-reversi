package view;

import java.awt.*;
import model.Color;
import javax.swing.*;

public class FlipAnimTester {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    final ReversiPiece piece1 = new ReversiPiece(25, 25, 200);
    final ReversiPiece piece2 = new ReversiPiece(25, 25, 200, Color.WHITE);
    PieceIcon icon1 = new PieceIcon(piece1, 250, 250);
    PieceIcon icon2 = new PieceIcon(piece2, 250, 250);
    final JLabel label1 = new JLabel(icon1);
    final JLabel label2 = new JLabel(icon2);
    frame.setLayout(new FlowLayout());
    frame.add(label1);
    frame.add(label2);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    while (true) {
      piece1.flip();
      piece2.flip();
      for (int theta = 3; theta <= 180; theta += 3) {
        piece1.setTheta(theta);
        piece2.setTheta(theta);
        label1.repaint();
        label2.repaint();
        try {Thread.sleep(16);} catch (Exception e) {} // Terrible way to do this, but it gets the job done.
      }
      try {Thread.sleep(984);} catch (Exception e) {}
    }
  }
}
