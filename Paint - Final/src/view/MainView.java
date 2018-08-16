package view;

import control.BoardListener;
import control.ButtonListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Hashtable;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class MainView {

    public JFrame frame;
    public JButton circle;
    public JButton rect;
    public JButton square;
    public JButton traingle;
    public JButton line;
    public JButton ellipse;
    public JButton select;
    public JButton delete;
    public JButton undo;
    public JButton redo;
    public JButton save;
    public JButton load;
    public JButton InColor;
    public JButton OutColor;
    public BoardPanel board;
    public JPanel buttonsPanel;
    public BorderLayout frameLayout;
    public Box buttonsLayout;
    public Hashtable<String, Integer> buttonsActions;
    public int currActnNum;
    public Color currentInColor;
    public Color currentOutColor;
    public BoardListener boardListen;
    public ButtonListener butListen;
    

    public MainView() {

        buttonsActions = new Hashtable<String, Integer>();
        this.frame = new JFrame();
        butListen = new ButtonListener(this);
        boardListen = new BoardListener(this);
        this.board = new BoardPanel(this, boardListen);
        this.buttonsPanel = new JPanel();        
        this.circle = new JButton("circle");        
        this.rect = new JButton("Rect");
        this.square = new JButton("Square");
        this.traingle = new JButton("Traingle");
        this.line = new JButton("Line");
        this.ellipse = new JButton("Ellipse");
        this.select = new JButton("Select");
        this.delete = new JButton("Delete");
        this.undo = new JButton("Undo");
        this.redo = new JButton("Redo");
        this.save = new JButton("Save");
        this.load = new JButton("Load");
        this.InColor = new JButton("InColor");
        this.OutColor = new JButton("OutColor");
        this.buttonsLayout = Box.createHorizontalBox();
        this.frameLayout = new BorderLayout();
        this.currActnNum = -1;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void run() {
        frame.setSize(1111, 700);
        board.setSize(frame.getSize().width, frame.getSize().height - 100);
        //frame.setLayout(frameLayout);
        frame.add(board, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.NORTH);
        board.setVisible(true);
        // frame.add(board);
        //frame.add(buttonsPanel);
        board.setName("hehe");

        buttonsLayout.add(circle);
        buttonsLayout.add(rect);
        buttonsLayout.add(square);
        buttonsLayout.add(traingle);
        buttonsLayout.add(line);
        buttonsLayout.add(ellipse);
        buttonsLayout.add(select);
        buttonsLayout.add(delete);
        buttonsLayout.add(undo);
        buttonsLayout.add(redo);
        buttonsLayout.add(save);
        buttonsLayout.add(load);
        buttonsLayout.add(InColor);
        buttonsLayout.add(OutColor);
        buttonsPanel.add(buttonsLayout);

        // set colors
        board.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.RED);

        // set actions used
        buttonsActions.put(circle.getText(), 1);
        buttonsActions.put(rect.getText(), 2);
        buttonsActions.put(square.getText(), 3);
        buttonsActions.put(traingle.getText(), 4);
        buttonsActions.put(line.getText(), 5);
        buttonsActions.put(ellipse.getText(), 6);        
        buttonsActions.put(select.getText(), 7);
        buttonsActions.put(undo.getText(), 8);
        buttonsActions.put(redo.getText(), 9);
        buttonsActions.put(save.getText(), 10);
        buttonsActions.put(load.getText(), 11);
        buttonsActions.put(delete.getText(), 12);
        buttonsActions.put(InColor.getText(), 13);
        buttonsActions.put(OutColor.getText(), 14);
        // Set Listeners
        board.addMouseListener(boardListen);
        board.addMouseMotionListener(boardListen);

        circle.addActionListener(butListen);
        rect.addActionListener(butListen);
        square.addActionListener(butListen);
        traingle.addActionListener(butListen);
        ellipse.addActionListener(butListen);
        line.addActionListener(butListen);
        undo.addActionListener(butListen);
        redo.addActionListener(butListen);
        select.addActionListener(butListen);
        save.addActionListener(butListen);
        load.addActionListener(butListen);
        delete.addActionListener(butListen);
        InColor.addActionListener(butListen);
        OutColor.addActionListener(butListen);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        // TODO code application logic here
        MainView view = new MainView();
        view.run();

    }
}
