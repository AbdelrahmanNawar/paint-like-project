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
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author user
 */
public class Triangle extends Shape {

    public Triangle(Point startPoint, Point endPoint, Color outColor, Color inColor) {
        this.setStartPoint(startPoint);
        this.setEndPoint(endPoint);
        this.setInColor(inColor);
        this.setOutColor(outColor);
    }

    public Triangle() {

    }

    @Override
    public void draw(Graphics g) {
       
       int[] x = new int[3];
       int []y = new int[3];
       x[0] = getStartPoint().x;
       x[1] = getStartPoint().x -( getEndPoint().x - getStartPoint().x);
       x[2] = getEndPoint().x;
       
       y[0] = getStartPoint().y;
       y[1] = getEndPoint().y;
       y[2] = getEndPoint().y;
       
        Polygon p= new Polygon(x, y, 3);
       ((Graphics2D) g).setColor(getOutColor());
       ((Graphics2D) g).setStroke(new BasicStroke(5));
       ((Graphics2D) g).drawPolygon(p);
       ((Graphics2D) g).setColor(getInColor());
       ((Graphics2D) g).fillPolygon(p);

    }

    @Override
    public boolean matchRegion(Point p) {
         Ellipse2D triangle = new Ellipse2D.Double(getStartPoint().x -( getEndPoint().x - getStartPoint().x),getStartPoint().y,(getEndPoint().x-getStartPoint().x)*2,getStartPoint().y + (getEndPoint().y - getStartPoint().y));
        return triangle.contains(p);
    }
    /**
     *
     * @return
     */
    @Override
    public Rectangle2D addSelectionRect() {
    return (new Ellipse2D.Double(getStartPoint().x -( getEndPoint().x - getStartPoint().x),getStartPoint().y,(getEndPoint().x-getStartPoint().x)*2,(getEndPoint().y - getStartPoint().y))).getBounds2D();
    }

    @Override
    public void move(Point startP, Point currentP) {
        int dx = currentP.x - startP.x;
        int dy = currentP.y - startP.y;
        setStartPoint(new Point(this.getStartPoint().x + dx, this.getStartPoint().y + dy));
        setEndPoint(new Point(this.getEndPoint().x + dx, this.getEndPoint().y + dy));

    }

    @Override
    public void resize(Point startP, Point currentPoint, int resizeDirection) {

        switch (resizeDirection) {
            case ResizeActionEnum.UP_LEFT: {
                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y + dy));
                setEndPoint(new Point(getEndPoint().x, getEndPoint().y));
                break;
            }
            case ResizeActionEnum.UP: {
                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x,getStartPoint().y + dy));
                setEndPoint(new Point(getEndPoint().x,getEndPoint().y));
                break;
            }
            case ResizeActionEnum.UP_RIGHT: {
                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x,getStartPoint().y + dy));
                setEndPoint(new Point(getEndPoint().x + dx,getEndPoint().y));
                break;
            }
            case ResizeActionEnum.LEFT: {

                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x, getEndPoint().y));
                break;
            }
            case ResizeActionEnum.RIGHT: {

                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setEndPoint(new Point(getEndPoint().x, getEndPoint().y));
                setEndPoint(new Point(getEndPoint().x + dx, getEndPoint().y));
                break;
            }
            case ResizeActionEnum.DOWN_LEFT: {

                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x + dx, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x , getEndPoint().y + dy));
                break;
            }
            case ResizeActionEnum.DOWN: {

                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x, getEndPoint().y + dy));

                break;
            }
            case ResizeActionEnum.DOWN_RIGHT: {

                int dx = currentPoint.x - startP.x;
                int dy = currentPoint.y - startP.y;
                setStartPoint(new Point(getStartPoint().x, getStartPoint().y));
                setEndPoint(new Point(getEndPoint().x + dx , getEndPoint().y + dy));
                break;
            }

        }
    }

    @Override
    /**
     * This method used to get copy of current Object
     */
    public Shape clone() {
        
        Shape c = new Triangle();
        c.setStartPoint(new Point(getStartPoint()));
        c.setEndPoint(new Point(getEndPoint()));
        if (getInColor() != null)
            c.setInColor(new Color(getInColor().getRGB()));
        if (getOutColor() != null)
            c.setOutColor(new Color(getOutColor().getRGB()));
        return c;
    }

}
