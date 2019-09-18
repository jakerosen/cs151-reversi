# Reversi

About Reversi (with Othello variant rules)
Two players (white and black)
8x8 checkered board (64 spaces total)
Each space can be white, black, or empty.

Initial starting position of pieces (in the middle of the board):
  W B
  B W

Take turns (black goes first):
  Place one piece in a legal location.
    Legal locations are any space where there is a direct straight of connected
    pieces to another piece of that player's color over one or more pieces of
    the enemy color.
    Straight is defined as horizontal, vertical, or diagonal.

  When a piece is placed, flip all pieces in between the placed piece and the
  piece that had a connected straight to that player's color.

  If a player cannot make a legal move, they pass.  If both players cannot make
  a legal move, the game ends.

When the game ends, determine the winner:
  Count the pieces of each player's color.  The player with the highest number
  of pieces wins.  If there is a tie, then the game is tied.

Interaction between player and computer:
  - When a player takes their turn, possible move locations will be highlighted
  for that player.  They click on which location they would like to put a
  piece.  If a player cannot take a turn, they will automatically pass with a
  notice that they could not make a move.
  - There will be a button to start a new game (scrapping the current one) with
  confirmation.

Use Cases:
  Play a game involving Player 1 (black) and Player 2 (white).
    1. Place 4 pieces in the center of the board oriented as such:
      W B
      B W
    2. Take turns, starting with black:
      2.1 The turn player places a piece on an empty square that traces back
      to a direct straight path (horizontal, vertical, or diagonal) to a piece
      of their own color, over one or more adjacent enemy color pieces.  The
      possible move locations for the turn player will be highlighted, and they
      click where they would like to go.  Afterwards, flip all enemy color
      pieces on that path to the turn player's color, then end their turn.
      2.2 If the turn player cannot make a legal move, they pass.
      2.3 If both players cannot make a legal move, the game ends.
    3. The game ends.  Count the score of each player by counting the number of
    pieces of their color on the board.  The higher score wins, or if it is
    tied, the game is tied. If the game ends before all squares are filled, the
    player with the higher score has the number of remaining empty squares
    added to their score. In case of a tie, each player has half of the remaining
    empty squares added to their scores.

  Start a new Game.
    1. Either player can choose to start a new game.  The current game will
    be scrapped and the initial position will be set up for a new game.
