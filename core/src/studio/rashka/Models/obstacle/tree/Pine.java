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
 * СОСНА
 */

public class Pine implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 219;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle trunk, foliage; // ствол и листва
    private Circle crown; // макушка
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Pine() {
        position = new Vector2(START_POSITION, Y);
        crown = new Circle(position.x + 110, position.y + 384, 60);
        trunk = new Rectangle(position.x + 40, position.y + 128, 160, 256);
        foliage = new Rectangle(position.x + 8, position.y + 210, 32, 32);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            crown.setPosition(position.x + 110, position.y + 384);
            trunk.setPosition(position.x + 40, position.y + 128);
            foliage.setPosition(position.x + 8, position.y + 210);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("pine"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(trunk) || Intersector.overlaps(crown, tail)|| tail.overlaps(foliage) ||
                pawsR11.overlaps(trunk) || Intersector.overlaps(crown, pawsR11) || pawsR11.overlaps(foliage) ||
                pawsR12.overlaps(trunk) || Intersector.overlaps(crown, pawsR12) || pawsR12.overlaps(foliage) ||
                paws.overlaps(trunk) || Intersector.overlaps(crown, paws) || paws.overlaps(foliage) ||
                head.overlaps(trunk) || Intersector.overlaps(crown, head) || head.overlaps(foliage) ||
                wings.overlaps(trunk) || Intersector.overlaps(crown, wings) || wings.overlaps(foliage));
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
            crown = null;
            foliage = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}