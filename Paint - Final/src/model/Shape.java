/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author user
 */
public abstract class Shape {
    
    private Point startPoint;
    private Point endPoint;
    private Color outColor;
    private Color inColor;

   

   
    
    /**
     *
     * @param start
     * @param end
     */
    public abstract void move(Point startP, Point currentP); 
    public abstract void draw(Graphics g);
    public abstract boolean matchRegion(Point p);
    public abstract Rectangle2D addSelectionRect();
    public abstract Shape clone();
    public abstract void resize(Point startP ,Point currentPoint , int resizeDirection);
    
    
        
        // Getters and Setters

    public Point getEndPoint() {
        return endPoint;
    }

    public Color getInColor() {
        return inColor;
    }

    public Color getOutColor() {
        return outColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setInColor(Color inColor) {
        this.inColor = inColor;
    }

    public void setOutColor(Color outColor) {
        this.outColor = outColor;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

       
}
