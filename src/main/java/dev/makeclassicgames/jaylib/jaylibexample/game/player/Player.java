package dev.makeclassicgames.jaylib.jaylibexample.game.player;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

public class Player extends Entity {

    private int lives;
    private int health;

    public static final int UP_ANIM =3;
    public static final int DOWN_ANIM =0;
    public static final int LEFT_ANIM =1;
    public static final int RIGHT_ANIM =2;


    public Player(){
        this.lives = 3;
        this.health = 100;
        this.sprite= new Sprite(ResourceIdentifier.PLAYER);
        //Down
        this.sprite.addAnimation(4,64,59,5);
        //Left
        this.sprite.addAnimation(4,64,59,5);
        //Right
        this.sprite.addAnimation(4,64,59,5);
        //UP
        this.sprite.addAnimation(4,64,59,5);
    }

    public void drawPlayer(){
        this.draw();
    }

}
