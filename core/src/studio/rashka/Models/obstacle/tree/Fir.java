package studio.rashka.Models.obstacle.tree;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * ПИХТА
 */

public class Fir implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 181;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle shell, // мокушка
            foliageUp, // листва
            foliageDown;
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Fir() {
        position = new Vector2(START_POSITION, Y);
        shell = new Rectangle(position.x + 70, position.y + 292, 32, 58);
        foliageUp = new Rectangle(position.x + 48, position.y + 228, 80, 64);
        foliageDown = new Rectangle(position.x + 7, position.y + 32, 140, 192);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            shell.setPosition(position.x + 70, position.y + 292);
            foliageUp.setPosition(position.x + 48, position.y + 228);
            foliageDown.setPosition(position.x + 7, position.y + 32);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("fir"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(shell) || tail.overlaps(foliageUp) || tail.overlaps(foliageDown) ||
                pawsR11.overlaps(shell) || pawsR11.overlaps(foliageUp) || pawsR11.overlaps(foliageDown) ||
                pawsR12.overlaps(shell) || pawsR12.overlaps(foliageUp) || pawsR12.overlaps(foliageDown) ||
                paws.overlaps(shell) || paws.overlaps(foliageUp) || paws.overlaps(foliageDown) ||
                head.overlaps(shell) || head.overlaps(foliageUp) || head.overlaps(foliageDown) ||
                wings.overlaps(shell) || wings.overlaps(foliageUp) || wings.overlaps(foliageDown));
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
            shell = null;
            foliageUp = null;
            foliageDown = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}