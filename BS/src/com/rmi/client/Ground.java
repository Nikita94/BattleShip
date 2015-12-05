package com.rmi.client;


import com.rmi.PositionDeck;
import com.rmi.server.Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;

/**
 * Created by nikita on 10.11.2015.
 */

public class Ground extends JFrame {

    private ClientBS client;
    private Server server;
    private JSplitPane splitPaneV;
    private JSplitPane splitPaneH;
    private JPanel panel2;
    private JPanel panel3;
    private JButton[][] squere;
    private JButton[][] squere2;

    private ArrayList <PositionDeck> singleDeck = new ArrayList<>();
    private ArrayList <PositionDeck> doubleDeck = new ArrayList<>();
    private ArrayList <PositionDeck> threeDeck = new ArrayList<>();
    private ArrayList <PositionDeck> fourDeck = new ArrayList<>();




    public Ground() {
        setTitle("Split Pane Application");
        setBackground(Color.gray);
        setMaximumSize(new Dimension(600, 600));

        squere = new JButton[10][10];
        squere2 = new JButton[10][10];
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        setSingleDeck();
        setDoubleDeck();
        setThreeDeck();
        setFourDeck();

        // Create the panels
        //createPanel1();
        createPanel2();
        createPanel3();

        // Create a splitter pane
        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        topPanel.add(splitPaneV, BorderLayout.NORTH);

        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //splitPaneH.setLeftComponent( panel1 );
        splitPaneH.setRightComponent(panel2);

        splitPaneV.setLeftComponent(panel2);
        splitPaneV.setRightComponent(panel3);


        doConnect();

        pack(); //Эта команда подбирает оптимальный размер в зависимости от содержимого окна
        setVisible(true); //С этого момента приложение запущено!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                initListeners(x, y);
            }
        }
    }

    public void createPanel2() {
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel2.setPreferredSize(new Dimension(640, 320));
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                squere[x][y] = new JButton("");
                for (PositionDeck deck : singleDeck)
                    if (x == deck.getX() && y == deck.getY())
                        squere[x][y] = new JButton("X");
                for (PositionDeck deck : doubleDeck)
                    if (x == deck.getX() && y == deck.getY())
                        squere[x][y] = new JButton("X");
                for (PositionDeck deck : threeDeck)
                    if (x == deck.getX() && y == deck.getY())
                        squere[x][y] = new JButton("X");
                for (PositionDeck deck : fourDeck)
                    if (x == deck.getX() && y == deck.getY())
                        squere[x][y] = new JButton("X");

                squere[x][y].setEnabled(false);

                squere[x][y].setPreferredSize(new Dimension(55, 25));
                panel2.add(squere[x][y]);
            }
        }
    }

    public void createPanel3() {
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setPreferredSize(new Dimension(640, 320));
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                squere2[x][y] = new JButton(); //создает новую кнопку

                squere2[x][y].setPreferredSize(new Dimension(55, 25));
                panel3.add(squere2[x][y]);
            }
        }

    }

    private void initListeners(int x, int y) {
        squere2[x][y].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendCell(x * 10 + y);
            }
        });
    }

    public void doConnect() {
        try {
            client = new ClientBS("lol");
            client.setGUI(this);
            System.out.println("Good");
            server = (Server) Naming.lookup("rmi://localhost/RMIBS");
            server.login(client);
            server.sendSingleDeck(singleDeck);
            server.sendDoubleDeck(doubleDeck);
            server.sendThreeDeck(threeDeck);
            server.sendFourDeck(fourDeck);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCell(int a) {
        try {
            server.checkPosition(a, client.getUUID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateSquare(int position, boolean hit) {
        int x = position / 10;
        int y = position - x * 10;
        if (hit == true) {
            squere[x][y].setBackground(Color.RED);
            //squere2[x][y].setBackground(Color.RED);
            squere[x][y].setText("X");
        }
        else {
            squere[x][y].setText("*");
           // squere2[x][y].setText("*");

        }
        squere[x][y].repaint();
        squere[x][y].repaint();
    }



    public void setSingleDeck(){
        singleDeck.add(new PositionDeck(0,0));
        singleDeck.add(new PositionDeck(9,8));
        singleDeck.add(new PositionDeck(3,4));
        singleDeck.add(new PositionDeck(4,8));
    }

    public void setDoubleDeck(){
        doubleDeck.add(new PositionDeck(0, 8));
        doubleDeck.add(new PositionDeck(1, 8));
        doubleDeck.add(new PositionDeck(7, 7));
        doubleDeck.add(new PositionDeck(7, 8));
        doubleDeck.add(new PositionDeck(9, 1));
        doubleDeck.add(new PositionDeck(9, 2));
    }


    public void setThreeDeck(){
        doubleDeck.add(new PositionDeck(2, 2));
        doubleDeck.add(new PositionDeck(3, 2));
        doubleDeck.add(new PositionDeck(4, 2));
        doubleDeck.add(new PositionDeck(6, 0));
        doubleDeck.add(new PositionDeck(6, 1));
        doubleDeck.add(new PositionDeck(6, 2));
    }

    public void setFourDeck(){
        fourDeck.add(new PositionDeck(7,4));
        fourDeck.add(new PositionDeck(7,5));
        fourDeck.add(new PositionDeck(6,5));
        fourDeck.add(new PositionDeck(5,5));
    }

    public void updateSquare2(int position, boolean hit) {
        int x = position / 10;
        int y = position - x * 10;
        if (hit == true) {
            //squere[x][y].setBackground(Color.RED);
            squere2[x][y].setBackground(Color.RED);
            squere2[x][y].setText("X");
        }
        else {
            //squere[x][y].setText("*");
            squere2[x][y].setText("*");

        }
    }
}
