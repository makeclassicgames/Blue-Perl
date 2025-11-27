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

    public Vector2D(Vector2D vector2D){
        this.x= vector2D.x;
        this.y= vector2D.y;
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

    public Vector2D normalize(){
        float sum_square = this.x*this.x+this.y*this.y;
        float length = Double.valueOf(Math.sqrt(sum_square)).floatValue();
        return new Vector2D(this.x/length, this.y/length);
    }
}
