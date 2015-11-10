package com.battleship.groundandship;

/**
 * Created by nikita on 10.11.2015.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikita on 29.10.2015.
 */
public class Ground extends JFrame {
    private     JSplitPane  splitPaneV;
    private     JSplitPane  splitPaneH;
    private     JPanel      panel1;
    private     JPanel      panel2;
    private     JPanel      panel3;

    private JPanel frame2;
    private JButton [][]squere;
    private JButton [][]squere2;


    public Ground(){
        setTitle( "Split Pane Application" );
        setBackground( Color.gray );
        setMaximumSize(new Dimension(600, 600));

        squere = new JButton[10][10];
        squere2 = new JButton[10][10];
        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );

        // Create the panels
        //createPanel1();
        createPanel2();
        createPanel3();

        // Create a splitter pane
        splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        topPanel.add( splitPaneV, BorderLayout.NORTH );

        splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        //splitPaneH.setLeftComponent( panel1 );
        splitPaneH.setRightComponent( panel2 );

        splitPaneV.setLeftComponent( panel2 );
        splitPaneV.setRightComponent( panel3 );


        pack(); //Эта команда подбирает оптимальный размер в зависимости от содержимого окна
        setVisible(true); //С этого момента приложение запущено!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initListeners();
    }

    public void createPanel2(){
        panel2 = new JPanel();
        panel2.setLayout( new FlowLayout() );

        panel2.setPreferredSize(new Dimension(640, 320));
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                squere[x][y]=new JButton(/*"("+x+","+y+")"*/); //создает новую кнопку
                squere[x][y].setPreferredSize(new Dimension(55, 25));
                panel2.add(squere[x][y]);
            }
        }
    }
    public void createPanel3(){
        panel3 = new JPanel();
        panel3.setLayout( new FlowLayout() );
        panel3.setPreferredSize(new Dimension(640, 320));
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                squere2[x][y]=new JButton(); //создает новую кнопку
                squere2[x][y].setPreferredSize(new Dimension(55,25));
                panel3.add(squere2[x][y]);
            }
        }

    }

    private void initListeners() {
        squere[0][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                squere[0][0].setText("X"); //.setBackground(Color.green);
                repaint();
            }
        });
    }


}
