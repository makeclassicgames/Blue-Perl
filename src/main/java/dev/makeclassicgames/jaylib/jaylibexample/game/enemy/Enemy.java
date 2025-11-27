package dev.makeclassicgames.jaylib.jaylibexample.game.enemy;

import dev.makeclassicgames.jaylib.jaylibexample.engine.Collider;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Entity;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Sprite;
import dev.makeclassicgames.jaylib.jaylibexample.engine.Vector2D;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;

import static com.raylib.Colors.RED;
import static com.raylib.Raylib.*;


public class Enemy extends Entity {
    private int health;

    private static final float ROTATION_ENEMY_SPEED = 175.0f;

    private static final float RAD2DEG = Double.valueOf(180/Math.PI).floatValue();

    public Enemy() {
        this.sprite = new Sprite(ResourceIdentifier.ENEMY);
        this.sprite.addAnimation(1, 64, 64, 5);
        this.sprite.setCurrentAnimation(0);
        this.health = 50;
        this.collider = new Collider(16.0f, 5.0f, 16.0f, 55.0f);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void updateEnemy(Vector2D playerPosition) {

        float dx = playerPosition.getX() - this.getPosition().getX();
        float dy = playerPosition.getY() - this.getPosition().getY();
        Vector2D normalizedSpeed = new Vector2D(dx, dy).normalize();
        Vector2D vel = new Vector2D(normalizedSpeed.getX() * 20.0f, normalizedSpeed.getY() * 20.f);
        this.setVelocity(vel);
        float degAngle= Double.valueOf(Math.atan2(dy, dx)).floatValue()*RAD2DEG;
        this.setRotation( degAngle * ROTATION_ENEMY_SPEED * GetFrameTime());
        this.update();
    }

    public void drawEnemy() {
        float width = (this.health*64)/100.0f;
        this.draw();
        DrawRectangle((int) this.position.getX()-24, (int) this.position.getY()-48, (int) width,5,RED);


    }
}
