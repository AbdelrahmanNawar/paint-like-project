package control;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.event.MouseInputListener;
import model.Circle;
import model.Ellipse;
import model.Line;
import model.Rectangle;
import model.Shape;
import model.ShapeList;
import model.Square;
import model.Triangle;
import org.w3c.dom.css.CSSPrimitiveValue;
import view.MainView;
import org.w3c.dom.css.Rect;
import org.w3c.dom.events.Event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class BoardListener implements MouseInputListener {

    MainView view;
    Point startP;
    Point currentP;
    ArrayList<Shape> boardShapes;
    Shape currentShape;
    Stack<ArrayList<Shape>> undoStack;
    Stack<ArrayList<Shape>> redoStack;
    Rectangle2D selectRect;
    int selectedIndex;
    Rectangle2D[] selectActnRects = new Rectangle2D.Double[8];
    int boxesIndex = 8;
    boolean moveState = false;
    int resizeState = -1;
    Point currentShapeSP;

    public  void  drawSelectionActionsRects(Rectangle2D t) {
        double x = t.getX();
        double y = t.getY();
        for (int i = 0; i<selectActnRects.length;i++)
        {
            selectActnRects[i] = new Rectangle2D.Double();
        }
        selectActnRects[0].setRect(x-5, y-5, 10, 10); //up left
        selectActnRects[1].setRect(x + t.getWidth() - 6, y-5, 10, 10);      // up right
        selectActnRects[2].setRect(x + t.getWidth() - 6, y + t.getHeight() - 6, 10, 10);    // down right
        selectActnRects[3].setRect(x-5, y + t.getHeight() - 6, 10, 10);     // down left
        selectActnRects[4].setRect(x + (t.getWidth() / 2)-5, y-5, 10, 10);  //up
        selectActnRects[5].setRect(x-5 + t.getWidth(), y + (t.getHeight() / 2 )-5, 10, 10);         //right
        selectActnRects[6].setRect(x+ (t.getWidth() / 2)-5, y+ t.getHeight()-5, 10, 10);    //down
        selectActnRects[7].setRect(x-5, y+ (t.getHeight() / 2)-5, 10, 10);      //left
    }

    public BoardListener(MainView view) {
        this.view = view;
        boardShapes = new ArrayList<Shape>();
        undoStack = new Stack<ArrayList<Shape>>();
        redoStack = new Stack<ArrayList<Shape>>();
        selectedIndex = -1;

    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        currentP = new Point(me.getX(), me.getY());
        switch (view.currActnNum) {
            case 7:
                selectedIndex = -1;
                for(int i = 0; i<boardShapes.size();i++){
                    if(boardShapes.get(i).matchRegion(currentP)){
                        selectedIndex = i;
                        break;
                    }
                }
            if(selectedIndex != -1)
            {
                selectRect = boardShapes.get(selectedIndex).addSelectionRect();
                drawSelectionActionsRects(selectRect);
            }
            view.board.repaint();
        break;
                        
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
        startP = new Point(me.getX(), me.getY());
        
        switch (view.currActnNum) {
            /// Move Case Part 1
            case 7:
                resizeState = -1;
                if (selectedIndex != -1)
                {
                    for (int i =0 ; i < 8 ; i++)
                    {
                        if (selectActnRects[i].contains(startP))
                        {
                            resizeState = i;
                            break;
                        }
                    }
                    if(selectRect.contains(startP) && resizeState == -1)
                    {
                        moveState = true;
                        selectRect = null;
                    }
                    addUndoRec();
                   
                        
                }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        currentP = new Point(me.getX(), me.getY());

        switch (view.currActnNum) {
            case 1:
                addUndoRec();
                currentShape = new Circle(startP, currentP, view.currentOutColor, view.currentInColor);
                boardShapes.add(currentShape);
                currentShape = null;
                break;
            case 2:
                addUndoRec();
                currentShape = new Rectangle(startP, currentP, view.currentOutColor, view.currentInColor);
                boardShapes.add(currentShape);
                currentShape = null;
                break;
            case 3:
                addUndoRec();
                currentShape = new Square(startP, currentP, view.currentOutColor, view.currentInColor);
                boardShapes.add(currentShape);
                currentShape = null;
                break;
            case 4:
                addUndoRec();
                currentShape = new Triangle(startP, currentP, view.currentOutColor, view.currentInColor);
                boardShapes.add(currentShape);
                currentShape = null;
                break;    
            case 5:
                addUndoRec();
                currentShape = new Line(startP, currentP, view.currentOutColor, null);
                boardShapes.add(currentShape);
                currentShape = null;
                break; 
            case 6:
                addUndoRec();
                currentShape = new Ellipse(startP, currentP, view.currentOutColor, view.currentInColor);
                boardShapes.add(currentShape);
                currentShape = null;
                break;   
            case 7:
                selectedIndex = -1;
                moveState=false;
                resizeState=-1;
                selectRect= null;
                break;
                        
        }
        view.board.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent me) {
  /*      int  choice = view.buttonsActions.get(((JButton)(me.getSource())).getText()).intValue();
         switch(choice)
         {
         case 1:
         ((JButton)me.getSource()).setToolTipText("Draw Circle");
         break;
         case 2 :
         ((JButton)me.getSource()).setToolTipText("Draw Rectangle");

                
         }
         */
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

        currentP = new Point(me.getX(), me.getY());
        switch (view.currActnNum) {
            case 1: //draw circle
                currentShape = new Circle(startP, currentP, null, null);
                break;
            case 2:
                currentShape = new Rectangle(startP, currentP, null ,null);
                break;
            case 3:
                currentShape = new Square(startP, currentP, null ,null);
                break;
            case 4:
                currentShape = new Triangle(startP, currentP, null, null);
                break;
            case 5:
                currentShape = new Line(startP, currentP, null ,null);
                break;    
            case 6:  
                currentShape = new Ellipse(startP, currentP, null ,null);
                break;
            case 7:
                if(selectedIndex != -1)
                {
                    if(resizeState != -1)
                    {
                        boardShapes.get(selectedIndex).resize(startP, currentP, resizeState);
                        selectRect= boardShapes.get(selectedIndex).addSelectionRect();
                        drawSelectionActionsRects(selectRect);
                    }else if(moveState)
                    {
                        boardShapes.get(selectedIndex).move(startP, currentP);
                    }
                    startP =new Point(currentP.x,currentP.y);
                }
        }
        view.board.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    public void addUndoRec() {
        
        ArrayList<Shape> copyList = new ArrayList<Shape>();
        for(int i= 0; i < boardShapes.size();i++)
            copyList.add(boardShapes.get(i).clone());
        undoStack.push((ArrayList<Shape>) copyList);
        redoStack.clear();
        
      

    }

    // Setters and Getters
    public ArrayList<Shape> getBoardShapes() {
        return boardShapes;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setBoardShapes(ArrayList<Shape> boardShapes) {
        this.boardShapes = boardShapes;
    }

    public Stack<ArrayList<Shape>> getUndoStack() {
        return undoStack;
    }

    public Stack<ArrayList<Shape>> getRedoStack() {
        return redoStack;
    }

    public void setRedoStack(Stack<ArrayList<Shape>> redoStack) {
        this.redoStack = redoStack;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public Rectangle2D getSelectRect() {
        return selectRect;
    }

    public Rectangle2D[] getSelectActnRects() {
        return selectActnRects;
    }  
    
}
