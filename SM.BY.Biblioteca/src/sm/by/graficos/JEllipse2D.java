/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.graficos;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author bateye
 */
public class JEllipse2D extends miShape{
    private static final int TIPOFORMA = 3;
    private Ellipse2D circ;
    private double distIniX = 0, distIniY = 0;
    private double distFinX = 0, distFinY = 0;
    
    
    
    public JEllipse2D(Point2D p1){
        circ = new Ellipse2D.Double(p1.getX(), p1.getY(), 1, 1);
    }
    @Override
    public void setForma(Point2D p1, Point2D p2) {
        circ.setFrameFromDiagonal(p1, p2);
    }

    @Override
    public Shape getForma() {
        return (this.circ !=null ? this.circ:null);
    }

    @Override
    public int getTipoForma() {
        return TIPOFORMA;
    }

    @Override
    public void setLocation(Point2D pos) {
        Point2D newp1 = new Point2D.Double(pos.getX()-distIniX, pos.getY()-distIniY);
        Point2D newp2 = new Point2D.Double(pos.getX()+distFinX,pos.getY()+distFinY);
        circ.setFrameFromDiagonal(newp1, newp2);
        
    }

    @Override
    public void setDistance(Point2D p1) {
        if(circ != null){
            distIniX = p1.getX() - circ.getMinX();
            distIniY = p1.getY() - circ.getMinY();
            distFinX = circ.getMaxX() - p1.getX();
            distFinY = circ.getMaxY() - p1.getY();
        }
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getBounds2D() {
        return (this.circ != null ? this.circ.getBounds2D() : null);
    }

    @Override
    public boolean contains(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Point2D p) {
        return this.circ.contains(p);
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
        return this.circ.getMinX();
    }

    @Override
    public double getminY() {
        return this.circ.getMinY();
    }

    @Override
    public double getWidth() {
        return this.circ.getWidth();
    }

    @Override
    public double getHeight() {
        return this.circ.getHeight();
    }
    
}
