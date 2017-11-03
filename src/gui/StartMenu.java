package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import tester.Application;
public class StartMenu {
    static{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        JFrame f = new JFrame("3D MAZE");
        f.setSize(1080,720);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        JButton start = new JButton("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Application game = new Application();
                try {
                    f.setVisible(false);
                    game.gameStart();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton instructions = new JButton("INSTRUCTIONS");
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                instruction();
            }
        });
        JButton exitGame = new JButton("EXIT");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        ImageIcon image = new ImageIcon("res/image/maze.jpg");
        JLabel label = new JLabel(image);
        con.gridx = 0;
        con.gridy = 0;
        p.add(label,con);
        con.insets = new Insets(10,10,10,10);
        JPanel p1 = new JPanel();
        p1.add(start);
        p1.add(instructions);
        p1.add(exitGame);
        con.gridy = 5; con.gridx = 0;
        con.gridwidth = 1; con.gridheight = 1;
        p.add(p1,con);
        f.add(p);
        f.setVisible(true);
    }
    public static void instruction(){
        JFrame instruction = new JFrame();
        JPanel instructionpic = new JPanel();
        ImageIcon inimage = new ImageIcon("res/image/instruction.JPG");
        JLabel inlabel = new JLabel(inimage);
        instructionpic.add(inlabel);
        instruction.setLayout(new BorderLayout());
        instruction.add(instructionpic, BorderLayout.CENTER);
        instruction.pack();
        instruction.setTitle("INSTRUCTIONS");
        instruction.setVisible(true);
    }
}
