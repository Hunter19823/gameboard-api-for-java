package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public class BackgroundCell implements BoardPiece {
    private Color color = Color.WHITE;
    public String name = "Background";

    public BackgroundCell(Color color)
    {
        this.color = color;
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {
        Color temp = g.getColor();
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(temp);
    }

    public boolean canMove(int x, int y, int targetX, int targetY){
        return false;
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        return false;
    }


    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
