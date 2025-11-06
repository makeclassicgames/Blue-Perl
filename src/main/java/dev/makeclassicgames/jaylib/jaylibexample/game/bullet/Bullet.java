package dev.makeclassicgames.jaylib.jaylibexample.game.bullet;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

public class Bullet extends Entity {
    private int power;

    public Bullet(){
        this.sprite= new Sprite(ResourceIdentifier.BULLET);
        this.sprite.addAnimation(1,8,8,5);
    }


}
