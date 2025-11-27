package dev.makeclassicgames.jaylib.jaylibexample.game.bullet;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

public class BulletEnemy extends Bullet{

    public BulletEnemy(){
        super();
        this.sprite= new Sprite(ResourceIdentifier.BULLET_ENEMY);
        this.sprite.addAnimation(1,8,8,5);
    }
}
