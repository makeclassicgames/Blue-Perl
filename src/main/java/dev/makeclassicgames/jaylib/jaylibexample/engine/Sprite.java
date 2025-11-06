package dev.makeclassicgames.jaylib.jaylibexample.engine;

import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceIdentifier;
import dev.makeclassicgames.jaylib.jaylibexample.game.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.*;

public class Sprite {

    private Texture texture;
    private List<Animation> animationList;
    private int currentAnimation;

    public Sprite(ResourceIdentifier resourceId) {
        this.texture = ResourceManager.getInstance().getTexture(resourceId);
        this.animationList = new ArrayList<>();
        this.currentAnimation=0;
    }

    public void addAnimation(int frameCount, int frameWidth, int frameHeight, int frameDelay){
        Animation animation = new Animation(frameCount,frameWidth,frameHeight,frameDelay);
        this.animationList.add(animation);
    }

    public void draw(Vector2D position, float rotation) {

        Animation animation = this.animationList.get(this.currentAnimation);
        Vector2 vec = new Vector2();
        vec.x(position.getX());
        vec.y(position.getY());
        Rectangle rect = new Rectangle();
        rect.x(position.getX());
        rect.y(position.getY());
        rect.width(animation.getFrameWidth());
        rect.height(animation.getFrameHeight());
        Vector2 origin = new Vector2();
        origin.x((float) animation.getFrameWidth() / 2);
        origin.y((float) animation.getFrameHeight() / 2);

        DrawTexturePro(this.texture,animation.getCurrentRect(this.currentAnimation),rect,origin,rotation,WHITE);

    }

    public void update() {
        this.animationList.get(this.currentAnimation).update();
    }

    public void setCurrentAnimation(int currentAnimation){
        //TODO: Check Index Out of Bounds
        this.currentAnimation= currentAnimation;
    }



    private class Animation {
        private int frameCount;
        private int currentFrame;
        private int frameWidth;
        private int frameHeight;
        private int frameDelay;
        private int elapsedFrames;

        public int getFrameWidth() {
            return frameWidth;
        }

        public void setFrameWidth(int frameWidth) {
            this.frameWidth = frameWidth;
        }

        public int getFrameHeight() {
            return frameHeight;
        }

        public void setFrameHeight(int frameHeight) {
            this.frameHeight = frameHeight;
        }

        public Animation(int frameCount, int frameWidth, int frameHeight, int frameDelay) {
            this.currentFrame = 0;
            this.elapsedFrames = 0;
            this.frameHeight = frameHeight;
            this.frameWidth = frameWidth;
            this.frameCount = frameCount;
            this.frameDelay = frameDelay;
        }


        public void update() {
            this.elapsedFrames++;
            if (this.elapsedFrames >= frameDelay) {
                this.currentFrame++;
                this.elapsedFrames = 0;
                if (this.currentFrame >= this.frameCount) {
                    this.currentFrame = 0;
                }
            }
        }

        public Rectangle getCurrentRect(int animationIndex){
            Rectangle rectangle= new Rectangle();
            rectangle.x(this.frameWidth*this.currentFrame);
            rectangle.y(animationIndex*this.frameHeight);
            rectangle.width(this.frameWidth);
            rectangle.height(this.frameHeight);
            return rectangle;
        }

    }
}
