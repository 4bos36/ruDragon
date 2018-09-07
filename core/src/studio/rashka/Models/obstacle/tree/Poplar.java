package studio.rashka.Models.obstacle.tree;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * ТОПОЛЬ
 */

public class Poplar implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 155;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle trunk, // ствол
            crown, // макушка
            foliage; // листва
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Poplar() {
        position = new Vector2(START_POSITION, Y);
        crown = new Rectangle(position.x + 48, position.y + 460, 32, 32);
        foliage = new Rectangle(position.x + 40, position.y + 300, 68, 160);
        trunk = new Rectangle(position.x + 20, position.y + 70, 32, 230);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            crown.setPosition(position.x + 48, position.y + 460);
            foliage.setPosition(position.x + 40, position.y + 300);
            trunk.setPosition(position.x + 20, position.y + 70);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("poplar"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(trunk) || tail.overlaps(foliage) || tail.overlaps(crown) ||
                pawsR11.overlaps(trunk) || pawsR11.overlaps(foliage) || pawsR11.overlaps(crown) ||
                pawsR12.overlaps(trunk) || pawsR12.overlaps(foliage) || pawsR12.overlaps(crown) ||
                paws.overlaps(trunk) || paws.overlaps(foliage) || paws.overlaps(crown) ||
                head.overlaps(trunk) || head.overlaps(foliage) || head.overlaps(crown) ||
                wings.overlaps(trunk) || wings.overlaps(foliage) || wings.overlaps(crown));
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