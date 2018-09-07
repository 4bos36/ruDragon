package studio.rashka.Models.obstacle.rock;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * Скала
 */

public class Rock7 implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 256;
    private static final int Y = 110;
    private static final float START_POSITION = RuDragonGame.WIDTH * 2 + 10;

    private Circle body;
    private Rectangle body1, body2, body3;
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Rock7() {
        position = new Vector2(START_POSITION, Y);
        body = new Circle(position.x + 24, 180, 50);
        body1 = new Rectangle(position.x + 10, 235, 20, 20);
        body2 = new Rectangle(position.x + 72, 260, 40, 32);
        body3 = new Rectangle(position.x + 133, 300, 45, 20);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            body.setPosition(position.x + 24, 180);
            body1.setPosition(position.x + 10, 235);
            body2.setPosition(position.x + 72, 260);
            body3.setPosition(position.x + 133, 300);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("rock_7"), position.x, position.y, 96, 128, 192, 256, 1, 1, -90);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (Intersector.overlaps(body, tail) || Intersector.overlaps(body, pawsR11) || Intersector.overlaps(body, pawsR12) ||
        Intersector.overlaps(body, paws) || Intersector.overlaps(body, head) || Intersector.overlaps(body, wings) ||
        tail.overlaps(body1) || tail.overlaps(body2) || tail.overlaps(body3) ||
        pawsR11.overlaps(body1) || pawsR11.overlaps(body2) || pawsR11.overlaps(body3) ||
        pawsR12.overlaps(body1) || pawsR12.overlaps(body2) || pawsR12.overlaps(body3) ||
        paws.overlaps(body1) || paws.overlaps(body2) || paws.overlaps(body3) ||
        head.overlaps(body1) || head.overlaps(body2) || head.overlaps(body3) ||
        wings.overlaps(body1) || wings.overlaps(body2) || wings.overlaps(body3));
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
        body1 = null;
        body2 = null;
        body3 = null;
        position = null;
    }
}