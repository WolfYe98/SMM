/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.imagenes;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.logging.Level;
import java.util.logging.Logger;
import sm.image.BufferedImageOpAdapter;
/**
 *
 * @author bateye
 */
public class PosterizarOp extends BufferedImageOpAdapter{
    private int niveles;
    public PosterizarOp (int niveles) {
        this.niveles = niveles; 
    }
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if((src.getType() != BufferedImage.TYPE_INT_RGB) && (src.getType() != BufferedImage.TYPE_INT_ARGB)){
            try {
                throw new Exception("La imagen no esta en rgb");
            } catch (Exception ex) {
                Logger.getLogger(PosterizarOp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (src == null) {  //Si la imagen fuente es nulo lanzamos una excepcion.
            throw new NullPointerException("src image is null"); 
        }
        if (dest == null) {     //Si la imagen destino es nulo, creamos un hijo compatible con la imagen src.
            dest = createCompatibleDestImage(src, null); 
        }
        double K = 256/this.niveles;
        WritableRaster srcRaster = src.getRaster(); 
        WritableRaster destRaster = dest.getRaster(); 
        int sample;
        for (int x = 0; x < src.getWidth(); x++) { 
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < srcRaster.getNumBands(); band++){ 
                    sample = srcRaster.getSample(x, y, band);
                    sample = (int)(K*(int)(sample/K));
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }
    
}
