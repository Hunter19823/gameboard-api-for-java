package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public class BackgroundCell implements BoardPiece {
    private Color color = Color.WHITE;
    public String name = "Background";
    private Cell parent;

    public BackgroundCell(Color color)
    {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        Color temp = g.getColor();
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(temp);
    }

    @Override
    public boolean canMove(Chess game, Cell me, Cell them) {
        return false;
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        return false;
    }

    @Override
    public void setParent(Cell parent)
    {
        this.parent = parent;
    }

    @Override
    public Cell getParent()
    {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
