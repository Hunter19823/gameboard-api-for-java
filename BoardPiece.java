package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public interface BoardPiece {
    Color color = Color.WHITE;
    String name = "Null";

    public void draw(Graphics g, int x, int y, int width, int height);
    public boolean canMove(Chess game, Cell me, Cell them);
    public boolean canTake(Chess game, Cell me, Cell them);
    public void setParent(Cell parent);
    public Cell getParent();

    String getName();
    Color getColor();
}
