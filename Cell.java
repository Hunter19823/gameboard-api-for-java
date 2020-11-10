package main.gameboard;

import main.gameboard.chess.Chess;

import java.awt.*;

public class Cell {
    private State currentState;
    private BoardPiece piece;
    private BoardPiece backgroundPiece;
    private int x,y;
    private boolean highlight;
    private Color highlightColor;


    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        currentState = State.EMPTY;
        if (x % 2 == 0 && y % 2 == 0) {
            backgroundPiece = new BackgroundCell(Color.YELLOW);
        } else if( x % 2 != 0 && y % 2 != 0) {
            backgroundPiece = new BackgroundCell(Color.YELLOW);
        }else{
            backgroundPiece = new BackgroundCell(Color.ORANGE);
        }
        backgroundPiece.setParent(this);
    }

    public static enum State
    {
        EMPTY,
        FULL
    }

    public int getColumn()
    {
        return x;
    }

    public int getRow()
    {
        return y;
    }

    public BoardPiece getPiece()
    {
        return piece;
    }

    public void setPiece(BoardPiece piece)
    {
        this.piece = piece;
        this.piece.setParent(this);
        this.currentState = State.FULL;
    }

    public BoardPiece removePiece()
    {
        this.currentState = State.EMPTY;
        this.piece.setParent(null);
        BoardPiece p = this.piece;
        this.piece = null;
        return p;
    }

    public BoardPiece getBackgroundPiece()
    {
        return backgroundPiece;
    }

    public State getCurrentState()
    {
        return currentState;
    }

    public boolean isEmpty()
    {
        return currentState == State.EMPTY;
    }

    public void draw(Graphics g, int x, int y, int width, int height)
    {

        backgroundPiece.draw(g,x,y,width, height);
        if(highlight) {
            g.setColor(highlightColor);
            int padding = 4;
            g.fillRoundRect(x+padding, y+padding, width-padding*2, height-padding*2,width/2,height/2);
        }
        if(currentState != Cell.State.EMPTY)
            piece.draw(g,x,y,width,height);

    }

    public void setHighlight(boolean highlight, Color highlightColor)
    {
        this.highlight = highlight;
        this.highlightColor = highlightColor;
    }

    public void setHighlight(boolean highlight)
    {
        this.highlight = highlight;
        this.highlightColor = Color.CYAN;
    }

    public static void drawPiece(Graphics g, int x, int y, int width, int height, String character)
    {
        int size = Math.min(width, height);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN,size));
        g.drawString(character, (int) (x + (((double) width/height - 1) * size)/2),y+size);
    }

    public Cell getLeft(Chess game){
        return game.getCell(getColumn()-1,getRow());
    }
    public Cell getRight(Chess game){
        return game.getCell(getColumn()+1,getRow());
    }


}
