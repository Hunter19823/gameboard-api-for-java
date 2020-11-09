package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;

import java.awt.*;

public class Knight implements BoardPiece {
    public String name = "Knight";
    private Color color;
    private Cell parent;

    public Knight(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        Cell.drawPiece(g,x,y,width,height,color == Color.BLACK ? "♞" : "♘");
    }

    @Override
    public boolean canMove(Chess game, Cell me, Cell them) {
        int xdist = Chess.distance(me.getColumn(),them.getColumn());
        int ydist = Chess.distance(me.getRow(),them.getRow());
        return ((xdist == 1 && ydist == 2) || (xdist == 2 && ydist == 1))  && them.isEmpty();
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        if(!me.isEmpty() && !them.isEmpty()) {
            if (me.getPiece().getColor() != them.getPiece().getColor()) {
                return canMove(game,me,them);
            }
        }
        return false;
    }

    @Override
    public void setParent(Cell parent) {
        this.parent = parent;
    }

    @Override
    public Cell getParent() {
        return this.parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}