import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Image;


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



    public ImageIcon processImage(String filePath) {
        if (new File(filePath).exists()) {
            ImageIcon icon = new ImageIcon(filePath);
            Image image = icon.getImage();
            int h = this.button.getHeight();
            int w = this.button.getWidth();
            image = image.getScaledInstance(h, w, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.out.println("File not found");
        }
        return null;
        
    }


    // instance methods
    public void revealCell() {
        // checks if it has bomb
        // checks what number of adjacent bombs and sets colors
        // sets revealed field to true
        this.isRevealed = true;

        // checks if has bomb
        if (this.hasBomb) {
            // sets the icon to bomb
            // background to white
            // checks if the file exists
            String bombFilePath = "images\\bombIcon.png";
            this.button.setIcon(processImage(bombFilePath));
            this.button.setBackground(new Color(230,230,230));
            
            return;
        } else {
            //checks adj bombs count and sets colors
            switch (this.numberAdjBombs) {
                case 0:
                    this.button.setBackground(new Color(230,230,230));
                    break;
                case 1:
                    String oneIconPath = "images\\oneIcon.png";
                    this.button.setIcon(processImage(oneIconPath));
                    break;
                case 2:
                    String twoIconPath = "images\\twoIcon.png";
                    this.button.setIcon(processImage(twoIconPath));
                    break;
                case 3:
                    String threeIconPath = "images\\threeIcon.png";
                    this.button.setIcon(processImage(threeIconPath));
                    break;
                case 4:
                    String fourIconPath = "images\\fourIcon.png";
                    this.button.setIcon(processImage(fourIconPath));
                    break;
                case 5:
                    String fiveIconPath = "images\\fiveIcon.png";
                    this.button.setIcon(processImage(fiveIconPath));
                    break;
                case 6:
                    String sixIconPath = "images\\sixIcon.png";
                    this.button.setIcon(processImage(sixIconPath));
                    break;
                case 7:
                    String sevenIconPath = "images\\sevenIcon.png";
                    this.button.setIcon(processImage(sevenIconPath));
                    break;
                case 8:
                    String eightIconPath = "images\\eightIcon.png";
                    this.button.setIcon(processImage(eightIconPath));
                    break;
                default:
                    break;
                



            }
        }
        
    }

}
