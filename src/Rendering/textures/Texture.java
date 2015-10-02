/* https://www.youtube.com/watch?v=rInmceDUcWs&list=PLzM5baL2UjtLEewQScGTWNgeoGXMxG7pc&index=13
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rendering.textures;

import Utils.Managers.TextureManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Texture {
    
    private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
    private TextureManager manager;
    private String fileName;
    
    public Texture(String fileName){
        this.fileName = fileName;
        TextureManager oldTexture = texMap.get(fileName);
        if(oldTexture != null){
            manager = oldTexture;
            manager.addReference();
        }
        else{
            try{
            manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
            }catch(IOException e){
            e.printStackTrace();
            }
        }

    }
   protected void finalize() throws Throwable {
       if(manager.removeReference() && !fileName.isEmpty()) 
           texMap.remove(fileName);
       super.finalize();
   } 
    
   public void render(Graphics g, double x, double y){
       g.drawImage(manager.getImage(), (int) x, (int) y, null);
   }
   
   public BufferedImage getImage(){
       return manager.getImage();
   }
}
