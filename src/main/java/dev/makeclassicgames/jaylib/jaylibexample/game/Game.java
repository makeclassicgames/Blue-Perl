package dev.makeclassicgames.jaylib.jaylibexample.game;

import dev.makeclassicgames.jaylib.jaylibexample.engine.GameObject;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Vector2D;
import dev.makeclassicgames.jaylib.jaylibexample.game.bullet.Bullet;
import dev.makeclassicgames.jaylib.jaylibexample.game.enemy.Enemy;
import dev.makeclassicgames.jaylib.jaylibexample.game.input.InputMappingManager;
import dev.makeclassicgames.jaylib.jaylibexample.game.player.Player;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Game implements GameObject {

    private static final float PLAYER_SPEED = 200.0f;
    private static final float ROTATION_SPEED = 180f;
    private GameState currentState;
    private InputMappingManager inputManager;
    private Player player;
    private List<Enemy> enemyList;
    private List<Bullet> playerBulletList;
    private List<Bullet> enemyBulletList;

    public Game(){
        this.currentState= GameState.INIT;
        this.player = new Player();
        this.enemyList = new ArrayList<>();
        this.inputManager = new InputMappingManager();
        this.playerBulletList = new ArrayList<>();
        this.enemyBulletList = new ArrayList<>();
        player.setPosition(new Vector2D(140.0f,140.0f));
        player.getSprite().setCurrentAnimation(2);
        Enemy e = new Enemy();
        e.setPosition(new Vector2D(20,40));
        Enemy e1 = new Enemy();
        e1.setPosition(new Vector2D(120,40));
        this.enemyList.add(e1);
        this.enemyList.add(e);
    }

    @Override
    public void update() {
        switch (this.currentState){
            case INIT -> updateInit();
            case GAME -> updateGame();
        }
    }

    private void updateInit(){
        if(IsKeyPressed(KEY_ENTER)){
            this.currentState= GameState.GAME;
        }
    }

    private void updateGame(){
        //TODO: Calcular todo en función de rotacion no usar direcciones
        Vector2D vel = new Vector2D();
        float dt = GetFrameTime();
       /* if(IsKeyDown(KEY_UP)){
            vel.setY(PLAYER_SPEED);
            vel.setX(PLAYER_SPEED);
        }

        if(IsKeyDown(KEY_DOWN)){*/
            vel.setY(PLAYER_SPEED);
            vel.setX(PLAYER_SPEED);
        //}

        if(IsKeyDown(KEY_LEFT)){
            this.player.setRotation((float) (this.player.getRotation() - ROTATION_SPEED * dt));
        }

        if(IsKeyDown(KEY_RIGHT)){
            this.player.setRotation((float) (this.player.getRotation() + ROTATION_SPEED * dt));
        }

        if(IsKeyPressed(KEY_A)){
            Bullet playerB = new Bullet();
            playerB.setPosition(new Vector2D(player.getPosition().getX(),
                    player.getPosition().getY()));
            playerB.setVelocity(new Vector2D(300,300));
            playerB.setRotation(player.getRotation()-90.0f);
            playerBulletList.add(playerB);
        }

        if(IsKeyPressed(KEY_D)){
            Bullet playerB = new Bullet();
            playerB.setPosition(new Vector2D(player.getPosition().getX(),
                    player.getPosition().getY()+32));
            playerB.setVelocity(new Vector2D(300,300));
            playerB.setRotation(player.getRotation()+90.0f);
            playerBulletList.add(playerB);
        }

        if(IsKeyPressed(KEY_S)){
            Bullet playerB = new Bullet();


            playerB.setPosition(new Vector2D(player.getPosition().getX(), player.getPosition().getY()));
            playerB.setVelocity(new Vector2D(300,300));
            playerB.setRotation(player.getRotation());
            playerBulletList.add(playerB);
        }
        this.player.setVelocity(vel);
        this.player.update();
        List<Bullet> toRemove = new ArrayList<>();
        for(Bullet b: playerBulletList){
            if(b.getPosition().getX()<0 || b.getPosition().getX()>GetScreenWidth()
            || b.getPosition().getY()<0 || b.getPosition().getY()>GetScreenHeight()){
                toRemove.add(b);
            }else {
                b.update();
            }
        }

        for(Bullet b : toRemove){
            this.playerBulletList.remove(b);
        }
    }

    @Override
    public void draw() {
        switch (this.currentState){
            case INIT:
                ClearBackground(BLACK);
                DrawText("Press ENTER to Init Game.",160,200,20,RAYWHITE);
                break;
            case GAME:
                DrawTexture(ResourceManager.getInstance().getTexture(ResourceIdentifier.MAP),-960,-640,WHITE);
                player.drawPlayer();
                for(Enemy e : this.enemyList){
                    e.draw();
                }
                for(Bullet b: this.playerBulletList){
                    b.draw();
                }
                break;
        }
    }

    public void unload(){
        ResourceManager.getInstance().UnloadResources();
    }
}
