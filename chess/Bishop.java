package main.gameboard.chess;

import main.gameboard.BoardPiece;

import java.awt.*;

public class Bishop implements BoardPiece {
    public String name = "Bishop";
    private Color color;

    public Bishop(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        //g.drawOval(x,y,width,height);

        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,width<height ? width : height));
        g.drawString("♗",x,y+height);
    }

    @Override
    public boolean canMove(int x, int y, int targetX, int targetY) {
        return Chess.movingDiagonally(x,y,targetX,targetY);
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