package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public interface BoardPiece {
    Color color = Color.WHITE;
    String name = "Null";

    void draw(Graphics g, int x, int y, int width, int height);
    boolean canMove(Chess game, Cell me, Cell them);
    boolean canTake(Chess game, Cell me, Cell them);
    void setParent(Cell parent);
    Cell getParent();

    String getName();
    Color getColor();
}
