import javax.swing.*;
import java.awt.event.*;  


public class InitGame {
    
    private int numRows;
    private int numCols;
    private int numBombs;
    private JFrame f;

    public InitGame() {

        f = new JFrame();
        f.setTitle("Settings");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(370,250);

        // adds labels for the categories
        JLabel difficulty = new JLabel("Difficulty");
        difficulty.setBounds(80, 20, 100, 20);

        JLabel gameSize = new JLabel("Size");
        gameSize.setBounds(220, 20, 100, 20);

        f.add(difficulty);
        f.add(gameSize);



        // adds radio buttons for the difficulty
        JRadioButton easy = new JRadioButton();
        easy.setBounds(60, 50, 20, 20);
        JLabel easyL = new JLabel("Easy");
        easyL.setBounds(100, 50, 50, 20);

        JRadioButton normal = new JRadioButton();
        normal.setBounds(60, 80, 20, 20);
        JLabel normalL = new JLabel("Normal");
        normalL.setBounds(100, 80, 50, 20);

        JRadioButton hard = new JRadioButton();
        hard.setBounds(60, 110, 20, 20);
        JLabel hardL = new JLabel("Hard");
        hardL.setBounds(100, 110, 50, 20);

        // adds difficulty buttons to the frame
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(easy);
        bg1.add(normal);
        bg1.add(hard);
        f.add(easy);
        f.add(easyL);
        f.add(normal);
        f.add(normalL);
        f.add(hard);
        f.add(hardL);

        // adds radio buttons for the game size
        JRadioButton small = new JRadioButton();
        small.setBounds(200, 50, 20, 20);
        JLabel smallL = new JLabel("Small");
        smallL.setBounds(240, 50, 50, 20);

        JRadioButton medium = new JRadioButton();
        medium.setBounds(200, 80, 20, 20);
        JLabel mediumL = new JLabel("Medium");
        mediumL.setBounds(240, 80, 50, 20);

        JRadioButton large = new JRadioButton();
        large.setBounds(200, 110, 20, 20);
        JLabel largeL = new JLabel("Large");
        largeL.setBounds(240, 110, 50, 20);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(small);
        bg2.add(medium);
        bg2.add(large);
        
        f.add(small);
        f.add(smallL);
        f.add(medium);
        f.add(mediumL);
        f.add(large);
        f.add(largeL);

        // adds the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(120,150,100,50);


        submitButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {
                // check size
                if (small.isSelected()) {
                    // small
                    numRows = 10;
                    numCols = 10;
                } else if (medium.isSelected()) {
                    // medium
                    numRows = 20;
                    numCols = 20;
                } else {
                    // large
                    numRows = 30;
                    numCols = 30;
                }
                
                // check difficulty
                if (easy.isSelected()) {
                    // easy
                    numBombs = (int)(numRows * numCols * 0.10);
                } else if (normal.isSelected()) {
                    // normal
                    numBombs = (int)(numRows * numCols * 0.15);
                } else {
                    // hard
                    numBombs = (int)(numRows * numCols * 0.20);
                }

                // close the window
                f.dispose();

                // starts and minesweeper game
                GameBoard board = new GameBoard(numRows,numCols,numBombs);
            }
        });  


        f.add(submitButton);

        f.setLayout(null);
        f.setVisible(true);
        
        // default values for difficulty and game size
        normal.setSelected(true);
        medium.setSelected(true);

    }
}
