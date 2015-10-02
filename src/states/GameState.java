/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import Entities.Entity;
import Entities.Player;
import Rendering.textures.Sprite;
import World.Tile;
import World.World;
import static game.Game.HEIGHT;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author alan
 */
public class GameState implements State{
    
    private ArrayList<Entity> entities;
    private ArrayList<Tile> tiles;
    private World world;
    
    @Override
    public void init(){
        entities = new ArrayList<Entity>();
        tiles = new ArrayList<Tile>();
        //new Player(new Sprite("test"),100,100, this);
        world = new World("level1", this);
        
    }
    
    @Override
    public void enter(){}
    
    @Override
    public void tick( StateManager stateManager){
        for(Entity e : entities)
            e.tick();
    }
    
    @Override
    public void render(Graphics2D g){
        for(Entity e : entities)
            e.render(g);
        for(Tile t : tiles)
            t.render(g);
    }
    
    @Override
    public void exit(){
        entities.clear();
    }
    
    @Override
    public String getName(){
        return "level1";
    }
    
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    
    public void addTile(Tile tile){
        tiles.add(tile);
    }
    
    public ArrayList<Tile> getTiles(){
        return tiles;
    }
}
