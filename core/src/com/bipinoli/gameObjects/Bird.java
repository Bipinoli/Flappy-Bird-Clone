package com.bipinoli.gameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.bipinoli.helpers.AssetLoader;

public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;
    private float rotation;

    private Circle boundingCircle;

    private boolean isAlive = true;

    public Bird(float x, float y, int width, int height) {
        position = new Vector2(x,y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        this.width = width;
        this.height = height;
        boundingCircle = new Circle();
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200)
            velocity.y = 200;
        // rotate counter-clockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;
            if (rotation < -20)
                rotation = -20;
        }
        // rotate clockwise
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90)
                rotation = 90;
        }
        position.add(velocity.cpy().scl(delta));
        // Set the circle's center to be (9, 6) with respect to the bird.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70 || !isAlive;
    }

    public void onClick() {
        if (isAlive) {
            velocity.y -= 160;
            AssetLoader.flapSound.play();
        }
        if (isAlive && position.y < 0) {
            position.y = 0;
            velocity.y = 100;
        }
    }

    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    public void die() {
        velocity.y = 0;
        isAlive = false;
    }

    public void decelerate() {
        acceleration.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }
}
