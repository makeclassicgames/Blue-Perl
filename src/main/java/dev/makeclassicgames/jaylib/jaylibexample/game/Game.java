package dev.makeclassicgames.jaylib.jaylibexample.game;

import dev.makeclassicgames.jaylib.jaylibexample.engine.GameObject;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Timer;
import dev.makeclassicgames.jaylib.jaylibexample.engine.TimerEvent;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Vector2D;
import dev.makeclassicgames.jaylib.jaylibexample.game.bullet.Bullet;
import dev.makeclassicgames.jaylib.jaylibexample.game.bullet.BulletEnemy;
import dev.makeclassicgames.jaylib.jaylibexample.game.enemy.Enemy;
import dev.makeclassicgames.jaylib.jaylibexample.game.input.InputEvent;
import dev.makeclassicgames.jaylib.jaylibexample.game.input.InputMappingManager;
import dev.makeclassicgames.jaylib.jaylibexample.game.player.Player;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class Game implements GameObject {

    private static final float PLAYER_SPEED = 200.0f;
    private static final float ROTATION_SPEED = 180f;
    private GameState currentState;
    private final InputMappingManager inputManager;
    private final Player player;
    private final List<Enemy> enemyList;
    private final List<Bullet> playerBulletList;
    private final List<BulletEnemy> enemyBulletList;
    private final Timer enemySpawnTimer;

    public Game() {
        this.currentState = GameState.INIT;
        this.player = new Player();
        this.enemyList = new ArrayList<>();
        this.inputManager = new InputMappingManager();
        this.playerBulletList = new ArrayList<>();
        this.enemyBulletList = new ArrayList<>();
        this.enemySpawnTimer = new Timer(180, true, true, new TimerEvent() {
            @Override
            public void runEvent() {
                Game.this.spawnEnemy();
            }
        });
        player.setPosition(new Vector2D(140.0f, 140.0f));
        player.getSprite().setCurrentAnimation(2);
    }

    @Override
    public void update() {
        this.inputManager.updateEvents();
        switch (this.currentState) {
            case INIT -> updateInit();
            case GAME -> updateGame();
            case PAUSE -> updatePause();
        }
    }

    private void updatePause() {
        for(InputEvent ievent: this.inputManager.getEvents()){
            if(ievent==InputEvent.START){
                currentState= GameState.GAME;
                return;
            }
        }
    }

    private void updateInit() {
        for(InputEvent event : this.inputManager.getEvents()){
            if(event==InputEvent.START){
                this.currentState = GameState.GAME;
                return;
            }
        }
    }

    private void updateGame() {
        //TODO: Calcular todo en función de rotacion no usar direcciones
        Vector2D vel = new Vector2D();
        float dt = GetFrameTime();
        Random r = new Random();

        this.enemySpawnTimer.update();
        for(InputEvent event: this.inputManager.getEvents()){
            switch (event){
                case UP:
                    vel= new Vector2D(PLAYER_SPEED,PLAYER_SPEED);
                    break;
                case DOWN:
                    vel= new Vector2D(-PLAYER_SPEED,-PLAYER_SPEED);
                    break;
                case LEFT:
                    this.player.setRotation((float) (this.player.getRotation() - ROTATION_SPEED * dt));
                    break;
                case RIGHT:
                    this.player.setRotation((float) (this.player.getRotation() + ROTATION_SPEED * dt));
                    break;
                case FIRE_LEFT:
                    Bullet playerB = new Bullet();
                    playerB.setPosition(new Vector2D(player.getPosition().getX(),
                            player.getPosition().getY()));
                    playerB.setVelocity(new Vector2D(300, 300));
                    playerB.setRotation(player.getRotation() - 90.0f);
                    playerBulletList.add(playerB);
                    break;
                case FIRE_RIGHT:
                    Bullet playerBRigth = new Bullet();
                    playerBRigth.setPosition(new Vector2D(player.getPosition().getX(),
                            player.getPosition().getY() + 32));
                    playerBRigth.setVelocity(new Vector2D(300, 300));
                    playerBRigth.setRotation(player.getRotation() + 90.0f);
                    playerBulletList.add(playerBRigth);
                    break;
                case FIRE_UP:
                    Bullet playerBUp = new Bullet();
                    playerBUp.setPosition(new Vector2D(player.getPosition().getX(), player.getPosition().getY()));
                    playerBUp.setVelocity(new Vector2D(300, 300));
                    playerBUp.setRotation(player.getRotation());
                    playerBulletList.add(playerBUp);
                    break;
                case START:
                    this.currentState=GameState.PAUSE;
                    return;
            }

        }
        this.player.setVelocity(vel);
        this.player.update();
        List<Bullet> toRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Bullet b : playerBulletList) {
            if (b.getPosition().getX() < 0 || b.getPosition().getX() > GetScreenWidth()
                || b.getPosition().getY() < 0 || b.getPosition().getY() > GetScreenHeight()) {
                toRemove.add(b);
            } else {
                b.update();
            }
            for(Enemy enemy: enemyList){
                if(b.collideWith(enemy.getCollider(),enemy.getPosition())){
                    enemy.setHealth(enemy.getHealth()-b.getPower());
                    toRemove.add(b);
                    if(enemy.getHealth()<=0){
                        enemiesToRemove.add(enemy);
                    }
                    break;
                }
            }

        }

        for (Bullet b : toRemove) {
            this.playerBulletList.remove(b);
        }

        for(Enemy enemy: enemiesToRemove){
            this.enemyList.remove(enemy);
        }

        for(Enemy enemy:this.enemyList){
            int fire = GetRandomValue(0,10);
            if(fire<1){
                BulletEnemy bulletEnemy= new BulletEnemy();
                bulletEnemy.setPosition(new Vector2D(enemy.getPosition()));
                bulletEnemy.setRotation(enemy.getRotation());
                bulletEnemy.setVelocity(new Vector2D(300.0f,300.0f));
                enemyBulletList.add(bulletEnemy);
            }
            enemy.updateEnemy(player.getPosition());
        }
        List<BulletEnemy> bulletEnemiesToRemove = new ArrayList<>();
        for(BulletEnemy bulletEnemy: enemyBulletList){
            if(bulletEnemy.getPosition().getX() < 0 || bulletEnemy.getPosition().getX() > GetScreenWidth()
               || bulletEnemy.getPosition().getY() < 0 || bulletEnemy.getPosition().getY() > GetScreenHeight()){
                bulletEnemiesToRemove.add(bulletEnemy);
            }else{
                bulletEnemy.update();

            }
        }

        for(BulletEnemy bulletEnemy: bulletEnemiesToRemove){
            this.enemyBulletList.remove(bulletEnemy);
        }

        if(player.getPosition().getY()<-64){
            player.getPosition().setY(GetScreenHeight()+64);
        }
        if(player.getPosition().getY()>GetScreenHeight()+64){
            player.getPosition().setY(-64);
        }
        if(player.getPosition().getX()<-64){
            player.getPosition().setX(GetScreenWidth()+64);
        }
        if(player.getPosition().getX()>GetScreenWidth()+64){
            player.getPosition().setX(-64);
        }
    }

    @Override
    public void draw() {
        switch (this.currentState) {
            case INIT:
                ClearBackground(BLACK);
                DrawText("Press ENTER(Start) to Init Game.", 160, 200, 20, RAYWHITE);
                break;
            case GAME:
                DrawTexture(ResourceManager.getInstance().getTexture(ResourceIdentifier.MAP), -960, -640, WHITE);
                player.drawPlayer();
                for (Enemy e : this.enemyList) {
                    e.drawEnemy();
                }
                for (Bullet b : this.playerBulletList) {
                    b.draw();
                }
                for(BulletEnemy b2: this.enemyBulletList){
                    b2.draw();
                }
                break;
            case PAUSE:
                ClearBackground(BLACK);
                DrawText("Game Paused. Press ENTER(Start) to Continue.", 160, 200, 20, RAYWHITE);
                break;
        }
    }

    private void spawnEnemy(){
        Random r = new Random();
        Enemy e = new Enemy();
        e.setPosition(new Vector2D(r.nextFloat(0.0f,GetScreenWidth()), r.nextFloat(0.0f, GetScreenHeight())));
        this.enemyList.add(e);
    }

    public void unload() {
        ResourceManager.getInstance().UnloadResources();
    }
}
