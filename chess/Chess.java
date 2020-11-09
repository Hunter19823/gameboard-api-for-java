package main.gameboard.chess;

import main.gameboard.BoardPiece;
import main.gameboard.Cell;
import main.gameboard.GameBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static main.cardgames.GoFish.print;

public class Chess {
    private GameBoard game;
    private static final char[] key = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private boolean debugMode = false;
    private Color lastMoved = Color.BLACK;
    private boolean gameOver = false;

    public Chess()
    {
        game = new GameBoard("Pie's Homemade Chess Game");
        populateBoard();
        //testPiece(new Queen(Color.WHITE));
        addMouseListener();
        //runTest();
    }

    public static void main(String[] args)
    {
        Chess chessBoard1 = new Chess();
        chessBoard1.setDebugMode(false);
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void addMouseListener()
    {
        game.getCanvas().addMouseListener(new MouseListener(){
            int lastX,lastY;
            Cell lastCell;
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                lastCell = findCell(lastY,lastX);
                if(lastCell != null)
                {
                    lastCell.setHighlight(true);
                }
                updateGraphics();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(lastCell != null)
                {
                    lastCell.setHighlight(false);
                }
                if(!gameOver) {
                    if (attemptMove(lastX, lastY, e.getX(), e.getY())) {
                        if (debugMode) runTest();
                    }

                }
                updateGraphics();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void testPiece(BoardPiece piece)
    {
        game.setPiece(3,3,piece);
        game.setPiece(1,1,new Queen(Color.BLACK));
        game.setPiece(5,1,new Queen(Color.BLACK));
        game.setPiece(1,5,new Queen(Color.BLACK));
        game.setPiece(5,5,new Queen(Color.BLACK));
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
        Cell c1 = findCell(y1, x1);
        Cell c2 = findCell(y2, x2);
        return attemptMove(c1,c2);
    }

    public boolean attemptMove(Cell c1, Cell c2)
    {
        if(c1 != null && c2 != null)
        {
            if(!c1.isEmpty()) {
                if(c1.getPiece().getColor() == lastMoved)
                    return false;
                if (c2.isEmpty()) {
                    if (c1.getPiece().canMove(this, c1, c2)) {
                        System.out.printf("(%s%s) [%s %s] to (%s%s) [Empty]%n",key[c1.getColumn()],8-c1.getRow(),getColorName(c1.getPiece().getColor()),c1.getPiece().getName(), key[c2.getColumn()],8-c2.getRow());
                        lastMoved = lastMoved == Color.BLACK ? Color.WHITE : Color.BLACK;
                        c2.setPiece(c1.removePiece());

                        return true;
                    }
                } else if (c1.getPiece().canTake(this, c1, c2)) {
                    lastMoved = lastMoved == Color.BLACK ? Color.WHITE : Color.BLACK;
                    System.out.printf("(%s%s) [%s %s] to (%s%s) [%s %s]%n", key[c1.getColumn()], 8 - c2.getRow(), getColorName(c1.getPiece().getColor()), c1.getPiece().getName(), key[c2.getColumn()], 8 - c2.getRow(), getColorName(c2.getPiece().getColor()), c2.getPiece().getName());
                    c2.setPiece(c1.removePiece());
                    if (c2.getPiece().getName() == "King") {
                        gameOver = true;
                        print(getColorName(c1.getPiece().getColor()) + " has won!");
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCell(int row, int col)
    {
        return (row>=0 && row<8) && (col>=0 && col<8);
    }

    public Cell findCell(int y, int x)
    {
        int row = y/(game.getCanvas().getHeight()/8);
        int col = x/(game.getCanvas().getWidth()/8);
        if(isCell(row,col))
        {
            return game.getCell(col,row);
        }
        return null;
    }

    public Cell getCell(int col, int row)
    {
        if(isCell(row,col))
        {
            return game.getCell(col,row);
        }
        return null;
    }

    public static String getColorName(Color c)
    {
        if (Color.BLACK.equals(c)) {
            return "Black";
        } else if (Color.WHITE.equals(c)) {
            return "White";
        }
        return "Unknown";
    }

    private void runTest()
    {
        for(Cell[] row : game.getBoard())
        {
            for(Cell cell : row)
            {
                if(!cell.isEmpty())
                {
                    print("Now testing: "+getColorName(cell.getPiece().getColor())+" "+cell.getPiece().getName());
                    printAllSpaces(cell,true,true);
                }
            }
        }
    }

    private void printAllSpaces(Cell cell, boolean includeEmptySpaces, boolean includeEnemySpaces){
        String topKey = "     ";
        for(int col = 0; col < 8; col++)
            topKey += String.format(" %1d ",col);
        System.out.println(topKey);
        for(int row = 0; row < 8; row++){
            System.out.printf("%-2d: |",row);
            for(int col = 0; col < 8; col++){
                if(game.getCell(col,row) != cell){
                    System.out.printf(" %s ",shorten((includeEmptySpaces && cell.getPiece().canMove(this, cell, game.getCell(col,row))) || (includeEnemySpaces && cell.getPiece().canTake(this,cell, game.getCell(col,row)))));
                }else{
                    System.out.printf(" %s ","S");
                }
            }
            System.out.printf("| %-2d%n",row);
        }
        System.out.println(topKey);
    }

    private void printAllEmptySpaces(Cell cell)
    {
        printAllSpaces(cell,true,false);
    }
    private void printAllAvailableTakes(Cell cell)
    {
        printAllSpaces(cell,false,true);
    }

    private void updateGraphics()
    {
        game.getCanvas().paint(game.getCanvas().getGraphics());
    }

    private static String shorten(boolean bool)
    {
        return bool ? "T" : " ";
    }

    public static int distance(int a, int b) {
        return Math.abs(a - b);
    }
    public static double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }
    public static double distance(Cell c1, Cell c2)
    {
        return Math.sqrt(Math.pow(c1.getColumn()-c2.getColumn(),2) + Math.pow(c1.getRow()-c2.getRow(),2));
    }
    public static boolean movingHorizontally(int x, int y, int targetX, int targetY)
    {
        return distance(x,targetX) != 0 && distance(y,targetY) == 0;
    }
    public static boolean movingHorizontally(Cell c1, Cell c2)
    {
        return movingHorizontally(c1.getColumn(),c1.getRow(),c2.getColumn(),c2.getRow());
    }
    public static boolean movingHorizontallyUnobstructed(Chess game, Cell c1, Cell c2)
    {
        if (Chess.movingHorizontally(c1, c2)) {
            int colMult = c1.getColumn() - c2.getColumn() < 0 ? -1 : 1;
            for(int i = 1; i < distance(c1.getColumn(), c2.getColumn()); i++){
                if(!game.getCell(c1.getColumn()-i*colMult, c1.getRow()).isEmpty()){
                    return false;
                }
            }

            return true;
        }
        return false;
    }
    public static boolean movingVertically(int x, int y, int targetX, int targetY)
    {
        return distance(x,targetX) == 0 && distance(y,targetY) != 0;
    }
    public static boolean movingVertically(Cell c1, Cell c2)
    {
        return movingVertically(c1.getColumn(),c1.getRow(),c2.getColumn(),c2.getRow());
    }
    public static boolean movingVerticallyUnobstructed(Chess game, Cell c1, Cell c2)
    {
        if (Chess.movingVertically(c1, c2)) {
            int rowMult = c1.getRow() - c2.getRow() < 0 ? -1 : 1;
            for(int i = 1; i < distance(c1.getRow(), c2.getRow()); i++){
                if(!game.getCell(c1.getColumn(), c1.getRow() - i*rowMult).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static boolean movingDiagonally(int x, int y, int targetX, int targetY)
    {
        return distance(targetX,x) == distance(targetY,y) && x != targetX;
    }
    public static boolean movingDiagonally(Cell c1, Cell c2)
    {
        return movingDiagonally(c1.getColumn(),c1.getRow(),c2.getColumn(),c2.getRow());
    }
    public static boolean movingDiagonallyUnobstructed(Chess game, Cell c1, Cell c2)
    {
        if (Chess.movingDiagonally(c1, c2)) {
            int colMult = c1.getColumn() - c2.getColumn() < 0 ? -1 : 1;
            int rowMult = c1.getRow() - c2.getRow() < 0 ? -1 : 1;
            int dist = distance(c1.getColumn(),c2.getColumn());
            for(int i=1; i < dist;i++){
                if(!game.getCell(c1.getColumn()-colMult*i,c1.getRow()-rowMult*i).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static boolean isEnemy(Cell c1, Cell c2)
    {
        if(!c1.isEmpty() && !c2.isEmpty())
            return (!c1.getPiece().getColor().equals(c2.getPiece().getColor()));
        return false;
    }
}
