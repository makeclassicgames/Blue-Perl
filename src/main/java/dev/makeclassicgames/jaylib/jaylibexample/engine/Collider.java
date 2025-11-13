package dev.makeclassicgames.jaylib.jaylibexample.engine;

import static com.raylib.Raylib.*;

public class Collider {
    private float xoffset;
    private float yoffset;
    private float width;
    private float height;

    public Collider(float xoffset, float yoffset, float width, float height) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.width = width;
        this.height = height;
    }

    public Rectangle getColliderRectangle(Vector2D position){
        Rectangle rectangle = new Rectangle();
        rectangle.x(position.getX()+xoffset);
        rectangle.y(position.getY()+yoffset);
        rectangle.width(width);
        rectangle.height(height);
        return rectangle;
    }
}
