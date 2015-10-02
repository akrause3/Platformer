package Entities;

public class Camera {
    
     Player player = null;
     int x,y;
    
    public Camera(Player e){
        
        x= (int)e.x;
        y= (int)e.y;
        player = e;
        
    }
    
    public void tick(){
        x= (int)player.x;
        y= (int)player.y; 
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
