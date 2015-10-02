package Entities;

import Rendering.textures.Sprite;
import input.KeyInput;
import java.awt.event.KeyEvent;
import states.GameState;

public class Player extends Mob{
    
    
    public Player(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);
    }
    
    @Override
    public void tick(){
        if(KeyInput.isDown(KeyEvent.VK_W)){ jump(13);}
        if(KeyInput.isDown(KeyEvent.VK_S)){ dy = 2;}
        if(KeyInput.isDown(KeyEvent.VK_A)){ dx = -4;}
        if(KeyInput.isDown(KeyEvent.VK_D)){ dx = 4; }
        
        if(KeyInput.wasReleased(KeyEvent.VK_W) || KeyInput.wasReleased(KeyEvent.VK_S)) dy=0;
        if(KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D)) dx=0;
        
        super.tick();
    }
    
}
