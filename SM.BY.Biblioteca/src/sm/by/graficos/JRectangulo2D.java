/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.graficos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author bateye
 */
public class JRectangulo2D extends miShape{
    private static final int TIPOFORMA = 2;
    private Rectangle2D rect;
    private double distIniX = 0, distIniY = 0;
    private double distFinX = 0, distFinY = 0;
    public JRectangulo2D(Point2D p1){
        rect = new Rectangle2D.Double(p1.getX(), p1.getY(), 1, 1);
    }
    
    public JRectangulo2D(double x, double y, double w, double h){
        rect = new Rectangle2D.Double(x,y,w,h);
    }
    public JRectangulo2D(Rectangle2D r){
        rect = r;
    }

    
    
    @Override
    public Shape getForma() {
        return (this.rect !=null ? this.rect:null);
    }

    @Override
    public int getTipoForma() {
        return TIPOFORMA;
    }

    @Override
    public void setLocation(Point2D pos) {
        Point2D newp1 = new Point2D.Double(pos.getX()-distIniX, pos.getY()-distIniY);
        Point2D newp2 = new Point2D.Double(pos.getX()+distFinX,pos.getY()+distFinY);
        rect.setFrameFromDiagonal(newp1, newp2);
    }

    @Override
    public void setDistance(Point2D p1) {
        if(rect != null){
            distIniX = p1.getX() - rect.getMinX();
            distIniY = p1.getY() - rect.getMinY();
            distFinX = rect.getMaxX() - p1.getX();
            distFinY = rect.getMaxY() - p1.getY();
        }
    }
    @Override
    public void setForma(Point2D p1, Point2D p2){
        if(this.rect != null){
            rect.setFrameFromDiagonal(p1, p2);
        }
    }
    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getBounds2D() {
        if(this.rect != null){
            return this.rect.getBounds2D();
        }
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Point2D p) {
        return rect.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getminX() {
        return this.rect.getMinX();
    }

    @Override
    public double getminY() {
        return this.rect.getMinY();
    }

    @Override
    public double getWidth() {
        return this.rect.getWidth();
    }

    @Override
    public double getHeight() {
        return this.rect.getHeight();
    }
    
}
