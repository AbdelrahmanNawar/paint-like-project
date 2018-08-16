/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author user
 */
public class Square extends Shape {

    public Square(Point startPoint, Point endPoint, Color outColor, Color inColor) {
        endPoint.x = ((int) (Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2))));
        endPoint.y = endPoint.x;
        this.setStartPoint(startPoint);
        this.setEndPoint(endPoint);
        this.setInColor(inColor);
        this.setOutColor(outColor);
    }

    public Square() {

    }

    @Override
    public void draw(Graphics g) {
       ((Graphics2D) g).setColor(getOutColor());
       ((Graphics2D) g).setStroke(new BasicStroke(5));
       ((Graphics2D) g).drawRect(getStartPoint().x,getStartPoint().y,getEndPoint().x,getEndPoint().y);
       ((Graphics2D) g).setColor(getInColor());
       ((Graphics2D) g).fillRect(getStartPoint().x,getStartPoint().y,getEndPoint().x,getEndPoint().y);

    }

    @Override
    public boolean matchRegion(Point p) {
        Ellipse2D square = new Ellipse2D.Double(getStartPoint().x,getStartPoint().y,getEndPoint().x,getEndPoint().y);
        return square.contains(p);
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle2D addSelectionRect() {
        return (new Ellipse2D.Double(getStartPoint().x,getStartPoint().y,getEndPoint().x,getEndPoint().y)).getBounds2D();
    }

    @Override
    public void move(Point startP, Point currentP) {
        int dx = currentP.x - startP.x;
        int dy = currentP.y - startP.y;
        setStartPoint(new Point(this.getStartPoint().x + dx, this.getStartPoint().y + dy));

    }

    @Override
    public void resize(Point startP, Point currentPoint, int resizeDirection) {

        switch (resizeDirection) {
            case ResizeActionEnum.UP_LEFT: {
                int dx = currentPoint.x-startP.x;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y + dx));
                setEndPoint(new Point(getEndPoint().x - dx , getEndPoint().y - dx));
                break;
            }
            case ResizeActionEnum.UP: {
                int dx = startP.y - currentPoint.y;
                setStartPoint(new Point(getStartPoint().x,getStartPoint().y - dx));
                setEndPoint(new Point(getEndPoint().x + dx,getEndPoint().y + dx));
                break;
            }
            case ResizeActionEnum.UP_RIGHT: {
                int dx = currentPoint.x-startP.x;
                setStartPoint(new Point(getStartPoint().x,getStartPoint().y - dx));
                setEndPoint(new Point(getEndPoint().x + dx,getEndPoint().y + dx));
                break;
            }
            case ResizeActionEnum.LEFT: {
                int dx =currentPoint.x-startP.x;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x - dx, getEndPoint().y - dx));
                break;
            }
            case ResizeActionEnum.RIGHT: {
                int dx =currentPoint.x-startP.x;
                setEndPoint(new Point(getEndPoint().x + dx, getEndPoint().y + dx));

                break;
            }
            case ResizeActionEnum.DOWN_LEFT: {
                int dx =currentPoint.x-startP.x;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x - dx, getEndPoint().y - dx));
                break;
            }
            case ResizeActionEnum.DOWN: {
                int dx = currentPoint.y-startP.y;
                setStartPoint(new Point(getStartPoint().x, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x + dx , getEndPoint().y + dx));

                break;
            }
            case ResizeActionEnum.DOWN_RIGHT: {
                int dx = currentPoint.x-startP.x;
                setStartPoint(new Point(getStartPoint().x, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x + dx , getEndPoint().y + dx));
                break;
            }

        }
    }

    @Override
    /**
     * This method used to get copy of current Object
     */
    public Shape clone() {
        
        Shape c = new Square();
        c.setStartPoint(new Point(getStartPoint()));
        c.setEndPoint(new Point(getEndPoint()));
        if (getInColor() != null)
            c.setInColor(new Color(getInColor().getRGB()));
        if (getOutColor() != null)
            c.setOutColor(new Color(getOutColor().getRGB()));
        return c;
    }

}
