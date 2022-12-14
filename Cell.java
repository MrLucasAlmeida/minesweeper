import javax.swing.*;
import java.awt.Color;


public class Cell {
    
    private JButton button;
    private int xPos;
    private int yPos;
    private boolean isRevealed;
    private boolean hasBomb;
    private boolean isFlagged;
    private int numberAdjBombs;
    private ImageIcon[] images;
    

    public Cell(JButton b, ImageIcon[] images) {
        // button
        this.button = b;

        // gets the x and y position
        String[] numbers = b.getName().split("_");
        this.xPos = Integer.parseInt(numbers[0]);
        this.yPos = Integer.parseInt(numbers[1]);

        // rest of fields
        this.isRevealed = false;
        this.hasBomb = false;
        this.isFlagged = false;
        this.numberAdjBombs = 0;

        // initializes the images
        this.images = images;
    }


    // getters and setters
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
    public boolean getIsFlagged() {
        return this.isFlagged;
    }
    public void setIsFlag() {
        // this function flags if unflagged and unflags if flagged

        // doesn't flag if already revealed
        if (this.isRevealed) {
            return;
        }

        this.isFlagged = !this.isFlagged;
        // set icon
        if (this.isFlagged) {
            // 9 is the image of the flag
            button.setIcon(this.images[9]);
        } else {
            button.setIcon(null);
        }
        
    }
    public int nAdjBombs() {
        return this.numberAdjBombs;
    }
    public void incrementAdjBombs() {
        this.numberAdjBombs++;
    }
    

    // instance methods

    // reveals the cell by placing an image on it
    public void revealCell() {
        // checks if it has bomb
        // checks what number of adjacent bombs and sets colors
        // sets revealed field to true
        this.isRevealed = true;
        this.isFlagged = false;

        // checks if has bomb
        if (this.hasBomb) {
            // sets the icon to bomb
            // background to white
            // checks if the file exists
            // 8 is the image of the bomb
            this.button.setIcon(this.images[8]);
            this.button.setBackground(new Color(230,230,230));
            
            return;
        } else {
            //checks adj bombs count and sets colors
            switch (this.numberAdjBombs) {
                case 0:
                    this.button.setBackground(new Color(230,230,230));
                    break;
                case 1:
                    this.button.setIcon(this.images[0]);
                    break;
                case 2:
                    this.button.setIcon(this.images[1]);
                    break;
                case 3:
                    this.button.setIcon(this.images[2]);
                    break;
                case 4:
                    this.button.setIcon(this.images[3]);
                    break;
                case 5:
                    this.button.setIcon(this.images[4]);
                    break;
                case 6:
                    this.button.setIcon(this.images[5]);
                    break;
                case 7:
                    this.button.setIcon(this.images[6]);
                    break;
                case 8:
                    this.button.setIcon(this.images[7]);
                    break;
                default:
                    break;
            }
        }
    }
}
