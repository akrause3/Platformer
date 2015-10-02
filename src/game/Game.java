
package game;

import states.MenuState;
import input.KeyInput;
import input.MouseInput;
import java.awt.*;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import states.GameState;
import states.StateManager;

public class Game extends Canvas implements Runnable{
    
    public static final String TITLE = "Game";
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 16 * 4 * 3;
    public final static boolean DEBUG = true;
    
    private boolean running;
    
    private StateManager stateManager;
    
    public static Game INSTANCE;
    
//    private Texture t1, t2,t3,t4;
//    private SpriteSheet sheet;
//    private Sprite sprite, s2;
//    private double sX=350 , sY=300;
    
    public Game(){
        //sheet= new SpriteSheet(new Texture("test_sheet"),81);
        //sprite = new Sprite(sheet,5 ,7);
        //s2 = new Sprite(sheet,4,5);
        addKeyListener(new KeyInput());
        MouseInput mi = new MouseInput();
        addMouseListener(mi);
        addMouseMotionListener(mi);
        stateManager = new StateManager();
        
        stateManager.addState(new MenuState());
        stateManager.addState(new GameState());
        
        INSTANCE = this;
    }
    
    private void tick(){
        stateManager.tick();
    }
    
    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-6, -24);
        //////////////////////////////////
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,WIDTH,HEIGHT);
        
        
        stateManager.render(g2d);
        //////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    private void start(){
        if(running) return;
        running=true;
        new Thread(this, "GameMain- Thread").start();
    }
    
    public void stop(){
        if(!running) return;
        running=false;
    }
    
    public void run(){
        requestFocus();
        
        double target = 60.0;
        double nsPerTick = 1000000000.0 / target;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double unprocessed = 0.0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;
        
        while(running){
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime=now;
            
            if(unprocessed >=1.0){
                tick();
                KeyInput.update();
                MouseInput.update();
                unprocessed--;
                tps++;
                canRender=true;
            } else canRender=false;
            try{
            Thread.sleep(1);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            
            if(canRender){
                render();
                fps++;
        }
            
        if(System.currentTimeMillis()- 1000 > timer){
            timer +=1000;
            System.out.println(fps+" "+ tps);
            fps=0;
            tps=0;
        }
    }
        
        System.exit(0);
    }
    

    public static void main(String[] args) {
        final Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.err.println("Exiting Game");
                game.stop();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
        game.start();
        
    }
    
    
}
