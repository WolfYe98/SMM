/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.graficos;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author bateye
 */
public class JLine2D extends miShape{
    private static final int TIPOFORMA = 1;
    private Line2D linea;
    
    private double distIniX = 0, distIniY = 0;
    private double distFinX = 0, distFinY = 0;
    
    public JLine2D(Point2D p1, Point2D p2) {
        linea = new Line2D.Double(p1, p2);
    }
    public JLine2D(){
        
    }
    public JLine2D(Line2D l){
        this.linea = l;
    }
    public JLine2D(JLine2D j) {
        this.linea = j.linea;
        this.distFinX = j.distFinX;
        this.distFinY = j.distFinY;
        this.distIniX = j.distIniX;
        this.distIniY = j.distIniY;
    }
    
    public Line2D getLinea(){
        return linea;
    }
    public double getX1(){
        return linea.getX1();
    }
    public double getX2(){
        return linea.getX2();
    }
    public double getY1(){
        return linea.getY1();
    }
    public double getY2(){
        return linea.getY2();
    }
    public Point2D getP1(){
        return linea.getP1();
    }
    public Point2D getP2(){
        return linea.getP2();
    }
    @Override
    public void setForma(Point2D p1, Point2D p2){
        if(linea != null){
            linea.setLine(p1, p2);
        }
    }
    
    @Override
    public void setLocation(Point2D pos){
        Point2D newp1 = new Point2D.Double(pos.getX()-distIniX, pos.getY()-distIniY);
        Point2D newp2 = new Point2D.Double(pos.getX()+distFinX,pos.getY()+distFinY);
        linea.setLine(newp1,newp2);
    }
    @Override
    public void setDistance(Point2D pos){
        if(linea != null){
            distIniX = pos.getX() - linea.getX1();
            distIniY = pos.getY() - linea.getY1();
            distFinX = linea.getX2() - pos.getX();
            distFinY = linea.getY2() - pos.getY();
        }
    }
    public void setLine(double x1, double y1, double x2, double y2) {
        linea.setLine(x1, y1, x2, y2);
    }
    
    @Override
    public Rectangle2D getBounds2D() {
        return linea.getBounds2D();
    }

    @Override
    public int getTipoForma() {
        return TIPOFORMA;
    }
  
    @Override
    public Shape getForma() {
        return this.linea;
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Point2D p) {
        return (linea.ptLineDist(p)<=2.0);
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
        return this.linea.getX1();
    }

    @Override
    public double getminY() {
        return this.linea.getY1();
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

}