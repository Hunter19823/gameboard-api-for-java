package main.gameboard;

import java.awt.*;

public class Cell {
    private State currentState;
    private BoardPiece piece;
    private BoardPiece backgroundPiece;
    private int x,y;


    public Cell(int x, int y) {
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
    }

    public static enum State {
        EMPTY,
        FULL
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public BoardPiece getPiece()
    {
        return piece;
    }

    public void setPiece(BoardPiece piece)
    {
        this.piece = piece;
        this.currentState = State.FULL;
    }

    public BoardPiece removePiece()
    {
        this.currentState = State.EMPTY;
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
        if(currentState == Cell.State.EMPTY) {
            backgroundPiece.draw(g,x,y,width, height);
        }else{
            backgroundPiece.draw(g,x,y,width, height);
            piece.draw(g,x,y,width,height);
        }
    }

}
