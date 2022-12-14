import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.Math;
import java.io.File;


public class GameBoard extends JPanel {

    private final int numRows;
    private final int numCols;
    private final int numBombs;
    private final int bSize;
    private boolean isGameRunning = false;
    private final ImageIcon[] images;
    private final String[] files = {
        "oneIcon.png",
        "twoIcon.png",
        "threeIcon.png",
        "fourIcon.png",
        "fiveIcon.png",
        "sixIcon.png",
        "sevenIcon.png",
        "eightIcon.png",
        "bombIcon.png",
        "flagIcon.png"
    };
    
    private Cell[][] board;
    

    public GameBoard(int rows, int cols, int nBombs) {

        numRows = rows;
        numCols = cols;
        numBombs = nBombs;
        bSize = (int) 600/rows;

        // loads images
        images = new ImageIcon[files.length];
        for (int i = 0; i < files.length; i++) {
            images[i] = processImage("images\\" + files[i]);
        }
        
        board = new Cell[numRows][numCols];
        // creates JFrame
        JFrame f = new JFrame("MineSweeper");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(numCols*bSize+bSize,numRows * bSize+2*bSize);

        JPanel p = new JPanel();

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col <numCols; col++) {
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
                b.setBounds(col*bSize, row*bSize, bSize, bSize);

                // add to frame
                f.add(b);
                // adds cell object to board
                Cell cell = new Cell(b,images);
                // places cell in board
                board[row][col] = cell;

                final int rowFinal = row;
                final int colFinal = col;

                // click event listener
                b.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        // checks if game is not running
                        if (!isGameRunning) {
                            isGameRunning = true;
                            // initializes board
                            placeBombs(rowFinal, colFinal);
                            countAdjacent();
                        }

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            // left click
                            // explores square and checks if game lost
                            if (cell.getHasBomb()) {
                                endGame("You lost");
                            }
                            explore(rowFinal,colFinal);

                            // checks if game is won
                            if (checkGameWon()) {
                                endGame("You won");
                            }

                        }
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            // right click
                            cell.setIsFlag();
                        }
                    }
                    public void mousePressed(MouseEvent e) {}
                    public void mouseReleased(MouseEvent e) {}
                    public void mouseEntered(MouseEvent e) {}
                    public void mouseExited(MouseEvent e) {}
                });
                
            }
        }
        f.add(p);
        f.setLayout(null);
        f.setVisible(true);
    }
    // helper method
    public void updateAdj(int row, int col) {
        if (row < 0 || row >= numRows) {
            return;
        }
        if (col < 0 || col >= numCols) {
            return;
        }
        board[row][col].incrementAdjBombs();
    }

    // does exploring of the cells
    public void explore(int row, int col) {
        // base case
        if (row < 0 || row >= numRows) {
            return;
        }
        if (col < 0 || col >= numCols) {
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
    // processes the file into an image
    // returns the ImageIcon object from the file
    public ImageIcon processImage(String filePath) {
        if (new File(filePath).exists()) {
            ImageIcon icon = new ImageIcon(filePath);
            Image image = icon.getImage();
            int h = bSize;
            int w = bSize;
            image = image.getScaledInstance(h, w, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.out.println("File not found");
        }
        return null;
        
    }

    // calculates the adjacent bombs
    public void countAdjacent() {
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
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
    // shows the message that ends the game as well as reveals all the cells
    public void endGame(String message) {
        revealAll();
        JOptionPane.showMessageDialog(null, message, "Status:", JOptionPane.INFORMATION_MESSAGE);
    } 

    public boolean checkGameWon() {
        // checks if the number of nonRevealed squares is equal to the number of bombs
        int nonRevealedCounter = 0;
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                if (!board[row][col].getIsRevealed()) {
                    nonRevealedCounter++;
                }
            }
        }
        return nonRevealedCounter == numBombs;
    }

    // place bombs
    public void placeBombs(int row, int col) {
        // row and col are where NOT to place a bomb
        int count = 0;

        while (count != numBombs) {
            // grabs random row and col
            int randRow = (int) (Math.random() * numRows);
            int randCol = (int) (Math.random() * numCols);

            if (randRow == row && randCol == col) {
                continue;
            }

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
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                board[row][col].revealCell();
            }
        }
    }

}
