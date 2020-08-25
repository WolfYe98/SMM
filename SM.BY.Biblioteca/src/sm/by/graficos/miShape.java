/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.Shape;

/**
 *
 * @author bateye
 */
public abstract class miShape implements Shape{
    private Color color = Color.BLACK;
    private Color colorRelleno = Color.BLACK;
    private float grosor = 1.0f;
    private float patronDiscontinuidad[] = {1.0f, 0.0f};
    private BasicStroke trazo = new BasicStroke(grosor,BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
    private Stroke stroke =(Stroke) trazo;;
    private RenderingHints render =render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    private boolean alias = false;
    private boolean relleno = false;
    private float transp = 1.0f;
    private Composite transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transp);
    private boolean gradiente = false;
    
    private Paint colorGradiente = null;
    private boolean gradienteHorizontal = true;
    
    public void pintar(Graphics2D g2d){
        g2d.setPaint(this.color);
        g2d.setStroke(stroke);
        g2d.setComposite(transparencia);
        g2d.setRenderingHints(render);
        if(this.getForma() != null){
            if(!relleno || (this.getTipoForma() == 1)){
                g2d.draw(this.getForma());
            }
            else{
                if(!gradiente){
                    g2d.draw(this.getForma());
                    
                    g2d.setPaint(colorRelleno);
                    
                    g2d.fill(this.getForma());
                }
            }
            if(gradiente){
                g2d.setPaint(colorGradiente);
                g2d.draw(this.getForma());
                g2d.fill(this.getForma());
            }
        } 
    }
    public void setColor(Color c){
        this.color = c;
        this.setGradiente(this.gradiente);
    }
    public void setRelleno(boolean r){
        this.relleno = r;
    }
    public void setGrosor(float gr){
        grosor = gr;
        trazo = new BasicStroke(grosor,BasicStroke.CAP_SQUARE, 
                BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
        stroke = (Stroke) trazo;
    }
    public void setTransparencia(float f){
        if(f != 0.0f){
            transp = f/10;
        }
        transparencia = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transp);
    }
    public void setRender(boolean ren){
        if(ren){
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else{
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        alias = ren;
    }
    public void setStroke(Stroke s){
        this.stroke = s;
    }
    public void setDiscontinuidad(int s){
        if(s == 0){
            patronDiscontinuidad[0] = 1.0f;
            patronDiscontinuidad[1] = 0.0f;
        }
        else{
            patronDiscontinuidad[0] = grosor;
            patronDiscontinuidad[1] = grosor+5;
        }
        trazo = new BasicStroke(grosor,BasicStroke.CAP_SQUARE, 
                BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
        stroke = (Stroke) trazo;
    }
    public void setGradiente(boolean gr){
        this.gradiente = gr;
        if(gr){
            float x1,y1;
            float x2,y2;
            if(this.gradienteHorizontal){
                x1 = (float)this.getminX();
                y1 = (float)((this.getminY()+this.getHeight())/2);
                x2 = (float)(this.getminX()+this.getWidth());
                y2 = y1;
                this.colorGradiente = new GradientPaint(x1,y1,color,x2,y2,colorRelleno);
            }
            else{
                x1 = (float)(this.getminX()+this.getWidth())/2;
                y1 = (float)this.getminY();
                x2 = x1;
                y2 = (float)(this.getminY()+this.getHeight());
                this.colorGradiente = new GradientPaint(x1,y1,color,x2,y2,colorRelleno);
            }
        }
    }
    public void setTipoGradiente(boolean b){
        this.gradienteHorizontal = b;
        this.setGradiente(this.gradiente);
    }
    public void setColorRelleno(Color c){
        this.colorRelleno = c;
        this.setGradiente(this.gradiente);
    }
    public float[] getPatron(){
        return this.patronDiscontinuidad;
    }
    public Color getColor(){
        return this.color;
    }
    public float getGrosor(){
        return this.grosor;
    }
    public boolean getRelleno(){
        return relleno;
    }
    public boolean getAliasing(){
        return alias;
    }
    public float getTransparencia(){
        return this.transp;
    }
    public boolean getGradiente(){
        return this.gradiente;
    }
    public boolean getTipoGradiente(){
        return this.gradienteHorizontal;
    }
    public Color getColorRelleno(){
        return this.colorRelleno;
    }
    public abstract void setForma(Point2D p1, Point2D p2);
    public abstract Shape getForma();
    public abstract int getTipoForma();
    public abstract void setLocation(Point2D p);
    public abstract void setDistance(Point2D p);
    public abstract double getminX();
    public abstract double getminY();
    public abstract double getWidth();
    public abstract double getHeight();
}
