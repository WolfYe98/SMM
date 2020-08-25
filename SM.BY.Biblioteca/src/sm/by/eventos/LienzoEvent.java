/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.eventos;

import java.awt.Color;
import java.awt.Shape;
import java.util.EventObject;

/**
 *
 * @author bateye
 */
public class LienzoEvent extends EventObject{
    private Shape forma;
    private Color color;
    
    public LienzoEvent(Object source, Shape forma, Color color) {
        super(source);
        this.forma = forma;
        this.color = color; 
    }
    public Shape getForma(){
        return forma;
    }
    public Color getColor(){
        return color;
    }
    
}
