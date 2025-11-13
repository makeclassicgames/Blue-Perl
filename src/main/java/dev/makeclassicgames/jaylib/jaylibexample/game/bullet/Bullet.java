package dev.makeclassicgames.jaylib.jaylibexample.game.bullet;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Collider;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

public class Bullet extends Entity {
    private int power;

    public Bullet(){
        this.power=10;
        this.sprite= new Sprite(ResourceIdentifier.BULLET);
        this.sprite.addAnimation(1,8,8,5);
        this.collider= new Collider(1,1,8,8);
    }

    public Bullet(int power){
        this.power=power;
        this.sprite= new Sprite(ResourceIdentifier.BULLET);
        this.sprite.addAnimation(1,8,8,5);
        this.collider= new Collider(1,1,8,8);
    }
    public int getPower(){
        return this.power;
    }


}
