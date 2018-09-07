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
 * БЕРЕЗА
 */

public class Birch implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 143;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle trunk; // ствол
    private Circle foliage; // листва
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Birch() {
        position = new Vector2(START_POSITION, Y);
        trunk = new Rectangle(position.x + 7, position.y + 32, 115, 265);
        foliage = new Circle(position.x + 64, position.y + 300, 40);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            trunk.setPosition(position.x + 7, position.y + 32);
            foliage.setPosition(position.x + 64, position.y + 300);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("birch"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(trunk) || Intersector.overlaps(foliage, tail) ||
                pawsR11.overlaps(trunk) || Intersector.overlaps(foliage, pawsR11) ||
                pawsR12.overlaps(trunk) || Intersector.overlaps(foliage, pawsR12) ||
                paws.overlaps(trunk) || Intersector.overlaps(foliage, paws) ||
                head.overlaps(trunk) || Intersector.overlaps(foliage, head) ||
                wings.overlaps(trunk) || Intersector.overlaps(foliage, wings));
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
            trunk = null;
            foliage = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}