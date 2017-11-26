package main;

import editor.EditorApplication;
import editor.MapData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Start {
    public static JFrame f;
    public static EditorApplication editorApp;
    static{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        f = new JFrame("3D MAZE");
        f.setSize(600,600);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        JButton start1 = new JButton("GENERATE RANDOM MAZE");
        start1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Application.start(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFileChooser fileChooser = new JFileChooser();
        JButton start2 = new JButton("LOAD MAZE");
        start2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int returnVal = fileChooser.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selected = fileChooser.getSelectedFile();
                    System.out.println(selected.getAbsolutePath());
                    try {
                        FileInputStream fileIn = new FileInputStream(selected);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        MapData mapData = (MapData) in.readObject();
                        Application.start(mapData);
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (ClassNotFoundException c) {
                        c.printStackTrace();
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        editorApp = new EditorApplication();
        JButton editor = new JButton("MAP EDITOR");
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editorApp.start();
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
        p1.add(start1);
        p1.add(start2);
        p1.add(editor);
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
