package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;

import java.awt.*;

public class Rook implements BoardPiece {
    public String name = "Rook";
    private Color color;
    private Cell parent;

    public Rook(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        g.setColor(Color.BLACK);
        //g.drawOval(x,y,width,height);
        Cell.drawPiece(g,x,y,width,height,color == Color.BLACK ? "♜" : "♖");
    }

    @Override
    public boolean canMove(Chess game, Cell me, Cell them)
    {
        return (Chess.movingVerticallyUnobstructed(game, me, them) || Chess.movingHorizontallyUnobstructed(game, me, them)) && them.isEmpty();
    }


    public boolean canTake(Chess game, Cell me, Cell them)
    {
        if(!me.isEmpty() && !them.isEmpty()) {
            if (me.getPiece().getColor() != them.getPiece().getColor()) {
                return (Chess.movingHorizontallyUnobstructed(game, me, them) || Chess.movingVerticallyUnobstructed(game, me, them));
            }
        }
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
        return this.parent;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Color getColor()
    {
        return this.color;
    }
}
