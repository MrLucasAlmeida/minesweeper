import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;


public class Cell {
    
    
    private JButton button;
    private int xPos;
    private int yPos;
    private boolean isRevealed;
    private boolean hasBomb;
    private int numberAdjBombs;
    




    public Cell(JButton b) {
        // button
        button = b;
        // initialize event listener
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                // does things when tile is clicked
                // reveals square
                
            }
        });
        


        // gets the x and y position
        String[] numbers = b.getName().split("_");
        xPos = Integer.parseInt(numbers[0]);
        yPos = Integer.parseInt(numbers[1]);
        // rest of fields
        isRevealed = false;
        hasBomb = false;
        numberAdjBombs = 0;
    }

    // getters
    public JButton getButton() {
        return this.button;
    }
    public boolean getIsRevealed() {
        return this.isRevealed;
    }
    public int getXPosition() {
        return this.xPos;
    }
    public int getYPosition() {
        return this.yPos;
    }
    public boolean getHasBomb() {
        return this.hasBomb;
    }
    public void setHasBomb(boolean b) {
        this.hasBomb = b;
    }
    public int nAdjBombs() {
        return this.numberAdjBombs;
    }
    public void incrementAdjBombs() {
        this.numberAdjBombs++;
    }






    // instance methods
    public void revealCell() {
        // checks if it has bomb
        // checks what number of adjacent bombs and sets colors
        // sets revealed field to true

        // checks if has bomb
        if (this.hasBomb) {
            // sets the icon to bomb
            // background to white
            // checks if the file exists
            if (new File(".resources\bombIcon.png").exists()) {
                this.button.setIcon(new ImageIcon(".resources\bombIcon.png"));
            } else {
                System.out.println("File not found");
            }
            
            return;
        } else {
            //checks adj bombs count and sets colors
            switch (this.numberAdjBombs) {
                case 0:
                    this.button.setBackground(Color.WHITE);
                    break;
                case 1:
                    this.button.setBackground(new Color(0,240,90));
                    this.button.setText("1");
                    break;
                case 2:
                    this.button.setBackground(new Color(255,248,10));
                    this.button.setText("2");
                    break;
                case 3:
                    this.button.setBackground(new Color(245,196,0));
                    this.button.setText("3");
                    break;
                case 4:
                    this.button.setBackground(new Color(60,0,255));
                    this.button.setText("4");
                    break;
                default:
                    this.button.setBackground(Color.GRAY);
                



            }
        }
        
    }

}
