package main.gameboard.chess;

import main.gameboard.Cell;
import main.gameboard.GameBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static main.cardgames.GoFish.print;

public class Chess {
    private GameBoard game;
    private boolean debugMode = false;
    public Chess(){
        game = new GameBoard();
        populateBoard();


        game.getCanvas().addMouseListener(new MouseListener(){
            int lastX,lastY;
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.printf("%20s: (%d,%d)%n","Mouse Clicked",e.getX(),e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                //System.out.printf("%20s: (%d,%d)%n","Mouse Pressed",e.getX(),e.getY());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                //System.out.printf("%20s: (%d,%d)%n","Mouse Released",e.getX(),e.getY());
                //System.out.printf("%20s: (%d,%d)%n","Last Cords",lastX,lastY);

                if(attemptMove(lastX,lastY,e.getX(),e.getY()) && debugMode)
                    runTest();
                updateGraphics();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //System.out.printf("%20s: (%d,%d)%n","Mouse Entered",e.getX(),e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.printf("%20s: (%d,%d)%n","Mouse Exited",e.getX(),e.getY());
            }
        });
    }
    public static void main(String[] args) {
        Chess chessBoard1 = new Chess();

    }

    public void populateBoard()
    {
        game.setPiece(4,7,new King(Color.WHITE));
        game.setPiece(4,0,new King(Color.BLACK));
        game.setPiece(3,7,new Queen(Color.WHITE));
        game.setPiece(3,0,new Queen(Color.BLACK));
        for(int i = 0; i < 8; i++)
        {
            game.setPiece(i,1,new Pawn(Color.BLACK));
            game.setPiece(i,6,new Pawn(Color.WHITE));
        }
        for( int i = 0; i < 2; i++) {
            game.setPiece(7*i, 7, new Rook(Color.WHITE));
            game.setPiece(7*i, 0, new Rook(Color.BLACK));
            game.setPiece(2+i*3, 7, new Bishop(Color.WHITE));
            game.setPiece(2+i*3, 0, new Bishop(Color.BLACK));
            game.setPiece(1+i*5, 7, new Knight(Color.WHITE));
            game.setPiece(1+i*5, 0, new Knight(Color.BLACK));
        }
    }

    public boolean attemptMove(int x1, int y1, int x2, int y2)
    {
        Cell c1 = getCell(y1, x1);
        Cell c2 = getCell(y2, x2);
        if(c1 != null && c2 != null)
        {
            if(debugMode) print("Not Null");
            if(!c1.isEmpty() && c2.isEmpty())
            {
                if(debugMode) print("Not Empty");
                if(c1.getPiece().canMove(c1.getX(),c1.getY(),c2.getX(),c2.getY())){
                    c2.setPiece(c1.removePiece());
                    return true;
                }
                if(debugMode) print("Can't move");
            }
        }
        return false;
    }

    public static boolean isCell(int row, int col)
    {
        //int row = x/(game.getCanvas().getWidth()/8);
        //int col = y/(game.getCanvas().getHeight()/8);

        //System.out.printf("Cell (%d,%d)",row,col);
        return (row>=0 && row<8) && (col>=0 && col<8);
    }

    public Cell getCell(int x, int y)
    {
        //print(game.getCanvas().getWidth());
        //print(game.getCanvas().getHeight());
        int row = x/(game.getCanvas().getHeight()/8);
        int col = y/(game.getCanvas().getWidth()/8);
        if(debugMode) System.out.printf("isCell? (C:%d,R:%d)%n",col,row);
        if(isCell(row,col))
        {
            return game.getCell(col,row);
        }
        return null;
    }

    private void runTest()
    {
        for(Cell[] row : game.getBoard())
        {
            for(Cell cell : row)
            {
                if(!cell.isEmpty())
                {
                    System.out.println("Now testing: "+cell.getPiece().getColor().toString().substring(14)+" "+cell.getPiece().getName());
                    printAllAvailableSpaces(cell);
                }
            }
        }
    }

    private void printAllAvailableSpaces(Cell cell)
    {
        String topKey = "     ";
        for(int col = 0; col < 8; col++)
            topKey += String.format(" %1d ",col);
        System.out.println(topKey);
        for(int row = 0; row < 8; row++){
            System.out.printf("%-2d: |",row);
            for(int col = 0; col < 8; col++){
                if(game.getCell(col,row) != cell){
                    System.out.printf(" %s ",shorten(cell.getPiece().canMove(cell.getX(),cell.getY(),col,row)));
                }else{
                    System.out.printf(" %s ","S");
                }
            }
            System.out.printf("| %-2d%n",row);
        }
        System.out.println(topKey);
    }

    private void updateGraphics()
    {
        game.getCanvas().paint(game.getCanvas().getGraphics());
    }

    private static String shorten(boolean bool)
    {
        return bool ? "T" : " ";
    }

    public static int distance(int a, int b){
        return Math.abs(a-b);
    }

    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }
    public static boolean movingHorizontally(int x, int y, int targetX, int targetY)
    {
        return distance(x,targetX) != 0 && distance(y,targetY) == 0;
    }

    public static boolean movingVertically(int x, int y, int targetX, int targetY)
    {
        return distance(x,targetX) == 0 && distance(y,targetY) != 0;
    }

    public static boolean movingDiagonally(int x, int y, int targetX, int targetY)
    {
        return distance(targetX,x) == distance(targetY,y) && x != targetX;
    }


}