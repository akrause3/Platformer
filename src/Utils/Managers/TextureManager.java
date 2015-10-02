/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Managers;

import Utils.Managers.ResourceManager;
import java.awt.image.BufferedImage;

/**
 *
 * @author alan
 */
public class TextureManager extends ResourceManager{
    private BufferedImage image;
    
    public TextureManager(BufferedImage image){
        this.image = image;
    }
    
//    protected void finalize() throws Throwable{
//        
//    }
    
    public BufferedImage getImage(){
        return image;
    }
}
