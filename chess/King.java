package main.gameboard.chess;

import main.gameboard.BoardPiece;

import java.awt.*;

public class King implements BoardPiece {
    public String name = "King";
    private Color color;

    public King(Color color)
    {
        this.color = color;
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {
        g.setColor(color);
        //g.drawOval(x,y,width,height);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,width<height ? width : height));
        g.drawString("♔",x,y+height);
    }

    public boolean canMove(int x, int y, int targetX, int targetY)
    {
        if((Chess.movingHorizontally(x,y,targetX,targetY) || Chess.movingVertically(x,y,targetX,targetY) || Chess.movingDiagonally(x,y,targetX,targetY))){
            return Chess.distance(x,y,targetX,targetY) < 2;
        }
        return false;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return name;
    }

}
