/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.imagenes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.logging.Level;
import java.util.logging.Logger;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author bateye
 */
public class GreenOp extends BufferedImageOpAdapter{
    private int umbral;
    public GreenOp(int um){
        this.umbral = um;
    }
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if(src == null){
            throw new NullPointerException("Src is null!");
        }
        if(dest == null){
            dest = createCompatibleDestImage(src,null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int []pixelComp = new int[srcRaster.getNumBands()];
        int []pixelCompDest = new int[srcRaster.getNumBands()];
        int val;
        int media;
        for(int x = 0; x < src.getWidth();x++){
            for(int y = 0; y < src.getHeight(); y++){
                srcRaster.getPixel(x, y, pixelComp);
                val = pixelComp[1];
                media = pixelComp[1];
                for(int i = 0; i < pixelComp.length; i++){
                    if(i != 1){
                        val = val-pixelComp[i];
                        media = media + pixelComp[i];
                    }
                }
                if(val > this.umbral){
                    pixelCompDest = pixelComp;
                }
                else{
                    media = media / pixelComp.length;
                    for(int j = 0; j < pixelCompDest.length; j++){
                        pixelCompDest[j] = media;
                    }
                }
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }
    
}
