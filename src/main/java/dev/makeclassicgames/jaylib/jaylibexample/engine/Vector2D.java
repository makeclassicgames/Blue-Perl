package dev.makeclassicgames.jaylib.jaylibexample.engine;

public class Vector2D {
    private float x;
    private float y;

    public static Vector2D ZEROS= new Vector2D();
    public static Vector2D ONES = new Vector2D(1.0f,1.0f);
    public Vector2D(){
        this.x=0.0f;
        this.y=0.0f;
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
