/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.BoardListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import view.MainView;

/**
 *
 * @author user
 */
public class BoardPanel extends JPanel {

    MainView view;
    BoardListener boardListen;

    public BoardPanel(MainView view, BoardListener boardListen) {
        this.view = view;
        this.boardListen = boardListen;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < boardListen.getBoardShapes().size(); i++) {
            boardListen.getBoardShapes().get(i).draw(g);

        }
        if (boardListen.getCurrentShape() != null) {
            boardListen.getCurrentShape().draw(g);
        }
        Graphics2D g2d = (Graphics2D) g;

        if (boardListen.getSelectedIndex() != -1  && boardListen.getSelectRect() != null) {
            g2d.draw(boardListen.getSelectRect());
            for (int i = 0; i < 8; i++) {
                g2d.draw(boardListen.getSelectActnRects()[i]);

            }

        }

    }
}
