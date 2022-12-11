import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.lang.Math;








public class GameBoard extends JPanel {



    private final int NUM_ROWS = 10;
    private final int NUM_COLS = 10;
    private final int NUM_BOMBS = 5;
    private final int Bsize = 60;
    
    
    private Cell[][] board;







    public GameBoard() {
        board = new Cell[NUM_ROWS][NUM_COLS];
        // creates JFrame
        JFrame f = new JFrame("MineSweeper");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(NUM_COLS*Bsize+Bsize,NUM_ROWS * Bsize+2*Bsize);

        JPanel p = new JPanel();

        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col <NUM_COLS; col++) {
                // create button
                JButton b = new JButton();
                // identifier for button in the form Row_Col
                String name = String.format("%d_%d", row,col);
                // set button properties
                b.setName(name);
                b.setBackground(Color.GRAY);
                Border raised = new EtchedBorder(EtchedBorder.RAISED,Color.black,Color.white);
                b.setBorder(raised);
                // add it to panel at specific location
                b.setBounds(col*Bsize, row*Bsize, Bsize, Bsize);

                // add to frame
                f.add(b);
                // adds cell object to board
                Cell cell = new Cell(b);
                // places cell in board
                board[row][col] = cell;


                final int rowFinal = row;
                final int colFinal = col;
                // action listener
                b.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                        // explores square
                        explore(rowFinal,colFinal);
                        




                        // checks if game is over

                        
                    }
                });
                
                
                
                
                

            }
        }
        f.add(p);
        f.setVisible(true); 

        // initializes board
        placeBombs();
        countAdjacent();
    }
    // helper method
    public void updateAdj(int row, int col) {
        if (row < 0 || row >= NUM_ROWS) {
            return;
        }
        if (col < 0 || col >= NUM_COLS) {
            return;
        }
        board[row][col].incrementAdjBombs();
    }



    public void explore(int row, int col) {
        // base case
        if (row < 0 || row >= NUM_ROWS) {
            return;
        }
        if (col < 0 || col >= NUM_COLS) {
            return;
        }
        Cell cell = board[row][col];
        if (cell.getIsRevealed()) {
            return;
        }
        cell.revealCell();
        if (cell.nAdjBombs() != 0) {
            return;
        }

        // recurse on neighbors
        explore(row, col-1);
        explore(row-1, col-1);
        explore(row-1, col);
        explore(row-1, col+1);
        explore(row, col+1);
        explore(row+1, col+1);
        explore(row+1, col);
        explore(row+1, col-1);



    }





    // calculates the adjacent bombs
    public void countAdjacent() {
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                Cell cell = board[row][col];
                // update adjacent tiles
                if (cell.getHasBomb()) {
                    updateAdj(row, col-1);
                    updateAdj(row-1, col-1);
                    updateAdj(row-1, col);
                    updateAdj(row-1, col+1);
                    updateAdj(row, col+1);
                    updateAdj(row+1, col+1);
                    updateAdj(row+1, col);
                    updateAdj(row+1, col-1);
                }
                

            }
        }
    }


    // place bombs
    public void placeBombs() {
        int count = 0;

        while (count != NUM_BOMBS) {
            // grabs random row and col
            int randRow = (int) (Math.random() * NUM_ROWS);
            int randCol = (int) (Math.random() * NUM_COLS);
            // checks if cell at row and col has a bomb if not sets it
            Cell cell = board[randRow][randCol];
            if (!cell.getHasBomb()) {
                // sets to bomb
                cell.setHasBomb(true);
                // inc counter
                count++;
            }
        }
    }


    // reveals all tiles
    public void revealAll() {
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                board[row][col].revealCell();
            }
        }
    }

}
