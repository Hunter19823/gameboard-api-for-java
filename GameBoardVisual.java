package main.gameboard;


import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;


public class GameBoardVisual extends Canvas {
    private GameBoard board;
    private JFrame frame;

    public GameBoardVisual(GameBoard board, String name, int width, int height)
    {
        frame = new JFrame(name);
        this.setBoard(board);
        this.setFrame(frame);
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Cell[][] cellMap = board.getBoard();
        int cellWidth = this.getWidth()/8;
        int cellHeight = this.getHeight()/8;
        for(int row = 0; row < cellMap.length; row++)
        {
            for(int col = 0; col < cellMap[row].length; col++)
            {
                cellMap[row][col].draw(g,cellWidth*col,row*cellHeight,cellWidth, cellHeight);
            }
        }
    }

    private void setBoard(GameBoard board)
    {
        this.board = board;
    }

    private void setFrame(JFrame frame)
    {
        this.frame = frame;
    }



}
