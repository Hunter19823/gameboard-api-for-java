package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public interface BoardPiece {
    Color color = Color.WHITE;
    String name = "Null";

    public void draw(Graphics g, int x, int y, int width, int height);
    public boolean canMove(int x, int y, int targetX, int targetY);
    public boolean canTake(Chess game, Cell me, Cell them);

    String getName();
    Color getColor();
}
