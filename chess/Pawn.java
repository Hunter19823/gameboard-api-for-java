package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;

import java.awt.*;

public class Pawn implements BoardPiece {
    public String name = "Pawn";
    private Color color;
    private Cell parent;

    public Pawn(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        g.setColor(Color.BLACK);
        //g.drawOval(x,y,width,height);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,width<height ? width : height));
        g.drawString(color == Color.BLACK ? "♟" : "♙",x,y+height);
    }

    @Override
    public boolean canMove(Chess game, Cell me, Cell them)
    {
        if(Chess.movingVerticallyUnobstructed(game,me,them) && them.isEmpty()){
            //System.out.println("Moving Vertically");
            if(hasMoved(me.getColumn(),me.getRow()))
                return color == Color.BLACK ? them.getRow() - me.getRow() == 1 : them.getRow() - me.getRow() == -1;
            return color == Color.BLACK ? them.getRow() - me.getRow() == 2 || them.getRow() - me.getRow() == 1  : them.getRow() - me.getRow() == -2 || them.getRow() - me.getRow() == -1;
        }
        return (canTake(game, me, them));
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        if(!me.isEmpty() && !them.isEmpty()) {
            if (me.getPiece().getColor() != them.getPiece().getColor()) {
                return Chess.movingDiagonally(me,them) && Chess.distance(me,them) < 2;
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

    private boolean hasMoved(int col, int row)
    {
        return !(this.color == Color.BLACK ? row==1 : row == 6);
    }
}
