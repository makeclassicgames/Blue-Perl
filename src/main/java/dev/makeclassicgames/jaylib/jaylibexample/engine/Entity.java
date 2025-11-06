package dev.makeclassicgames.jaylib.jaylibexample.engine;

import static com.raylib.Raylib.*;

public class Entity implements GameObject{
    protected Sprite sprite;
    protected Vector2D position;
    protected Vector2D velocity;
    private float rotation;
    private static final float DEG2RAD= (float) (Math.PI / 180f);

    protected Direction direction;

    public Entity(){
        this.position= new Vector2D();
        this.velocity = new Vector2D();
        this.rotation=0.0f;
    }
    public Entity(Vector2D position){
        this.position = position;
        this.velocity= new Vector2D();
        this.rotation=0.0f;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public float getRotation(){
        return this.rotation;
    }

    public void setRotation(float rotation){
        this.rotation=rotation;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update() {
        float dt = GetFrameTime();
        this.position.setX((float) (this.position.getX() + (this.velocity.getX()) * Math.cos(this.rotation*DEG2RAD)*dt));
        this.position.setY((float) (this.position.getY() + (this.velocity.getY()) * Math.sin(this.rotation*DEG2RAD)*dt));
        this.sprite.update();
    }

    @Override
    public void draw() {
        this.sprite.draw(this.position,this.rotation);
    }

}
