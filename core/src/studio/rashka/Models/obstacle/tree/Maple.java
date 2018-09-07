package studio.rashka.Models.obstacle.tree;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * КЛЕН
 */

public class Maple implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 204;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle foliage; // листва
    private Circle crown; // макушка
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Maple() {
        position = new Vector2(START_POSITION, Y);
        crown = new Circle(position.x + 105, position.y + 176, 80);
        foliage = new Rectangle(position.x + 5, position.y + 64, 32, 96);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            crown.setPosition(position.x + 105, position.y + 176);
            foliage.setPosition(position.x + 5, position.y + 64);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("maple"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (Intersector.overlaps(crown, tail) || tail.overlaps(foliage) ||
                Intersector.overlaps(crown, pawsR11) || pawsR11.overlaps(foliage) ||
                Intersector.overlaps(crown, pawsR12) || pawsR12.overlaps(foliage) ||
                Intersector.overlaps(crown, paws) || paws.overlaps(foliage) ||
                Intersector.overlaps(crown, head) || head.overlaps(foliage) ||
                Intersector.overlaps(crown, wings) || wings.overlaps(foliage));
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
        try {
            crown = null;
            foliage = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}