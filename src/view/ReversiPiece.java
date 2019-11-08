package view;

import model.Color;
import java.awt.*;
import java.awt.geom.*;

public class ReversiPiece {
  private int x;
  private int y;
  private int width;
  private Color state;
  private int theta;
  private Area bounds;
  private Area leftHalf;
  
  public ReversiPiece(int x, int y, int width) {
    this(x, y, width, Color.BLACK);
  }
  
  public ReversiPiece(int x, int y, int width, Color state) {
	  this.x = x;
	  this.y = y;
	  this.width = width;
	  this.state = state;
	  theta = 180;
	  bounds = new Area(new Rectangle2D.Double(x, y, width, width));
    leftHalf = new Area(new Rectangle2D.Double(x, y, width / 2., width));
  }
  
  public void setTheta(int theta) {
    this.theta = theta;
  }
  
  public void draw(Graphics2D g2) {
    double xWidthOuter = (Math.abs(.6 * Math.cos(Math.toRadians(theta))) + .4) * width;
    double xWidthInner = Math.abs(Math.cos(Math.toRadians(theta))) * width;
    Ellipse2D.Double outer = new Ellipse2D.Double(x + (width - xWidthOuter)/2, y, xWidthOuter, width);
    Ellipse2D.Double inner = new Ellipse2D.Double(x + (width - xWidthInner)/2, y, xWidthInner, width);
    g2.setStroke(new BasicStroke(2));
    g2.setColor(java.awt.Color.BLACK);
    g2.draw(outer);
    Area black = setBlackArea(inner);
    g2.setClip(black);
    g2.fill(outer);
    black.exclusiveOr(bounds);
    g2.setColor(java.awt.Color.WHITE);
    g2.setClip(black);
    g2.fill(outer);
  }
  
  public void flip() {
    if (state == Color.BLACK) state = Color.WHITE;
    else state = Color.BLACK;
  }
  
  private Area setBlackArea(Ellipse2D.Double inner) {
    Area black = new Area(inner);
    if (theta <= 90) {
      black.exclusiveOr(bounds);
      black.intersect(leftHalf);
    }
    else {
      black.add(leftHalf);
    }
    if (state == Color.WHITE) black.exclusiveOr(bounds);
    return black;
  }
}
