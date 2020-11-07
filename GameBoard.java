package main.gameboard;

import main.cardgames.Game;

import javax.swing.*;

public class GameBoard
{
    private int ROWS;
    private int COLUMNS;
    private int SIZE;
    private Cell[][] board;
    private GameBoardVisual canvas;

    public GameBoard()
    {
        ROWS = 8;
        COLUMNS = 8;
        SIZE = 50;
        board = new Cell[ROWS][COLUMNS];
        for(int row = 0; row < ROWS; row++)
        {
            for(int column = 0; column < COLUMNS; column++)
            {
                board[row][column] = new Cell(column,row);
            }
        }
        canvas = new GameBoardVisual(this,"Default",ROWS*SIZE,COLUMNS*SIZE);
    }

    public GameBoard(String name)
    {
        ROWS = 8;
        COLUMNS = 8;
        SIZE = 50;
        board = new Cell[ROWS][COLUMNS];
        for(int row = 0; row < ROWS; row++)
        {
            for(int column = 0; column < COLUMNS; column++)
            {
                board[row][column] = new Cell(column,row);
            }
        }
        canvas = new GameBoardVisual(this,name,ROWS*SIZE,COLUMNS*SIZE);
    }


    public GameBoardVisual getCanvas() {
        return canvas;
    }

    public Cell[][] getBoard()
    {
        return board;
    }

    public int getSize()
    {
        return SIZE;
    }

    public Cell getCell(int col, int row)
    {
        return board[row][col];
    }

    public void setPiece(int col, int row, BoardPiece piece)
    {
        board[row][col].setPiece(piece);
    }

    public BoardPiece removePiece(int col, int row)
    {
        return board[row][col].removePiece();
    }

    public void movePiece(int col, int row, int x, int y)
    {
        this.setPiece(x,y,this.removePiece(col,row));
    }

    public boolean isEmpty(int col, int row)
    {
        return board[row][col].isEmpty();
    }
}
