package control;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.awt.Color;
import view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import model.Shape;
import model.ShapeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class ButtonListener implements ActionListener {

    MainView view;

  public static Color x;
    public ButtonListener(MainView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        view.currActnNum = view.buttonsActions.get(ae.getActionCommand());
        switch (view.currActnNum) {
            case 8:
                if(!view.boardListen.getUndoStack().isEmpty())
                {
                view.boardListen.getRedoStack().push(view.boardListen.getBoardShapes());
                view.boardListen.setBoardShapes(view.boardListen.getUndoStack().pop());
                
                }
                break;
            case 9:                
                if(!view.boardListen.getRedoStack().isEmpty())
                {
                view.boardListen.getUndoStack().push(view.boardListen.getBoardShapes());
                view.boardListen.setBoardShapes(view.boardListen.getRedoStack().pop());
                
                }              
                break;
            case 10:
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(view.frame) == JFileChooser.APPROVE_OPTION)
                    saveXML(fileChooser.getSelectedFile());
                
                break;
            case 11:
                 JFileChooser fileLoader = new JFileChooser();
                if (fileLoader.showOpenDialog(view.frame) == JFileChooser.APPROVE_OPTION)
                    loadXML(fileLoader.getSelectedFile());
              
            case 12:
                if(view.boardListen.selectedIndex != -1){
                    view.boardListen.addUndoRec();
                    view.boardListen.boardShapes.remove(view.boardListen.selectedIndex);
                    view.boardListen.selectedIndex =-1;
                }
            break;
                
            case 13:
                view.currentInColor = JColorChooser.showDialog(null," Which Color?", Color.BLACK);
                break;
            case 14:                
                view.currentOutColor = JColorChooser.showDialog(null, "Which Color?", Color.BLACK);
                break;


        }
        view.board.repaint();
    }

    public void saveXML(File file) {
        
        XStream xstream = new XStream (new StaxDriver());
        xstream.alias("ShapeList",ShapeList.class);
        xstream.alias("Shape",Shape.class);
        ShapeList shapeList = new ShapeList();
        shapeList.setShapes(view.boardListen.getBoardShapes());
        String xml = xstream.toXML(shapeList);
        
        try
        {
            PrintWriter printWrite = new PrintWriter(file);
            printWrite.append(xml);
            printWrite.close();
        }
        catch (FileNotFoundException ex)
        {
        Logger.getLogger(ButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        
        }
       
        

    }

    private void loadXML(File file) {
        
        XStream xstream = new XStream (new StaxDriver());
        xstream.alias("ShapeList",ShapeList.class);
        xstream.alias("Shape",Shape.class);
        ShapeList shapeList;
        shapeList = (ShapeList)(xstream.fromXML(file));
        view.boardListen.setBoardShapes((ArrayList<Shape>) shapeList.getShapes().clone());
    }

   
}
