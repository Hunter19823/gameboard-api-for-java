package main.gameboard.chess;

import main.gameboard.BoardPiece;

import java.awt.*;

public class Knight implements BoardPiece {
    public String name = "Knight";
    private Color color;

    public Knight(Color color)
    {
        this.color = color;
    }


    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        //g.drawOval(x,y,width,height);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,width<height ? width : height));
        g.drawString(color == Color.BLACK ? "♞" : "♘",x,y+height);
    }

    @Override
    public boolean canMove(int x, int y, int targetX, int targetY) {
        int xdist = Chess.distance(x,targetX);
        int ydist = Chess.distance(y,targetY);
        return (xdist == 1 && ydist == 2) || (xdist == 2 && ydist == 1);
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