/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.userInteface;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.*;
import java.math.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import static java.beans.Beans.isInstanceOf;
import javafx.geometry.BoundingBox;
import sm.by.graficos.Herramientas;
import sm.by.graficos.JEllipse2D;
import sm.by.graficos.JLine2D;
import sm.by.graficos.JRectangulo2D;
import sm.by.graficos.miShape;
import static sun.awt.SunToolkit.isInstanceOf;
/**
 *
 * @author bateye
 */
public class lienzo2D extends javax.swing.JPanel {
    private boolean relleno = false;
    private ArrayList<Shape> vectorShape = new ArrayList();
    private Color color;
    private float patronDiscontinuidad[] = {1.0f,0.0f};
    private float grosor = 1.0f;
    private BasicStroke trazo = new BasicStroke(grosor,BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
    private Stroke stroke = (Stroke)trazo;
    private Herramientas herra = Herramientas.punto;
    private Shape s;
    private JLine2D jline;
    private Point2D p1;
    private Point2D p2;
    private boolean editar = false;
    private boolean transparencia = false;
    private boolean alisar = false;
   
    private RenderingHints render;
    private BufferedImage img = null;
    private Rectangle2D ventana;
    private int ancho = 300;
    private int alto = 300;
    private boolean imgAbierto = false;
    private boolean guardar = false;
    private ArrayList<miShape> vectorMishape = new ArrayList();
    private miShape ms;
    private float transparenciaNivel = 10.0f;
    private JRectangulo2D marco;
    private int seleccionDiscontinuidad = 0;
    private boolean gradiente = false;
    private boolean tipoGradiente = true;
    private Color colorRelleno;
    /**
     * Creates new form lienzo2D
     */
    public lienzo2D() {
        initComponents();
        color = Color.black;
        colorRelleno = Color.black;
    }
    @Override
    public void paint(Graphics g){ 
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g; 
        g2d.setPaint(Color.white);
        g2d.fill(new Rectangle2D.Double(0, 0, ancho, alto));
        if(img == null){
            g2d = (Graphics2D)img.createGraphics();
            g2d.setPaint(Color.white);
            g2d.fill(new Rectangle2D.Float(0,0,ancho,alto));
        }
        else{
            g2d.drawImage(img,0,0,ancho,alto,0,0,ancho,alto,this);
            
            if(!imgAbierto){  
                this.ancho = img.getWidth();
                this.alto = img.getHeight();
                imgAbierto = true;
            }
        }
        if(!guardar){
            g2d.setPaint(Color.black);
            float patronD[] = {1.0f, 3.0f};
            float gr = 1.0f;
            BasicStroke tra = new BasicStroke(gr,BasicStroke.CAP_SQUARE, 
                BasicStroke.JOIN_MITER, 1.0f, patronD, 0.0f);
            stroke = (Stroke)tra;
            g2d.setStroke(stroke);
            ventana = new Rectangle2D.Double(0, 0, ancho, alto);
            g2d.clip(ventana);
            g2d.draw(ventana);
            guardar = false;
        }
        
        if(editar){
            if(marco != null){
                marco.pintar(g2d);
            }
        }
        
        for (miShape x : vectorMishape) {
            x.pintar(g2d);
        }
    }
    
    public BufferedImage getImage(){
        return this.img;
    }
    
    public BufferedImage getImagen(boolean dib){
        if(dib){
            BufferedImage imgout;
            this.guardar = true;
            if(img == null){
                imgout = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
            }
            else{
                imgout = new BufferedImage(ancho,alto,img.getType());
            }
            this.marco = null;
            this.paint(imgout.createGraphics());
            return imgout;
        }
        return this.img;
    }
    public void marcarSeleccionado(){
        if(editar){
            float patronD[] = {1.0f, 3.0f};
            float gr = 1.0f;
            BasicStroke tra = new BasicStroke(gr, BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_MITER, 1.0f, patronD, 0.0f);
            stroke = (Stroke) tra;
            
          
            if(ms != null){
                double espacio = 5;
                if(grosor > 5){
                    espacio = (double)grosor;
                }
                marco = new JRectangulo2D(ms.getBounds2D().getX()-(double)espacio,
                        ms.getBounds2D().getY()-(double)espacio,ms.getBounds2D().getWidth()+2*(double)espacio,
                        ms.getBounds2D().getHeight()+2*(double)espacio);
                
                marco.setStroke(stroke);
            }
            else{
                marco = null;
            }
           
            
        }
    }
    
    public boolean getRelleno(){
        return relleno;
    }
    public boolean getTransparencia(){
        return this.transparencia;
    }
    public boolean getAlisar(){
        return this.alisar;
    }
    public float getGrosor(){
        return this.grosor;
    }
    public Color getColor(){
        return this.color;
    }
    public boolean getEditar(){
        return this.editar;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }
    public Herramientas getHerramienta(){
        return this.herra;
    }
    public miShape getSelectedmiShape(){
        for(miShape sh:vectorMishape){
            if(sh instanceof JLine2D){
                if(((JLine2D) sh).contains(p1)){
                    return sh;
                }
            }
            else if(sh instanceof JRectangulo2D){
                if(((JRectangulo2D) sh).contains(p1)){
                    return sh;
                }
            }
            else if(sh instanceof JEllipse2D){
                if(((JEllipse2D) sh).contains(p1)){
                    return sh;
                }
            }
        }
        return null;
    }
    
    
    public void setContinuidad(int seleccion){
        this.seleccionDiscontinuidad = seleccion;
        if(editar){
            ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setDiscontinuidad(seleccionDiscontinuidad);
            }
        }    
        this.repaint();
    }
    public void setImagen(BufferedImage im){
        this.img = im;
        Graphics2D g2 = this.img.createGraphics();
        if(img != null) {
            ancho = img.getWidth();
            alto = this.img.getHeight();
            if(img.getType() == BufferedImage.TYPE_INT_RGB){
                g2.setPaint(Color.white);
                g2.fill(new Rectangle2D.Float(0,0,ancho,alto));
            }
            setPreferredSize(new Dimension(ancho,alto));
        }
        this.repaint();
    }
    public void setRelleno(boolean r){
        relleno = r;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setRelleno(r);
            }
        }
        this.repaint();
    }
    public void setColor(Color c){
        this.color = c;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setColor(color);
            }
        }
        this.repaint();
    }
    public void setTransparencia(float f){
        transparenciaNivel = f;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setTransparencia(f);
            }
        }
        this.repaint();
    }
    public void setAlisar(boolean s){
        alisar = s;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setRender(alisar);
            }
        }
        this.repaint();
    }
    public void setGrosor(float g){
        grosor = g;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setGrosor(g);
                this.marcarSeleccionado();
            }
        }
        this.repaint();
    }
    public void setAncho(int anc){
        this.ancho = anc;
        if(img.getType() == BufferedImage.TYPE_INT_RGB){
            BufferedImage img1;
            img1 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
            this.setImagen(img1);
        }
        else{
            this.repaint();
        }
        this.setPreferredSize(new Dimension(ancho,alto));
    }
    public void setAlto(int alt){
        this.alto = alt;
        if(img.getType() == BufferedImage.TYPE_INT_RGB){
            BufferedImage img1;
            img1 = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
            this.setImagen(img1);
        }
        else{
            this.repaint();
        }
        this.setPreferredSize(new Dimension(ancho,alto));
    }
    public void setHerramienta(int h){
        switch(h){
            case 0:
                herra = Herramientas.nada;
                break;
            case 1:
                herra = Herramientas.punto;
                break;
            case 2:
                herra = Herramientas.linea;
                break;
            case 3:
                herra = Herramientas.cuadrado;
                break;
            case 4:
                herra = Herramientas.circulo;
                break;
        }
    }
    public void setGradiente(boolean gr){
        this.gradiente = gr;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setGradiente(gr);
            }
        }
        this.repaint();
    }
    public void setTipoGradiente(int t){
        if(t == 0){
            this.tipoGradiente = true;
        }
        else{
            this.tipoGradiente = false;
        }
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setTipoGradiente(tipoGradiente);
            }
        }
        this.repaint();
    }
    public void setColorRelleno(Color c){
        this.colorRelleno = c;
        if(editar){
            miShape ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setColorRelleno(this.colorRelleno);
            }
        }
        this.repaint();
    }
    private void crearShape(){
        if(!editar){
            switch(herra){
                case punto:
                    ms = new JRectangulo2D(p1);
                    break;
                case linea:
                    ms = new JLine2D(p1,p1);
                    break;
                case cuadrado:
                    ms = new JRectangulo2D(p1);
                    break;
                case circulo:
                    ms = new JEllipse2D(p1);
                    break;
                case nada:
                    ms = null;
                    break;
                default:
                    throw new AssertionError(herra.name());
            }
            if(ms != null){
                this.ms.setColor(color);
                this.ms.setRelleno(relleno);
                this.ms.setColorRelleno(colorRelleno);
                this.ms.setGrosor(grosor);
                this.ms.setDiscontinuidad(seleccionDiscontinuidad);
                this.ms.setTransparencia(transparenciaNivel);
                this.ms.setRender(this.alisar);
                this.ms.setGradiente(this.gradiente);
                this.ms.setTipoGradiente(this.tipoGradiente);
                this.vectorMishape.add(ms);
            }
        }
        else{
            ms = this.getSelectedmiShape();
            if(ms != null){
                ms.setDistance(p1);
            }
        }
    }
    
    
    private void updateShape(){
        if(herra != Herramientas.nada && herra != Herramientas.punto && !editar){
            ms.setForma(p1, p2);   
        }
        if(editar){
            if(ms != null){
                ms.setLocation(p2);
                this.marcarSeleccionado();
            }
        }
        if(ms != null){
            ms.setGradiente(this.gradiente);
        }
        this.repaint();
    }
    
    public void clearFiguras(){
        this.vectorMishape.clear();
    }
    public void setEditar(boolean edit){
        editar = edit;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        p1 = evt.getPoint();
        crearShape();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        p2 = evt.getPoint();
    }//GEN-LAST:event_formMouseReleased
    
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        p2 = evt.getPoint();
        updateShape();
    }//GEN-LAST:event_formMouseDragged
    public Point2D getCoordenadasRaton(){
        return p2;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
