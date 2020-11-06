package main.gameboard.chess;

import main.gameboard.BoardPiece;

import java.awt.*;

public class Pawn implements BoardPiece {
    public String name = "Pawn";
    private Color color;

    public Pawn(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        //g.drawOval(x,y,width,height);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,width<height ? width : height));
        g.drawString("♙",x,y+height);
    }

    @Override
    public boolean canMove(int x, int y, int targetX, int targetY) {
        if(Chess.movingVertically(x,y,targetX,targetY)){
            //System.out.println("Moving Vertically");
            if(hasMoved(x,y))
                return color == Color.BLACK ? targetY - y == 1 : targetY - y == -1;
            return color == Color.BLACK ? targetY - y == 2 || targetY - y == 1  : targetY - y == -2 || targetY - y == -1;
        }
        if(Chess.movingDiagonally(x,y,targetX,targetY))
        {
            //System.out.println("Moving Diagonally");
            if(color == Color.BLACK) {
                return targetY - y == 1;
            }else{
                return targetY - y == -1;
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
