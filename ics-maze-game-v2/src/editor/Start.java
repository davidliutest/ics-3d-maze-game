package editor;

import game.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Start {
    // Different style
    public static JFrame f;
    static{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // Creates Frame
        f = new JFrame("3D MAZE");
        f.setSize(1000, 600);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GridBagLayout());
        // Grid Bag Layout
        GridBagConstraints con = new GridBagConstraints();
        // Creates Buttons
        JButton start1 = new JButton("GENERATE RANDOM MAZE");
        // Ddd action listeners
        start1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    f.dispose();
                    Launcher.run();
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
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selected = fileChooser.getSelectedFile();
                    System.out.println(selected.getAbsolutePath());
                    try {
                        FileInputStream fileIn = new FileInputStream(selected);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        MapData1 mapData1 = (MapData1) in.readObject();
                        f.dispose();
                        Launcher.run(mapData1);
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
        JButton editor = new JButton("MAP EDITOR");
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new EditorApplication().start();
            }
        });

        con.gridwidth = 1;
        con.gridheight = 1;
        con.insets = new Insets(10,10,10,10);
        con.weightx = 1;
        con.weighty = 1;
        con.gridx = 0;
        con.gridy = 0;

        // Gridbaglayout to format
        JLabel label = new JLabel("ICS Maze Game");
        label.setFont(new Font("Arial", 0, 48));

        p.add(label,con);

        con.gridy++;

        JPanel btnPanel = new JPanel();
        btnPanel.add(start1);
        btnPanel.add(start2);
        btnPanel.add(editor);
        p.add(btnPanel, con);

        f.add(p);
        f.setVisible(true);
    }

}