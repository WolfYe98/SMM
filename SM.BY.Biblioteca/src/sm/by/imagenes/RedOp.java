/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.by.imagenes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author bateye
 */
public class RedOp extends BufferedImageOpAdapter{
    private int umbral;
    public RedOp(int umb){
        this.umbral = umb;
    }
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if(src == null){
            throw new NullPointerException("The source is null!");
        }
        
        if(dest == null){
            dest = createCompatibleDestImage(src,null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()]; 
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        int val;
        int media;
        for (int x = 0; x < src.getWidth(); x++) { 
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                val = pixelComp[0];
                media = pixelComp[0];
                for(int i = 1; i < pixelComp.length; i++){
                    media = media + pixelComp[i];
                    val = val - pixelComp[i];
                }
                if(val > this.umbral){
                    pixelCompDest = pixelComp;
                }
                else{
                    media = media/pixelComp.length;
                    for(int j =0 ; j < pixelComp.length; j++){
                        pixelCompDest[j] = media;
                    }
                }
                destRaster.setPixel(x, y, pixelCompDest); 
            }
        }
        return dest;
    }
    
}
