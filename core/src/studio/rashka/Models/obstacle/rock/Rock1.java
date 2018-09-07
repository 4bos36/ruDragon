package studio.rashka.Models.obstacle.rock;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;
import studio.rashka.Screen.module.Scenes;

/**
 * Скала
 */

public class Rock1 implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 256;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH * 2 + 10;

    private Circle body;
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Rock1() {
        position = new Vector2(START_POSITION, Y);
        body = new Circle(position.x + 106, 140, 104);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            body.setPosition(position.x + 106, 140);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("rock_1"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (Intersector.overlaps(body, tail) || Intersector.overlaps(body, pawsR11) || Intersector.overlaps(body, pawsR12) ||
        Intersector.overlaps(body, paws) || Intersector.overlaps(body, head) || Intersector.overlaps(body, wings));
    }

    public float getX() {
        return position.x;
    }

    public float setAddX(float addX) {
        return position.x = START_POSITION + addX;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setGo(boolean go) {
        isGo = go;
    }

    public boolean isGo() {
        return isGo;
    }

    public void dispose() {
        body = null;
        position = null;
    }
}