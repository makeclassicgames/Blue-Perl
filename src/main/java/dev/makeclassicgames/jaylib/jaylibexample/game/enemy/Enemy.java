package dev.makeclassicgames.jaylib.jaylibexample.game.enemy;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Collider;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

public class Enemy extends Entity {
    private int health;

    public Enemy(){
        this.sprite = new Sprite(ResourceIdentifier.ENEMY);
        this.sprite.addAnimation(4,32,32,5);
        this.sprite.addAnimation(4,32,32,5);
        this.sprite.addAnimation(4,32,32,5);
        this.sprite.setCurrentAnimation(2);
        this.health=50;
        this.collider=new Collider(17.0f,24.0f,32.0f,32.0f);
    }

    public void setHealth(int health){
        this.health=health;
    }

    public int getHealth(){
        return this.health;
    }
}
