package dev.makeclassicgames.jaylib.jaylibexample.game.player;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Player extends Entity {

    private int lives;
    private int health;

    public static final int UP_ANIM =3;
    public static final int DOWN_ANIM =0;
    public static final int LEFT_ANIM =1;
    public static final int RIGHT_ANIM =2;

    private static final int MAX_HEALTH = 100;


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
        float width = (this.health*64)/100.0f;
        this.draw();
        DrawRectangle((int) this.position.getX()-48, (int) this.position.getY()-32, (int) width,5,RED);

    }

}
