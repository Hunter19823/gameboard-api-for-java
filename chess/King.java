package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;

import java.awt.*;

public class King implements BoardPiece {
    public String name = "King";
    private Color color;
    private Cell parent;

    public King(Color color)
    {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        Cell.drawPiece(g,x,y,width,height,color == Color.BLACK ? "♚" : "♔");
    }
    @Override
    public boolean canMove(Chess game, Cell me, Cell them)
    {
        if((Chess.movingHorizontally(me.getColumn(),me.getRow(),them.getColumn(),them.getRow()) || Chess.movingVertically(me.getColumn(),me.getRow(),them.getColumn(),them.getRow()) || Chess.movingDiagonally(me.getColumn(),me.getRow(),them.getColumn(),them.getRow()))  && them.isEmpty()){
            return Chess.distance(me.getColumn(),me.getRow(),them.getColumn(),them.getRow()) < 2;
        }
        return false;
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        if(!me.isEmpty() && !them.isEmpty()) {
            if (me.getPiece().getColor() != them.getPiece().getColor()) {
                return canMove(game, me, them);
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
    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return name;
    }

}
