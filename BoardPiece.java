package main.gameboard;

import java.awt.*;

public interface BoardPiece {
    Color color = Color.WHITE;
    String name = "Null";

    public void draw(Graphics g, int x, int y, int width, int height);
    public boolean canMove(int x, int y, int targetX, int targetY);

    String getName();
    Color getColor();
}
