package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;

import java.awt.*;

public class Pawn implements BoardPiece {
    public String name = "Pawn";
    private Color color;

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
        if(Chess.movingVertically(me.getColumn(),me.getRow(),them.getColumn(),them.getRow())){
            //System.out.println("Moving Vertically");
            if(hasMoved(me.getColumn(),me.getRow()))
                return color == Color.BLACK ? them.getRow() - me.getRow() == 1 : them.getRow() - me.getRow() == -1;
            return color == Color.BLACK ? them.getRow() - me.getRow() == 2 || them.getRow() - me.getRow() == 1  : them.getRow() - me.getRow() == -2 || them.getRow() - me.getRow() == -1;
        }
        if(Chess.movingDiagonally(me.getColumn(),me.getRow(),them.getColumn(),them.getRow()))
        {
            //System.out.println("Moving Diagonally");
            if(color == Color.BLACK) {
                return them.getRow() - me.getRow() == 1;
            }else{
                return them.getRow() - me.getRow() == -1;
            }
        }
        return false;
    }

    @Override
    public boolean canTake(Chess game, Cell me, Cell them) {
        if(!me.isEmpty() && !them.isEmpty()) {
            if (me.getPiece().getColor() != them.getPiece().getColor()) {
                if(Chess.movingDiagonally(me,them)){
                    return this.canMove(game, me, them);
                }
            }
        }
        return false;
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
