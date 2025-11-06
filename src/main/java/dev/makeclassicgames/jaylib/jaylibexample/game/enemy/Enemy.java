package dev.makeclassicgames.jaylib.jaylibexample.game.enemy;

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
    }
}
