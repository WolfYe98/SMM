/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.imagenes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author bateye
 */
public class TintadoAutoOp extends BufferedImageOpAdapter{
    float color[];
    public TintadoAutoOp(Color c){
        this.color = c.getColorComponents(null);
        for(int i = 0; i < color.length; i++){
            this.color[i] *=255;
        }
    }
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if(src == null){
            throw new NullPointerException("SRC IS NULL!");
        }
        if(dest == null){
            dest = createCompatibleDestImage(src,null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = src.getRaster();
        float media = 0;
        float []sample = new float[srcRaster.getNumBands()];
        float alfa;
        int sampleb;
        for(int i = 0; i < src.getWidth(); i++){
            for(int j = 0; j < src.getHeight(); j++){
                srcRaster.getPixel(i, j, sample);
                for(int x = 0; x < sample.length; x++){
                    media=media+sample[x];
                }
                media = media/(float)sample.length;
                alfa = 1-(media/255);
                for(int band = 0; band < srcRaster.getNumBands(); band++){
                   sampleb = srcRaster.getSample(i, j, band);
                   sampleb = (int) (alfa*this.color[band] + (1.0f - alfa)*sampleb);
                   destRaster.setSample(i, j, band, sampleb);
                }
            }
        }
        return dest;
    }
    
}
